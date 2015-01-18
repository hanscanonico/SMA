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

    public Entite[][] getMap() {
        return map;
    }

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
//        this.map[nbRow / 2][nbCol / 2] = new Humain(new Position(nbRow / 2, nbCol / 2), this);
//        this.map[nbRow / 2][nbCol / 2+1] = new Zombie(new Position(nbRow / 2, nbCol / 2+1), this);
    }

    public void remiseMouvementAZero() {
        for (int i = 0; i < nbRow; i++) {
            for (int j = 0; j < nbCol; j++) {
                if (map[i][j] != null) {
                    map[i][j].setDejaPlace(false);
                }
            }
        }
    }

    public int compterNbHumains() {

        int nbHumains = 0;
        for (int i = 0; i < nbRow; i++) {
            for (int j = 0; j < nbCol; j++) {

                if (map[i][j] instanceof Humain) {
                    nbHumains++;
                }
            }
        }
        return nbHumains;
    }

    public void deplacerLesAgents() {

        remiseMouvementAZero();
        for (int i = 0; i < nbRow; i++) {
            for (int j = 0; j < nbCol; j++) {

                if (map[i][j] != null && map[i][j] instanceof Agent) {
                    ((Agent)(map[i][j])).seDeplacer();
                }
            }

        }
    }

}
