/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiAgent;

/**
 *
 * @author canonico
 */
public final class Terrain extends Thread {

    private int nbRow;
    private int nbCol;
    private int nbTour;
    public static int etoile=0;

    private static Terrain terrain;
    public int getNbTour() {
        return nbTour;
    }

    public void setNbTour(int nbTour) {
        this.nbTour = nbTour;
    }
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

    public static Terrain getInstance(int n,int m)
    {
        if(terrain==null)
        {
            terrain=new Terrain(n, m);
        }
        return terrain;
    }
    private Terrain(int n, int m) {
        map = new Entite[n][m];
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

        nbTour=0;
        etoile=0;
        MersenneTwisterFast ms = new MersenneTwisterFast();
        MersenneTwisterFast ms2 = new MersenneTwisterFast();
        int li, col;
        for (int i = 0; i < 20; i++) {
            li = ms2.nextInt(getNbRow());
            col = ms2.nextInt(getNbCol());
            if (ms.nextBoolean(0.40)) {
                this.map[li][col] = new Zombie(new Position(li, col), this);

            } else {
                this.map[li][col] = new Humain(new Position(li, col), this);

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
                    ((Agent) (map[i][j])).seDeplacer();
                }
            }

        }
        boolean place=false;
        nbTour++;
        if(nbTour%50==0 && etoile==0)
        {
            do{
                 MersenneTwisterFast ms = new MersenneTwisterFast();
                 int li, col;
                 li = ms.nextInt(getNbRow());
                 col = ms.nextInt(getNbCol());
                 if(this.map[li][col]==null)
                 {
                    this.map[li][col]=new Etoile(new Position(li, col), this);
                    place=true;
                 }

                 
            }while (!place);
            etoile ++;
        }
    }

    public void reset() {
       map = new Entite[nbRow][nbCol];
       initialiser();
    }

}
