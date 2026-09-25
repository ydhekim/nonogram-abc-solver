package io.github.ydhekim.nonogram.solver;

import io.github.ydhekim.nonogram.model.Puzzle;
import io.github.ydhekim.nonogram.model.Solution;

/**
 * Common contract for every nonogram solving strategy.
 *
 * <p>Implementations take a puzzle definition and search for a grid filling that satisfies its row
 * and column clues. This is the Strategy interface of the project: callers depend on it instead of a
 * concrete search algorithm.
 *
 * @see AbstractAbcSolver
 */
public interface NonogramSolver {
    /**
     * Searches for a solution to the given puzzle.
     *
     * <p>Heuristic implementations are not guaranteed to find a perfect solution, so the returned
     * value is the best candidate found. Inspect {@link Solution#isPerfect()} or
     * {@link Solution#getFitness()} to tell an exact solution from an approximation.
     *
     * @param puzzle the puzzle to solve
     * @return the best solution found, evaluated and never {@code null}
     */
    Solution solve(Puzzle puzzle);
}
