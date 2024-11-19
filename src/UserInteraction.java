import java.util.ArrayList;
import java.util.Scanner;

public class UserInteraction {
    private final Scanner scan = new Scanner(System.in);

    public int[] chosePlayers() {
        View display = new View();
        display.clearScreen();
        int[] temp = { 0, 0 };
        try {
            display.printAskForHumanOrComputer(1);
            temp[0] = askUserForInteger();

            display.printAskForHumanOrComputer(2);
            temp[1] = askUserForInteger();
        } catch (NumberFormatException e) {
            System.err.println(e);
        }
        return temp;
    }

    public int askUserForInteger() {
        return Integer.parseInt(scan.nextLine());
    }

    public int[] getMoveFromPlayer(ArrayList<Cell> cells, int size) {
        View display = new View();
        int[] inputArray = { 0, 0 };
        while (true) {
            try {
                display.printUserEnterColNbr();
                int inputOne = askUserForInteger();
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
}