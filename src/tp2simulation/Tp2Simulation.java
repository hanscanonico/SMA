/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2simulation;

/**
 *
 * @author canonico
 */
public class Tp2Simulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Terrain t=new Terrain(10, 10);
       System.out.println(t);
       System.out.println("test");
       t.run();
    }
}
