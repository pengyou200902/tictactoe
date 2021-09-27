import java.util.Scanner;

/**
 * @Author Friende.Peng_You
 * @Date 2021-09-10 13:56
 */

public class Board {

    // -2 means no status
    private int status = -2;

    private int countSteps;

//    final private Scanner scanner;

    final private int width = 3;

    final private char[][] grid;

    public Board() {
        // new int array with all values = 0
        grid = new char[this.width][this.width];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.width; j++) {
                grid[i][j] = 'N';
            }
        }
        this.status = 0;
        this.countSteps = 0;
//        this.scanner = new Scanner(System.in);
    }

    public int setPiece(Player player, int x, int y) {
        int availability = checkAvailability(x, y);
        if (availability == 1) {
            this.grid[x][y] = player.getChess();
            this.countSteps++;
        }
//        else if (availability == 0) {
//            System.out.println("Invalid position! Please enter another one.");
//        }
//        else if (availability == -1) {
//            System.out.println("Invalid position! Please enter another one.");
//        }
        return availability;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Now have steps: ");
        sb.append(this.countSteps);
        sb.append("\n");
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.width; j++) {
                sb.append(grid[i][j]);
                sb.append('\t');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public boolean isFull() {
        // board is full.
        return this.countSteps >= this.width * this.width;
    }

    public int checkAvailability(int x, int y) {
        // -1 means out of bounds
        if (x < 0 || x >= this.width || y < 0 || y >= this.width) {
            return -1;
        }

        // 1 means available
        if (this.grid[x][y] == 'N') {
            return 1;
        }
        else {
            // 0 means there's already one chess. 2 means the board is full.
            return isFull() ? 2 : 0;
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

    public char[][] getGrid() {
        return grid;
    }

    public static void main(String[] args) {
        Board b = new Board();
        System.out.println(b.toString());
    }
}
