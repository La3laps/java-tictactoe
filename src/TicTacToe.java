
import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {
    private final int size = 9;
    private final ArrayList<Cell> cells = new ArrayList<>();
    private Player player;
    private final Scanner scan = new Scanner(System.in);

    public void display() {
        clearScreen();
        initializeBoard();
        setOwner(getMoveFromPlayer());
        printBoard();
    }

    private void printHorizontalLine() {
        System.out.println("\n---------------");
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            cells.add(new Cell());
        }
    }

    private int[] getMoveFromPlayer() {
        int[] inputArray = { 0, 0 };
        while (true) {
            try {
                System.out.println("Enter row number (Smaller than three): ");
                int inputOne = Integer.parseInt(scan.nextLine());
                if (inputOne < 3) {
                    inputArray[0] = inputOne;
                    break;
                }
            } catch (Exception e) {
                clearScreen();
                System.out.println("\u001B[31mPlease enter an integer number\u001B[0m\n");
            }
        }
        while (true) {
            try {

                System.out.println("Enter column number (Smaller than three): ");
                int inputTwo = Integer.parseInt(scan.nextLine());
                if (inputTwo < 3) {
                    inputArray[1] = inputTwo;
                    break;
                }
            } catch (Exception e) {
                clearScreen();
                System.out.println("\u001B[31mPlease enter an integer number\u001B[0m\n");
            }
        }
        return inputArray;
    }

    public void printBoard() {
        printHorizontalLine();
        int i = 1;
        for (Cell cell : cells) {
            System.out.print("|" + cell.getRepresentation() + "|");
            if (i % 3 == 0) {
                printHorizontalLine();
            }
            i++;
        }
        System.out.println();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void setOwner(int[] inputArray) {
        this.player = new Player();
        int index = inputArray[0] * 3 + inputArray[1];

        if (cells.get(index).getRepresentation().equals(" X ") || cells.get(index).getRepresentation().equals(" O ")) {
            clearScreen();
            System.out.println("\u001B[31mDésolé mais cette case est occupée!\u001B[0m\n");
            getMoveFromPlayer();
        } else {
            cells.set(index, player);
        }
    }
}