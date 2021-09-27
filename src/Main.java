import java.util.Scanner;

/**
 * @Author Friende.Peng_You
 * @Date 2021-09-13 13:18
 */

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe.welcome();

        int begin = 0;

        do {
            System.out.print("\nWant to start a new game ? (1 for yes, others for no) >>> ");
            begin = scanner.nextInt();
            System.out.println();
            if (begin != 1) {
                break;
            }
            else {
                Player[] players = Player.createPlayers();
                Board board = new Board();
                TicTacToe game = new TicTacToe(board, players);
                game.run();
            }
        }while (true);
    }
}
