package model.player;

public class HumanPlayer extends Player {
    private String representation = "   ";

    @Override
    public void setRepresentation(String rep) {
        this.representation = rep;
    }

    @Override
    public String getRepresentation() {
        return representation;
    }

    /**
     * Function to see which player has won.
     * 
     * @return winner string which is either "Player One" or "Player Two"
     */
    public String getWinner() {
        String player = "";
        if (representation.equals(" ❌ ")) {
            player = "Player One";
        } else if (representation.equals(" ⭕ ")) {
            player = "Player Two";
        }
        return player;
    }
}