package com.example.sudoku_app.back;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.example.sudoku_app.back.SudokuGrid.GRID_ROW_COL_SIZE;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class GridUtils {

    public static boolean isComplete(SudokuGrid grid) {
        final List<List<Integer>> cloneOfTheGrid = grid.getACloneOfTheGrid();
        int rowIndex = 0;
        for (List<Integer> row : cloneOfTheGrid) {
            if (row.contains(0)) return false;
            if (new HashSet<>(row).size() != row.size()) return false;
            for (int colIndex = 0; colIndex < GRID_ROW_COL_SIZE; colIndex++) {
                List<Integer> column = grid.getColumn(colIndex);
                if (column.contains(0)) return false;
                if (new HashSet<>(column).size() != GRID_ROW_COL_SIZE) return false;
                List<Integer> square = grid.get_square(rowIndex, colIndex);
                if (new HashSet<>(square).size() != GRID_ROW_COL_SIZE) return false;
            }
            rowIndex++;
        }
        return true;
    }

    public static List<Integer> possibleNumbers(SudokuGrid grid, int rowIndex, int colIndex) {
        final int currentCellValue = grid.getCellValue(rowIndex, colIndex);
        if (currentCellValue != 0) return singletonList(currentCellValue);
        List<Integer> result = new ArrayList<>();
        List<Integer> commonValues = asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        for (int number : commonValues) {
            if (numberIsNotInSquareAndNotInRowAndNotInColumn(grid, rowIndex, colIndex, number)) {
                result.add(number);
            }
        }
        return result;
    }

    private static boolean numberIsNotInSquareAndNotInRowAndNotInColumn(SudokuGrid grid, int rowIndex, int colIndex, int number) {
        return !grid.get_square(rowIndex, colIndex).contains(number) && !grid.getRow(rowIndex).contains(number) && !grid.getColumn(colIndex).contains(number);
    }
}
