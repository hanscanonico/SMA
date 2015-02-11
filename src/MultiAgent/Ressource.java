/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

/**
 *
 * @author canonico
 */
public abstract class Ressource extends Entite {

    /**
     * Constructeur abstrait de ressource
     * @param pos
     * @param terrain
     */
    public Ressource(Position pos, Terrain terrain) {
        super(pos, terrain);
    }

}
