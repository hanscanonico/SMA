/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

import java.util.HashSet;

/**
 *
 * @author canonico
 */
public class Zombie extends Agent {

    public Zombie(Position pos, Terrain terrain) {
        super(pos, terrain);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Z");
        return sb.toString();
    }

    @Override
    public void seDeplacer() {
        if (!dejaPlace) {
            HashSet<Position> listpos = (HashSet<Position>) getNewPos();
            // float rand=ms.nextFloat(true,true);

            int ind = (int) (Math.random() * listpos.size());

            Position p = (Position) listpos.toArray()[ind];
            if (!(terrain.getMap()[p.ligne][p.colonne] instanceof Zombie)) {

                if ((terrain.getMap()[p.ligne][p.colonne] instanceof Humain)) {
                    terrain.getMap()[p.ligne][p.colonne] = new Zombie(p, terrain);
                } else {
                    terrain.getMap()[pos.ligne][pos.colonne] = null;
                    terrain.getMap()[p.ligne][p.colonne] = this;
                    pos = p;
                }

            }
            this.dejaPlace = true;
        }
    }

}
