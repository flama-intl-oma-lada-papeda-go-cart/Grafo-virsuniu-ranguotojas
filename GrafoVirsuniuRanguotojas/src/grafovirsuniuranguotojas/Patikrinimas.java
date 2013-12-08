/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafovirsuniuranguotojas;

/**
 *
 * @author Rimvydas
 */
public class Patikrinimas {
    Generavimas g = new Generavimas();// sukuriamas Generavimas klasės objektas
    private int[][] m = g.gautiMatrica(); // is pofkes
    
    boolean tikrintiMatmenis()
    {
        return false;//kol nera, tol false :)
    }
    
    int[][] gautiMatrica()
    {
        return m;
    }
}
