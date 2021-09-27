
/**
 * @Author Friende.Peng_You
 * @Date 2021-09-10 15:41
 */

import java.util.Scanner;

public class Player {

    final private char chess;

    final private String name;

    private Player(String name, char chess) {
        this.name = name;
        this.chess = chess;
    }

    public static Player[] createPlayers() {
        Player[] players = new Player[2];
        Scanner scanner = new Scanner(System.in);
        String nameFirst;
        String nameSecond;
        char chessFirst;
        char chessSecond;

        do {
            System.out.print("Enter the name for the first player >>> ");
            nameFirst = scanner.nextLine().trim();
            System.out.print("Enter the name for the second player >>> ");
            nameSecond = scanner.nextLine().trim();
            if (nameFirst.length() < 1 || nameSecond.length() < 1
                    || nameFirst.equals(" ") || nameSecond.equals(" ") || nameFirst.equals(nameSecond)) {
                System.out.println("Do not use empty names or the same names. Please enter again.");
            }
            else break;
        }while(true);

        do {
            System.out.print("Enter the ChessType for the first player >>> ");
            chessFirst = scanner.next().charAt(0);
            if (chessFirst != 'x' && chessFirst != 'o' && chessFirst != 'X' && chessFirst != 'O') {
                System.out.println("Please enter a valid ChessType.");
            }
            else {
                // 'o' and 'x', or 'O' and 'X'
                chessSecond = (char)((chessFirst == 'o' || chessFirst == 'O') ? (chessFirst + 9) : (chessFirst - 9));
                break;
            }
        }while(true);

        players[0] = new Player(nameFirst, chessFirst);
        players[1] = new Player(nameSecond, chessSecond);
        return players;
    }

    public int move(Board board, int x, int y) {
       return board.setPiece(this, x, y);
    }

    public char getChess() {
        return chess;
    }

    public String getName() {
        return name;
    }
}
