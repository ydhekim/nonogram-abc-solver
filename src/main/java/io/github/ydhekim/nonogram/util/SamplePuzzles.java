package io.github.ydhekim.nonogram.util;

import io.github.ydhekim.nonogram.model.Puzzle;

import java.util.List;

/**
 * A small library of hand-transcribed nonogram puzzles used for manual
 * testing and benchmarking. Each factory method returns a fresh {@link Puzzle}
 * instance so callers can safely reuse them across multiple solver runs.
 */
public final class SamplePuzzles {
    private SamplePuzzles() {
        // utility class, not instantiable
    }

    public static Puzzle threeByThree() {
        return new Puzzle(3, 3,
                List.of(List.of(1), List.of(3), List.of(1)),
                List.of(List.of(1), List.of(3), List.of(1)));
    }

    public static Puzzle sevenBySeven() {
        return new Puzzle(7, 7,
                List.of(List.of(2), List.of(1), List.of(1, 2), List.of(2, 1, 1), List.of(1, 1, 1), List.of(1, 3), List.of(1, 2)),
                List.of(List.of(2), List.of(1), List.of(1, 2), List.of(2, 1, 1), List.of(1, 1, 1), List.of(1, 3), List.of(1, 2)));
    }

    public static Puzzle tenByTen() {
        return new Puzzle(10, 10,
                List.of(List.of(4), List.of(2, 2), List.of(1, 1), List.of(1, 1, 1, 1), List.of(1, 1), List.of(10), List.of(1, 1, 1, 1), List.of(1, 1, 1, 1), List.of(1, 1, 1, 1), List.of(2, 1, 1, 1)),
                List.of(List.of(3, 1), List.of(1, 1, 3), List.of(1, 2), List.of(2, 1, 1, 2), List.of(1, 3), List.of(1, 1), List.of(2, 1, 2), List.of(1, 1, 3), List.of(1, 2), List.of(3, 3)));
    }

    public static Puzzle fifteenByFifteenA() {
        return new Puzzle(15, 15,
                List.of(List.of(4), List.of(5), List.of(1, 1), List.of(2, 2), List.of(5), List.of(2, 2), List.of(6, 3), List.of(3, 4), List.of(1, 10), List.of(4, 1, 1, 4), List.of(2, 3, 3, 2), List.of(1, 2, 1, 2, 2, 1), List.of(1, 2, 5, 2, 1), List.of(2, 2, 2, 2), List.of(4, 4)),
                List.of(List.of(4), List.of(2, 2), List.of(2, 1, 2, 1), List.of(1, 4, 2, 1), List.of(4, 2, 2, 2), List.of(2, 2, 1, 1, 4), List.of(2, 3, 3, 1), List.of(7, 1, 1), List.of(2, 6), List.of(5, 4), List.of(6, 2), List.of(4, 2, 1), List.of(2, 2, 1), List.of(3, 2), List.of(1, 4)));
    }

    public static Puzzle fifteenByFifteenB() {
        return new Puzzle(15, 15,
                List.of(List.of(13), List.of(1, 1, 1, 1, 1, 1), List.of(1, 1, 1, 3), List.of(1, 1, 1), List.of(13, 1), List.of(1), List.of(9), List.of(1), List.of(1), List.of(3), List.of(3), List.of(3), List.of(3), List.of(3), List.of(3)),
                List.of(List.of(5), List.of(1, 1), List.of(2, 1), List.of(1, 1), List.of(1, 1, 1), List.of(1, 1, 6), List.of(2, 1, 9), List.of(1, 1, 1, 6), List.of(3, 1, 1), List.of(1, 1, 1), List.of(2, 1, 1), List.of(1, 1, 1), List.of(5, 1), List.of(1, 1), List.of(5)));
    }
}
