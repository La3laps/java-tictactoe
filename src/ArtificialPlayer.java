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

    public int[] getMachineMove() {
        int[] temp = { 0, 0 };

        Random rand = new Random();

        temp[0] = rand.nextInt(3);
        temp[1] = rand.nextInt(3);

        return temp;
    }

    public String printWinner(String representation) {
        String winner = "";
        if (representation.equals(" X ")) {
            winner = "Computer One";
        } else if (representation.equals(" O ")) {
            winner = "Computer Two";
        }
        return winner;
    }
}