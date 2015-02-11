package MultiAgent;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author canonico
 */
public abstract class Agent extends Entite {

    /**
     * générateur de nombre aléatoire ( seed basé sur le temp)
     */
    static MersenneTwisterFast ms = new MersenneTwisterFast();

    /**
     * Constructeur abstrait d'Agent
     *
     * @param pos
     * @param terrain
     */
    public Agent(Position pos, Terrain terrain) {
        super(pos, terrain);
        dejaPlace = false;
    }

    /**
     * Calcul de nouvelles position possible pour le deplacement d'une case
     *
     * @return un set des position adjacente ( voisinage de von neuman) par
     * rapport a la positionde l'agent
     */
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

    /**
     * L'agent calcul sont deplacement
     */
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
     *
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

    /**
     * Calcul de la distance entre 2 position dans une grille (distance en
     * nombre de cases qui separe 2 positions)
     *
     * @param p1
     * @param p2
     * @return la distance en entier
     */
    int distance(Position p1, Position p2) {
        return Math.abs(p1.ligne - p2.ligne) + Math.abs(p1.colonne - p2.colonne);
    }

    /**
     * Enum des direction possible
     */
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

    /**
     * Calcul la direction dans laquelle se trouve la position enemi par rapport
     * a la position actuelle
     *
     * @param enemi
     * @return
     */
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

    /**
     * Calcul des position possible pour s'eloigner de la position enemi la plus
     * proche
     *
     * @param positionsAFuire Les positions des enemi visible dans le champ de
     * vision
     * @return un hashset de positions possible pour s'eloigne de l'enemi
     */
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

    /**
     * Methode similaire a la methode de fuite sauf que ici on cherche à se
     * rapprocher de la position la plus proche dans le chmp de vision
     *
     * @param positionsAPoursuivre hashset des postion des agent visibles dans
     * le champ de vision
     * @return une liste de position sur lesquelle on peut se deplacer pour se
     * rapprocher
     */
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
