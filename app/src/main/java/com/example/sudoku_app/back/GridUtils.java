package com.example.sudoku_app.back;

import java.util.HashSet;
import java.util.List;

import static com.example.sudoku_app.back.SudokuGrid.GRID_ROW_COL_SIZE;

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
}
