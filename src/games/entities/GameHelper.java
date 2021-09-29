/**
 * @Author Friende.Peng_You
 * @Date 2021-09-27 15:09
 */

package games.entities;

import games.ordersandchaos.OACPlayer;
import games.ordersandchaos.OrdersAndChaos;
import games.tictactoe.TTTPlayer;
import games.tictactoe.TicTacToe;

import java.util.HashMap;
import java.util.Scanner;

public class GameHelper {

    private static HashMap<Integer, String> getGames() {
        HashMap<Integer, String> games = new HashMap<>();
        games.put(0, "TicTacToe");
        games.put(1, "OrdersAndChaos");
        return games;
    }

    public static String chooseGame(HashMap<Integer, String> games) {
        StringBuilder sb = new StringBuilder();
        sb.append("Now we have games: \n");
        for (int i = 0; i < games.size(); i++) {
            sb.append(String.format("\t%d. %s\n", i, games.get(i)));
        }
        sb.append("\nChoose the game you want to play by index(input sth. else for exit)\n>>> ");
        System.out.print(sb);
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return games.get(choice);
    }

    public static void runGame() {
        int begin;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("\nWant to start a new game ? (1 for yes, others for no) >>> ");
            begin = scanner.nextInt();
            System.out.println();
            if (1 != begin) {
                break;
            }
            else {
                HashMap<Integer, String> games = getGames();
                String gameName = chooseGame(games);
                if (gameName.equals("TicTacToe")) {
                    getTicTacToe().run();
                }
                else if (gameName.equals("OrdersAndChaos")) {
                    getOrdersAndChaos().run();
                }
            }
        }while (true);
    }

    private static TicTacToe getTicTacToe() {
        Board board = Board.createBoard();
        TTTPlayer[] tttPlayers = TTTPlayer.createPlayers();
        return new TicTacToe(board, tttPlayers);
    }

    private static OrdersAndChaos getOrdersAndChaos() {
        Board board;
        do {
            System.out.println("\nOrders and Chaos needs Board size AT LEAST(6 6)");
            board = Board.createBoard();
        }while (!(board.getWidth() >= 6 && board.getLength() >= 6));
        OACPlayer[] oacPlayers = OACPlayer.createPlayers();
        return new OrdersAndChaos(board, oacPlayers);
    }



}
