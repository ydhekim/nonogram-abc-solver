package io.github.ydhekim.nonogram.model;

import java.util.Arrays;
import java.util.random.RandomGenerator;

/**
 * A candidate filling of a nonogram grid.
 *
 * <p>The grid is stored as a flat {@code boolean[]} in row-major order: the cell at
 * {@code (row, col)} lives at index {@code row * cols + col}, where {@code true} means filled. The
 * grid dimensions are not stored here; callers translate indices using their {@link Puzzle}.
 *
 * <p>The fitness field caches the error score assigned by the evaluator. Lower is better, and
 * {@code 0} means every row and column clue is satisfied, i.e. a perfect solution. A solution that
 * has never been evaluated carries {@link Integer#MAX_VALUE}.
 *
 * @see io.github.ydhekim.nonogram.fitness.FitnessEvaluator
 */
public class Solution {
    private final boolean[] cells;
    private int fitness;

    /**
     * Wraps the given cell array without copying it; the array becomes owned by this solution.
     *
     * @param cells row-major cell states
     */
    public Solution(boolean[] cells) {
        this.cells = cells;
        this.fitness = Integer.MAX_VALUE;
    }

    /**
     * Creates an unevaluated solution whose cells are filled independently at random.
     *
     * @param cellCount      number of cells in the grid
     * @param randomGenerator source of randomness
     * @return the new random solution
     */
    public static Solution random(int cellCount, RandomGenerator randomGenerator) {
        boolean[] cells = new boolean[cellCount];
        for (int i = 0; i < cellCount; i++) {
            cells[i] = randomGenerator.nextBoolean();
        }
        return new Solution(cells);
    }

    /**
     * @return a deep copy of the cells; the copy's fitness is reset to unevaluated
     */
    public Solution copy() {
        return new Solution(Arrays.copyOf(cells, cells.length));
    }

    /**
     * @param index row-major cell index
     * @return {@code true} if the cell is filled
     */
    public boolean get(int index) {
        return cells[index];
    }

    /**
     * Toggles the cell at the given row-major index between filled and empty.
     *
     * @param index row-major cell index
     */
    public void flip(int index) {
        cells[index] = !cells[index];
    }

    /**
     * @return the number of cells in the grid
     */
    public int size() {
        return cells.length;
    }

    /**
     * @return the cached error score; lower is better and {@code 0} is a perfect solution
     */
    public int getFitness() {
        return this.fitness;
    }

    /**
     * Caches the error score computed for this solution.
     *
     * @param fitness the error score; lower is better and {@code 0} is a perfect solution
     */
    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    /**
     * Compares cached fitness values, so both solutions must already be evaluated.
     *
     * @param solution the solution to compare against
     * @return {@code true} if this solution has a strictly lower error score
     */
    public boolean isBetterThan(Solution solution) {
        return this.fitness < solution.fitness;
    }

    /**
     * @return {@code true} if the cached fitness is {@code 0}, meaning all clues are satisfied
     */
    public boolean isPerfect() {
        return fitness == 0;
    }

    /**
     * @return the cells as one unbroken string, {@code X} for filled and {@code .} for empty
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (boolean b : cells) {
            sb.append(b ? "X" : ".");
        }
        return sb.toString();
    }
}