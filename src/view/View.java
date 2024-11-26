package view;

import java.util.ArrayList;
import model.Cell;
import model.player.ArtificialPlayer;
import model.player.HumanPlayer;

public class View {

    /**
     * Clears the screen on Linux machine
     */
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Print the board of TicTacToe in the console.
     * 
     * @param cells ArrayList to be printed.
     * @param size  size of the array.
     */
    public void displayBoard(ArrayList<Cell> cells, int size) {
        printHorizontalLine();
        int i = 1;
        for (Cell cell : cells) {
            if (i % size == 1) {
                System.out.print("\t");
            }
            System.out.print("|" + cell.getRepresentation() + "|");
            if (i % size == 0) {
                System.out.print("\t");
                printHorizontalLine();
            }
            i++;
        }
        System.out.println();
    }

    /**
     * Asks user for who player one and player two will be (human/computer).
     * 
     * @param num used to switch between first and second question.
     */
    public void printAskForHumanOrComputer(int num) {
        switch (num) {
            case 1 -> System.out.println("\033[0;35mIs Player One Human(1) or Computer(2)?\033[0m");
            case 2 -> System.out.println("\033[0;35mIs Player Two Human(1) or Computer(2)?\033[0m");
            default -> {
            }
        }
    }

    public void askUserForCorrectPlayers() {
        System.out.println("\033[0;31mPlease enter valid numbers (1) or (2) for machine or player.\n\033[0m");
    }

    public void printHorizontalLine() {
        System.out.println("\n\t------------------");
    }

    /**
     * Asks user to enter a number of rows
     */
    public void printUserEnterRowNbr() {
        System.out.println("\u001B[33mEnter row number (0 - 1 - 2):\u001B[0m ");
    }

    /**
     * Asks user to enter a number of columns
     */
    public void printUserEnterColNbr() {
        System.out.println("\033[0;32mEnter column number (0 - 1 - 2):\u001B[0m ");
    }

    /**
     * Prints that the cell is occupied and player cannot put a representation in
     * it.
     */
    public void printOccupiedCell() {
        System.out.println("\u001B[31mDésolé mais cette case est occupée!\u001B[0m");
    }

    /**
     * Prints out a custom error message.
     * 
     * @param e exception
     */
    public void printErrorInsertInteger(Exception e) {
        System.out
                .println("\u001B[31mPlease enter a number\n" + e.getMessage() + " instead of integer\u001B[0m");
    }

    /**
     * Prints an ascii representation of "Game Over".
     */
    public void printGameOver() {
        clearScreen();
        System.out.println("""
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⡀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣤⠀⠀⠀⢀⣴⣿⡶⠀⣾⣿⣿⡿⠟⠛⠁
                ⠀⠀⠀⠀⠀⠀⣀⣀⣄⣀⠀⠀⠀⠀⣶⣶⣦⠀⠀⠀⠀⣼⣿⣿⡇⠀⣠⣿⣿⣿⠇⣸⣿⣿⣧⣤⠀⠀⠀
                ⠀⠀⢀⣴⣾⣿⡿⠿⠿⠿⠇⠀⠀⣸⣿⣿⣿⡆⠀⠀⢰⣿⣿⣿⣷⣼⣿⣿⣿⡿⢀⣿⣿⡿⠟⠛⠁⠀⠀
                ⠀⣴⣿⡿⠋⠁⠀⠀⠀⠀⠀⠀⢠⣿⣿⣹⣿⣿⣿⣿⣿⣿⡏⢻⣿⣿⢿⣿⣿⠃⣼⣿⣯⣤⣴⣶⣿⡤⠀
                ⣼⣿⠏⠀⣀⣠⣤⣶⣾⣷⠄⣰⣿⣿⡿⠿⠻⣿⣯⣸⣿⡿⠀⠀⠀⠁⣾⣿⡏⢠⣿⣿⠿⠛⠋⠉⠀⠀⠀
                ⣿⣿⠲⢿⣿⣿⣿⣿⡿⠋⢰⣿⣿⠋⠀⠀⠀⢻⣿⣿⣿⠇⠀⠀⠀⠀⠙⠛⠀⠀⠉⠁⠀⠀⠀⠀⠀⠀⠀
                ⠹⢿⣷⣶⣿⣿⠿⠋⠀⠀⠈⠙⠃⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠈⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣤⣴⣶⣦⣤⡀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀⠀⠀⣠⡇⢰⣶⣶⣾⡿⠷⣿⣿⣿⡟⠛⣉⣿⣿⣿⠆
                ⠀⠀⠀⠀⠀⠀⢀⣤⣶⣿⣿⡎⣿⣿⣦⠀⠀⠀⢀⣤⣾⠟⢀⣿⣿⡟⣁⠀⠀⣸⣿⣿⣤⣾⣿⡿⠛⠁⠀
                ⠀⠀⠀⠀⣠⣾⣿⡿⠛⠉⢿⣦⠘⣿⣿⡆⠀⢠⣾⣿⠋⠀⣼⣿⣿⣿⠿⠷⢠⣿⣿⣿⠿⢻⣿⣧⠀⠀⠀
                ⠀⠀⠀⣴⣿⣿⠋⠀⠀⠀⢸⣿⣇⢹⣿⣷⣰⣿⣿⠃⠀⢠⣿⣿⢃⣀⣤⣤⣾⣿⡟⠀⠀⠀⢻⣿⣆⠀⠀
                ⠀⠀⠀⣿⣿⡇⠀⠀⢀⣴⣿⣿⡟⠀⣿⣿⣿⣿⠃⠀⠀⣾⣿⣿⡿⠿⠛⢛⣿⡟⠀⠀⠀⠀⠀⠻⠿⠀⠀
                ⠀⠀⠀⠹⣿⣿⣶⣾⣿⣿⣿⠟⠁⠀⠸⢿⣿⠇⠀⠀⠀⠛⠛⠁⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠈⠙⠛⠛⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                """);
    }

    /**
     * Prints which computer has won.
     * 
     * @param machinePlayer current artificial intelligence that played in
     *                      TicTacToe.
     */
    public void printComputersWin(ArtificialPlayer machinePlayer) {
        try {
            System.out.println(
                    "\tTHE WINNER IS \u001B[35m" + machinePlayer.getWinner(machinePlayer.getRepresentation()) + "\n");

        } catch (Exception e) {
        }
    }

    /**
     * Prints which player has won.
     * 
     * @param player current player that played in TicTacToe.
     */
    public void printPlayersWin(HumanPlayer player) {
        try {
            System.out.println("\tTHE WINNER IS \u001B[35m" + player.getWinner() + "\n");
        } catch (Exception e) {
        }
    }

    /**
     * Prints that there are no more possible moves.
     */
    public void printOutOfMoves() {
        System.out.println("\tTHERE ARE NO MOVES LEFT\n\t     NO WINNER...");
    }

    /**
     * Prints player as winner.
     */
    public void printPlayerWin() {
        System.out.println("\tTHE WINNER IS \u001B[35m THE PLAYER\n");
    }

    /**
     * Prints computer as winner
     */
    public void printComputerWin() {
        System.out.println("\tTHE WINNER IS \u001B[35m THE COMPUTER\n");
    }
}