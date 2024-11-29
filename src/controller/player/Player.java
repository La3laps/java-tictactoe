package controller.player;

import java.util.Random;
import model.Board;
import view.View;

public class Player {
    private String representation = "    ";
    private String name = "";
    private String playerNumber = "";

    public Player(String name, String playerNumber) {
        this.name = name;
        this.playerNumber = playerNumber;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public int[] getMoveFromPlayer(Board board) {
        int[] move = { -1, -1 };

        if (this.name.equals("human")) {
            move = getHumanPlayerMove(board);
        } else if (this.name.equals("computer")) {
            move = getComputerPlayerMove();
        }
        return move;
    }

    /**
     * This function makes the logic of taking user input and making it two values
     * in
     * an array that symbolize rows and columns min val(0,0) max val(2,2).
     * 
     * @param board the board to show it to the user
     * @return an array of two integers (mandatory) that are smaller than 2.
     */
    private int[] getHumanPlayerMove(Board board) {
        View display = new View();
        PlayerInteraction interact = new PlayerInteraction();

        int[] inputArray = { 0, 0 };
        while (true) {
            try {
                display.printUserEnterRowNbr();
                int inputOne = interact.askUserForInteger();
                display.clearScreen();
                display.printBoardRep(board.getRep());

                if (inputOne < board.getSize()) {
                    inputArray[0] = inputOne;
                    break;
                }
            } catch (NumberFormatException e) {
                display.clearScreen();
                display.printErrorInsertInteger(e);
                display.printBoardRep(board.getRep());
            }
        }
        while (true) {
            try {
                display.printUserEnterColNbr();
                int inputTwo = interact.askUserForInteger();
                display.clearScreen();
                display.printBoardRep(board.getRep());
                if (inputTwo < board.getSize()) {
                    inputArray[1] = inputTwo;
                    break;
                }
            } catch (NumberFormatException e) {
                display.clearScreen();
                display.printErrorInsertInteger(e);
                display.printBoardRep(board.getRep());
            }
        }
        return inputArray;
    }

    /**
     * Function wich initializes two random numbers between 0 & 2
     * to simulate machine input.
     * 
     * @return machine move array with rows and columns
     */
    private int[] getComputerPlayerMove() {
        int[] temp = { 0, 0 };

        Random rand = new Random();

        temp[0] = rand.nextInt(3);
        temp[1] = rand.nextInt(3);

        return temp;
    }

    @Override
    public String toString() {
        String toStringString = "THE WINNER IS : " + this.name + " " + this.playerNumber + "\n";
        return toStringString;
    }
}