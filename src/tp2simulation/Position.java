/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2simulation;

/**
 *
 * @author canonico
 */
public class Position {
    int x;

    int y;
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
