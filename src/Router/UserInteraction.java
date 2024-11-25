package Router;

import Model.Player;
import View.View;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInteraction {
    private final Scanner scan = new Scanner(System.in);

    /**
     * Takes user input to get two integers and catch and exception if they are not.
     * 
     * @return temp which is a temporary array to store two integers representing
     *         rows and columns.
     */
    public int[] chosePlayers() {
        View display = new View();
        display.clearScreen();
        int[] temp = { 0, 0 };
        while (true) {
            try {
                display.printAskForHumanOrComputer(1);
                temp[0] = askUserForInteger();

                display.printAskForHumanOrComputer(2);
                temp[1] = askUserForInteger();
                if (checkInput(temp[0], temp[1])) {
                    break;
                }
                display.clearScreen();
                display.askUserForCorrectPlayers();
            } catch (NumberFormatException e) {
                display.clearScreen();
                display.askUserForCorrectPlayers();
            }
        }
        return temp;
    }

    /**
     * @return a single integer from user input.
     */
    public int askUserForInteger() {
        return Integer.parseInt(scan.nextLine());
    }

    /**
     * This function makes the logic of taking user input and making it two values
     * in
     * an array that symbolize rows and columns min val(0,0) max val(2,2).
     * 
     * @param cells the array of the TicTacToe passed to this function.
     * @param size  the size of the array.
     * @return an array of two integers (mandatory) that are smaller than 2.
     */
    public int[] getMoveFromPlayer(ArrayList<Player> cells, int size) {
        View display = new View();
        int[] inputArray = { 0, 0 };
        while (true) {
            try {
                display.printUserEnterRowNbr();
                int inputOne = askUserForInteger();
                display.clearScreen();
                display.displayBoard(cells, size);
                if (inputOne < size) {
                    inputArray[0] = inputOne;
                    break;
                }
            } catch (NumberFormatException e) {
                display.clearScreen();
                display.printErrorInsertInteger(e);
                display.displayBoard(cells, size);
            }
        }
        while (true) {
            try {
                display.printUserEnterColNbr();
                int inputTwo = askUserForInteger();
                display.clearScreen();
                display.displayBoard(cells, size);
                if (inputTwo < size) {
                    inputArray[1] = inputTwo;
                    break;
                }
            } catch (NumberFormatException e) {
                display.clearScreen();
                display.printErrorInsertInteger(e);
                display.displayBoard(cells, size);
            }
        }
        return inputArray;
    }

    private boolean checkInput(int input, int input_two) {
        return input > 0 && input < 3 && input_two > 0 && input_two < 3;
    }
}