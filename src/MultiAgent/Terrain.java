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
import java.util.Hashtable;

/**
 *
 * @author canonico
 */
public class Terrain {

    private int n;

    public void setN(int n) {
        this.n = n;
    }

    public void setM(int m) {
        this.m = m;
    }
    private int m;
    Object[][] map;

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

    public void run() throws IOException {

        for (int i = 0; i < 10; i++) {
            System.out.println(this);
            deplacerLesAgents();
            
        }

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

    void initialiser() {
        Humain test = new Humain(new Position(0, 0));
        Zombie z = new Zombie(new Position(4, 4));
        this.map[0][0] = test;
        this.map[4][4] = z;
    }

    private void deplacerLesAgents() {
        Agent temp;
        Position nouv;
        Hashtable  dejaDeplacer=new Hashtable();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j]!=null && !dejaDeplacer.contains((Agent)map[i][j]))
                {
                    temp=(Agent)map[i][j];
                    do
                    {
                        nouv=temp.calculNouvellelPosition(n, m);
                    }while(map[nouv.x][nouv.y]!=null);
                    map[i][j]=null;
                    temp.setPos(nouv);
                    map[nouv.x][nouv.y]=temp;   
                    dejaDeplacer.put(temp.hashCode(), temp);
                }
            }
        }
    }

}
