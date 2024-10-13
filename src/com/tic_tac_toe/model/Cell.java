package com.tic_tac_toe.model;

import com.tic_tac_toe.enums.CellType;
import com.tic_tac_toe.exception.InvalidMoveException;

import java.util.Objects;

public class Cell {
    private int row;
    private int col;
    private CellType cellType;
    private Player player;

    public Cell(int row, int col) {
        this.cellType = CellType.EMPTY;
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Cell occupyCell(Player player) {
        if (this.cellType != CellType.EMPTY) {
            throw new InvalidMoveException(String.format("Cell(row=%d, col=%d) is unavailable to occupy.%n", row, col));
        }
        this.cellType = CellType.UNAVAILABLE;
        this.player = player;
        return this;
    }

    public String getCellStatus() {
        if (Objects.isNull(this.player)) {
            return "";
        } else {
            return this.player.getSymbol();
        }
    }
}
