package io.github.ydhekim.nonogram.solver;

import io.github.ydhekim.nonogram.fitness.FitnessEvaluator;
import io.github.ydhekim.nonogram.model.Bee;
import io.github.ydhekim.nonogram.model.Puzzle;
import io.github.ydhekim.nonogram.model.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

/**
 * Template Method implementation of the Artificial Bee Colony search for nonograms.
 *
 * <p>This class owns the invariant part of the algorithm: colony initialization and the employed,
 * onlooker (roulette-wheel selection) and scout bee phases, repeated until a perfect solution is
 * found or the iteration budget runs out. The single varying step, how a neighboring solution is
 * derived from a current one, is delegated to subclasses through {@link #generateNeighbor}.
 *
 * <p>The random generator and the evaluation counter are instance state, so an instance must not be
 * shared across threads. Calling {@link #solve} repeatedly on the same instance is fine: each call
 * starts from a fresh random colony, so successive runs are independent samples.
 *
 * @see AbcSolver
 * @see EnhancedAbcSolver
 */
public abstract class AbstractAbcSolver implements NonogramSolver {
    private final int colonySize;
    private final int limit;
    private final int maxIterations;
    private long evaluations;

    protected final FitnessEvaluator fitnessEvaluator = new FitnessEvaluator();
    protected final RandomGenerator randomGenerator = RandomGeneratorFactory.getDefault().create();

    /**
     * @param colonySize    number of bees, i.e. food sources kept in parallel
     * @param limit         failed trials after which a food source is abandoned by a scout bee
     * @param maxIterations upper bound on search iterations
     */
    public AbstractAbcSolver(int colonySize, int limit, int maxIterations) {
        this.colonySize = colonySize;
        this.limit = limit;
        this.maxIterations = maxIterations;
    }

    /**
     * Produces a candidate solution in the neighborhood of {@code current}; this is the variation
     * operator each subclass defines.
     *
     * <p>Implementations must leave {@code current} untouched — start from {@link Solution#copy()}
     * — and must return a solution of the same size. They need not evaluate the candidate; the
     * template scores it before comparing. The returned solution should differ from {@code current}
     * by a small perturbation, since the search relies on local exploitation.
     *
     * @param current the solution to perturb; must not be modified
     * @param puzzle  the puzzle being solved, available for clue-aware strategies
     * @return a new, unevaluated neighbouring solution
     */
    protected abstract Solution generateNeighbor(Solution current, Puzzle puzzle);

    /**
     * Runs the colony until a perfect solution appears or the iteration budget is exhausted.
     *
     * @param puzzle the puzzle to solve
     * @return the best solution encountered across all iterations, with the number of iterations and
     * fitness evaluations spent finding it
     */
    @Override
    public SolveResult solve(Puzzle puzzle) {
        evaluations = 0;
        List<Bee> colony = initializeColony(puzzle);
        Solution best = trackBest(colony, null);

        int iterations = 0;
        while (iterations < maxIterations && !best.isPerfect()) {
            employedBeePhase(puzzle, colony);
            onlookerBeePhase(puzzle, colony);
            scoutBeePhase(puzzle, colony);

            best = trackBest(colony, best);
            iterations++;
        }
        return new SolveResult(best, iterations, evaluations);
    }

    private void evaluate(Puzzle puzzle, Solution solution) {
        fitnessEvaluator.evaluate(puzzle, solution);
        evaluations++;
    }

    private List<Bee> initializeColony(Puzzle puzzle) {
        List<Bee> colony = new ArrayList<>(colonySize);

        for (int i = 0; i < colonySize; i++) {
            Solution solution = Solution.random(puzzle.cellCount(), randomGenerator);
            evaluate(puzzle, solution);
            colony.add(new Bee(solution));
        }
        return colony;
    }

    private Solution trackBest(List<Bee> colony, Solution currentSolution) {
        Solution best = currentSolution;

        for (Bee bee : colony) {
            if (best == null || bee.getSolution().isBetterThan(best)) {
                best = bee.getSolution();
            }
        }
        return best;
    }

    private void employedBeePhase(Puzzle puzzle, List<Bee> colony) {
        for (Bee bee : colony) {
            Solution current = bee.getSolution();
            Solution candidate = generateNeighbor(current, puzzle);
            evaluate(puzzle, candidate);

            if (candidate.isBetterThan(current)) {
                bee.updateSolution(candidate);
            } else {
                bee.incrementTrialCount();
            }
        }
    }

    private void onlookerBeePhase(Puzzle puzzle, List<Bee> colony) {
        double[] selectionProbabilities = calculateSelectionProbabilities(colony);

        for (int i = 0; i < colonySize; i++) {
            Bee selected = selectByRouletteWheel(colony, selectionProbabilities);

            Solution current = selected.getSolution();
            Solution candidate = generateNeighbor(current, puzzle);
            evaluate(puzzle, candidate);

            if (candidate.isBetterThan(current)) {
                selected.updateSolution(candidate);
            } else {
                selected.incrementTrialCount();
            }
        }
    }

    private double[] calculateSelectionProbabilities(List<Bee> colony) {
        double[] weights = new double[colonySize];
        double totalWeight = 0;

        for (int i = 0; i < colonySize; i++) {
            double weight = 1.0 / (1.0 + colony.get(i).getSolution().getFitness());
            weights[i] = weight;
            totalWeight += weight;
        }

        double[] probabilities = new double[colonySize];

        for (int i = 0; i < colonySize; i++) {
            probabilities[i] = weights[i] / totalWeight;
        }
        return probabilities;
    }

    private Bee selectByRouletteWheel(List<Bee> colony, double[] selectionProbabilities) {
        double random = randomGenerator.nextDouble();
        double cumulative = 0;

        for (int i = 0; i < colonySize; i++) {
            cumulative += selectionProbabilities[i];
            if (random <= cumulative) {
                return colony.get(i);
            }
        }
        return colony.get(colonySize - 1);
    }

    private void scoutBeePhase(Puzzle puzzle, List<Bee> colony) {
        for (Bee bee : colony) {
            if (bee.isExhausted(limit)) {
                Solution fresh = Solution.random(puzzle.cellCount(), randomGenerator);
                evaluate(puzzle, fresh);
                bee.resetWithNewSolution(fresh);
            }
        }
    }
}