
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TicTacToe {
    private final int size = 9;
    private final ArrayList<Cell> cells = new ArrayList<>();
    private Player player;
    private ArtificialPlayer machinePlayer;
    private final Scanner scan = new Scanner(System.in);

    private void display() {
        printBoard();
    }

    private void printHorizontalLine() {
        System.out.println("\n\t---------------");
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            cells.add(new Cell());
        }
    }

    public void printBoard() {
        printHorizontalLine();
        int i = 1;
        for (Cell cell : cells) {
            if (i % 3 == 1) {
                System.out.print("\t");
            }
            System.out.print("|" + cell.getRepresentation() + "|");
            if (i % 3 == 0) {
                System.out.print("\t");
                printHorizontalLine();
            }
            i++;
        }
        System.out.println();
    }

    private int[] chosePlayers() {
        clearScreen();
        int[] temp = { 0, 0 };
        try {
            System.out.println("Is Player One Human(1) or Computer(2)?");
            temp[0] = Integer.parseInt(scan.nextLine());
            System.out.println("Is Player One Human(1) or Computer(2)?");
            temp[1] = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.err.println(e);
        }
        return temp;
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
            } catch (NumberFormatException e) {
                clearScreen();
                System.out.println(
                        "\u001B[31mPlease enter a number\n" + e.getMessage() + " instead of integer.\u001B[0m");
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
            } catch (NumberFormatException e) {
                clearScreen();
                System.out
                        .println("\u001B[31mPlease enter a number\n" + e.getMessage() + " instead of integer\u001B[0m");
                display();
            }
        }
        return inputArray;
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

        while (cells.get(index).getRepresentation().equals(" X ")
                || cells.get(index).getRepresentation().equals(" O ")) {
            clearScreen();
            System.out.println("\u001B[31mDésolé mais cette case est occupée!\u001B[0m");
            display();
            inputArray = getMoveFromPlayer();
            index = inputArray[0] * 3 + inputArray[1];
        }
        cells.set(index, player);
        turn++;

        return turn;
    }

    public void play() {
        int[] playersComputers = chosePlayers();
        int turn = 0;
        boolean gameOver = false;

        if (playersComputers[0] == 1 && playersComputers[1] == 1) { // player against player
            clearScreen();
            initializeBoard();
            display();
            while (!gameOver) {
                int[] move = getMoveFromPlayer();
                turn = setOwner(move, turn);
                display();
                gameOver = isOver(turn);
                System.out.println(turn);
            }

            printGameOver();
            if (turn != 9) {
                printPlayerWin();
            } else {
                printOutOfMoves();
            }
        } else if (playersComputers[0] == 2 && playersComputers[1] == 2) { // computer against computer
            clearScreen();
            initializeBoard();
            display();
            machinePlayer = new ArtificialPlayer();
            while (!gameOver) {
                clearScreen();
                int[] move = machinePlayer.getMachineMove();
                turn = setMachineMove(move, turn);
                display();
                gameOver = isOver(turn);
                // wait one second
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }

            printGameOver();
            if (turn != 9) {
                printComputerWin();
            } else {
                printOutOfMoves();
            }
        } else {
            clearScreen();
            initializeBoard();
            display();
            machinePlayer = new ArtificialPlayer();
            while (true) {
                // machine turn
                int[] machineMove = machinePlayer.getMachineMove();
                turn = setMachineMove(machineMove, turn);
                gameOver = isOver(turn);
                clearScreen();
                display();
                if (gameOver) {
                    printGameOver();
                    if (turn != 9) {
                        System.out.println("\tTHE WINNER IS \u001B[35m THE COMPUTER\n");
                    } else {
                        printOutOfMoves();
                    }
                    return;
                }

                // player turn
                int[] move = getMoveFromPlayer();
                turn = setOwner(move, turn);
                display();
                gameOver = isOver(turn);
                if (gameOver) {
                    printGameOver();
                    if (turn != 9) {
                        System.out.println("\tTHE WINNER IS \u001B[35m THE PLAYER\n");
                    } else {
                        printOutOfMoves();
                    }
                    return;
                }
            }

        }

    }

    public int setMachineMove(int[] inputArray, int turn) {
        switch (turn % 2) {
            case 0 -> {
                this.machinePlayer = new ArtificialPlayer();
                machinePlayer.setRepresentation(" X ");
            }
            case 1 -> {
                this.machinePlayer = new ArtificialPlayer();
                machinePlayer.setRepresentation(" O ");
            }
            default -> throw new AssertionError();
        }

        int index = inputArray[0] * 3 + inputArray[1];

        while (cells.get(index).getRepresentation().equals(" X ")
                || cells.get(index).getRepresentation().equals(" O ")) {
            inputArray = machinePlayer.getMachineMove();
            index = inputArray[0] * 3 + inputArray[1];
        }
        cells.set(index, machinePlayer);
        turn++;

        return turn;
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
    }

    private void printComputerWin() {
        try {
            System.out.println(
                    "\tTHE WINNER IS \u001B[35m" + machinePlayer.printWinner(machinePlayer.getRepresentation()) + "\n");

        } catch (Exception e) {
        }
    }

    private void printPlayerWin() {
        try {
            System.out.println("\tTHE WINNER IS \u001B[35m" + player.printWinner() + "\n");
        } catch (Exception e) {
        }
    }

    private void printOutOfMoves() {
        System.out.println("\tTHERE ARE NO MOVES LEFT\n\t     NO WINNER...");
    }
}