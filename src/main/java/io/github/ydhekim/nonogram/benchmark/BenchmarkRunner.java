package io.github.ydhekim.nonogram.benchmark;

import io.github.ydhekim.nonogram.model.Puzzle;
import io.github.ydhekim.nonogram.model.Solution;
import io.github.ydhekim.nonogram.solver.NonogramSolver;

/**
 * Runs two {@link NonogramSolver} implementations against the same puzzle
 * for a fixed number of trials, printing each trial's resulting error and
 * elapsed time for side-by-side comparison.
 */
public class BenchmarkRunner {
    private final NonogramSolver baseline;
    private final NonogramSolver candidate;
    private final String baselineLabel;
    private final String candidateLabel;

    public BenchmarkRunner(NonogramSolver baseline, String baselineLabel, NonogramSolver candidate, String candidateLabel) {
        this.baseline = baseline;
        this.baselineLabel = baselineLabel;
        this.candidate = candidate;
        this.candidateLabel = candidateLabel;
    }

    public void run(Puzzle puzzle, int trials) {
        for (int i = 0; i < trials; i++) {
            report(baseline, baselineLabel, puzzle);
            report(candidate, candidateLabel, puzzle);
        }
    }

    private void report(NonogramSolver solver, String label, Puzzle puzzle) {
        long start = System.currentTimeMillis();
        Solution result = solver.solve(puzzle);
        long elapsed = System.currentTimeMillis() - start;

        System.out.printf("%-14s -> Error: %d, Time: %dms%n", label, result.getFitness(), elapsed);
    }
}
