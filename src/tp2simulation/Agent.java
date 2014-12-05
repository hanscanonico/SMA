/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2simulation;

/**
 *
 * @author canonico
 */
public abstract class Agent {

    Position pos;

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public Agent(Position pos) {
        this.setPos(pos);
    }

    public Position getPos() {
        return pos;
    }

    public Position deplacer(int n,int m) {
        int aleaN = (int) (Math.random() * 3) - 1;
        int aleaM = (int) (Math.random() * 3) - 1;
        int nouvI, nouvJ;

        nouvI = (pos.getX() + aleaN)%n;
        nouvJ = (pos.getY() + aleaM)%m;
        if(nouvI==-1)
            nouvI=n-1;
        if(nouvJ==-1)
            nouvJ=m-1;
        Position p = new Position(nouvI, nouvJ);
        setPos(p);
        return p;

    }
}
