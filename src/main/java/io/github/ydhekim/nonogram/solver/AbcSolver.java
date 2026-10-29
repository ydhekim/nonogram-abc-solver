package io.github.ydhekim.nonogram.solver;

import io.github.ydhekim.nonogram.model.Puzzle;
import io.github.ydhekim.nonogram.model.Solution;

/**
 * Plain Artificial Bee Colony solver that generates neighbors by blind random bit flips.
 *
 * <p>Every cell is flipped independently with probability {@code mutationRate}, so the puzzle's
 * clues play no part in choosing where to mutate — they only score the result. This is the baseline
 * implementation, kept for comparison against the error-driven variant, which targets the worst
 * scoring row and column instead of mutating uniformly.
 *
 * @see EnhancedAbcSolver
 * @see AbstractAbcSolver
 */
public class AbcSolver extends AbstractAbcSolver {
    private final double mutationRate;

    /**
     * @param colonySize    number of bees, i.e. food sources kept in parallel
     * @param limit         failed trials after which a food source is abandoned by a scout bee
     * @param maxIterations upper bound on search iterations
     * @param mutationRate  per-cell probability of a flip when generating a neighbor
     */
    public AbcSolver(int colonySize, int limit, int maxIterations, double mutationRate) {
        super(colonySize, limit, maxIterations);
        this.mutationRate = mutationRate;
    }

    /**
     * Copies the current solution and flips each cell independently with probability
     * {@code mutationRate}.
     *
     * @param current the solution to perturb; left unmodified
     * @param puzzle  the puzzle being solved; unused by this strategy
     * @return the mutated neighbor
     */
    @Override
    protected Solution generateNeighbor(Solution current, Puzzle puzzle) {
        Solution neighbor = current.copy();

        for (int i = 0; i < neighbor.size(); i++) {
            if (randomGenerator.nextDouble() < mutationRate) {
                neighbor.flip(i);
            }
        }
        return neighbor;
    }
}
