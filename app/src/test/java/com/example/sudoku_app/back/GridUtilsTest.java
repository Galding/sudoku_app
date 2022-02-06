package com.example.sudoku_app.back;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class GridUtilsTest {

    @Test
    public void should_be_complete() {
        SudokuGrid grid = given_a_complete_grid();

        assertThat(GridUtils.isComplete(grid)).isTrue();
    }
    
    @Test
    public void should_get_all_of_the_possible_numbers_for_the_given_grid() {
        final int colIndex = 0;
        final int rowIndex = 1;
        SudokuGrid grid = given_a_uncompleted_grid(colIndex, rowIndex);

        List<Integer> possibleNums = GridUtils.possibleNumbers(grid, colIndex, rowIndex);

        assertThat(GridUtils.isComplete(grid)).isFalse();
        assertThat(possibleNums).isEqualTo(singletonList(2)).hasSize(1);
    }

    private SudokuGrid given_a_uncompleted_grid(int colIndexToRemove, int rowIndexToRemove) {
        SudokuGrid modifiedGrid = given_a_complete_grid();
        modifiedGrid.removeElementToGrid(colIndexToRemove, rowIndexToRemove);
        return modifiedGrid;
    }

    private SudokuGrid given_a_complete_grid() {
        List<List<Integer>> result = new ArrayList<>();
        result.add(asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        result.add(asList(4, 5, 6, 7, 8, 9, 1, 2, 3));
        result.add(asList(7, 8, 9, 1, 2, 3, 4, 5, 6));
        result.add(asList(2, 1, 4, 3, 6, 5, 8, 9, 7));
        result.add(asList(3, 6, 5, 8, 9, 7, 2, 1, 4));
        result.add(asList(8, 9, 7, 2, 1, 4, 3, 6, 5));
        result.add(asList(5, 3, 1, 6, 4, 2, 9, 7, 8));
        result.add(asList(6, 4, 2, 9, 7, 8, 5, 3, 1));
        result.add(asList(9, 7, 8, 5, 3, 1, 6, 4, 2));
        return new SudokuGrid(result);
    }

       /*   ---------------------------------------
            | 1 | 2 | 3 || 4 | 5 | 6 || 7 | 8 | 9 |
            ---------------------------------------
            | 4 | 5 | 6 || 7 | 8 | 9 || 1 | 2 | 3 |
            ---------------------------------------
            | 7 | 8 | 9 || 1 | 2 | 3 || 4 | 5 | 6 |
            =======================================
            | 2 | 1 | 4 || 3 | 6 | 5 || 8 | 9 | 7 |
            ---------------------------------------
            | 3 | 6 | 5 || 8 | 9 | 7 || 2 | 1 | 4 |
            ---------------------------------------
            | 8 | 9 | 7 || 2 | 1 | 4 || 3 | 6 | 5 |
            =======================================
            | 5 | 3 | 1 || 6 | 4 | 2 || 9 | 7 | 8 |
            ---------------------------------------
            | 6 | 4 | 2 || 9 | 7 | 8 || 5 | 3 | 1 |
            ---------------------------------------
            | 9 | 7 | 8 || 5 | 3 | 1 || 6 | 4 | 2 |
            ---------------------------------------*/

}