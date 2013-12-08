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
        private Patikrinimas patikrinimas;
    
    int[] pradines_virsunes = null;; //pradiniu virsuniu sarasas, pvz [1,2,3]
    int[] rangai;   //masyvas, kur indeksas - virsunes numeris; verte - rangas
    int[][] matrica={ { 0, 1, 1, -1, 0, 0 },
                     { -1, 0, 0, 0, 0, 0 },
                     { -1, 0, 0, 0, -1, 0 },
                     { 1, 0, 0, 0, -1, 0 },
                     { 0, 0, 1, 1, 0, 0 },
                     { 0, 0, 0, 0, 0, 0 }
                     };
         //Duomenys, gaunami kaip matrica.
                        //Rezultatai, tikriausiai saraso tipo.    
        
    

    public void imtiDuomenis(){
        //Metodas, gauti duomenims. Gaunama matrica.
        patikrinimas.gautiMatrica();
    }
    
    public int[] ranguoti(){
        //Atliekami skaiciavimai, reikalingi suranguoti grafui.
    pradines_virsunes = rasti_pradines(matrica);
    int rangas = 0;
    do {
    int[] virsunes;
        virsunes = pradines_virsunes;
    rangas = rangas + 1;
    for (int i=0; i < virsunes.length; i++) {
        rangai[i] = rangas;
    }
    pradines_virsunes = iseinancios_virsunes(virsunes);
    }
    while (pradines_virsunes.length == 0);
    return rangai;
    }
    
    public int[] rasti_pradines(int matrica[][]) {
    boolean pradine = true;
    for (int i=0; i < matrica.length; i++) {       
        for (int j=0; j < matrica[i].length; j++) {
            if (matrica[i][j] < 0)  {
                pradine = false;
            }
        }
        if (pradine == true) {
            pradines_virsunes[pradines_virsunes.length+1]=i;
        }
    }
    return pradines_virsunes;
    }    
    
    public int[] iseinancios_virsunes(int virsunes[]) {
    int[] naujos_virsunes = new int[virsunes.length];
    for (int j=0; j < virsunes.length; j++) {
        for (int i=0; i < matrica.length; i++) {
            if (matrica[virsunes[j]][i] > 0) {
                naujos_virsunes[naujos_virsunes.length+1]=i;
            }
        }
    }
    return naujos_virsunes;
    }
            
    public int[] siustiRezultatus(){ 
        //Pateikiami apskaičiuoti rezultatai.
        return rangai;
    }
}
