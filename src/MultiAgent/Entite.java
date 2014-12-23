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
public abstract class Entite {
    protected Position pos;
    protected boolean dejaPlace;

    public boolean isDejaPlace() {
        return dejaPlace;
    }

    public void setDejaPlace(boolean dejaPlace) {
        this.dejaPlace = dejaPlace;
    }
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
    protected Terrain terrain;

    void seDeplacer() {
    
    }
 
    public Set<Position> getNewPos()
    {
         
      
         HashSet<Position> listPos=new HashSet();
        if(pos.x-1==-1)
        {
          // if(this.terrain.map[terrain.getNbRow()-1][pos.y]==null)
           //{
               listPos.add(new Position(terrain.getNbRow()-1, pos.y));
         //  }
        }
        else
        {
           // if(this.terrain.map[(pos.x+1)%terrain.getNbRow()][pos.y]!=null )
           // {
                listPos.add(new Position(pos.x-1, pos.y));
           // }
        }
        listPos.add(new Position((pos.x+1)%terrain.getNbRow(), pos.y));
        if(pos.y-1==-1)
        {
           // if(this.terrain.map[pos.x][terrain.getNbCol()-1]!=null )
            //{

                listPos.add(new Position(pos.x, terrain.getNbCol()-1));
          //  }
        }
        else
        {
          //  if(this.terrain.map[pos.x][(pos.y-1)%terrain.getNbCol()]!=null )
            //{

                listPos.add(new Position(pos.x, pos.y-1));
           // }
        }
       // if(this.terrain.map[pos.x][(pos.y+1)%terrain.getNbCol()]!=null )
        //{
        listPos.add(new Position(pos.x, (pos.y+1)%terrain.getNbRow()));
        //}
        return listPos;
    }
    
}
