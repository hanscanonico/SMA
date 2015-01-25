/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

import java.util.HashSet;

/**
 *
 * @author Amine
 */
public class SuperHumain extends Humain {

    public SuperHumain(Position pos, Terrain terrain) {
        super(pos, terrain);
    }

    @Override
    public void seDeplacer() {
        HashSet<Position> adversaires;
        HashSet<Position> listpos;
        if (!dejaPlace) {
            adversaires = reperage(3, Zombie.class);
            if (adversaires.isEmpty()) {
                listpos = (HashSet<Position>) getNewPos();
            } else {
                listpos = poursuivre(adversaires);
            }
            // float rand=ms.nextFloat(true,true);

            if (!listpos.isEmpty()) {

                int ind = (int) (Math.random() * listpos.size());

                Position p = (Position) listpos.toArray()[ind];
                if ((terrain.getMap()[p.ligne][p.colonne] instanceof Zombie)) {
                    terrain.getMap()[pos.ligne][pos.colonne] = null;
                    terrain.getMap()[p.ligne][p.colonne] = new Humain(p, terrain);
                    pos = p;

                } else if (!(terrain.getMap()[p.ligne][p.colonne] instanceof Humain) && !(terrain.getMap()[p.ligne][p.colonne] instanceof Etoile)) {
                    terrain.getMap()[pos.ligne][pos.colonne] = null;
                    terrain.getMap()[p.ligne][p.colonne] = this;
                    pos = p;

                }

            }

            this.dejaPlace = true;
        }

    }

}
