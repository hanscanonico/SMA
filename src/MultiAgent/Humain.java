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
     
        if(!dejaPlace)
        {
            HashSet<Position> listpos=(HashSet<Position>) getNewPos();
           // float rand=ms.nextFloat(true,true);

            
            int ind=(int) (Math.random()*listpos.size());
                 
            Position p=(Position) listpos.toArray()[ind];
            if(!(terrain.getMap()[p.x][p.y] instanceof Humain) && !(terrain.getMap()[p.x][p.y] instanceof Zombie))
            {
                terrain.getMap()[pos.x][pos.y]=null;
                terrain.getMap()[p.x][p.y]=this;
                pos=p;
            }
            this.dejaPlace=true;
        }
        
    }
    
}
