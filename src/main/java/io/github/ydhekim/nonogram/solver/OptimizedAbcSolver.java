package io.github.ydhekim.nonogram.solver;

import io.github.ydhekim.nonogram.model.Puzzle;
import io.github.ydhekim.nonogram.model.Solution;

/**
 * Artificial Bee Colony solver with error-driven neighbor generation.
 *
 * <p>Instead of mutating blindly, it locates the row and the column with the highest clue error and
 * flips a single cell inside one of them, spending each mutation where the solution is currently
 * most wrong. With probability {@code explorationRate} it falls back to flipping a uniformly random
 * cell, which keeps the search from stalling on a local optimum.
 *
 * @see AbcSolver
 * @see AbstractAbcSolver
 */
public class OptimizedAbcSolver extends AbstractAbcSolver {
    private final double explorationRate;

    /**
     * @param colonySize      number of bees, i.e. food sources kept in parallel
     * @param limit           failed trials after which a food source is abandoned by a scout bee
     * @param maxIterations   upper bound on search iterations
     * @param explorationRate probability of flipping a random cell instead of targeting the worst
     *                        scoring line
     */
    public OptimizedAbcSolver(int colonySize, int limit, int maxIterations, double explorationRate) {
        super(colonySize, limit, maxIterations);
        this.explorationRate = explorationRate;
    }

    /**
     * Copies the current solution and flips exactly one cell, chosen either at random (with
     * probability {@code explorationRate}) or from the worst scoring row or column.
     *
     * @param current the solution to perturb; left unmodified
     * @param puzzle  the puzzle being solved, used to locate the worst scoring lines
     * @return the mutated neighbor
     */
    @Override
    protected Solution generateNeighbor(Solution current, Puzzle puzzle) {
        Solution neighbor = current.copy();

        if (randomGenerator.nextDouble() < explorationRate) {
            neighbor.flip(randomGenerator.nextInt(neighbor.size()));
            return neighbor;
        }

        int worstRow = findWorstRow(puzzle, neighbor);
        int worstCol = findWorstColumn(puzzle, neighbor);
        int targetIndex = randomGenerator.nextBoolean()
                ? worstRow * puzzle.cols() + randomGenerator.nextInt(puzzle.cols())
                : randomGenerator.nextInt(puzzle.rows()) * puzzle.cols() + worstCol;
        neighbor.flip(targetIndex);
        return neighbor;
    }

    private int findWorstRow(Puzzle puzzle, Solution solution) {
        int worstRow = 0;
        int worstError = -1;

        for (int row = 0; row < puzzle.rows(); row++) {
            int error = fitnessEvaluator.rowError(puzzle, solution, row);
            if (error > worstError) {
                worstError = error;
                worstRow = row;
            }
        }
        return worstRow;
    }

    private int findWorstColumn(Puzzle puzzle, Solution solution) {
        int worstCol = 0;
        int worstError = -1;

        for (int col = 0; col < puzzle.cols(); col++) {
            int error = fitnessEvaluator.columnError(puzzle, solution, col);
            if (error > worstError) {
                worstError = error;
                worstCol = col;
            }
        }
        return worstCol;
    }
}
