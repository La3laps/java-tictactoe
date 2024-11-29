import controller.TicTacToe;
import controller.player.MenuLogic;

public class Main {
    public static void main(String[] args) throws Exception {
        MenuLogic userChoice = new MenuLogic();
        TicTacToe ticTacToe = new TicTacToe();

        //Menu choice
        int[] choice = userChoice.gameMenu();

        //launches tictactoe game
        ticTacToe.play(choice[0], choice[1]);
    }
}
