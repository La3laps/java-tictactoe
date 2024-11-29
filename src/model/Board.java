package model;

import java.util.ArrayList;

public class Board {
    private final int size = 3;
    private ArrayList<Cell> board;

    public Board() {
        this.board = new ArrayList<>();
    }

    public void initializeBoard(int size) {
        for (int i = 0; i < (size * size); i++) {
            this.board.add(new Cell());
        }
    }

    public int getSize() {
        return this.size;
    }

    public ArrayList<Cell> getBoard() {
        return this.board;
    }

    public String getRep() {
        /**
         * Print the board of TicTacToe in the console.
         * 
         * @param cells ArrayList to be printed.
         * @param size  size of the array.
         */
        String rep = "";
        int i = 1;
        rep += "\n\t------------------\n";
        for (Cell cell : board) {
            if (i % size == 1) {
                rep += "\t";
            }
            rep += "|" + cell.getRep() + "|";
            if (i % size == 0) {
                rep += "\t\n\t------------------\n";
            }
            i++;
        }
        rep += "\n";
        return rep;
    }

    public String getRepAtCell(int cellNbr) {
        return board.get(cellNbr).getRep();
    }

    public boolean occupiedAt(int cellNbr) {
        return board.get(cellNbr).isOccupied();
    }

    public void occupyAt(int cellNbr, String rep) {
        board.get(cellNbr).occupy(rep);
    }
}