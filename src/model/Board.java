package model;

import java.util.ArrayList;

public class Board {
    private ArrayList<Cell> board;

    public Board() {
        this.board = new ArrayList<>();
    }

    public void initializeBoard(int size) {
        for (int i = 0; i < (size * size); i++) {
            this.board.add(new Cell());
        }
    }

    public ArrayList<Cell> getBoard() {
        return this.board;
    }

    public void setBoard(ArrayList<Cell> board) {
        this.board = board;
    }

}