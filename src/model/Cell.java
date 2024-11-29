package model;

public class Cell {
    private String representation = "    ";

    public String getRep() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public boolean isOccupied() {
        return !this.representation.equals("    ");
    }

    public boolean occupy(String rep) {
        if(isOccupied()){
            return false;
        }
        else {
            this.representation = rep;
            return true;
        }
    }

    public void clear() {
        this.representation = "    ";
    }
}