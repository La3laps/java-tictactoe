package model.player;

public class Player {
    private String representation = "    ";

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return "Player";
    }
}