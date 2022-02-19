package com.example.sudoku_app.back;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.sudoku_app.back.GridUtils.isComplete;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class PerfectSolverTest {

    private final PerfectSolver solver = new PerfectSolver();

    @Test
    public void should_resolve_the_given_grid() {
        SudokuGrid grid = given_an_incomplete_grid();
        SudokuGrid result = solver.resolve(grid);
        assertThat(isComplete(result)).isTrue();
    }

    private SudokuGrid given_an_incomplete_grid() {
        List<List<Integer>> result = new ArrayList<>();
        result.add(asList(0, 0, 0, 0, 0, 0, 0, 0));
        result.add(asList(0, 0, 0, 0, 0, 0, 0, 0));
        result.add(asList(0, 0, 0, 0, 0, 0, 0, 0));
        result.add(asList(0, 0, 0, 0, 0, 0, 0, 0));
        result.add(asList(0, 0, 0, 0, 0, 0, 0, 0));
        result.add(asList(0, 0, 0, 0, 0, 0, 0, 0));
        result.add(asList(0, 0, 0, 0, 0, 0, 0, 0));
        result.add(asList(0, 0, 0, 0, 0, 0, 0, 0));
        return new SudokuGrid(result);
    }
}