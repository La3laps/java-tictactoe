
import java.util.ArrayList;

public class TicTacToe {
    private final int size = 9;
    private final ArrayList<Cell> cells = new ArrayList<>();

    public void display(){
        initializeBoard();

        printHorizontalLine();
        int i = 1;
        for (Cell cell : cells) {
            System.out.print("|" + cell.getRepresentation() + "|");
            if (i % 3 == 0){
                printHorizontalLine();
            }
            i++;
        }
        
    }

    private void printHorizontalLine(){
        System.out.println("\n---------------");
    }

    private void initializeBoard(){
        for(int i = 0; i < size; i++){
            cells.add(new Cell());
        }
    }
}