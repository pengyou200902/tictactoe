import java.util.Scanner;

/**
 * @Author Friende.Peng_You
 * @Date 2021-09-13 11:36
 */

public class TicTacToe {
    final private Board board;

    final private Player[] players;

    final private Scanner scanner;

    public TicTacToe(Board board, Player[] players) {
        this.players = players;
        this.board = board;
        this.scanner = new Scanner(System.in);
    }

    public static void welcome() {
        String welcomeMsg = "\n==========================================================================\n"
                          + "Welcome to JavaTicTacToe!\n"
                          + "This program is to design and implement the game of Tic Tac Toe (in Java).\n"
                          + "The game should allow for two players to play each other.\n"
                          + "You can choose which of the two players begins. The players should continue\n"
                          + "to take turns until there is a winner or until there is a stalemate.\n"
                          + "==========================================================================\n";
        System.out.println(welcomeMsg);
    }

    public void init() {
        int choice;

        System.out.println("Current players:");
        for (int i = 0; i < this.players.length; i++) {
            System.out.printf("\tPlayer Number %d. Name: %s, Chess: %c\n", i, this.players[i].getName(), this.players[i].getChess());
        }

        do {
            System.out.print("Choose one player to start first (Enter the index) >>> ");
            choice = this.scanner.nextInt();
            if (choice < 0 || choice > 1) {
                System.out.printf("There are only %d players. Please input again.", this.players.length);
            }
        }while(choice < 0 || choice > 1);

        if (choice > 0) {
            Player chosen = this.players[choice];
            this.players[choice] = this.players[0];
            this.players[0] = chosen;
        }
    }
    
    public int nextMove(Board board, Player player) {
        int x;
        int y;
        int result;

        do {
            System.out.printf("Player: %s Chess: %c now enters the position(eg. 2 3) >>> ", player.getName(), player.getChess());
            x = scanner.nextInt();
            y = scanner.nextInt();
            result = player.move(board, x, y);
            if (result == 0) {
                System.out.println("The position has been used. Please enter another one.\n");
            }
            else if (result == -1) {
                System.out.println("The position is out of the board. Please enter another one.\n");
            }
        }while (result == 0 || result == -1);

        return result;
    }

    public void run() {
        int i = 0;
        boolean isFull = false;


        init();
        System.out.println(this.board.toString());
        do {
            int result = nextMove(this.board, this.players[i]);
            System.out.println(this.board.toString());

            i = (i + 1) % 2;

            if (this.board.getCountSteps() >= 5) {
                int boardStatus = checkWin();
                this.board.setStatus(boardStatus);
                if (boardStatus != -1) {
                    // somebody wins
                    break;
                }
            }
            isFull = this.board.isFull();
        }while (!isFull);
        if (isFull && this.board.getStatus() == -1) {
            System.out.println("\n===============Game Draw===============");
        }
        else if (this.board.getStatus() == 0) {
            System.out.printf("\n===============Player: %s ChessType: %c Wins!===============",
                    this.players[0].getName(), this.players[0].getChess());
        }
        else if (this.board.getStatus() == 1) {
            System.out.printf("\n===============Player: %s ChessType: %c Wins!===============",
                    this.players[1].getName(), this.players[1].getChess());
        }
    }

    public int checkWin() {
        // horizontal
        for (int i = 0; i < this.board.getWidth(); i++) {
            if (this.board.getGrid()[i][0] != 'N' &&
                    this.board.getGrid()[i][0] == this.board.getGrid()[i][1] &&
                    this.board.getGrid()[i][0] == this.board.getGrid()[i][2]) {
                if (this.board.getGrid()[i][0] == this.players[0].getChess()) {
                    return 0;
                } else if (this.board.getGrid()[i][0] == this.players[1].getChess()) {
                    return 1;
                }
            }
        }
        // vertical
        for (int i = 0; i < this.board.getWidth(); i++) {
            if (this.board.getGrid()[0][i] != 'N' &&
                    this.board.getGrid()[0][i] == this.board.getGrid()[1][i] &&
                    this.board.getGrid()[0][i] == this.board.getGrid()[2][i]) {
                if (this.board.getGrid()[0][i] == this.players[0].getChess()) {
                    return 0;
                } else if (this.board.getGrid()[0][i] == this.players[1].getChess()) {
                    return 1;
                }
            }
        }
        // main
        if (this.board.getGrid()[0][0] != 'N' &&
                this.board.getGrid()[0][0] == this.board.getGrid()[1][1] &&
                this.board.getGrid()[1][1] == this.board.getGrid()[2][2]) {
            if (this.board.getGrid()[0][0] == this.players[0].getChess()) {
                return 0;
            } else if (this.board.getGrid()[0][0] == this.players[1].getChess()) {
                return 1;
            }
        }
        // minor
        if (this.board.getGrid()[0][2] != 'N' &&
                this.board.getGrid()[0][2] == this.board.getGrid()[1][1] &&
                this.board.getGrid()[1][1] == this.board.getGrid()[2][0]) {
            if (this.board.getGrid()[0][2] == this.players[0].getChess()) {
                return 0;
            } else if (this.board.getGrid()[0][2] == this.players[1].getChess()) {
                return 1;
            }
        }
        // no result
        return -1;
    }


}
