/**
 * @Author Friende.Peng_You
 * @Date 2021-09-27 23:18
 */

package games.entities;

import org.jetbrains.annotations.NotNull;

public class Mark implements Comparable<Mark> {

    private char markType;

    public Mark(char markType) {
        this.markType = markType;
    }

    public char getMarkType() {
        return markType;
    }

    public void setMark(char markType) {
        this.markType = markType;
    }

    @Override
    public String toString() {
        return String.valueOf(this.markType);
    }

    @Override
    public int compareTo(@NotNull Mark o) {
        return this.getMarkType() - o.getMarkType();
    }
}
