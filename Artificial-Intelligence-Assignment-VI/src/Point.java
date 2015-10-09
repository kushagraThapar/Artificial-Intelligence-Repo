/**
 * Created by kushagrathapar on 10/9/15.
 */

/**
 * This class represents a x and y coordinates of a point on Tic Tac Toe board
 */
class Point {

    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
