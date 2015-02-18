/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

import Interface.Fenetre;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;

/**
 *
 * @author canonico
 */
public class Tp2Simulation {

    private int countHummain(Entite[][] map) {
        int c = 0;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 50; j++) {
                if (map[i][j] instanceof Humain) {
                    c++;
                }
            }
        }
        return c;
    }

    private int countZombie(Entite[][] map) {
        int c = 0;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 50; j++) {
                if (map[i][j] instanceof Zombie) {
                    c++;
                }
            }
        }
        return c;
    }

    public static void main(String[] args) throws IOException {
        Tp2Simulation tp = new Tp2Simulation();
        Terrain t = Terrain.getInstance(30, 50);
        t.initialiser();
        File ff = new File("resultat.txt"); // définir l'arborescence
        ff.createNewFile();
        FileWriter ffw = new FileWriter(ff);
        //System.out.println(t);
        int z =0,h =0;
        for (int i = 0; i < 20000; i++) {
            //t.initialiser();
            t.reset();
            while (tp.countHummain(t.map) != 0 && tp.countZombie(t.map) != 0) {
                t.deplacerLesAgents();
            }
            if(tp.countHummain(t.map) == 0)
                z++;
            else h++;
            System.out.println(i);
            ffw.write(String.valueOf(t.getNbTour()));
            ffw.write("\n");// écrire une ligne dans le fichier resultat.txt
            //ffw.write(); // forcer le passage à la ligne

        }
        ffw.close(); // fermer le fichier à la fin des traitements
        System.out.println(z);
        System.out.println(h);
    }
}
