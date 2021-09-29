/**
 * @Author Friende.Peng_You
 * @Date 2021-09-13 11:36
 */

package games.tictactoe;

import games.entities.BaseGame;
import games.entities.Board;
import games.interfaces.GameInterface;
import games.interfaces.Winnable;
import java.util.Scanner;

public class TicTacToe extends BaseGame implements GameInterface, Winnable {

    final private Board board;

    final private TTTPlayer[] tttPlayers;

    final private Scanner scanner;

//    public TicTacToe() {}

    public TicTacToe(Board board, TTTPlayer[] tttPlayers) {
        this.tttPlayers = tttPlayers;
        this.board = board;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void welcome() {
        String welcomeMsg = "\n==========================================================================\n"
                          + "Welcome to JavaTicTacToe!\n"
                          + "This program is to design and implement the game of Tic Tac Toe (in Java).\n"
                          + "The game should allow for two players to play each other.\n"
                          + "You can choose which of the two players begins. The players should continue\n"
                          + "to take turns until there is a winner or until there is a stalemate.\n"
                          + "==========================================================================\n";
        System.out.println(welcomeMsg);
    }

    @Override
    public void init() {
        int choice;

        System.out.println("Current players:");
        for (int i = 0; i < this.tttPlayers.length; i++) {
            System.out.printf("\tPlayer Number %d. Name: %s, Chess: %c\n",
                    i, this.tttPlayers[i].getName(), this.tttPlayers[i].getMark()[0].getMarkType());
        }

        do {
            System.out.print("Choose one player to start first (Enter the index) >>> ");
            choice = this.scanner.nextInt();
            if (choice < 0 || choice > 1) {
                System.out.printf("There are only %d players. Please input again.", this.tttPlayers.length);
            }
        }while(choice < 0 || choice > 1);

        if (choice > 0) {
            TTTPlayer chosen = this.tttPlayers[choice];
            this.tttPlayers[choice] = this.tttPlayers[0];
            this.tttPlayers[0] = chosen;
        }
    }

    public int nextMove(Board board, TTTPlayer tttPlayer) {
        int x;
        int y;
        int result;

        do {
            System.out.printf("Player: %s Chess: %c now enters the position(eg. 1 2) >>> ",
                    tttPlayer.getName(), tttPlayer.getMark()[0].getMarkType());
            x = scanner.nextInt();
            y = scanner.nextInt();
            result = tttPlayer.move(board, tttPlayer.getMark()[0], x, y);
            if (result == 0) {
                System.out.println("The position has been used. Please enter another one.\n");
            }
            else if (result == -1) {
                System.out.println("The position is out of the board. Please enter another one.\n");
            }
        }while (result == 0 || result == -1);

        return result;
    }

    @Override
    public void run() {
        int i = 0;
        boolean isFull = false;


        init();
        System.out.println(this.board.toString());
        do {
            int result = nextMove(this.board, this.tttPlayers[i]);
            System.out.println(this.board);

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
            System.out.println("\n===============entities.Game Draw===============");
        }
        else if (this.board.getStatus() == 0) {
            System.out.printf("\n===============Player: %s ChessType: %c Wins!===============",
                    this.tttPlayers[0].getName(), this.tttPlayers[0].getMark()[0].getMarkType());
        }
        else if (this.board.getStatus() == 1) {
            System.out.printf("\n===============Player: %s ChessType: %c Wins!===============",
                    this.tttPlayers[1].getName(), this.tttPlayers[1].getMark()[0].getMarkType());
        }
    }

    @Override
    public int checkWin() {
        // horizontal
        for (int i = 0; i < this.board.getWidth(); i++) {
            for (int j = 0; j < this.board.getLength() - 2; j++) {
                if (this.board.getGrid()[i][j].getMarkType() != 'N' &&
                        this.board.getGrid()[i][j].getMarkType() == this.board.getGrid()[i][j + 1].getMarkType() &&
                        this.board.getGrid()[i][j].getMarkType() == this.board.getGrid()[i][j + 2].getMarkType()) {
                    if (this.board.getGrid()[i][j].getMarkType() == this.tttPlayers[0].getMark()[0].getMarkType()) {
                        return 0;
                    } else if (this.board.getGrid()[i][j].getMarkType() == this.tttPlayers[1].getMark()[0].getMarkType()) {
                        return 1;
                    }
                }
            }
        }
        // vertical
        for (int j = 0; j < this.board.getLength(); j++) {
            for (int i = 0; i < this.board.getWidth() - 2; i++) {
                if (this.board.getGrid()[i][j].getMarkType() != 'N' &&
                        this.board.getGrid()[i][j].getMarkType() == this.board.getGrid()[i + 1][j].getMarkType() &&
                        this.board.getGrid()[i][j].getMarkType() == this.board.getGrid()[i + 2][j].getMarkType()) {
                    if (this.board.getGrid()[i][j].getMarkType() == this.tttPlayers[0].getMark()[0].getMarkType()) {
                        return 0;
                    } else if (this.board.getGrid()[i][j].getMarkType() == this.tttPlayers[1].getMark()[0].getMarkType()) {
                        return 1;
                    }
                }
            }

        }

        // main diagonal
        for (int i = 0; i < this.board.getWidth() - 2; i++) {
            for (int j = 0; j < this.board.getLength() - 2; j++) {
                if (this.board.getGrid()[i][j].getMarkType() != 'N' &&
                        this.board.getGrid()[i][j].getMarkType() == this.board.getGrid()[i + 1][j + 1].getMarkType() &&
                        this.board.getGrid()[i][j].getMarkType() == this.board.getGrid()[i + 2][j + 2].getMarkType()) {
                    if (this.board.getGrid()[i][j].getMarkType() == this.tttPlayers[0].getMark()[0].getMarkType()) {
                        return 0;
                    } else if (this.board.getGrid()[i][j].getMarkType() == this.tttPlayers[1].getMark()[0].getMarkType()) {
                        return 1;
                    }
                }
            }

        }

        // minor diagonal
        for (int i = 0; i < this.board.getWidth() - 2; i++) {
            for (int j = this.board.getLength() - 1; j > 1; j--) {
                if (this.board.getGrid()[i][j].getMarkType() != 'N' &&
                        this.board.getGrid()[i][j].getMarkType() == this.board.getGrid()[i + 1][j - 1].getMarkType() &&
                        this.board.getGrid()[i][j].getMarkType() == this.board.getGrid()[i + 2][j - 2].getMarkType()) {
                    if (this.board.getGrid()[i][j].getMarkType() == this.tttPlayers[0].getMark()[0].getMarkType()) {
                        return 0;
                    } else if (this.board.getGrid()[i][j].getMarkType() == this.tttPlayers[1].getMark()[0].getMarkType()) {
                        return 1;
                    }
            }
        }

        }
        // no result
        return -1;
    }



}
