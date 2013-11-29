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
    private int spalva;
    private boolean moving;
    
    public int getX(){ return x;}
    public int getY(){ return y;}
    public int getRangas(){ return rangas;}
    public int[] getRysiai(){ return rysiai;}
    public int getSpalva(){ return spalva;}
    public boolean isMoving(){ return moving;}
    
    public void setX(int x){ this.x = x;}
    public void setY(int y){ this.y = y;}
    public void setRangas(int rangas){ this.rangas = rangas;}
    public void setRysiai(int[] rysiai){ this.rysiai = rysiai;}
    public void setSpalva(int spalva){ this.spalva = spalva;}
    public void setMoving(boolean moving){ this.moving = moving;}
}