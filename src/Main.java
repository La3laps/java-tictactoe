import controller.TicTacToe;
import controller.player.PlayerInteraction;

public class Main {
    public static void main(String[] args) throws Exception {
        PlayerInteraction userChoice = new PlayerInteraction();
        TicTacToe ticTacToe = new TicTacToe();

        //Menu choice
        int[] choice = userChoice.gameMenu();

        //launches tictactoe game
        ticTacToe.play(choice[0], choice[1]);
    }
}
