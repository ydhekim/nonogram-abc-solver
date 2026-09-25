package io.github.ydhekim.nonogram.model;

/**
 * One member of the artificial bee colony: a food source (its current {@link Solution}) together
 * with the abandonment counter that drives the scout bee phase.
 *
 * <p>{@code trialCount} counts consecutive failed improvement attempts on this food source. It is
 * incremented whenever a generated neighbor is not better and reset to zero whenever the solution
 * is replaced. Once it reaches the solver's limit the source is considered exhausted and the scout
 * bee phase abandons it for a fresh random solution.
 */
public class Bee {
    private Solution solution;
    private int trialCount;

    /**
     * Creates a bee assigned to the given food source, with a trial count of zero.
     *
     * @param solution the initial solution
     */
    public Bee(Solution solution) {
        this.solution = solution;
        this.trialCount = 0;
    }

    /**
     * @return the solution this bee is currently exploiting
     */
    public Solution getSolution() {
        return this.solution;
    }

    /**
     * Accepts an improved solution and resets the trial count.
     *
     * @param solution the better solution found for this food source
     */
    public void updateSolution(Solution solution) {
        this.solution = solution;
        this.trialCount = 0;
    }

    /**
     * Records one failed improvement attempt on the current food source.
     */
    public void incrementTrialCount() {
        this.trialCount++;
    }

    /**
     * @return the number of consecutive failed improvement attempts
     */
    public int getTrialCount() {
        return this.trialCount;
    }

    /**
     * @param limit the abandonment threshold configured by the solver
     * @return {@code true} if the food source should be abandoned by the scout bee phase
     */
    public boolean isExhausted(int limit) {
        return this.trialCount >= limit;
    }

    /**
     * Abandons the exhausted food source for a fresh one and resets the trial count.
     *
     * @param solution the replacement solution, typically randomly generated
     */
    public void resetWithNewSolution(Solution solution) {
        this.solution = solution;
        this.trialCount = 0;
    }
}
