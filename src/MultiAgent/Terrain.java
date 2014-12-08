/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Hashtable;

/**
 *
 * @author canonico
 */
public class Terrain extends Thread {

    private int n;

    public void setN(int n) {
        this.n = n;
    }

    public void setM(int m) {
        this.m = m;
    }
    int m;
    public Object[][] map;

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public Terrain(int n, int m) {
        map = new Object[m][m];
        setN(n);
        setM(m);
    }

//    public void run() throws IOException {
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(this);
//            deplacerLesAgents();
//
//        }
    public void run() {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == null) {
                    sb.append("_").append(" ");
                } else {
                    sb.append(map[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void initialiser() {
        //Humain test = new Humain(new Position(0, 0));
        //Zombie z = new Zombie(new Position(3, 3));
        //this.map[0][0] = test;
        //this.map[3][3] = z;
        int h1 = 3, h2 = 3, z1 = n - 5, z2 = m - 5;
        MersenneTwisterFast ms = new MersenneTwisterFast();
        for (int i = 0; i < 30; i++) {
            if (ms.nextBoolean(0.15)) {
                this.map[z1 - i % 5][z2] = new Zombie(new Position(z1 - i % 5, z2));
                if (i % 5 == 4) {
                    z2--;
                }
            } else {
                this.map[i][i % 5 + h1 / h2] = new Humain(new Position(i % 5 + h1, h2));
                if (i % 5 == 4) {
                    h2++;
                }

            }
        }
    }

    public void deplacerLesAgents() {
        Agent temp;
        Position nouv;
        HashMap<Integer, Agent> dejaDeplacer;
        dejaDeplacer = new HashMap<>();
        int cpt ;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != null && !dejaDeplacer.containsValue((Agent) map[i][j])) {
                    temp = (Agent) map[i][j];
                    cpt=0;
                    do {
                        nouv = temp.calculNouvellelPosition(n, m);
                        cpt++;
                    } while (map[nouv.x][nouv.y] != null && cpt < 5);
                   
                    if (cpt < 5) {
                        map[i][j] = null;
                        temp.setPos(nouv);
                        map[nouv.x][nouv.y] = temp;

                        if (temp instanceof Zombie) {
                            if (map[(nouv.x + 1) % n][nouv.y] instanceof Humain) {
                                map[(nouv.x + 1) % n][nouv.y] = new Zombie(new Position((nouv.x + 1) % n, nouv.y));
                                temp = (Agent) map[(nouv.x + 1) % n][nouv.y];
                                dejaDeplacer.put(temp.hashCode(), temp);
                            } else if (map[(nouv.x - 1 + n) % n][nouv.y] instanceof Humain) {
                                map[(nouv.x - 1 + n) % n][nouv.y] = new Zombie(new Position((nouv.x - 1 + n) % n, nouv.y));
                                temp = (Agent) map[(nouv.x - 1 + n) % n][nouv.y];
                                dejaDeplacer.put(temp.hashCode(), temp);
                            } else if (map[nouv.x][(nouv.y + 1) % n] instanceof Humain) {
                                map[nouv.x][(nouv.y + 1) % n] = new Zombie(new Position(nouv.x, (nouv.y + 1) % n));
                                temp = (Agent) map[nouv.x][(nouv.y + 1) % n];
                                dejaDeplacer.put(temp.hashCode(), temp);
                            } else if (map[nouv.x][(nouv.y - 1 + n) % n] instanceof Humain) {
                                map[nouv.x][(nouv.y - 1 + n) % n] = new Zombie(new Position(nouv.x, (nouv.y - 1 + n) % n));
                                temp = (Agent) map[nouv.x][(nouv.y - 1 + n) % n];
                                dejaDeplacer.put(temp.hashCode(), temp);
                            }
                        }
                    }
                    dejaDeplacer.put(temp.hashCode(), temp);

                }
            }

        }
    }

}
