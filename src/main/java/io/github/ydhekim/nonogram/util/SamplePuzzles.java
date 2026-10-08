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

    public static Puzzle fiveByFive1() {
        return new Puzzle(5, 5,
                List.of(List.of(2), List.of(2, 1), List.of(4), List.of(1, 1), List.of(1, 1)),
                List.of(List.of(4), List.of(2), List.of(3), List.of(3), List.of(1)));
    }

    public static Puzzle fiveByFive2() {
        return new Puzzle(5, 5,
                List.of(List.of(2), List.of(1, 1), List.of(3), List.of(1, 1), List.of(3)),
                List.of(List.of(3), List.of(2, 1), List.of(1, 3), List.of(1), List.of(1)));
    }

    public static Puzzle fiveByFive3() {
        return new Puzzle(5, 5,
                List.of(List.of(2), List.of(2, 1), List.of(4), List.of(2), List.of(1, 1)),
                List.of(List.of(2, 1), List.of(4), List.of(1, 2), List.of(1, 1), List.of(1)));
    }

    public static Puzzle fiveByFive4() {
        return new Puzzle(5, 5,
                List.of(List.of(1), List.of(3, 1), List.of(4), List.of(1, 1), List.of(1, 1)),
                List.of(List.of(4), List.of(3), List.of(4), List.of(1), List.of(1)));
    }

    public static Puzzle fiveByFive5() {
        return new Puzzle(5, 5,
                List.of(List.of(1), List.of(1, 2), List.of(1), List.of(2, 1), List.of(1)),
                List.of(List.of(1), List.of(2, 1), List.of(1), List.of(1, 2), List.of(1)));
    }

    public static Puzzle tenByTen1() {
        return new Puzzle(10, 10,
                List.of(List.of(4), List.of(2, 3, 2), List.of(2, 3, 1), List.of(1, 4, 1), List.of(1, 1, 5), List.of(1, 2, 3), List.of(2, 1, 1), List.of(1, 2, 1), List.of(1, 1, 1), List.of(3, 3)),
                List.of(List.of(3), List.of(3, 3), List.of(2, 1, 1), List.of(2, 1), List.of(3, 1, 1), List.of(5, 3), List.of(6, 1), List.of(1, 7), List.of(2, 2, 1), List.of(4)));
    }

    public static Puzzle tenByTen2() {
        return new Puzzle(10, 10,
                List.of(List.of(3), List.of(2, 1), List.of(2, 3), List.of(1, 2, 2), List.of(2, 2), List.of(1, 3), List.of(3, 4), List.of(4, 4), List.of(4, 2), List.of(2)),
                List.of(List.of(2), List.of(4), List.of(4), List.of(7), List.of(1, 1), List.of(1, 1), List.of(1, 1, 2), List.of(1, 1, 4), List.of(1, 7), List.of(8)));
    }

    public static Puzzle tenByTen3() {
        return new Puzzle(10, 10,
                List.of(List.of(2, 2), List.of(2, 1), List.of(4), List.of(4), List.of(6), List.of(5), List.of(7), List.of(1, 5), List.of(1, 2, 1, 1), List.of(1, 1, 1)),
                List.of(List.of(1), List.of(1, 2), List.of(1, 2, 2), List.of(4, 1), List.of(5, 2), List.of(2, 5), List.of(1, 1, 4), List.of(1, 5), List.of(3), List.of(4)));
    }

    public static Puzzle tenByTen4() {
        return new Puzzle(10, 10,
                List.of(List.of(4), List.of(2, 2), List.of(2, 4), List.of(5), List.of(3, 1, 1), List.of(4, 1), List.of(5, 2), List.of(3, 2), List.of(3), List.of(1, 1, 1)),
                List.of(List.of(1), List.of(2), List.of(3, 1), List.of(2, 5), List.of(3, 3, 2), List.of(1, 3, 2), List.of(1, 2, 2, 1), List.of(7), List.of(3), List.of(3)));
    }

    public static Puzzle tenByTen5() {
        return new Puzzle(10, 10,
                List.of(List.of(4), List.of(3, 1), List.of(2, 1, 2), List.of(2, 1), List.of(1, 4), List.of(2, 2, 1), List.of(2, 1, 1), List.of(3, 2, 1), List.of(2, 1, 2), List.of(2, 2, 1)),
                List.of(List.of(3), List.of(5), List.of(3), List.of(3, 2, 1), List.of(4, 1), List.of(2, 1, 1), List.of(1, 1, 1, 3), List.of(2, 2, 1), List.of(1, 1, 3), List.of(2, 2)));
    }

    public static Puzzle fifteenByFifteen1() {
        return new Puzzle(15, 15,
                List.of(List.of(1, 1), List.of(1, 1, 1), List.of(1), List.of(4), List.of(6), List.of(8, 1, 1), List.of(9, 2, 1), List.of(6, 2, 4), List.of(9, 2), List.of(4, 2), List.of(13), List.of(11), List.of(3, 2, 2), List.of(2, 2), List.of(2, 2)),
                List.of(List.of(4, 1, 1), List.of(1, 5, 2, 2), List.of(1, 6, 4), List.of(8, 3), List.of(1, 6, 3), List.of(1, 6, 2), List.of(3, 5), List.of(9), List.of(6, 2), List.of(4, 1), List.of(2, 3), List.of(2, 3), List.of(4), List.of(2), List.of(3)));
    }

    public static Puzzle fifteenByFifteen2() {
        return new Puzzle(
                15, 15,
                List.of(
                        List.of(4),
                        List.of(1, 3),
                        List.of(1, 3),
                        List.of(8),
                        List.of(3, 1, 1),
                        List.of(1, 3, 3),
                        List.of(1, 1, 1, 1),
                        List.of(11),
                        List.of(1, 2),
                        List.of(1, 12),
                        List.of(2, 10),
                        List.of(3, 9),
                        List.of(2, 8),
                        List.of(1, 7),
                        List.of(3, 6)
                ),
                List.of(
                        List.of(2),
                        List.of(2, 1, 2),
                        List.of(2, 2, 1, 2),
                        List.of(1, 5, 1, 1),
                        List.of(5, 1, 1, 2),
                        List.of(4, 3, 2, 3),
                        List.of(3, 1, 1, 3, 1),
                        List.of(1, 3, 4, 1),
                        List.of(1, 1, 5),
                        List.of(4, 6),
                        List.of(1, 8),
                        List.of(1, 7),
                        List.of(1, 6),
                        List.of(6),
                        List.of(6)
                )
        );
    }

    public static Puzzle fifteenByFifteen3() {
        return new Puzzle(15, 15,
                List.of(List.of(4), List.of(5), List.of(3, 1), List.of(4, 2), List.of(7, 6), List.of(3, 2, 1), List.of(4, 3, 1), List.of(3, 4, 1), List.of(4, 1, 1), List.of(4, 3, 1), List.of(4, 3, 1), List.of(4, 3, 1), List.of(1, 1, 3, 1), List.of(1, 2, 3, 1), List.of(1, 3, 5)),
                List.of(List.of(8), List.of(11), List.of(10), List.of(3, 1, 6), List.of(6, 1, 2), List.of(2, 4), List.of(2, 2, 2, 6), List.of(4, 9), List.of(1, 6), List.of(1), List.of(11), List.of(1, 1), List.of(1, 1), List.of(1, 1), List.of(1, 1)));
    }

    public static Puzzle fifteenByFifteen4() {
        return new Puzzle(
                15, 15,
                List.of(
                        List.of(5, 8),
                        List.of(2, 5, 2, 2),
                        List.of(1, 8, 1),
                        List.of(1, 2, 4),
                        List.of(1, 4, 1),
                        List.of(2, 2, 1),
                        List.of(5),
                        List.of(3, 2, 2),
                        List.of(3, 1, 4),
                        List.of(2, 8),
                        List.of(2, 7),
                        List.of(1, 1, 2, 2),
                        List.of(2, 1, 1, 1),
                        List.of(4, 1, 1),
                        List.of(3, 1, 1)
                ),
                List.of(
                        List.of(6, 2, 4),
                        List.of(2, 5, 3),
                        List.of(1, 3, 2),
                        List.of(1, 3, 2, 4),
                        List.of(7, 1),
                        List.of(3, 2),
                        List.of(4, 1),
                        List.of(4, 3),
                        List.of(5, 2),
                        List.of(1, 2, 1, 2),
                        List.of(3, 7),
                        List.of(2, 5),
                        List.of(1, 4),
                        List.of(2, 4),
                        List.of(3, 6)
                )
        );
    }

    public static Puzzle fifteenByFifteen5() {
        return new Puzzle(
                15, 15,
                List.of(
                        List.of(5),
                        List.of(7, 3),
                        List.of(6, 1, 3, 1),
                        List.of(4, 3, 2),
                        List.of(2, 3, 4),
                        List.of(2, 2, 5),
                        List.of(3, 4, 3),
                        List.of(2, 3, 2, 1),
                        List.of(2, 4, 1),
                        List.of(1, 6, 2),
                        List.of(2, 3, 1, 1),
                        List.of(1, 5, 1),
                        List.of(1, 1, 4, 4),
                        List.of(2, 3, 1),
                        List.of(5, 4)
                ),
                List.of(
                        List.of(2, 4),
                        List.of(5, 3, 1),
                        List.of(9, 2, 1),
                        List.of(4, 2, 3, 1),
                        List.of(3, 2, 5, 1),
                        List.of(3, 2, 8),
                        List.of(2, 1, 4, 4),
                        List.of(1, 2, 4, 2),
                        List.of(2, 2, 2),
                        List.of(1, 2),
                        List.of(1, 2, 1, 1, 1),
                        List.of(1, 2, 1, 3),
                        List.of(2, 3, 2, 1, 1),
                        List.of(1, 2, 1, 2, 2, 1),
                        List.of(3, 2)
                )
        );
    }

    public static Puzzle twentyByTwenty1() {
        return new Puzzle(
                20, 20,
                List.of(
                        List.of(2, 3, 4, 1, 1),
                        List.of(1, 2, 1, 3, 2, 2),
                        List.of(1, 1, 1, 1, 1, 2),
                        List.of(2, 3, 2),
                        List.of(1, 1, 4),
                        List.of(2, 4, 2),
                        List.of(4, 1, 1, 4, 1),
                        List.of(1, 8, 2),
                        List.of(2, 2, 4, 3, 1),
                        List.of(1, 1, 4, 4),
                        List.of(2, 2, 6),
                        List.of(2, 1, 1, 6),
                        List.of(1, 2, 2, 3, 1),
                        List.of(2, 3, 1, 3),
                        List.of(1, 4, 2, 4),
                        List.of(2, 5, 1, 3),
                        List.of(1, 6, 2, 1),
                        List.of(1, 7, 1, 4),
                        List.of(1, 4, 1, 5),
                        List.of(3, 5)
                ),
                List.of(
                        List.of(5),
                        List.of(2, 3, 1),
                        List.of(1, 1, 3, 2),
                        List.of(2, 1, 2, 3),
                        List.of(2, 3, 5),
                        List.of(1, 3, 7),
                        List.of(2, 3, 7),
                        List.of(1, 1, 4, 5),
                        List.of(1, 5, 3),
                        List.of(2, 1, 3, 3),
                        List.of(2, 3, 3, 1),
                        List.of(1, 1, 1, 3, 3, 1),
                        List.of(1, 8, 4),
                        List.of(1, 1, 1, 4, 2),
                        List.of(1, 3, 3, 1),
                        List.of(2, 1, 1, 3, 1),
                        List.of(1, 1, 1, 7, 2),
                        List.of(16, 1),
                        List.of(1, 3, 1, 3, 5),
                        List.of(1, 1, 2, 2, 2, 1)
                )
        );
    }

    public static Puzzle twentyByTwenty2() {
        return new Puzzle(
                20, 20,
                List.of(
                        List.of(3),
                        List.of(5, 4),
                        List.of(1, 5, 2),
                        List.of(1, 1, 1, 4, 2),
                        List.of(1, 1, 2, 1),
                        List.of(2, 2, 1),
                        List.of(2, 3, 2),
                        List.of(1, 1, 1, 1, 8),
                        List.of(1, 2, 1, 2),
                        List.of(1, 1, 2, 1),
                        List.of(2, 2, 4, 2),
                        List.of(3, 7),
                        List.of(3, 8),
                        List.of(1, 4, 1, 2),
                        List.of(9, 1),
                        List.of(4, 3, 3),
                        List.of(1, 4, 2, 1),
                        List.of(4, 2, 4),
                        List.of(2, 2, 7),
                        List.of(13)
                ),
                List.of(
                        List.of(1),
                        List.of(1, 1),
                        List.of(1, 1, 5),
                        List.of(1, 4, 2),
                        List.of(3, 4, 4),
                        List.of(2, 2, 1, 8, 1),
                        List.of(4, 4, 1, 2, 1, 2),
                        List.of(5, 1, 5, 2),
                        List.of(5, 1, 2, 4, 1, 1),
                        List.of(2, 2, 1, 1, 1, 2, 1, 1),
                        List.of(2, 1, 1, 3, 1, 2),
                        List.of(1, 2, 2, 1, 2, 2),
                        List.of(1, 1, 6, 3),
                        List.of(2, 1, 4, 1, 3),
                        List.of(2, 1, 3, 4),
                        List.of(1, 2, 1, 3),
                        List.of(4, 1, 2),
                        List.of(1, 1, 1),
                        List.of(2, 2),
                        List.of(3)
                )
        );
    }

    public static Puzzle twentyByTwenty3() {
        return new Puzzle(
                20, 20,
                List.of(
                        List.of(1, 2),
                        List.of(2, 4),
                        List.of(2, 3, 2),
                        List.of(5, 4),
                        List.of(7, 3),
                        List.of(9, 4),
                        List.of(9, 6),
                        List.of(8, 6),
                        List.of(7, 6),
                        List.of(5, 7),
                        List.of(3, 8),
                        List.of(8),
                        List.of(11),
                        List.of(13),
                        List.of(14),
                        List.of(8, 5),
                        List.of(9, 1, 1),
                        List.of(8, 1, 1),
                        List.of(6, 1, 1),
                        List.of(3, 1, 1)
                ),
                List.of(
                        List.of(3),
                        List.of(4),
                        List.of(5),
                        List.of(4, 5),
                        List.of(6, 5),
                        List.of(5, 5),
                        List.of(6, 5),
                        List.of(7, 5),
                        List.of(7, 5),
                        List.of(7, 4),
                        List.of(7, 3),
                        List.of(5, 4),
                        List.of(4, 6),
                        List.of(1, 10),
                        List.of(1, 10, 1),
                        List.of(3, 14),
                        List.of(2, 13, 1),
                        List.of(15),
                        List.of(13),
                        List.of(5)
                )
        );
    }

    public static Puzzle twentyByTwenty4() {
        return new Puzzle(
                20, 20,
                List.of(
                        List.of(1),
                        List.of(3),
                        List.of(2, 1),
                        List.of(5, 1, 1),
                        List.of(2, 4, 6),
                        List.of(6, 1, 2, 1),
                        List.of(1, 3, 1, 1, 1, 2),
                        List.of(1, 1, 2, 2, 1, 1),
                        List.of(1, 5, 2, 1),
                        List.of(2, 2, 1, 6),
                        List.of(2, 2, 6, 2),
                        List.of(6, 3, 1, 2),
                        List.of(2, 2, 1, 2),
                        List.of(1, 1, 1, 1, 2),
                        List.of(5, 2, 2, 2),
                        List.of(4, 1, 2, 3),
                        List.of(2, 6, 4),
                        List.of(2, 2, 5),
                        List.of(2, 2, 10),
                        List.of(5, 11)
                ),
                List.of(
                        List.of(2),
                        List.of(5, 6),
                        List.of(2, 2, 1, 5),
                        List.of(4, 2, 3, 2),
                        List.of(1, 3, 1, 1, 1),
                        List.of(1, 2, 1, 1),
                        List.of(1, 1, 1, 1),
                        List.of(2, 2, 2),
                        List.of(1, 5, 2),
                        List.of(1, 2, 4, 1),
                        List.of(8, 1, 2),
                        List.of(2, 2, 2, 1, 2),
                        List.of(1, 2, 4, 1, 2),
                        List.of(6, 1, 2, 1, 2),
                        List.of(1, 4, 2, 2),
                        List.of(1, 2, 2, 2, 3),
                        List.of(1, 7, 4),
                        List.of(1, 4, 5),
                        List.of(3, 11),
                        List.of(13)
                )
        );
    }

    public static Puzzle twentyByTwenty5() {
        return new Puzzle(
                20, 20,
                List.of(
                        List.of(4),
                        List.of(2, 2, 1, 1),
                        List.of(4, 2, 1, 1),
                        List.of(4, 3, 2, 2),
                        List.of(2, 2, 3, 2),
                        List.of(2, 3),
                        List.of(4, 3),
                        List.of(2, 5, 4),
                        List.of(9, 4),
                        List.of(10, 3),
                        List.of(4, 7, 4),
                        List.of(2, 8, 3),
                        List.of(8, 4),
                        List.of(2, 5, 3),
                        List.of(2, 3, 2),
                        List.of(3, 3, 2),
                        List.of(2, 2, 2, 2, 2),
                        List.of(1, 1, 1, 1, 1, 1, 3),
                        List.of(2, 2, 2, 2, 4),
                        List.of(3, 3, 3, 1)
                ),
                List.of(
                        List.of(1, 3),
                        List.of(2, 2, 2),
                        List.of(3, 2, 1, 1),
                        List.of(3, 5, 2),
                        List.of(3, 3, 3),
                        List.of(6),
                        List.of(2, 6),
                        List.of(4, 7),
                        List.of(4, 8),
                        List.of(2, 10),
                        List.of(11, 3),
                        List.of(2, 6, 3, 2),
                        List.of(2, 1, 1, 1),
                        List.of(4, 2, 2),
                        List.of(2, 1, 3),
                        List.of(1, 2, 3, 1),
                        List.of(4, 7, 2),
                        List.of(1, 15),
                        List.of(8, 7),
                        List.of(8, 3)
                )
        );
    }
}
