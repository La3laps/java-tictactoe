public class Player extends Cell {
    private String representation = "   ";

    @Override
    public void setRepresentation(String rep) {
        this.representation = rep;
    }

    @Override
    public String getRepresentation() {
        return representation;
    }

    public String printWinner() {
        String player = "";
        if (representation.equals(" X ")) {
            player = "Player One";
        } else if (representation.equals(" O ")) {
            player = "Player Two";
        }
        return player;
    }
}