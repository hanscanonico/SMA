/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Canonico
 */
public class Entite {
    private Position pos;

    public Entite(Position pos, Terrain terrain) {
        this.pos = pos;
        this.terrain = terrain;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
    private Terrain terrain;

    void seDeplacer() {
    
    }
 
    public Set<Position> getNewPos()
    {
        HashSet<Position> listPos=new HashSet();
        if(this.terrain.map[pos.x-1][pos.y]!=null )
        {
            
        }
        return listPos;
    }
    
}
