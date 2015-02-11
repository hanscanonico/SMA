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

    /**
     * nombre de ligne su terrain
     */
    private int nbRow;
    /**
     * nombre de colonne du terrain
     */
    private int nbCol;
    /**
     * Nombre de tour effetuer
     */
    private int nbTour;
    /**
     * nombre d'etoile sur le terrain
     */
    public static int etoile = 0;
    /**
     * Generateur de nombre aléatoire
     */
    private MersenneTwisterFast ms = new MersenneTwisterFast();

    /**
     * Instance unique du terrain
     */
    private static Terrain terrain;

    /**
     * Acceseur du nombre de tour
     *
     * @return
     */
    public int getNbTour() {
        return nbTour;
    }

    /**
     * Mutateur du nombre de tour
     *
     * @param nbTour
     */
    public void setNbTour(int nbTour) {
        this.nbTour = nbTour;
    }

    /**
     * Nutateur du nombre de ligne
     *
     * @param nbRow
     */
    public void setNbRow(int nbRow) {
        this.nbRow = nbRow;
    }

    /**
     * Mutateur du nombre de colonne
     *
     * @param nbCol
     */
    public void setNbCol(int nbCol) {
        this.nbCol = nbCol;
    }

    /**
     * Matrice des entites
     */
    public Entite[][] map;

    /**
     * Acceseur de la matrice des entite
     *
     * @return
     */
    public Entite[][] getMap() {
        return map;
    }

    /**
     * Acceseur nombre de ligne
     *
     * @return
     */
    public int getNbRow() {
        return nbRow;
    }

    /**
     *Accesseur nombre de colonne
     * @return
     */
    public int getNbCol() {
        return nbCol;
    }

    /**
     * Acceseur a l'instance unique du terrain
     * @param n 
     * @param m
     * @return 
     */
    public static Terrain getInstance(int n, int m) {
        if (terrain == null) {
            terrain = new Terrain(n, m);
        }
        return terrain;
    }

    /**
     * Constructeur privé du terrain
     * @param n nombre de lignes
     * @param m nmbre de colonnes
     */
    private Terrain(int n, int m) {
        map = new Entite[n][m];
        setNbRow(n);
        setNbCol(m);
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

    /**
     * Initialise la matrice en placant à des position aléatoire des humains et des zombie 
     * Avec une proportion de 50/50%
     */
    public void initialiser() {

        nbTour = 0;
        etoile = 0;
        int li, col;
        for (int i = 0; i < 20; i++) {
            li = ms.nextInt(getNbRow());
            col = ms.nextInt(getNbCol());
            if (ms.nextBoolean(0.40)) {
                this.map[li][col] = new Zombie(new Position(li, col), this);

            } else {
                this.map[li][col] = new Humain(new Position(li, col), this);

            }
        }
//        this.map[nbRow / 2][nbCol / 2] = new Humain(new Position(nbRow / 2, nbCol / 2), this);
//        this.map[nbRow / 2][nbCol / 2+1] = new Zombie(new Position(nbRow / 2, nbCol / 2+1), this);
    }

    /**
     * Declare tout les agent de la matrice comme non deplacer
     */
    public void remiseMouvementAZero() {
        for (int i = 0; i < nbRow; i++) {
            for (int j = 0; j < nbCol; j++) {
                if (map[i][j] != null) {
                    map[i][j].setDejaPlace(false);
                }
            }
        }
    }

    /**
     * Compte le nombre d'humain dans la matrice
     * @return 
     */
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

    /**
     * Parcours tout les agent de la matrice et apelle leur methode sedeplacer
     */
    public void deplacerLesAgents() {

        remiseMouvementAZero();
        for (int i = 0; i < nbRow; i++) {
            for (int j = 0; j < nbCol; j++) {

                if (map[i][j] != null && map[i][j] instanceof Agent) {
                    ((Agent) (map[i][j])).seDeplacer();
                }
            }

        }
        boolean place = false;
        nbTour++;
        if (nbTour % 50 == 0 && etoile == 0) {
            do {

                int li, col;
                li = ms.nextInt(getNbRow());
                col = ms.nextInt(getNbCol());
                if (this.map[li][col] == null) {
                    this.map[li][col] = new Etoile(new Position(li, col), this);
                    place = true;
                }

            } while (!place);
            etoile++;
        }
    }

    /**
     * Vide la matrice et replace de nouveau agents
     */
    public void reset() {
        map = new Entite[nbRow][nbCol];
        initialiser();
    }

}
