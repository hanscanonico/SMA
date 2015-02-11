/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

/**
 *
 * @author Canonico
 */
public abstract class Entite {

    /**
     * position de l'entite sur la grille
     */
    protected Position pos;
    /**
     * boolen pour savoir si l'entite s'est deja deplacer lors d'un parcours
     * pour deplacer toute les entités de la matrice
     */
    protected boolean dejaPlace;

    /**
     * Accesseur de l'attribut dejaPlace
     *
     * @return
     */
    public boolean isDejaPlace() {
        return dejaPlace;
    }

    /**
     * Mutateur de dejaPlace
     *
     * @param dejaPlace
     */
    public void setDejaPlace(boolean dejaPlace) {
        this.dejaPlace = dejaPlace;
    }

    /**
     * Constructeur abstrait d'entité
     *
     * @param pos
     * @param terrain
     */
    public Entite(Position pos, Terrain terrain) {
        this.pos = pos;
        this.terrain = terrain;
    }

    /**
     * Acceseur de la position de l'entite
     *
     * @return
     */
    public Position getPos() {
        return pos;
    }

    /**
     * Mutateur de la poistion de l'entite
     *
     * @param pos
     */
    public void setPos(Position pos) {
        this.pos = pos;
    }

    /**
     * Acceseur du terrain sur lequelle se trouve l'entite
     *
     * @return
     */
    public Terrain getTerrain() {
        return terrain;
    }

    /**
     * Mutateur de la reference vers le terrain
     *
     * @param terrain
     */
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    /**
     * Reference vers le terrain
     */
    protected Terrain terrain;

}
