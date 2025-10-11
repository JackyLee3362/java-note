package com.example.demo.bean;

public class Sudoku {
    private final int LINE = 9;
    private boolean flag = false;
    private int[][] chess;
    public Sudoku(){
        chess = new int[9][9];
        for(int i = 0; i < LINE; i++)
            for(int j = 0; j < LINE; j++)
                chess[i][j] = 0;
    }

    public boolean check(int row, int col, int num){
        int blockRow = row / 3 * 3;
        int blockCol = col / 3 * 3;
        if(chess[row][col] != 0)
            return false;
        for(int i = 0; i < LINE; i++)
            for(int j = 0; j < LINE; j++)
                if(chess[row][j] == num || chess[i][col] == num)
                    return false;
        for(int i = blockRow; i < blockRow+3; i++)
            for(int j = blockCol; j < blockCol+3; j++)
                if(chess[i][j] == num)
                    return false;
        return true;
    }

    public void dfs(int row, int col){
        if(row==LINE) {
            flag = true;
            return;
        }
        else if(chess[row][col] > 0)
            dfs(row + (col + 1) / LINE, (col + 1) % LINE);
        for(int i = 1; i <= LINE; i++){
            if(!check(row, col, i)) continue;
            chess[row][col] = i;
            dfs(row + (col + 1) / LINE, (col + 1) % LINE);
            if(flag) return ;
            chess[row][col] = 0;
        }
    }

    public void printSudoku(){
        for(int i = 0; i < LINE; i++) {
            for (int j = 0; j < LINE; j++)
                System.out.print(chess[i][j]+" ");
            System.out.println("");
        }
    }

    public int[][] getChess() {
        return chess;
    }

    public void setChess(int i, int j, int number) {
        chess[i][j] = number;
    }

}
