/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

/**
 *
 * @author Amine
 */
public class SuperHumain extends Humain{

    
    private int nbToursRestants;
    public SuperHumain(Position pos, Terrain terrain) {
        super(pos, terrain);
        nbToursRestants=50;
    }

    @Override
    public void seDeplacer() {
        super.seDeplacer();
        nbToursRestants--;
        if(nbToursRestants==0)
        {
              terrain.getMap()[pos.ligne][pos.colonne] = new Humain(pos, terrain);
        }
    }
 
    
    
}
