package com.example.sudoku_app.back;

import java.util.List;

import static com.example.sudoku_app.back.SudokuGrid.GRID_ROW_COL_SIZE;

public class PerfectSolver implements Solver {
    @Override
    public SudokuGrid resolve(SudokuGrid grid) {
        final List<List<Integer>> cloneOfTheGrid = grid.getACloneOfTheGrid();
        for (int rowIndex = 0; rowIndex < GRID_ROW_COL_SIZE; rowIndex++) {
            for (int colIndex = 0; colIndex < GRID_ROW_COL_SIZE; colIndex++) {
                if (cloneOfTheGrid.get(colIndex).get(rowIndex) == 0) {
                    List<Integer> possiblesNumbers = GridUtils.possibleNumbers(grid, rowIndex, colIndex);
                    for (int possibleNumber : possiblesNumbers) {
                        cloneOfTheGrid.get(colIndex).set(rowIndex, possibleNumber);
                        if (!cloneOfTheGrid.isEmpty()) {
                            return resolve(new SudokuGrid(cloneOfTheGrid));
                        }
                        cloneOfTheGrid.get(colIndex).set(rowIndex, 0);
                    }
                }
            }
        }
        return new SudokuGrid(cloneOfTheGrid);
    }
}
