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

    int ligne;
    int colonne;

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public Position(int x, int y) {
        this.ligne = x;
        this.colonne = y;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("x :").append(ligne);
        sb.append(" y :").append(colonne);
        return sb.toString();
    }

}
