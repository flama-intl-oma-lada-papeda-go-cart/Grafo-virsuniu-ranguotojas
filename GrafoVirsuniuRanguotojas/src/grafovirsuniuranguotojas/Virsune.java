/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafovirsuniuranguotojas;

/**
 *
 * @author Paulius Šukys
 */
class Virsune 
{
    private int x;
    private int y;
    private int rangas;
    //į kokias viršūnes nurodo briaunos
    private int[] rysiai;
    private boolean moving;
    
    public Virsune()
    {
        x = 0;
        y = 0;
        rangas = -1;
        rysiai = new int[]{-1};
        moving = false;
    }
    public Virsune(int x, int y, int rangas, int[] rysiai, boolean moving)
    {
        this.x = x;
        this.y = y;
        this.rangas = rangas;
        this.rysiai = rysiai;
        this.moving = moving;
    }
    
    public int getX(){ return x;}
    public int getY(){ return y;}
    public int getRangas(){ return rangas;}
    public int[] getRysiai(){ return rysiai;}
    public boolean isMoving(){ return moving;}
    
    public void setX(int x){ this.x = x;}
    public void setY(int y){ this.y = y;}
    public void setRangas(int rangas){ this.rangas = rangas;}
    public void setRysiai(int[] r){ this.rysiai = r;}
    public void setMoving(boolean moving){ this.moving = moving;}
}