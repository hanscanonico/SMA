/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author canonico
 */
public abstract class Agent extends Entite {

    MersenneTwisterFast mtsign = new MersenneTwisterFast();

    public Agent(Position pos, Terrain terrain) {
        super(pos, terrain);
        dejaPlace = false;
    }

    public Position calculNouvellelPosition(int n, int m) {
        Random rand = new Random();
        int max = 3;
        int min = 0;

        int randomNum = mtsign.nextInt((max - min) + 1) + min;//rand.nextInt((max - min) + 1) + min;

        int nouvI = 0, nouvJ = 0;
        switch (randomNum) {
            case 0:
                nouvI = (pos.getLigne() - 1) % n;
                if (nouvI < 0) {
                    nouvI = n - 1;
                }
                nouvJ = (pos.getColonne());
                break;
            case 1:
                nouvI = (pos.getLigne());
                nouvJ = (pos.getColonne() - 1) % m;
                if (nouvJ < 0) {
                    nouvJ = m - 1;
                }
                break;
            case 2:
                nouvI = (pos.getLigne() + 1) % n;
                nouvJ = (pos.getColonne());
                break;
            case 3:
                nouvI = (pos.getLigne());
                nouvJ = (pos.getColonne() + 1) % m;
                break;
        }
        // System.out.println(nouvI + " - "+ nouvJ);

        Position p = new Position(nouvI, nouvJ);
        //setPos(p);
        return p;

    }

    public Set<Position> getNewPos() {

        HashSet<Position> listPos = new HashSet();
        if (pos.ligne - 1 != -1) {
            listPos.add(new Position(pos.ligne - 1, pos.colonne));
        }
        if (pos.colonne - 1 != -1) {
            listPos.add(new Position(pos.ligne, pos.colonne - 1));
        }
        if (pos.colonne + 1 < terrain.getNbCol()) {
            listPos.add(new Position(pos.ligne, (pos.colonne + 1)));
        }
        if (pos.ligne + 1 < terrain.getNbRow()) {
            listPos.add(new Position(pos.ligne + 1, (pos.colonne)));
        }

        return listPos;
    }

    public abstract void seDeplacer();

    /**
     * Repere les agent d'une certaine class à une distance de voisinnage de von
     * neumann
     *
     * @param distance
     * @param classAdverse
     * @return
     */
    HashSet<Position> reperage(int distance, Class classAdverse) {
        HashSet<Position> positions = new HashSet<>();
        int x = this.pos.ligne;
        int y = this.pos.colonne;
        for (int i = distance * -1; i <= distance; i++) {
            for (int j = distance * -1 + Math.abs(i); j <= distance - Math.abs(i); j++) {
                if (x + i < terrain.getNbRow() && x + i >= 0 && y + j < terrain.getNbCol() && y + j >= 0) {
                    if ((this.terrain.map[x + i][y + j])!=null && (this.terrain.map[x + i][y + j]).getClass() == (Zombie.class)) {
                        positions.add((this.terrain.map[x + i][y + j]).getPos());
                    }
                }

            }
        }
        return positions;
    }

    int distance(Position p1, Position p2) {
        return Math.abs(p1.ligne - p2.ligne) + Math.abs(p1.colonne - p2.colonne);
    }

    public enum Cardinalite {

        NORD,
        SUD,
        EST,
        OUEST,
        NORD_EST,
        NORD_OUEST,
        SUD_EST,
        SUD_OUEST;

    }

    Cardinalite direction(Position enemi) {
        if (enemi.colonne < pos.colonne) {
            if (enemi.ligne < pos.ligne) {
                return Cardinalite.NORD_OUEST;
            } else if (enemi.ligne > pos.ligne) {
                return Cardinalite.SUD_OUEST;
            } else {
                return Cardinalite.OUEST;
            }
        }

        if (enemi.colonne > pos.colonne) {
            if (enemi.ligne < pos.ligne) {
                return Cardinalite.NORD_EST;
            } else if (enemi.ligne > pos.ligne) {
                return Cardinalite.SUD_EST;
            } else {
                return Cardinalite.EST;
            }
        }
        if (enemi.colonne == pos.colonne && enemi.ligne > pos.ligne) {
            return Cardinalite.SUD;
        }
        return Cardinalite.NORD;

    }

}
