/**
 * @Author Friende.Peng_You
 * @Date 2021-09-26 23:23
 */

package games.ordersandchaos;

import games.entities.BaseGame;
import games.entities.Mark;
import games.interfaces.GameInterface;
import games.interfaces.Winnable;
import games.entities.Board;

import java.util.Scanner;

public class OrdersAndChaos extends BaseGame implements GameInterface, Winnable {

    private final Board board;

    private final OACPlayer[] oacPlayers;

    private final Scanner scanner;

    public OrdersAndChaos(Board board, OACPlayer[] oacPlayers) {
        this.oacPlayers = oacPlayers;
        this.board = board;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void welcome() {
        String welcomeMsg = "\n==========================================================================\n"
                + "Welcome to Orders and Chaos!\n"
                + "This program is to design and implement the game of Orders and Chaos (in Java).\n"
                + "The game should allow for two players to play aginst each other.\n"
                + "You can choose which of the two players begins. The players should continue\n"
                + "to take turns until there is a winner or until there is a stalemate.\n"
                + "==========================================================================\n";
        System.out.println(welcomeMsg);
    }

    @Override
    public void init() {
        int choice;

        System.out.println("Current players:");
        for (int i = 0; i < this.oacPlayers.length; i++) {
            System.out.printf("\tPlayer Number %d. Name: %s, Chess: %s\n",
                    i, this.oacPlayers[i].getName(), this.oacPlayers[i].getMarkString());
        }

        do {
            System.out.print("Choose one player to start first (Enter the index) >>> ");
            choice = this.scanner.nextInt();
            if (choice < 0 || choice > 1) {
                System.out.printf("There are only %d players. Please input again.", this.oacPlayers.length);
            }
        }while(choice < 0 || choice > 1);

        if (choice > 0) {
            OACPlayer chosen = this.oacPlayers[choice];
            this.oacPlayers[choice] = this.oacPlayers[0];
            this.oacPlayers[0] = chosen;
        }
    }

    @Override
    public void run() {
        int i = 0;
        boolean isFull = false;

        init();
        System.out.println(this.board.toString());
        do {
            int result = nextMove(this.board, this.oacPlayers[i]);
            System.out.println(this.board);

            i = (i + 1) % 2;

            if (this.board.getCountSteps() >= 5) {
                int boardStatus = this.checkWin();
//                System.out.printf("checkwin: %d\n", boardStatus);
                this.board.setStatus(boardStatus);
                if (boardStatus != -1) {
                    // either -1 or 0, 0 means first-hand wins, -1 means no winner yet
                    break;
                }
            }
            isFull = this.board.isFull();
        }while (!isFull);

        if (isFull && this.board.getStatus() == -1) {
            System.out.printf("\n===============Player: %s ChessType: %s Wins!===============",
                    this.oacPlayers[1].getName(), this.oacPlayers[1].getMarkString());
        }
        else if (this.board.getStatus() == 0) {
            System.out.printf("\n===============Player: %s ChessType: %s Wins!===============",
                    this.oacPlayers[0].getName(), this.oacPlayers[0].getMarkString());
        }

    }

    public int nextMove(Board board, OACPlayer oacPlayer) {
        int x;
        int y;
        int markId;
        int result;

        do {
            System.out.printf("Player: %s Chess: %s now moves (input eg. 1 1 2 for putting X on Position[1,2])\n>>> ",
                    oacPlayer.getName(), oacPlayer.getMarkString());
            markId = scanner.nextInt();
            x = scanner.nextInt();
            y = scanner.nextInt();
            result = oacPlayer.move(board, oacPlayer.getMark()[markId], x, y);
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
    public int checkWin() {
        int countConsecutive = 5;
        boolean foundWinner = false;
        Mark emptyMark = new Mark('N');
        // horizontal
        for (int i = 0; i < this.board.getWidth() && !foundWinner; i++) {
            for (int j = 0; j < this.board.getLength() - countConsecutive + 1; j++) {
                if (this.board.getGrid()[i][j].compareTo(emptyMark) != 0 &&
                        this.board.getGrid()[i][j].compareTo(this.board.getGrid()[i][j + 1]) == 0 &&
                        this.board.getGrid()[i][j].compareTo(this.board.getGrid()[i][j + 2]) == 0 &&
                        this.board.getGrid()[i][j].compareTo(this.board.getGrid()[i][j + 3]) == 0 &&
                        this.board.getGrid()[i][j].compareTo(this.board.getGrid()[i][j + 4]) == 0) {
                    if (this.board.getGrid()[i][j].compareTo(this.oacPlayers[0].getMark()[0]) == 0 ||
                            this.board.getGrid()[i][j].compareTo(this.oacPlayers[0].getMark()[1]) == 0) {
                        foundWinner = true;
                        break;
                    }
                }
            }
        }
        // vertical
        if (!foundWinner) {
            for (int j = 0; j < this.board.getLength() && !foundWinner; j++) {
                for (int i = 0; i < this.board.getWidth() - countConsecutive + 1; i++) {
                    if (this.board.getGrid()[i][j].compareTo(emptyMark) != 0 &&
                            this.board.getGrid()[i][j].compareTo(this.board.getGrid()[i + 1][j]) == 0 &&
                            this.board.getGrid()[i][j].compareTo(this.board.getGrid()[i + 2][j]) == 0 &&
                            this.board.getGrid()[i][j].compareTo(this.board.getGrid()[i + 3][j]) == 0 &&
                            this.board.getGrid()[i][j].compareTo(this.board.getGrid()[i + 4][j]) == 0) {
                        if (this.board.getGrid()[i][j].compareTo(this.oacPlayers[0].getMark()[0]) == 0 ||
                                this.board.getGrid()[i][j].compareTo(this.oacPlayers[0].getMark()[1]) == 0) {
                            foundWinner = true;
                            break;
                        }
                    }
                }
            }
        }

        // main diagonal
        if (!foundWinner) {
            for (int i = 0; i < this.board.getWidth() - countConsecutive + 1 && !foundWinner; i++) {
                for (int j = 0; j < this.board.getLength() - countConsecutive + 1; j++) {
                    if (this.board.getGrid()[i][j].compareTo(emptyMark) != 0 &&
                            this.board.getGrid()[i][j].compareTo(this.board.getGrid()[i + 1][j + 1]) == 0 &&
                            this.board.getGrid()[i][j].compareTo(this.board.getGrid()[i + 2][j + 2]) == 0 &&
                            this.board.getGrid()[i][j].compareTo(this.board.getGrid()[i + 3][j + 3]) == 0 &&
                            this.board.getGrid()[i][j].compareTo(this.board.getGrid()[i + 4][j + 4]) == 0) {
                        if (this.board.getGrid()[i][j].compareTo(this.oacPlayers[0].getMark()[0]) == 0 ||
                                this.board.getGrid()[i][j].compareTo(this.oacPlayers[0].getMark()[1]) == 0) {
                            foundWinner = true;
                            break;
                        }
                    }
                }
            }
        }

        // minor diagonal
        if (!foundWinner) {
            for (int i = 0; i < this.board.getWidth() - countConsecutive + 1 && !foundWinner; i++) {
                for (int j = this.board.getLength() - 1; j >= countConsecutive - 1; j--) {
                    if (this.board.getGrid()[i][j].compareTo(emptyMark) != 0 &&
                            this.board.getGrid()[i][j].compareTo(this.board.getGrid()[i + 1][j - 1]) == 0 &&
                            this.board.getGrid()[i][j].compareTo(this.board.getGrid()[i + 2][j - 2]) == 0 &&
                            this.board.getGrid()[i][j].compareTo(this.board.getGrid()[i + 3][j - 3]) == 0 &&
                            this.board.getGrid()[i][j].compareTo(this.board.getGrid()[i + 4][j - 4]) == 0) {
                        if (this.board.getGrid()[i][j].compareTo(this.oacPlayers[0].getMark()[0]) == 0 ||
                                this.board.getGrid()[i][j].compareTo(this.oacPlayers[0].getMark()[1]) == 0) {
                            foundWinner = true;
                            break;
                        }
                    }
                }

            }
        }
        // first player wins
        if (foundWinner) return 0;
        // no result
        return -1;
    }
}
