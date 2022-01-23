package com.example.sudoku_app.back;

import com.example.sudoku_app.R;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.example.sudoku_app.back.SudokuGrid.GRID_ROW_COL_SIZE;
import static org.assertj.core.api.Assertions.assertThat;

public class SudokuGridTest {
    private final SudokuGrid grid = new SudokuGrid();

    @Test
    public void should_only_contains_zero(){
        assertThat(grid.getACloneOfTheGrid().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList())).allMatch(el -> el == 0);
    }

    @Test
    public void should_create_an_immutable_clone(){
        List<List<Integer>> res = grid.getACloneOfTheGrid();
        res.get(0).add(1);
        assertThat(res.get(0)).isNotEqualTo(grid.getACloneOfTheGrid().get(0));
    }

    @Test
    public void should_correctly_fill_the_grid(){
        List<List<Integer>> listToFill = given_a_grid_to_fill_with();

        assertThat(grid.fillGrid(listToFill)).isTrue();
        assertThat(grid.getACloneOfTheGrid()).isNotEmpty();
    }



    @Test
    public void should_correctly_add_element_to_the_grid(){
        grid.addElementToGrid(1,0,0);
        assertThat(grid.getCellValue(0,0)).isEqualTo(1);
    }

    @Test
    public void should_correctly_remove_element_to_the_grid(){
        grid.addElementToGrid(1,0,0);
        grid.removeElementToGrid(0,0);
        assertThat(grid.getCellValue(0,0)).isZero();
    }

    private List<List<Integer>> given_a_grid_to_fill_with() {
        Random random = new Random();
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < GRID_ROW_COL_SIZE; i++){
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j < GRID_ROW_COL_SIZE; j++){
                if(random.nextInt(10) == 1){
                    row.add(random.nextInt(9)+1);
                } else {
                    row.add(0);
                }
            }
            res.add(row);
        }
        return res;
    }
}