/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

/**
 *
 * @author canonico
 */
public class Etoile extends Ressource {

    /**
     *Constructeur de la ressource Etoile
     * @param pos
     * @param terrain
     */
    public Etoile(Position pos, Terrain terrain) {
        super(pos, terrain);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("E");
        return sb.toString();
    }

}
