package Controller;

import Model.ArtificialPlayer;
import Model.HumanPlayer;
import Model.Player;
import Router.UserInteraction;
import View.View;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TicTacToe {
    private final int size = 3;
    private ArrayList<Player> cells = new ArrayList<>();
    private HumanPlayer player;
    private ArtificialPlayer machinePlayer;

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
                this.player = new HumanPlayer();
                player.setRepresentation(" ❌ ");
            }
            case 1 -> {
                this.player = new HumanPlayer();
                player.setRepresentation(" ⭕ ");
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
        cells.set(index, player);
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
                this.machinePlayer = new ArtificialPlayer();
                machinePlayer.setRepresentation(" ❌ ");
            }
            case 1 -> {
                this.machinePlayer = new ArtificialPlayer();
                machinePlayer.setRepresentation(" ⭕ ");
            }
            default -> throw new AssertionError();
        }

        int index = inputArray[0] * size + inputArray[1];

        while (cells.get(index).getRepresentation().equals(" ❌ ")
                || cells.get(index).getRepresentation().equals(" ⭕ ")) {
            inputArray = machinePlayer.getMachineMove();
            index = inputArray[0] * size + inputArray[1];
        }
        cells.set(index, machinePlayer);
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
    public void play() {
        View display = new View();
        UserInteraction interact = new UserInteraction();

        int[] playersComputers = interact.chosePlayers();
        int turn = 0;
        boolean gameOver = false;

        if (playersComputers[0] == 1 && playersComputers[1] == 1) { // player against player
            display.clearScreen();
            Player cell = new Player();
            cells = cell.initializeBoard(size, cells);
            display.displayBoard(cells, size);
            while (!gameOver) {
                int[] move = interact.getMoveFromPlayer(cells, size);
                turn = setPlayerMove(move, turn);
                display.displayBoard(cells, size);
                gameOver = isOver(turn);
            }

            display.printGameOver();
            if (turn != 9) {
                display.printPlayersWin(player);
            } else {
                display.printOutOfMoves();
            }
        } else if (playersComputers[0] == 2 && playersComputers[1] == 2) { // computer against computer
            display.clearScreen();
            Player cell = new Player();
            cells = cell.initializeBoard(size, cells);
            display.displayBoard(cells, size);
            machinePlayer = new ArtificialPlayer();
            while (!gameOver) {
                display.clearScreen();
                int[] move = machinePlayer.getMachineMove();
                turn = setMachineMove(move, turn);
                display.displayBoard(cells, size);
                gameOver = isOver(turn);
                // wait one second
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }

            display.printGameOver();
            if (turn != 9) {
                display.printComputersWin(machinePlayer);
            } else {
                display.printOutOfMoves();
            }
        } else {
            display.clearScreen();
            Player cell = new Player();
            cells = cell.initializeBoard(size, cells);
            display.displayBoard(cells, size);
            machinePlayer = new ArtificialPlayer();
            while (true) {
                try {
                    // machine turn
                    int[] machineMove = machinePlayer.getMachineMove();
                    turn = setMachineMove(machineMove, turn);
                    gameOver = isOver(turn);
                    display.clearScreen();
                    display.displayBoard(cells, size);
                    if (gameOver) {
                        display.printGameOver();
                        if (turn != 9) {
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
                    gameOver = isOver(turn);
                    if (gameOver) {
                        display.printGameOver();
                        if (turn != 9) {
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

    }

    /**
     * Checks if a player/computer has won the game.
     * 
     * @param turn takes in parameter which turn we are.
     * @return true if conditions are met or false if the game should not be over.
     */
    private boolean isOver(int turn) {
        boolean over = false;
        if (turn > 8) {
            over = true;
        }

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

        return over;
    }

    private String getCell(int i) {
        return cells.get(i).getRepresentation();
    }

}