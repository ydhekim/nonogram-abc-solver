package io.github.ydhekim.nonogram.model;

import java.util.List;

/**
 * An immutable nonogram puzzle definition: the grid dimensions plus the clues that a solution must
 * satisfy.
 *
 * <p>Each clue list holds the lengths of the consecutive filled blocks of one line, in order. For
 * example {@code List.of(2, 1)} means a block of two filled cells, at least one empty cell, then a
 * single filled cell. An empty line is expressed as {@code List.of(0)}.
 *
 * @param rows     number of rows in the grid
 * @param cols     number of columns in the grid
 * @param rowClues one clue list per row, top to bottom; must contain exactly {@code rows} entries
 * @param colClues one clue list per column, left to right; must contain exactly {@code cols} entries
 */
public record Puzzle(
        int rows,
        int cols,
        List<List<Integer>> rowClues,
        List<List<Integer>> colClues
) {
    /**
     * @throws IllegalArgumentException if the clue lists do not match the declared dimensions
     */
    public Puzzle {
        if (rowClues.size() != rows) {
            throw new IllegalArgumentException("Size doesn't match for rows of puzzle");
        }
        if (colClues.size() != cols) {
            throw new IllegalArgumentException("Size doesn't match for cols of puzzle");
        }
    }

    /**
     * @return the total number of cells in the grid, {@code rows * cols}
     */
    public int cellCount() {
        return rows * cols;
    }
}
