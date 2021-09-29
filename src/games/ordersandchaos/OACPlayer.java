/**
 * @Author Friende.Peng_You
 * @Date 2021-09-27 22:43
 */

package games.ordersandchaos;

import games.entities.Mark;
import games.interfaces.BoardInterface;
import games.interfaces.PlayerInterface;

import java.util.Scanner;

public class OACPlayer implements PlayerInterface {

    private final Mark[] mark;
    private final String name;

    public OACPlayer(String name, Mark[] mark) {
        this.mark = mark;
        this.name = name;
    }

    public static OACPlayer[] createPlayers() {
        OACPlayer[] oacPlayers = new OACPlayer[2];
        Scanner scanner = new Scanner(System.in);
        Mark[] markFirst = new Mark[2];
        Mark[] markSecond = new Mark[2];
        markFirst[0] = new Mark('O');
        markFirst[1] = new Mark('X');
        markSecond[0] = new Mark('O');
        markSecond[1] = new Mark('X');
        String nameFirst;
        String nameSecond;

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

        oacPlayers[0] = new OACPlayer(nameFirst, markFirst);
        oacPlayers[1] = new OACPlayer(nameSecond, markSecond);
        return oacPlayers;
    }

    @Override
    public int move(BoardInterface board, Mark mark, int x, int y) {

        return board.setPiece(mark, x, y);
    }

    @Override
    public Mark[] getMark() {
        return this.mark;
    }

    public String getMarkString() {
        StringBuilder sb = new StringBuilder();
        int i;
        for (i = 0; i < this.getMark().length - 1; i++) {
            sb.append(String.format("[%d].%c", i, this.getMark()[i].getMarkType()));
            sb.append(", ");
        }
        sb.append(String.format("[%d].%c", i, this.getMark()[i].getMarkType()));
        return sb.toString();
    }

    @Override
    public String getName() {
        return this.name;
    }
}
