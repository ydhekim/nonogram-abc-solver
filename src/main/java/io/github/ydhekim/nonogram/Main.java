package io.github.ydhekim.nonogram;

import io.github.ydhekim.nonogram.benchmark.BenchmarkRunner;
import io.github.ydhekim.nonogram.model.Puzzle;
import io.github.ydhekim.nonogram.solver.AbcSolver;
import io.github.ydhekim.nonogram.solver.EnhancedAbcSolver;
import io.github.ydhekim.nonogram.util.SamplePuzzles;

/**
 * Entry point that benchmarks the pure and enhanced ABC solvers against a sample puzzle, printing
 * one summary line of normalized metrics per solver.
 */
public class Main {
    private static final int TRIALS = 30;

    public static void main(String[] args) {
        Puzzle puzzle = SamplePuzzles.twentyByTwenty1();
        BenchmarkRunner runner = new BenchmarkRunner(TRIALS);

        System.out.println(runner.run("Pure ABC", puzzle,
                new AbcSolver(80, 40, 5000, 0.0089)));
        System.out.println(runner.run("Enhanced ABC", puzzle,
                new EnhancedAbcSolver(80, 40, 5000, 0.7)));
    }
}