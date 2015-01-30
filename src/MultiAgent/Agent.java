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
     * @param classAReperer
     * @return
     */
    HashSet<Position> reperage(int distance, Class classAReperer) {
        HashSet<Position> positions = new HashSet<>();
        int x = this.pos.ligne;
        int y = this.pos.colonne;
        for (int i = distance * -1; i <= distance; i++) {
            for (int j = distance * -1 + Math.abs(i); j <= distance - Math.abs(i); j++) {
                if (x + i < terrain.getNbRow() && x + i >= 0 && y + j < terrain.getNbCol() && y + j >= 0) {
                    if ((this.terrain.map[x + i][y + j]) != null && (this.terrain.map[x + i][y + j]).getClass() == (classAReperer)) {
                        positions.add((this.terrain.map[x + i][y + j]).getPos());
                    }
                }

            }
        }
        return positions;
    }

    /**
     * REppere tout les agent d'une class sur la map
     * @param classAReperer
     * @return 
     */
    HashSet<Position> reperage(Class classAReperer) {
        HashSet<Position> positions = new HashSet<>();
        for (int i = 0; i < terrain.getNbRow(); i++) {
            for (int j = 0; j < terrain.getNbCol(); j++) {
                if ((this.terrain.map[i][j]) != null && (this.terrain.map[i][j]).getClass() == (classAReperer)) {
                    positions.add((this.terrain.map[i][j]).getPos());
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

    protected HashSet<Position> fuire(HashSet<Position> positionsAFuire) {
        HashSet<Position> p = new HashSet<>();
        Position plusProche = (Position) (positionsAFuire.toArray())[0];
        int distance = distance(this.pos, plusProche);
        int aux;
        for (Position next : positionsAFuire) {
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
                if (pos.ligne + 1 < getTerrain().getNbRow()) {
                    p.add(new Position(pos.ligne + 1, pos.colonne));
                }
                if (pos.ligne > 0) {
                    p.add(new Position(pos.ligne - 1, pos.colonne));
                }
                break;
            case NORD:
                if (pos.ligne + 1 < getTerrain().getNbRow()) {
                    p.add(new Position(pos.ligne + 1, pos.colonne));
                }
                if (pos.colonne + 1 < getTerrain().getNbCol()) {
                    p.add(new Position(pos.ligne, pos.colonne + 1));
                }
                if (pos.colonne > 0) {
                    p.add(new Position(pos.ligne, pos.colonne - 1));
                }
                break;
            case OUEST:
                if (pos.colonne + 1 < getTerrain().getNbRow()) {
                    p.add(new Position(pos.ligne, pos.colonne + 1));
                }
                if (pos.ligne + 1 < getTerrain().getNbRow()) {
                    p.add(new Position(pos.ligne + 1, pos.colonne));
                }
                if (pos.ligne > 0) {
                    p.add(new Position(pos.ligne - 1, pos.colonne));
                }
                break;
            case SUD:
                if (pos.ligne > 0) {
                    p.add(new Position(pos.ligne - 1, pos.colonne));
                }
                if (pos.colonne + 1 < getTerrain().getNbRow()) {
                    p.add(new Position(pos.ligne, pos.colonne + 1));
                }
                if (pos.colonne > 0) {
                    p.add(new Position(pos.ligne, pos.colonne - 1));
                }
                break;
            case SUD_EST:
                if (pos.ligne > 0) {
                    p.add(new Position(pos.ligne - 1, pos.colonne));
                }
                if (pos.colonne > 0) {
                    p.add(new Position(pos.ligne, pos.colonne - 1));
                }
                break;
            case SUD_OUEST:
                if (pos.ligne > 0) {
                    p.add(new Position(pos.ligne - 1, pos.colonne));
                }
                if (pos.colonne + 1 < getTerrain().getNbRow()) {
                    p.add(new Position(pos.ligne, pos.colonne + 1));
                }
                break;
            case NORD_EST:
                if (pos.ligne + 1 < getTerrain().getNbRow()) {
                    p.add(new Position(pos.ligne + 1, pos.colonne));
                }
                if (pos.colonne > 0) {
                    p.add(new Position(pos.ligne, pos.colonne - 1));
                }
                break;
            case NORD_OUEST:
                if (pos.colonne + 1 < getTerrain().getNbRow()) {
                    p.add(new Position(pos.ligne, pos.colonne + 1));
                }
                if (pos.ligne + 1 < getTerrain().getNbRow()) {
                    p.add(new Position(pos.ligne + 1, pos.colonne));
                }
                break;
        }

        return p;
    }

    protected HashSet<Position> poursuivre(HashSet<Position> positionsAPoursuivre) {
        HashSet<Position> p = new HashSet<>();
        Position plusProche = (Position) (positionsAPoursuivre.toArray())[0];
        int distance = distance(this.pos, plusProche);
        int aux;
        for (Position next : positionsAPoursuivre) {
            aux = distance(this.pos, next);
            if (aux < distance) {
                distance = aux;
                plusProche = next;
            }
        }
        switch (direction(plusProche)) {
            case EST:
                if (pos.colonne + 1 < getTerrain().getNbCol()) {
                    p.add(new Position(pos.ligne, pos.colonne + 1));
                }
                break;
            case NORD:
                if (pos.ligne > 0) {
                    p.add(new Position(pos.ligne - 1, pos.colonne));
                }
                break;
            case OUEST:
                if (pos.colonne > 0) {
                    p.add(new Position(pos.ligne, pos.colonne - 1));
                }
                break;
            case SUD:
                if (pos.ligne + 1 < getTerrain().getNbRow()) {
                    p.add(new Position(pos.ligne + 1, pos.colonne));
                }
                break;
            case SUD_EST:
                if (pos.colonne + 1 < getTerrain().getNbRow()) {
                    p.add(new Position(pos.ligne, pos.colonne + 1));
                }
                if (pos.ligne + 1 < getTerrain().getNbRow()) {
                    p.add(new Position(pos.ligne + 1, pos.colonne));
                }
                break;
            case NORD_EST:
                if (pos.ligne > 0) {
                    p.add(new Position(pos.ligne - 1, pos.colonne));
                }
                if (pos.colonne + 1 < getTerrain().getNbRow()) {
                    p.add(new Position(pos.ligne, pos.colonne + 1));
                }

                break;
            case SUD_OUEST:
                if (pos.ligne + 1 < getTerrain().getNbRow()) {
                    p.add(new Position(pos.ligne + 1, pos.colonne));
                }
                if (pos.colonne > 0) {
                    p.add(new Position(pos.ligne, pos.colonne - 1));
                }
                break;
            case NORD_OUEST:
                if (pos.ligne > 0) {
                    p.add(new Position(pos.ligne - 1, pos.colonne));
                }
                if (pos.colonne > 0) {
                    p.add(new Position(pos.ligne, pos.colonne - 1));
                }
                break;
        }

        return p;
    }
}
