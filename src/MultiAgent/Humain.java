/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

import java.util.HashSet;

/**
 *
 * @author canonico
 */
public class Humain extends Agent{

    static int compteur=0;

 
    public Humain(Position pos,Terrain terrain) {
        super(pos,terrain);
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

    @Override
    void seDeplacer() {
        MersenneTwisterFast ms = new MersenneTwisterFast();
        HashSet<Position> listpos=(HashSet<Position>) getNewPos();
        
        
    }
    
}
