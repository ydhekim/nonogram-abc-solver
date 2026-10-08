package io.github.ydhekim.nonogram.solver;

import io.github.ydhekim.nonogram.model.Puzzle;

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
     * <p>Heuristic implementations are not guaranteed to find a perfect solution, so the result
     * carries the best candidate found. Check {@link SolveResult#solved()} to tell an exact solution
     * from an approximation; the search effort is reported alongside it.
     *
     * @param puzzle the puzzle to solve
     * @return the best solution found with its search effort, never {@code null}
     */
    SolveResult solve(Puzzle puzzle);
}
