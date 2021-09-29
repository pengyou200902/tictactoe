/**
 * @Author Friende.Peng_You
 * @Date 2021-09-26 23:22
 */

package games.interfaces;

import games.entities.Mark;

public interface PlayerInterface {

    public int move(BoardInterface board, Mark mark, int x, int y);

    public Mark[] getMark();

    public String getName();

}
