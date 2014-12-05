/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

/**
 *
 * @author canonico
 */
public class Humain extends Agent{

    static int compteur=0;

 
    public Humain(Position pos) {
        super(pos);
        id=compteur;
        compteur++;
    }
    int id;
    
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(id);
        return sb.toString();
    }
    
}
