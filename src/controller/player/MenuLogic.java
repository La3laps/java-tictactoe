package controller.player;

import view.View;

public class MenuLogic {

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
                temp[0] = display.askUserForInteger();

                display.printAskForHumanOrComputer(2);
                temp[1] = display.askUserForInteger();
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

    private boolean checkInput(int input, int input_two) {
        return input > 0 && input < 3 && input_two > 0 && input_two < 3;
    }
}