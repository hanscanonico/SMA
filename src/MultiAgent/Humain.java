/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author canonico
 */
public class Humain extends Agent {

    static int compteur = 0;

    public Humain(Position pos, Terrain terrain) {
        super(pos, terrain);
        id = compteur;
        compteur++;
    }
    int id;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        return sb.toString();
    }

    @Override
    public void seDeplacer() {
        HashSet<Position> adversaires;
        HashSet<Position> listpos = null;
        HashSet<Position> posEtoile = null;
        if (!dejaPlace) {
            adversaires = reperage(3, Zombie.class);
            posEtoile = reperage(terrain.getNbCol()*2, Etoile.class);
            if (adversaires.isEmpty()) {
//                listpos = (HashSet<Position>) getNewPos();
                if (!posEtoile.isEmpty()) {
                    listpos = poursuivre(posEtoile);
                }
            } else {
                listpos = fuire(adversaires);
            }
            // float rand=ms.nextFloat(true,true);

            if (listpos != null && !listpos.isEmpty()) {

                int ind = (int) (Math.random() * listpos.size());

                Position p = (Position) listpos.toArray()[ind];
                if ((terrain.getMap()[p.ligne][p.colonne] instanceof Etoile)) {

                    terrain.getMap()[pos.ligne][pos.colonne] = null;
                    terrain.getMap()[p.ligne][p.colonne] = new SuperHumain(p, terrain);
                    pos = p;
                    Terrain.etoile--;

                } else if (!(terrain.getMap()[p.ligne][p.colonne] instanceof Agent)) {
                    terrain.getMap()[pos.ligne][pos.colonne] = null;
                    terrain.getMap()[p.ligne][p.colonne] = this;
                    pos = p;
                }

            }

            this.dejaPlace = true;
        }

    }

}
