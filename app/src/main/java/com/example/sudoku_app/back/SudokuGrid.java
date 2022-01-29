package com.example.sudoku_app.back;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SudokuGrid {
    public static final int GRID_ROW_COL_SIZE = 9;
    private final List<List<Integer>> grid = new ArrayList<>();

    public SudokuGrid(){
        for(int i = 0; i < GRID_ROW_COL_SIZE; i++){
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j < GRID_ROW_COL_SIZE; j++){
                row.add(0);
            }
            grid.add(row);
        }
    }

    public SudokuGrid(List<List<Integer>> initializedGrid){
        fillGrid(initializedGrid);
    }

    public boolean fillGrid(List<List<Integer>> wantedGrid){
        return grid.addAll(wantedGrid);
    }
    public void addElementToGrid(int element, int colIndex, int rowIndex){
        grid.get(colIndex).add(rowIndex, element);
    }

    public void removeElementToGrid(int colIndex, int rowIndex){
        grid.get(colIndex).set(rowIndex, 0);
    }

    public int getCellValue(int colIndex, int rowIndex){
        return grid.get(colIndex).get(rowIndex);
    }

    public List<List<Integer>> getACloneOfTheGrid(){
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < GRID_ROW_COL_SIZE; i++){
            res.add(new ArrayList<>(grid.get(i)));
        }
        return res;
    }

    public List<Integer> getRow(int rowIndex){
        return new ArrayList<>(grid.get(rowIndex));
    }

    public List<Integer> getColumn(int colIndex){
        List<Integer> col = new ArrayList<>();
        for(int i = 0; i < GRID_ROW_COL_SIZE; i++){
            col.add(grid.get(i).get(colIndex));
        }
        return col;
    }

    public Map<Integer, Integer> get_first_case_of_a_square(int rowIndex, int colIndex){
        Map<Integer, Integer> result = new HashMap<>();
        for(int x0 = 0; x0 < GRID_ROW_COL_SIZE; x0+=3){
            if(rowIndex >= x0 && rowIndex < x0+3){
                for(int y0 = 0; y0 < GRID_ROW_COL_SIZE; y0+=3){
                    if(colIndex >= y0 && colIndex < y0+3){
                        result.put(x0, y0);
                        return result;
                    }
                }
            }
        }
        return new HashMap<>(-1, -1);
    }

    public List<Integer> get_square(int rawIndex, int colIndex){
        List<Integer> result = new ArrayList<>();

        Map<Integer, Integer> firstSquareCase = get_first_case_of_a_square(rawIndex, colIndex);
        final int x = firstSquareCase.keySet().stream().findFirst().orElse(-1);
        final int y = firstSquareCase.values().stream().findFirst().orElse(-1);
        for(int x0 = x; x0< x+3; x0++){
            for(int y0 = y; y0 < y+3; y0++){
                result.add(grid.get(y0).get(x0));
            }
        }
        return result;
    }

}
