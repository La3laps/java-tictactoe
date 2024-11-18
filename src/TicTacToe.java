
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
            case 0 -> {
                this.player = new Player();
                player.setRepresentation(" X ");
            }
            case 1 -> {
                this.player = new Player();
                player.setRepresentation(" O ");
            }
            default -> throw new AssertionError();
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
        boolean gameOver = false;
        clearScreen();
        initializeBoard();
        display();
        while (!gameOver) {
            int[] move = getMoveFromPlayer();
            turn = setOwner(move, turn);
            display();
            gameOver = isOver(turn);
        }
        printGameOver();
    }

    private boolean isOver(int turn) {
        boolean over = false;
        if (turn > 8) {
            over = true;
        }

        for (int i = 0; i < 3; i++) {
            // Check rows
            if (!getCell(i * 3).equals("   ") && getCell(i * 3).equals(getCell(i * 3 + 1))
                    && getCell(i * 3).equals(getCell(i * 3 + 2))) {
                return true;
            }

            // Check columns
            if (!getCell(i).equals("   ") && getCell(i).equals(getCell(i + 3)) && getCell(i).equals(getCell(i + 6))) {
                return true;
            }
        }

        // Check diag
        if (!getCell(0).equals("   ") && getCell(0).equals(getCell(4)) && getCell(0).equals(getCell(8))) {
            return true;
        }

        if (!getCell(2).equals("   ") && getCell(2).equals(getCell(4)) && getCell(2).equals(getCell(6))) {
            return true;
        }

        return over;
    }

    private String getCell(int i) {
        return cells.get(i).getRepresentation();
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
        System.out.println("\tTHE WINNER IS \u001B[35m" + player.toString() + "\n");
    }
}