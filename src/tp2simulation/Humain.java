/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2simulation;

/**
 *
 * @author canonico
 */
public class Humain extends Agent{

    public Humain(Position pos) {
        super(pos);
    }

    
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("H");
        return sb.toString();
    }
    
}
