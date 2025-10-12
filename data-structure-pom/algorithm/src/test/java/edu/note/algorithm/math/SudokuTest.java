package edu.note.algorithm.math;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-10-11 19:59
 */
public class SudokuTest {

    @Test
    @DisplayName("测试")
    void test01() {
        Sudoku sudoku = new Sudoku();
        sudoku.printSudoku();
        sudoku.dfs(0, 0);
        sudoku.printSudoku();

    }

}
