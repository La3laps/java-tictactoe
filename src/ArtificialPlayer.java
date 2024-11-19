import java.util.Random;

public class ArtificialPlayer extends Cell {

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
     * Function wich initializes two random numbers between 0 & 2
     * to simulate machine input.
     * 
     * @return machine move array with rows and columns
     */
    public int[] getMachineMove() {
        int[] temp = { 0, 0 };

        Random rand = new Random();

        temp[0] = rand.nextInt(3);
        temp[1] = rand.nextInt(3);

        return temp;
    }

    /**
     * Function to see which computer has won.
     * 
     * @param representation Either an X or and O
     * @return winner string which is either "Computer One" or "Computer Two"
     */
    public String getWinner(String representation) {
        String winner = "";
        if (representation.equals(" X ")) {
            winner = "Computer One";
        } else if (representation.equals(" O ")) {
            winner = "Computer Two";
        }
        return winner;
    }
}