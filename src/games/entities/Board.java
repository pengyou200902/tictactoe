/**
 * @Author Friende.Peng_You
 * @Date 2021-09-10 13:56
 */

package games.entities;

import games.interfaces.BoardInterface;
import games.interfaces.Scalable;

import java.util.Scanner;


public class Board extends BaseBoard implements BoardInterface, Scalable {

    // -2 means no status
    private int status = -2;

    private int countSteps;

    private int length = 3;

    private int width = 3;

    private Mark[][] grid;

    private Board() {}

    public Board(int width, int length) {
        if (!this.setSize(width, length)) {
            throw new IllegalArgumentException(String.format("Invalid Size for Board(min:%d %d, max: %d %d)",
                    Board.minWidth, Board.minLength, Board.maxWidth, Board.maxLength));
        }
    }

    public Board(int width) {
        this(width, width);
    }

    private void reset() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.length; j++) {
                this.grid[i][j] = new Mark('N');
            }
        }
        this.status = -2;
        this.countSteps = 0;
    }

    public static Board createBoard() {
        int width;
        int length;
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.printf("Input the width and length of the board respectively(min:%d %d, max: %d %d)\n>>> ",
                    Board.minWidth, Board.minLength, Board.maxWidth, Board.maxLength);
            width = scanner.nextInt();
            length = scanner.nextInt();
        }while(!board.setSize(width, length));

        return board;
    }

    @Override
    public int setPiece(Mark mark, int x, int y) {
        int availability = checkAvailability(x, y);
        if (availability == 1) {
            this.grid[x][y].setMark(mark.getMarkType());
            this.countSteps++;
        }
        return availability;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Now have steps: %d, Board Status: %d\n", this.countSteps, this.status));

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.length; j++) {
                sb.append(this.grid[i][j].getMarkType());
                sb.append('\t');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public boolean isFull() {
        // board is full.
        return this.countSteps >= this.width * this.length;
    }

    @Override
    public int checkAvailability(int x, int y) {
        // -1 means out of bounds
        if (x < 0 || x >= this.width || y < 0 || y >= this.length) {
            return -1;
        }

        // 1 means available
        if (this.grid[x][y].getMarkType() == 'N') {
            return 1;
        }
        else {
            // 0 means there's already one chess. 2 means the board is full.
            return isFull() ? 2 : 0;
        }
    }

    @Override
    public boolean setSize(int width, int length) {
        if (length < minLength || length > maxLength
                || width < minWidth || width > maxWidth) {
            System.out.println("Invalid board size!");
            return false;
        }
        else {
            this.length = length;
            this.width = width;
            this.grid = new Mark[this.width][this.length];
            this.reset();
            return true;
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCountSteps() {
        return countSteps;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public Mark[][] getGrid() {
        return grid;
    }

}


