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
public class Humain extends Agent {

    /**
     * Compteur de creaion d'humain pour affecter un id unique a chaque Humain
     * Utiliser pour differencer les Humains avec l'affichage texte
     */
    static int compteur = 0;
    private int id;

    /**
     * Constructeur d'agent Humain
     * @param pos
     * @param terrain 
     */
    public Humain(Position pos, Terrain terrain) {
        super(pos, terrain);
        id = compteur;
        compteur++;
    }

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
            posEtoile = reperage(Etoile.class);
            if (adversaires.isEmpty()) {
                if (!posEtoile.isEmpty()) {
                    listpos = poursuivre(posEtoile);
                }
            } else {
                listpos = fuire(adversaires);
            }

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
