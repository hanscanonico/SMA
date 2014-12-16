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
public final class Terrain extends Thread {

    private int nbRow;
    private int nbCol;

    public void setNbRow(int nbRow) {
        this.nbRow = nbRow;
    }

    public void setNbCol(int nbCol) {
        this.nbCol = nbCol;
    }

    public Entite[][] map;

    public int getNbRow() {
        return nbRow;
    }

    public int getNbCol() {
        return nbCol;
    }

    public Terrain(int n, int m) {
        map = new Entite[m][m];
        setNbRow(n);
        setNbCol(m);
    }

//    public void run() throws IOException {
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(this);
//            deplacerLesAgents();
//
//        }
    @Override
    public void run() {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nbRow; i++) {
            for (int j = 0; j < nbCol; j++) {
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

        MersenneTwisterFast ms = new MersenneTwisterFast();
        for (int i = 0; i < nbCol; i++) {
            if (ms.nextBoolean(0.15)) {
                this.map[5][i] = new Zombie(new Position(5, i),this);

            } else {
                this.map[nbRow - 5][i] = new Humain(new Position(nbRow - 5, i),this);

            }
        }
    }

    
    
    
    public void deplacerLesAgents() {
        Agent temp;
        Position nouv;
        HashSet dejaDeplacer;
        dejaDeplacer = new HashSet();
        int cpt;
        for (int i = 0; i < nbRow; i++) {
            for (int j = 0; j < nbCol; j++) {
                
                if(map[i][j] !=null)
                {
                    map[i][j].seDeplacer();
                }
                /*if (map[i][j] != null && !dejaDeplacer.contains((Agent) map[i][j])) {
                    temp = (Agent) map[i][j];
                    cpt = 0;
                    do {
                        nouv = temp.calculNouvellelPosition(nbRow, nbCol);
                        cpt++;
                    } while (map[nouv.x][nouv.y] != null && cpt < 5);

                    if (cpt < 5) {
                        map[i][j] = null;
                        temp.setPos(nouv);
                        map[nouv.x][nouv.y] = temp;

                        if (temp instanceof Zombie) {
                            if (map[(nouv.x + 1) % nbRow][nouv.y] instanceof Humain) {
                                map[(nouv.x + 1) % nbRow][nouv.y] = new Zombie(new Position((nouv.x + 1) % nbRow, nouv.y),this);
                                temp = (Agent) map[(nouv.x + 1) % nbRow][nouv.y];
                                dejaDeplacer.add(temp);
                            } else if (map[(nouv.x - 1 + nbRow) % nbRow][nouv.y] instanceof Humain) {
                                map[(nouv.x - 1 + nbRow) % nbRow][nouv.y] = new Zombie(new Position((nouv.x - 1 + nbRow) % nbRow, nouv.y),this);
                                temp = (Agent) map[(nouv.x - 1 + nbRow) % nbRow][nouv.y];
                                dejaDeplacer.add(temp);
                            } else if (map[nouv.x][(nouv.y + 1) % nbRow] instanceof Humain) {
                                map[nouv.x][(nouv.y + 1) % nbRow] = new Zombie(new Position(nouv.x, (nouv.y + 1) % nbRow),this);
                                temp = (Agent) map[nouv.x][(nouv.y + 1) % nbRow];
                                dejaDeplacer.add(temp);
                            } else if (map[nouv.x][(nouv.y - 1 + nbRow) % nbRow] instanceof Humain) {
                                map[nouv.x][(nouv.y - 1 + nbRow) % nbRow] = new Zombie(new Position(nouv.x, (nouv.y - 1 + nbRow) % nbRow),this);
                                temp = (Agent) map[nouv.x][(nouv.y - 1 + nbRow) % nbRow];
                                dejaDeplacer.add(temp);
                            }
                        }
                    }
                    dejaDeplacer.add(temp);

                }*/
            }

        }
    }

}
