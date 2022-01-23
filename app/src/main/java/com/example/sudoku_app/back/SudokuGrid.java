package com.example.sudoku_app.back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

}
