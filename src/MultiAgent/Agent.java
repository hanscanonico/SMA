/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author canonico
 */
public abstract class Agent extends Entite{
MersenneTwisterFast mtsign = new MersenneTwisterFast();  
     

    public Agent(Position pos,Terrain terrain) {
        super(pos,terrain);
        dejaPlace=false;
    }

   
    public Position calculNouvellelPosition(int n, int m) {
        Random rand = new Random();
        int max = 3;
        int min = 0;

        int randomNum = mtsign.nextInt((max - min) + 1)+ min;//rand.nextInt((max - min) + 1) + min;

        int nouvI=0, nouvJ=0;
        switch (randomNum) {
            case 0:
                nouvI = (pos.getX() - 1) % n;
                if (nouvI < 0) {
                    nouvI =n-1;
                }
                nouvJ = (pos.getY());
                break;
            case 1:
                nouvI = (pos.getX());
                nouvJ = (pos.getY() - 1) % m;
                if (nouvJ < 0) {
                    nouvJ =m-1;
                }
                break;
            case 2:
                nouvI = (pos.getX() + 1) % n;
                nouvJ = (pos.getY());
                break;
            case 3:
                nouvI = (pos.getX());
                nouvJ = (pos.getY() + 1) % m;
                break;
        }
       // System.out.println(nouvI + " - "+ nouvJ);

        Position p = new Position(nouvI, nouvJ);
        //setPos(p);
        return p;

    }

    
    @Override
    void seDeplacer() {
        
    }
   
}
