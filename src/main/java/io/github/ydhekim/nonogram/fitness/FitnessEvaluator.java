package io.github.ydhekim.nonogram.fitness;

import io.github.ydhekim.nonogram.model.Puzzle;
import io.github.ydhekim.nonogram.model.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Scores how far a {@link Solution} is from satisfying a {@link Puzzle}'s clues.
 *
 * <p>Every line is scored by comparing the block lengths actually present against the ones the clue
 * demands: pairwise length differences are summed, and each surplus or missing block adds a fixed
 * penalty. Scores are errors, so lower is better and {@code 0} means the solution is correct.
 */
public class FitnessEvaluator {
    private static final int MISSING_BLOCK_PENALTY = 5;

    /**
     * Scores the solution over every row and column of the puzzle.
     *
     * <p>As a side effect the total is stored on the solution via
     * {@link Solution#setFitness(int)}, so callers may read it back later with
     * {@link Solution#getFitness()} instead of re-evaluating.
     *
     * @param puzzle   the puzzle whose clues define correctness
     * @param solution the candidate to score; its fitness field is overwritten
     * @return the total error across all lines; {@code 0} means every clue is satisfied
     */
    public int evaluate(Puzzle puzzle, Solution solution) {
        int totalErrors = 0;

        for (int row = 0; row < puzzle.rows(); row++) {
            boolean[] line = extractRow(puzzle, solution, row);
            totalErrors += lineError(line, puzzle.rowClues().get(row));
        }

        for (int col = 0; col < puzzle.cols(); col++) {
            boolean[] line = extractColumn(puzzle, solution, col);
            totalErrors += lineError(line, puzzle.colClues().get(col));
        }
        solution.setFitness(totalErrors);
        return totalErrors;
    }

    /**
     * Scores a single row without touching the solution's stored fitness.
     *
     * @param puzzle   the puzzle whose clues define correctness
     * @param solution the candidate to score
     * @param row      zero-based row index
     * @return the error of that row; {@code 0} means its clue is satisfied
     */
    public int rowError(Puzzle puzzle, Solution solution, int row) {
        boolean[] line = extractRow(puzzle, solution, row);
        return lineError(line, puzzle.rowClues().get(row));
    }

    /**
     * Scores a single column without touching the solution's stored fitness.
     *
     * @param puzzle   the puzzle whose clues define correctness
     * @param solution the candidate to score
     * @param col      zero-based column index
     * @return the error of that column; {@code 0} means its clue is satisfied
     */
    public int columnError(Puzzle puzzle, Solution solution, int col) {
        boolean[] line = extractColumn(puzzle, solution, col);
        return lineError(line, puzzle.colClues().get(col));
    }

    private boolean[] extractRow(Puzzle puzzle, Solution solution, int row) {
        boolean[] line = new boolean[puzzle.cols()];
        int offset = row * puzzle.cols();
        for (int col = 0; col < puzzle.cols(); col++) {
            line[col] = solution.get(offset + col);
        }
        return line;
    }

    private boolean[] extractColumn(Puzzle puzzle, Solution solution, int col) {
        boolean[] line = new boolean[puzzle.rows()];
        for (int row = 0; row < puzzle.rows(); row++) {
            line[row] = solution.get(row * puzzle.cols() + col);
        }
        return line;
    }

    private int lineError(boolean[] line, List<Integer> clue) {
        List<Integer> actualBlocks = extractBlocks(line);
        List<Integer> expectedBlocks = normalizeClue(clue);

        int errors = 0;
        int commonLength = Math.min(actualBlocks.size(), expectedBlocks.size());

        for (int i = 0; i < commonLength; i++) {
            errors += Math.abs(actualBlocks.get(i) - expectedBlocks.get(i));
        }

        int blockCountDiff = Math.abs(actualBlocks.size() - expectedBlocks.size());
        errors += blockCountDiff * MISSING_BLOCK_PENALTY;

        return errors;
    }

    private List<Integer> extractBlocks(boolean[] line) {
        List<Integer> blocks = new ArrayList<>();
        int currentBlock = 0;

        for (boolean cell : line) {
            if (cell) {
                currentBlock++;
            } else if (currentBlock > 0) {
                blocks.add(currentBlock);
                currentBlock = 0;
            }
        }
        if (currentBlock > 0) {
            blocks.add(currentBlock);
        }
        return blocks;
    }

    private List<Integer> normalizeClue(List<Integer> clue) {
        if (clue.size() == 1 && clue.getFirst() == 0) {
            return List.of();
        }
        return clue;
    }
}
