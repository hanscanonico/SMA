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
        HashSet<Position> listpos = new HashSet<>();
        if (!dejaPlace) {
            adversaires = reperage(3, Zombie.class);
            if (adversaires.isEmpty()) {
                //listpos = (HashSet<Position>) getNewPos();
            } else {
                listpos = fuire(adversaires);
            }
            // float rand=ms.nextFloat(true,true);

            if (!listpos.isEmpty()) {

                int ind = (int) (Math.random() * listpos.size());

                Position p = (Position) listpos.toArray()[ind];
                if (!(terrain.getMap()[p.ligne][p.colonne] instanceof Agent)) {
                    terrain.getMap()[pos.ligne][pos.colonne] = null;
                    terrain.getMap()[p.ligne][p.colonne] = this;
                    pos = p;
                }

            }

            this.dejaPlace = true;
        }

    }

    private HashSet<Position> fuire(HashSet<Position> adversaires) {
        HashSet<Position> p = new HashSet<>();
        Position plusProche = (Position) (adversaires.toArray())[0];
        int distance = distance(this.pos, plusProche);
        int aux;
        for (Position next : adversaires) {
            aux = distance(this.pos, next);
            if (aux < distance) {
                distance = aux;
                plusProche = next;
            }
        }
        switch (direction(plusProche)) {
            case EST:
                if (pos.colonne > 0) {
                    p.add(new Position(pos.ligne, pos.colonne - 1));
                }
                break;
            case NORD:
                if (pos.ligne+1 < getTerrain().getNbRow()) {
                    p.add(new Position(pos.ligne + 1, pos.colonne));
                }
                break;
            case OUEST:
                if (pos.colonne+1 < getTerrain().getNbRow()) {
                    p.add(new Position(pos.ligne, pos.colonne + 1));
                }
                break;
            case SUD:
                if (pos.ligne > 0) {
                    p.add(new Position(pos.ligne - 1, pos.colonne));
                }
                break;
        }

        return p;
    }

}
