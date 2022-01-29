package com.example.sudoku_app.back;

import android.util.Pair;
import com.example.sudoku_app.R;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.sudoku_app.back.SudokuGrid.GRID_ROW_COL_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

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

    @Test
    public void should_get_the_first_row(){
        grid.fillGrid(given_a_grid_to_fill_with());

        List<Integer> firstRow = grid.getRow(0);

        assertThat(firstRow).isEqualTo(grid.getACloneOfTheGrid().get(0));
    }

    @Test
    public void should_get_the_first_column(){
        grid.fillGrid(given_a_grid_to_fill_with());

        List<Integer> firstColumn = grid.getColumn(0);

        assertThat(firstColumn).isEqualTo(given_a_column(0));
    }

    @Test
    public void should_get_the_first_case_of_the_first_square(){
        Map<Integer, Integer> firstCaseOfTheFirstSquare = grid.get_first_case_of_a_square(1,2);

        assertThat(firstCaseOfTheFirstSquare).contains(entry(0,0));
    }

    @Test
    public void should_get_the_first_square(){
        grid.fillGrid(given_a_grid_to_fill_with());

        List<Integer> firstSquare = grid.get_square(1,2);

        assertThat(firstSquare).isEqualTo(given_a_square(1,2));
    }

    private List<Integer> given_a_square(int rowIndex, int colIndex) {
        List<List<Integer>> gridClone = grid.getACloneOfTheGrid();
        Map<Integer, Integer> firstCase = grid.get_first_case_of_a_square(rowIndex, colIndex);
        List<Integer> res = new ArrayList<>();
        final int x = firstCase.keySet().stream().findFirst().orElse(-1);
        final int y = firstCase.values().stream().findFirst().orElse(-1);
        for(int x0 = x; x0 < x+3; x0++){
            for(int y0 = y; y0 < y+3;y0++){
                res.add(gridClone.get(y0).get(x0));
            }
        }
        return res;
    }

    private List<Integer> given_a_column(int colIndex) {
        List<List<Integer>> gridClone = grid.getACloneOfTheGrid();
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < GRID_ROW_COL_SIZE; i++){
            res.add(gridClone.get(i).get(colIndex));
        }
        return res;
    }

    private List<List<Integer>> given_a_grid_to_fill_with() {
        Random random = new Random();
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < GRID_ROW_COL_SIZE; i++){
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j < GRID_ROW_COL_SIZE; j++){
                if(random.nextInt(10) <= 4){
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