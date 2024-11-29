package controller.player;

import java.util.Scanner;
import view.View;

public class PlayerInteraction {
    private final Scanner scan = new Scanner(System.in);

    /**
     * Takes user input to get two integers and catch and exception if they are not.
     * 
     * @return temp which is a temporary array to store two integers representing
     *         rows and columns.
     */
    public int[] gameMenu() {
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

    private boolean checkInput(int input, int input_two) {
        return input > 0 && input < 3 && input_two > 0 && input_two < 3;
    }
}