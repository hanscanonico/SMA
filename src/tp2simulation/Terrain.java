/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2simulation;

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
     public Terrain(int n,int m) {
        map=new Object[m][m];
        setN(n);
        setM(m);
        initialiser();
    }
    
    public void run()
    {
        int l=0;
        while(l<2)
        {
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<m;j++)
                {
                    if(map[i][j] instanceof Agent)
                    {
                        deplacer((Agent)map[i][j]);
                    }
                }    
                
            }
            System.out.println(this);
            l++;
        }
        
    }
    
    public void deplacer(Agent a)
    {
        Position oldPos=a.getPos();
        Position nouv = a.deplacer(n,m);
        
        if(map[nouv.getX()][nouv.getY()]==null)
        {
            map[nouv.getX()][nouv.getY()]=a;
            map[oldPos.getX()][oldPos.getY()]=null;
        }        
     
    }
     
     
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(map[i][j]==null)
                {
                    sb.append("_").append(" ");
                }
                else
                {
                    sb.append(map[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    void initialiser()
    {
        for(int i=0;i<8;i++)
        {
            int aleaN = (int) (Math.random() *(n));
            int aleaM = (int) (Math.random() *(m));
            float alea=(float) Math.random();
            if(alea<0.2) {
                map[aleaN][aleaM]=new Zombie(new Position(aleaN,aleaM));
            }
            else {
                map[aleaN][aleaM]=new Humain(new Position(aleaN,aleaM));
            }
        }
    }
    
}
