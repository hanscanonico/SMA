/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

/**
 *
 * @author canonico
 */
public class Zombie extends Agent {

    public Zombie(Position pos) {
        super(pos);
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("Z");
        return sb.toString();
    }
    
}
