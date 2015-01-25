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

    static private MersenneTwisterFast ms = new MersenneTwisterFast();

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
        HashSet<Position> adversaires;
        HashSet<Position> listpos = null;
        if (!dejaPlace) {
            adversaires = reperage(2, Humain.class);
            if (adversaires.isEmpty()) {
                listpos = (HashSet<Position>) getNewPos();
            } else {
                listpos = poursuivre(adversaires);
            }
            // float rand=ms.nextFloat(true,true);

            if (listpos != null && !listpos.isEmpty()) {

                int ind = (int) (Math.random() * listpos.size());
                Position p = (Position) listpos.toArray()[ind];
                if (!(terrain.getMap()[p.ligne][p.colonne] instanceof Zombie)
                        && !(terrain.getMap()[p.ligne][p.colonne] instanceof Etoile)) {

                    if ((terrain.getMap()[p.ligne][p.colonne] instanceof SuperHumain)) {
                        if (ms.nextBoolean(0.4)) {
                            terrain.getMap()[p.ligne][p.colonne] = new Zombie(p, terrain);
                        } else {
                            terrain.getMap()[pos.ligne][pos.colonne] = null;
                            terrain.getMap()[p.ligne][p.colonne] = null;
                            terrain.getMap()[p.ligne][p.colonne] = new Humain(p, terrain);
                        }

                    } else if ((terrain.getMap()[p.ligne][p.colonne] instanceof Humain)) {
                        terrain.getMap()[p.ligne][p.colonne] = new Zombie(p, terrain);
                    } else {
                        terrain.getMap()[pos.ligne][pos.colonne] = null;
                        terrain.getMap()[p.ligne][p.colonne] = this;
                        pos = p;
                    }

                }
            }
            this.dejaPlace = true;
        }
    }

}
