/**
 * @Author Friende.Peng_You
 * @Date 2021-09-10 15:41
 */

package games.tictactoe;

import games.entities.Mark;
import games.interfaces.BoardInterface;
import games.interfaces.PlayerInterface;

import java.util.Scanner;

public class TTTPlayer implements PlayerInterface{

    final private Mark[] mark;

    final private String name;

    private TTTPlayer(String name, Mark[] mark) {
        this.name = name;
        this.mark = mark;
    }

    public static TTTPlayer[] createPlayers() {
        TTTPlayer[] tttPlayers = new TTTPlayer[2];
        Scanner scanner = new Scanner(System.in);
        String nameFirst;
        String nameSecond;
        Mark[] markFirst = new Mark[1];
        Mark[] markSecond = new Mark[1];

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
            markFirst[0] = new Mark(scanner.next().charAt(0));
            if (markFirst[0].getMarkType() != 'x'
                    && markFirst[0].getMarkType() != 'o'
                    && markFirst[0].getMarkType() != 'X'
                    && markFirst[0].getMarkType() != 'O') {
                System.out.println("Please enter a valid ChessType.");
            }
            else {
                // 'o' and 'x', or 'O' and 'X'
                markSecond[0] = new Mark(
                        (char)((markFirst[0].getMarkType() == 'o' || markFirst[0].getMarkType() == 'O') ?
                                (markFirst[0].getMarkType() + 9) : (markFirst[0].getMarkType() - 9))
                );
                break;
            }
        }while(true);

        tttPlayers[0] = new TTTPlayer(nameFirst, markFirst);
        tttPlayers[1] = new TTTPlayer(nameSecond, markSecond);
        return tttPlayers;
    }

    @Override
    public int move(BoardInterface tttBoard, Mark mark, int x, int y) {
        return tttBoard.setPiece(mark, x, y);
    }

    @Override
    public Mark[] getMark() {
        return this.mark;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
