package Model;

import java.util.ArrayList;

public class Player {
    private String representation = "    ";

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    /**
     * Sets 9 cells of free space => (" ").
     */
    public ArrayList<Player> initializeBoard(int size, ArrayList<Player> cells) {
        for (int i = 0; i < (size * size); i++) {
            cells.add(new Player());
        }
        return cells;
    }

    @Override
    public String toString() {
        return "Cell";
    }
}