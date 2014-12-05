/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

import java.io.IOException;

/**
 *
 * @author canonico
 */
public class Tp2Simulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       Terrain t=new Terrain(5, 5);
       t.initialiser();
       System.out.println(t);
       t.run();
    }
}
