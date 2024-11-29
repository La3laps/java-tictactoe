package controller;

import controller.player.Player;
import java.util.concurrent.TimeUnit;
import model.Board;
import view.View;

public class TicTacToe {

    private final Board board = new Board();

    /**
     * Main logic of the game.
     * Three modes, combinations of players or computers.
     */

    public void play(int choice_one, int choice_two) {
        View display = new View();

        Player player_one = choosePlayerOne(choice_one);
        Player player_two = choosePlayerTwo(choice_two);

        board.initializeBoard(board.getSize());

        display.clearScreen();
        display.printBoardRep(board.getRep());

        int turn = 0;

        while (!isOver()) {
            // player_one
            int[] move_one = player_one.getMoveFromPlayer(board);
            playerPlaysMove(move_one, turn, player_one);
            turn++;

            display.printBoardRep(board.getRep());
            waitASec();

            // player_two
            if (!isOver()) {
                int[] move_two = player_two.getMoveFromPlayer(board);
                playerPlaysMove(move_two, turn, player_two);
                turn++;

                display.printBoardRep(board.getRep());
                waitASec();
            }
        }
        getGameOver(turn, player_one, player_two);
    }

    private void playerPlaysMove(int[] inputArray, int actual_turn, Player player) {

        String current_turn_rep = getCurrentRepDependingOnTurn(actual_turn);

        int index = inputArray[0] * board.getSize() + inputArray[1];

        while (board.occupiedAt(index)) {
            inputArray = player.getMoveFromPlayer(board);
            index = inputArray[0] * board.getSize() + inputArray[1];
        }

        board.occupyAt(index, current_turn_rep);
    }

    private String getCurrentRepDependingOnTurn(int turn) {
        String current_turn_rep = "    ";
        switch (turn % 2) {
            case 0 -> {
                current_turn_rep = " ❌ ";
            }
            case 1 -> {
                current_turn_rep = " ⭕ ";
            }
            default -> {

                throw new AssertionError();
            }
        }
        return current_turn_rep;
    }

    private Player choosePlayerOne(int choice_one) {
        Player player_one = new Player("toBeDecided", "n° 0");
        if (choice_one == 1) {
            player_one = new Player("human", "n° 1");
        } else if (choice_one == 2) {
            player_one = new Player("computer", "n° 1");
        }
        return player_one;
    }

    private Player choosePlayerTwo(int choice_two) {
        Player player_two = new Player("toBeDecided", "n° 0");
        if (choice_two == 1) {
            player_two = new Player("human", "n° 2");
        } else if (choice_two == 2) {
            player_two = new Player("computer", "n° 2");
        }
        return player_two;
    }

    private void getGameOver(int turn, Player player_one, Player player_two) {
        View display = new View();

        display.printGameOver();
        display.printBoardRep(board.getRep());

        if (board.isFull()) {
            display.printOutOfMoves();
        } else if (turn % 2 == 1) {
            display.print(player_one.toString() + getCurrentRepDependingOnTurn(turn + 1) + "\n");
        } else if (turn % 2 == 0) {
            display.print(player_two.toString() + getCurrentRepDependingOnTurn(turn + 1) + "\n");
        }
    }

    /**
     * Checks if a player/computer has won the game.
     * 
     * @return true if conditions are met or false if the game should not be over.
     */
    private boolean isOver() {
        boolean over = false;

        for (int i = 0; i < board.getSize(); i++) {
            // Check rows
            if (!checkCell(i * board.getSize()).equals("    ")
                    && checkCell(i * board.getSize()).equals(checkCell(i * 3 + 1))
                    && checkCell(i * board.getSize()).equals(checkCell(i * board.getSize() + 2))) {
                over = true;
                return over;
            }

            // Check columns
            if (!checkCell(i).equals("    ") && checkCell(i).equals(checkCell(i + 3))
                    && checkCell(i).equals(checkCell(i + 6))) {
                over = true;
                return over;
            }
        }

        // Check diag
        if (!checkCell(0).equals("    ") && checkCell(0).equals(checkCell(4)) && checkCell(0).equals(checkCell(8))) {
            over = true;
            return over;
        }

        if (!checkCell(2).equals("    ") && checkCell(2).equals(checkCell(4)) && checkCell(2).equals(checkCell(6))) {
            over = true;
            return over;
        }

        if (board.isFull()) {
            over = true;
        }

        return over;
    }

    private String checkCell(int i) {
        return board.getRepAtCell(i);
    }

    private void waitASec() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

}