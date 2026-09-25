package io.github.ydhekim.nonogram;


import io.github.ydhekim.nonogram.benchmark.BenchmarkRunner;
import io.github.ydhekim.nonogram.model.Puzzle;
import io.github.ydhekim.nonogram.solver.AbcSolver;
import io.github.ydhekim.nonogram.solver.NonogramSolver;
import io.github.ydhekim.nonogram.solver.OptimizedAbcSolver;
import io.github.ydhekim.nonogram.util.SamplePuzzles;

/**
 * Entry point that benchmarks the pure and optimized ABC solvers against
 * a sample puzzle, printing the resulting error and elapsed time per run.
 */
public class Main {
    public static void main(String[] args) {
        Puzzle puzzle = SamplePuzzles.fifteenByFifteenB();

        NonogramSolver pureAbc = new AbcSolver(60, 40, 3000, 0.02);
        NonogramSolver optimizedAbc = new OptimizedAbcSolver(60, 40, 3000, 0.1);

        BenchmarkRunner runner = new BenchmarkRunner(pureAbc, "Pure ABC", optimizedAbc, "Optimized ABC");

        runner.run(puzzle, 10);
    }
}