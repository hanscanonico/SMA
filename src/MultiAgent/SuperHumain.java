package MultiAgent;

import java.util.HashSet;

/**
 *
 * @author Amine
 */
public class SuperHumain extends Humain {

    /**
     * Constructeur de superhumain
     * @param pos
     * @param terrain 
     */
    public SuperHumain(Position pos, Terrain terrain) {
        super(pos, terrain);
    }

    /**
     * L'agent calcul sa nouvelle position et se deplace lui meme sur la matrice
     * Si sur la nouvelle position il se trouve un Zombie, ce dernier et eliminé 
     * et le superhumain prend sa place
     * Si c'est un humain ou une etoile , le superhumain ne bouge pas
     */
    @Override
    public void seDeplacer() {
        HashSet<Position> adversaires;
        HashSet<Position> listpos;
        if (!dejaPlace) {
            adversaires = reperage(4, Zombie.class);
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
