package io.github.ydhekim.nonogram.benchmark;

import io.github.ydhekim.nonogram.model.Puzzle;
import io.github.ydhekim.nonogram.solver.NonogramSolver;
import io.github.ydhekim.nonogram.solver.SolveResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Runs a solver configuration repeatedly against a puzzle and condenses the runs into a
 * {@link Summary} of size-independent metrics.
 *
 * <p>The same solver instance is reused for every trial. Each run starts from a fresh random colony,
 * so the runs are independent samples of the solver's behavior.
 */
public class BenchmarkRunner {
    private final int trials;

    /**
     * @param trials number of runs per configuration, at least 1
     */
    public BenchmarkRunner(int trials) {
        if (trials < 1) {
            throw new IllegalArgumentException("trials must be at least 1, got " + trials);
        }
        this.trials = trials;
    }

    /**
     * Solves the puzzle repeatedly and summarizes the runs.
     *
     * @param label  name shown in the summary line
     * @param puzzle the puzzle every run solves
     * @param solver the solver configuration under test
     * @return aggregate metrics over all runs
     */
    public Summary run(String label, Puzzle puzzle, NonogramSolver solver) {
        List<Trial> results = new ArrayList<>(trials);

        for (int i = 0; i < trials; i++) {
            long start = System.nanoTime();
            SolveResult result = solver.solve(puzzle);
            long elapsedMs = (System.nanoTime() - start) / 1_000_000;

            results.add(new Trial(result, puzzle.cellCount(), elapsedMs));
        }
        return summarize(label, results);
    }

    private static Summary summarize(String label, List<Trial> trials) {
        int count = trials.size();
        int solvedCount = 0;
        double errorSum = 0;
        double normalizedSum = 0;
        double evaluationSum = 0;
        double timeSum = 0;
        double solvedIterationSum = 0;

        for (Trial trial : trials) {
            errorSum += trial.error();
            normalizedSum += trial.normalizedError();
            evaluationSum += trial.evaluations();
            timeSum += trial.elapsedMs();
            if (trial.solved()) {
                solvedCount++;
                solvedIterationSum += trial.iterations();
            }
        }

        double meanNormalized = normalizedSum / count;
        double squaredDeviations = 0;
        for (Trial trial : trials) {
            double deviation = trial.normalizedError() - meanNormalized;
            squaredDeviations += deviation * deviation;
        }
        // Sample standard deviation (n - 1); a single run has no spread to report.
        double stdNormalized = count > 1 ? Math.sqrt(squaredDeviations / (count - 1)) : 0;

        return new Summary(
                label,
                count,
                solvedCount,
                errorSum / count,
                meanNormalized,
                stdNormalized,
                solvedCount > 0 ? solvedIterationSum / solvedCount : Double.NaN,
                evaluationSum / count,
                timeSum / count
        );
    }

    private record Trial(int error, double normalizedError, boolean solved, int iterations,
                         long evaluations, long elapsedMs) {
        Trial(SolveResult result, int cellCount, long elapsedMs) {
            this(result.best().getFitness(),
                    (double) result.best().getFitness() / cellCount,
                    result.solved(),
                    result.iterations(),
                    result.evaluations(),
                    elapsedMs);
        }
    }

    /**
     * Aggregate metrics for one solver configuration over all its runs.
     *
     * <p>{@code meanNormalizedError} is the fitness score divided by the grid's cell count. The score
     * weights block-length mismatches and missing blocks, so it is a relative measure for comparing
     * runs across grid sizes, not the fraction of wrongly filled cells.
     *
     * @param label                 configuration name
     * @param trials                number of runs
     * @param solvedCount           runs that reached a perfect solution
     * @param meanError             mean raw fitness score of the best solutions
     * @param meanNormalizedError   mean fitness score per cell
     * @param stdNormalizedError    sample standard deviation of the fitness score per cell
     * @param meanIterationsToSolve mean iterations over solved runs only, {@code NaN} if none solved
     * @param meanEvaluations       mean number of fitness evaluations per run
     * @param meanTimeMs            mean wall-clock time per run in milliseconds
     */
    public record Summary(String label, int trials, int solvedCount, double meanError,
                          double meanNormalizedError, double stdNormalizedError,
                          double meanIterationsToSolve, double meanEvaluations, double meanTimeMs) {

        /**
         * @return fraction of runs that reached a perfect solution, between 0 and 1
         */
        public double successRate() {
            return (double) solvedCount / trials;
        }

        /**
         * @return a single human-readable line with all metrics
         */
        @Override
        public String toString() {
            String iterations = Double.isNaN(meanIterationsToSolve)
                    ? "n/a" : String.format(Locale.ROOT, "%.0f", meanIterationsToSolve);

            return String.format(Locale.ROOT,
                    "%-14s | solved %d/%d (%.0f%%) | error/cell %.3f +/- %.3f | error %.1f"
                            + " | iters-to-solve %s | evals %.0f | time %.0fms",
                    label, solvedCount, trials, successRate() * 100,
                    meanNormalizedError, stdNormalizedError, meanError,
                    iterations, meanEvaluations, meanTimeMs);
        }
    }
}