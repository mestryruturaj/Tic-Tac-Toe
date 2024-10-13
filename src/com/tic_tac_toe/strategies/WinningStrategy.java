package com.tic_tac_toe.strategies;

import com.tic_tac_toe.model.Board;
import com.tic_tac_toe.model.Cell;
import com.tic_tac_toe.model.Player;

import java.util.Objects;

public class WinningStrategy {
    private static WinningStrategy winningStrategy;

    private WinningStrategy() {
    }

    public static WinningStrategy getWinningStrategy() {
        if (Objects.isNull(WinningStrategy.winningStrategy)) {
            WinningStrategy.winningStrategy = new WinningStrategy();
        }
        return WinningStrategy.winningStrategy;
    }

    public boolean checkForWin(Board board, Player player) {
        String symbol = player.getSymbol();
        Cell[][] localBoard = board.getBoard();
        //Rows
        for (int i = 0; i < localBoard.length; i++) {
            boolean allPlayer = true;
            for (int j = 0; j < localBoard[i].length; j++) {
                if (!localBoard[i][j].getCellStatus().equals(symbol)) {
                    allPlayer = false;
                    break;
                }
            }
            if (allPlayer) {
                return true;
            }
        }

        //Cols
        for (int j = 0; j < localBoard[0].length; j++) {
            boolean allPlayer = true;
            for (int i = 0; i < localBoard.length; i++) {
                if (!localBoard[i][j].getCellStatus().equals(symbol)) {
                    allPlayer = false;
                    break;
                }
            }
            if (allPlayer) {
                return true;
            }
        }

        //Left diagonal
        boolean allPlayer = true;
        for (int i = 0; i < localBoard.length; i++) {
            if (!localBoard[i][i].getCellStatus().equals(symbol)) {
                allPlayer = false;
                break;
            }
        }
        if (allPlayer) {
            return true;
        }

        //Right diagonal
        allPlayer = true;
        for (int i = 0; i < localBoard.length; i++) {
            if (!localBoard[i][localBoard.length - 1 - i].getCellStatus().equals(symbol)) {
                allPlayer = false;
                break;
            }
        }
        if (allPlayer) {
            return true;
        }

        return false;
    }
}
