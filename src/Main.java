import com.tic_tac_toe.enums.GameStatus;
import com.tic_tac_toe.exception.GameConstraintViolationException;
import com.tic_tac_toe.exception.InvalidMoveException;
import com.tic_tac_toe.model.*;
import com.tic_tac_toe.strategies.WinningStrategy;

import java.util.HashMap;
import java.util.Map;

import static com.tic_tac_toe.utility.InputOutputUtility.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {
        try {
            writeToConsole("------Welcome to tic tac toe game------%n");
            writeToConsole(getInstructions());
            Board board = new Board();
            Player[] players = createPlayer();
            Game game = Game.getGameBuilder()
                    .setGameStatus(GameStatus.IN_PROGRESS)
                    .setBoard(board)
                    .setPlayers(players)
                    .setIndexOfCurrentPlayer(0)
                    .setWinningStrategy(WinningStrategy.getWinningStrategy())
                    .build();


            while (game.getGameStatus() != GameStatus.COMPLETE) {
                board.displayBoard();
                makeMove(game);
            }
        } catch (GameConstraintViolationException exception) {
            if (checkIfQuit(exception.getMessage())) {
                writeToConsole("Sad to see you go! Bye.");
            } else {
                writeToConsole(exception.getMessage());
            }
        } catch (Exception exception) {
            writeToConsole(exception.getMessage());
        } finally {
            closeScanner();
        }

    }

    private static Player[] createPlayer() {
        Player[] players = new Player[2];
        Map<String, Integer> symbolToPlayer = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            writeToConsole("-".repeat(25) + "\n");
            writeToConsole(String.format("Fill Player %d Details%n", i + 1));
            String name = readInputOrQuit("Name : ");
            String symbol = "";
            while (true) {
                String localSymbol = readInputOrQuit("Symbol(at most 2 chars long) : ");
                if (symbolToPlayer.containsKey(localSymbol)) {
                    ErrorInfo errorInfo = new ErrorInfo("Player %d (%s) is using '%s' Symbol.%nPlease try another one!%n%n",
                            symbolToPlayer.get(localSymbol) + 1, players[symbolToPlayer.get(localSymbol)].getName(), localSymbol);
                    writeErrorInfo(errorInfo);
                    continue;
                } else if (localSymbol.length() <= 0 || localSymbol.length() > 2) {
                    ErrorInfo errorInfo = new ErrorInfo("Length of the symbol should be 1 to 2 chars long.%nPlease try another one!%n%n");
                    writeErrorInfo(errorInfo);
                    continue;
                }
                symbol = localSymbol;
                symbolToPlayer.put(localSymbol, i);
                break;
            }

            Player player = new Player(name, symbol);
            players[i] = player;
        }

        return players;
    }

    public static void makeMove(Game game) {
        while (true) {
            int indexOfCurrentPlayer = game.getIndexOfCurrentPlayer();
            Cell[][] localBoard = game.getBoard().getBoard();
            Player currPlayer = game.getPlayers()[indexOfCurrentPlayer];
            int row = -1;
            int col = -1;
            while (row < 0 || row > 2 || col < 0 || col > 2) {
                writeToConsole(String.format("%s make a valid move by choosing row and col.%n", game.getPlayers()[indexOfCurrentPlayer].getName()));
                row = readRowOrColInput("Row : ", String.format("Invalid Row Input: Row should be a number. Row should lie within %d - %d (inclusive)", 0, 2), 0, 2);
                col = readRowOrColInput("Col : ", String.format("Invalid Row Input: Row should be a number. Col should lie within %d - %d (inclusive)", 0, 2), 0, 2);
            }
            try {
                localBoard[row][col].occupyCell(currPlayer);
            } catch (InvalidMoveException exception) {
                ErrorInfo errorInfo = new ErrorInfo(exception.getMessage());
                writeErrorInfo(errorInfo);
                continue;
            }
            game.incrementIndexOfCurrentPlayer();

            boolean hasGameCompleted = game.getWinningStrategy().checkForWin(game.getBoard(), currPlayer);
            if (hasGameCompleted) {
                game.getBoard().displayBoard();
                writeToConsole(String.format("%s has won!!! Hurray!!!", currPlayer.getName()));
                game.setGameStatus(GameStatus.COMPLETE);
            }
            break;
        }
    }

    public static String getInstructions() {
        String instructions = """
                Game Instructions:
                1.Strictly follow the prompts.
                2.To quit from game at any moment type in :quit;
                3.Use of special symbols such as '%%', '-', '.' is strictly prohibited
                4.Each player will get to choose his/her own Symbol. Player's chosen symbol
                    will be used to denote cells occupied by him/her.    
                """;
        return instructions;
    }
}
//Tic-Tac-Toe Game
/*
 * 1. one game has one board, 9 cells, 2 players
 * 2. board will have 9 cells
 * 3. cell can either be empty or occupied
 * 4. Player will have name, Symbol
 * 5. A player can make a move
 * 6. In a move player can consume an available cell and put its symbol there
 * 7. After each move we can check whether player with its symbol has won
 * 8. Then move to the next Player
 * */


/*
 * Validations
 * 1. row and col should be withing boundary
 * 2. Symbol should be at most 2 chars
 * 3. Symbol should not be repeated.
 * 4. Integer validation
 * */