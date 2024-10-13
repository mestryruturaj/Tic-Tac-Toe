package com.tic_tac_toe.model;

import com.tic_tac_toe.enums.GameStatus;
import com.tic_tac_toe.strategies.WinningStrategy;
import com.tic_tac_toe.exception.GameConstraintViolationException;

import java.util.Objects;

public class Game {
    private Board board;
    private Player[] players;
    private GameStatus gameStatus;
    private int indexOfCurrentPlayer;
    private WinningStrategy winningStrategy;

    private Game(Board board, Player[] players, GameStatus gameStatus, int indexOfCurrentPlayer, WinningStrategy winningStrategy) {
        this.board = board;
        this.players = players;
        this.gameStatus = gameStatus;
        this.indexOfCurrentPlayer = indexOfCurrentPlayer;
        this.winningStrategy = winningStrategy;
    }

    public Board getBoard() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getIndexOfCurrentPlayer() {
        return indexOfCurrentPlayer;
    }

    public WinningStrategy getWinningStrategy() {
        return winningStrategy;
    }

    public int incrementIndexOfCurrentPlayer() {
        this.indexOfCurrentPlayer = (this.indexOfCurrentPlayer + 1) % this.players.length;
        return this.indexOfCurrentPlayer;
    }

    public static GameBuilder getGameBuilder() {
        return new GameBuilder();
    }

    public static class GameBuilder {
        private Board board;
        private Player[] players;
        private GameStatus gameStatus;
        private int indexOfCurrentPlayer;
        private WinningStrategy winningStrategy;

        public Board getBoard() {
            return board;
        }

        public GameBuilder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public Player[] getPlayers() {
            return players;
        }

        public GameBuilder setPlayers(Player[] players) {
            if (Objects.isNull(players[0]) || Objects.isNull(players[1])) {
                throw new GameConstraintViolationException("Game must strictly have two players.");
            }
            this.players = players;
            return this;
        }

        public GameStatus getGameStatus() {
            return gameStatus;
        }

        public GameBuilder setGameStatus(GameStatus gameStatus) {
            this.gameStatus = gameStatus;
            return this;
        }

        public int getIndexOfCurrentPlayer() {
            return indexOfCurrentPlayer;
        }

        public GameBuilder setIndexOfCurrentPlayer(int indexOfCurrentPlayer) {
            this.indexOfCurrentPlayer = indexOfCurrentPlayer;
            return this;
        }

        public WinningStrategy getWinningStrategy() {
            return winningStrategy;
        }

        public GameBuilder setWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }

        public Game build() {
            return new Game(this.board, this.players, this.gameStatus, this.indexOfCurrentPlayer, this.winningStrategy);
        }
    }
}
