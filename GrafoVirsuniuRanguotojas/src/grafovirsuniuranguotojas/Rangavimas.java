package grafovirsuniuranguotojas;

import java.io.*;
import java.util.*;
import java.math.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author AtsilupeliS
 */
public class Rangavimas {
    List<Rangavimas> rezultatai = new LinkedList<Rangavimas>();
    private Patikrinimas patikrinimas;
    
    int rang;   //Rangas
    int[][] matrix={ { 0, 1, 1, -1, 0, 0 },
                     { -1, 0, 0, 0, 0, 0 },
                     { -1, 0, 0, 0, -1, 0 },
                     { 1, 0, 0, 0, -1, 0 },
                     { 0, 0, 1, 1, 0, 0 },
                     { 0, 0, 0, 0, 0, 0 }
                     };
         //Duomenys, gaunami kaip matrica.
                        //Rezultatai, tikriausiai saraso tipo.
    
        
    };

    public static void imtiDuomenis(){
        //Metodas, gauti duomenims. Gaunama matrica.
        patikrinimas.gautiMatrica();
    }
    
    public static void ranguoti(){
        //Atliekami skaiciavimai, reikalingi suranguoti grafui.
    }
    
    public static void siustiRezultatus(){ 
        //Pateikiami apskaičiuoti rezultatai.
        //Jie pateikiami sarasu.
    }
}
