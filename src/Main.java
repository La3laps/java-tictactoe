import controller.TicTacToe;
import router.UserInteraction;

public class Main {
    public static void main(String[] args) throws Exception {
        UserInteraction userChoice = new UserInteraction();
        TicTacToe ticTacToe = new TicTacToe();
        
        int[] choice = userChoice.gameMenu();
        ticTacToe.play(choice[0], choice[1]);
    }
}
