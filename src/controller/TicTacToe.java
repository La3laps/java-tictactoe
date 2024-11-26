package controller;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import model.Board;
import model.Cell;
import model.player.ArtificialPlayer;
import model.player.HumanPlayer;
import router.UserInteraction;
import view.View;

public class TicTacToe {
    private final int size = 3;
    private final Board board = new Board();
    private HumanPlayer humanPlayer;
    private ArtificialPlayer artificialPlayer;
    private ArrayList<Cell> cells = board.getBoard();

    /**
     * Sets a player move if possible but also depending on which turn we are.
     * 
     * @param inputArray takes in the actuall cell in the TicTacToe.
     * @param turn       takes in which turn we are
     * @return increments the turn once the function has been used.
     */
    private int setPlayerMove(int[] inputArray, int actual_turn) {
        View display = new View();
        UserInteraction interact = new UserInteraction();

        switch (actual_turn % 2) {
            case 0 -> {
                this.humanPlayer = new HumanPlayer();
                humanPlayer.setRepresentation(" ❌ ");
            }
            case 1 -> {
                this.humanPlayer = new HumanPlayer();
                humanPlayer.setRepresentation(" ⭕ ");
            }
            default -> throw new AssertionError();
        }

        int index = inputArray[0] * size + inputArray[1];

        while (cells.get(index).getRepresentation().equals(" ❌ ")
                || cells.get(index).getRepresentation().equals(" ⭕ ")) {
            display.clearScreen();
            display.printOccupiedCell();
            display.displayBoard(cells, size);

            inputArray = interact.getMoveFromPlayer(cells, size);
            index = inputArray[0] * size + inputArray[1];
        }

        Cell cellule = new Cell();
        cellule.setRepresentation(humanPlayer.getRepresentation());

        cells.set(index, cellule);

        actual_turn++;
        return actual_turn++;
    }

    /**
     * Sets a machine move if possible but also depending on which turn we are.
     * 
     * @param inputArray takes in the actuall cell in the TicTacToe.
     * @param turn       takes in which turn we are
     * @return increments the turn once the function has been used.
     */
    private int setMachineMove(int[] inputArray, int actual_turn) {
        switch (actual_turn % 2) {
            case 0 -> {
                this.artificialPlayer = new ArtificialPlayer();
                artificialPlayer.setRepresentation(" ❌ ");
            }
            case 1 -> {
                this.artificialPlayer = new ArtificialPlayer();
                artificialPlayer.setRepresentation(" ⭕ ");
            }
            default -> {

                throw new AssertionError();
            }
        }

        int index = inputArray[0] * size + inputArray[1];

        while (cells.get(index).getRepresentation().equals(" ❌ ")
                || cells.get(index).getRepresentation().equals(" ⭕ ")) {
            inputArray = artificialPlayer.getMachineMove();
            index = inputArray[0] * size + inputArray[1];
        }
        Cell cellule = new Cell();
        cellule.setRepresentation(artificialPlayer.getRepresentation());

        cells.set(index, cellule);

        actual_turn++;
        return actual_turn;
    }

    /**
     * Main logic of the game.
     * 1. Takes user input to know how many computers/players are playing and what
     * they play.
     * 2. Increments a turn variable depending on when player/computer plays.
     * 3. Stops the game when a player/computer has won or there are no more
     * possible moves.
     */
    public void play(int playerOne, int playerTwo) {

        if (playerOne == 1 && playerTwo == 1) {
            playerVSPlayer();
        } else if (playerOne == 2 && playerTwo == 2) {
            computerVSComputer();
        } else {
            playerVSComputer();
        }

    }

    private void playerVSPlayer() {

        View display = new View();
        UserInteraction interact = new UserInteraction();

        board.initializeBoard(size);
        this.cells = board.getBoard();

        display.clearScreen();
        display.displayBoard(cells, size);

        int turn = 0;

        while (!isOver(turn)) {
            int[] move = interact.getMoveFromPlayer(cells, size);
            turn = setPlayerMove(move, turn);
            display.displayBoard(cells, size);
        }

        display.printGameOver();

        if (turn != 9) {
            display.displayBoard(cells, size);
            display.printPlayersWin(humanPlayer);
        } else {
            display.printOutOfMoves();
        }
    }

    private void computerVSComputer() {

        View display = new View();
        this.artificialPlayer = new ArtificialPlayer();

        board.initializeBoard(size);
        this.cells = board.getBoard();

        display.clearScreen();
        display.displayBoard(cells, size);

        int turn = 0;

        while (!isOver(turn)) {
            int[] move = artificialPlayer.getMachineMove();
            turn = setMachineMove(move, turn);

            display.clearScreen();
            display.displayBoard(cells, size);
            waitASec();
        }

        display.printGameOver();
        
        if (turn != 9) {
            display.displayBoard(cells, size);
            display.printComputersWin(artificialPlayer);
        } else {
            display.printOutOfMoves();
        }
    }

    private void playerVSComputer() {
        View display = new View();
        UserInteraction interact = new UserInteraction();
        this.artificialPlayer = new ArtificialPlayer();

        board.initializeBoard(size);
        display.clearScreen();
        display.displayBoard(cells, size);

        int turn = 0;

        while (!isOver(turn)) {
            try {
                // machine turn
                int[] machineMove = artificialPlayer.getMachineMove();
                turn = setMachineMove(machineMove, turn);
                
                display.clearScreen();
                waitASec();
                display.displayBoard(cells, size);

                if (isOver(turn)) {
                    display.printGameOver();
                    if (turn != 9) {
                        display.displayBoard(cells, size);
                        display.printComputerWin();
                    } else {
                        display.printOutOfMoves();
                    }
                    return;
                }

                // player turn
                int[] move = interact.getMoveFromPlayer(cells, size);
                turn = setPlayerMove(move, turn);
                display.displayBoard(cells, size);

                if (isOver(turn)) {
                    display.printGameOver();
                    if (turn != 9) {
                        display.displayBoard(cells, size);
                        display.printPlayerWin();
                    } else {
                        display.printOutOfMoves();
                    }
                    return;
                }
            } catch (Exception e) {

            }
        }
    }

    /**
     * Checks if a player/computer has won the game.
     * 
     * @param turn takes in parameter which turn we are.
     * @return true if conditions are met or false if the game should not be over.
     */
    private boolean isOver(int turn) {
        boolean over = false;

        for (int i = 0; i < size; i++) {
            // Check rows
            if (!getCell(i * size).equals("    ") && getCell(i * size).equals(getCell(i * 3 + 1))
                    && getCell(i * size).equals(getCell(i * size + 2))) {
                return true;
            }

            // Check columns
            if (!getCell(i).equals("    ") && getCell(i).equals(getCell(i + 3)) && getCell(i).equals(getCell(i + 6))) {
                return true;
            }
        }

        // Check diag
        if (!getCell(0).equals("    ") && getCell(0).equals(getCell(4)) && getCell(0).equals(getCell(8))) {
            return true;
        }

        if (!getCell(2).equals("    ") && getCell(2).equals(getCell(4)) && getCell(2).equals(getCell(6))) {
            return true;
        }

        if (turn > 8) {
            over = true;
        }

        return over;
    }

    private String getCell(int i) {
        return cells.get(i).getRepresentation();
    }

    private void waitASec() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

}