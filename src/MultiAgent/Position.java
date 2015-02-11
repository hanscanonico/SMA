/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

/**
 *
 * @author canonico
 */
public class Position {

    /**
     * numero de la ligne de la position
     */
    int ligne;
    /**
     * numeroe de la colonne de la position
     */
    int colonne;

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    /**
     * Mutateur du numer de colonne
     * @param colonne 
     */
    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    /**
     * Acceseur du numero de ligne
     * @return 
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * Accesseur du numero de colonne
     * @return 
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * Constructeur de Position
     * @param x numero de ligne
     * @param y numero de colonne
     */
    public Position(int x, int y) {
        this.ligne = x;
        this.colonne = y;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("x :").append(ligne);
        sb.append(" y :").append(colonne);
        return sb.toString();
    }

}
