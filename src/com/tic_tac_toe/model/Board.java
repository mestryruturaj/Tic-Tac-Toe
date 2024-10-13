package com.tic_tac_toe.model;

import com.tic_tac_toe.enums.CellType;

public class Board {
    private Cell[][] board;

    public Board() {
        this.board = new Cell[3][3];
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public boolean isCellEmpty(int row, int col) {
        return this.board[row][col].getCellType() == CellType.EMPTY;
    }

    public void displayBoard() {
        System.out.println("|------|------|------|");
        for (int i = 0; i < this.board.length; i++) {
            String row = "|";
            for (int j = 0; j < this.board[i].length; j++) {
                String currCell = String.format("  %2s  |", this.board[i][j].getCellStatus());
                row += currCell;
            }
            System.out.println(row);
            System.out.println("|------|------|------|");
        }
        System.out.println();
    }
}
