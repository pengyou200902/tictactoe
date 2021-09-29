/**
 * @Author Friende.Peng_You
 * @Date 2021-09-27 13:34
 */

package games.interfaces;

import games.entities.Mark;

public interface BoardInterface {

    public int setPiece(Mark mark, int x, int y);

    public boolean isFull();

    public int checkAvailability(int x, int y);
}
