package io.github.ydhekim.nonogram.solver;

import io.github.ydhekim.nonogram.model.Solution;

/**
 * Outcome of a single {@link NonogramSolver#solve} call: the best solution found together with the
 * effort it took to find it.
 *
 * <p>The effort counters make runs comparable independently of wall-clock time, which is noisy
 * because of JIT warm-up and machine load.
 *
 * @param best        the best solution encountered; evaluated and never {@code null}
 * @param iterations  search iterations executed; {@code 0} if the initial colony already contained a
 *                    perfect solution
 * @param evaluations number of full fitness evaluations performed, counting the initial colony, every
 *                    candidate neighbor and every scout replacement. Line-level scoring used internally
 *                    by guided neighbor generation is not included, so strategies with equal
 *                    evaluation counts can still differ in actual computation
 */
public record SolveResult(Solution best, int iterations, long evaluations) {

    /**
     * @return {@code true} if the best solution satisfies every row and column clue
     */
    public boolean solved() {
        return best.isPerfect();
    }
}