
import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {
    private final int size = 9;
    private final ArrayList<Cell> cells = new ArrayList<>();
    private Player player;
    private final Scanner scan = new Scanner(System.in);

    private void display() {
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
                System.out.println("\u001B[33mEnter row number (Smaller than three):\u001B[0m ");
                int inputOne = Integer.parseInt(scan.nextLine());
                if (inputOne < 3) {
                    inputArray[0] = inputOne;
                    break;
                }
            } catch (Exception e) {
                clearScreen();
                System.out.println("\u001B[31mPlease enter an integer number\u001B[0m\n");
                display();
            }
        }
        while (true) {
            try {

                System.out.println("\u001B[33mEnter column number (Smaller than three):\u001B[0m ");
                int inputTwo = Integer.parseInt(scan.nextLine());
                clearScreen();
                if (inputTwo < 3) {
                    inputArray[1] = inputTwo;
                    break;
                }
            } catch (Exception e) {
                clearScreen();
                System.out.println("\u001B[31mPlease enter an integer number\u001B[0m\n");
                display();
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

    private int setOwner(int[] inputArray, int turn) {
        switch (turn % 2) {
            case 0:
                this.player = new Player();
                player.setRepresentation(" X ");
                break;
            case 1:
                this.player = new Player();
                player.setRepresentation(" O ");
                break;
            default:
                throw new AssertionError();
        }

        int index = inputArray[0] * 3 + inputArray[1];

        if (cells.get(index).getRepresentation().equals(" X ") || cells.get(index).getRepresentation().equals(" O ")) {
            clearScreen();
            System.out.println("\u001B[31mDésolé mais cette case est occupée!\u001B[0m\n");
        } else {
            cells.set(index, player);
            turn++;
        }

        return turn;
    }

    public void play() {
        int turn = 0;
        // boolean isGameOver = false;
        clearScreen();
        initializeBoard();
        display();
        while (turn < 9) {
            int[] move = getMoveFromPlayer();
            turn = setOwner(move, turn);
            display();
        }
        printGameOver();
    }

    private void printGameOver() {
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
}