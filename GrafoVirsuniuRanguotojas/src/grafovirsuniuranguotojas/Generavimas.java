/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafovirsuniuranguotojas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Povilas...
 */
public class Generavimas {

    private int[][] matrica = new int[3][3];
    int idetabriaunu = 0;


    public void generuotiMatrica(double nulis, double vienetas, double minus_vienetas, int virsuniu_kiekis, int pradiniu_virsuniu, int galutiniu_virsuniu) throws IOException {

        if ((nulis + vienetas + minus_vienetas) != 1 || vienetas != minus_vienetas) {
            System.out.println("Klaida!");
            return;
        }
        if (pradiniu_virsuniu > virsuniu_kiekis) {
            System.out.println("Klaida2!");
            return;
        }


        nulis *= 10;
        vienetas *= 10;
        minus_vienetas *= 10;

        // 1,2,3,4,5,6  7,8   9,10
        // nulis,vienetas,minus_vienetas
        if (((virsuniu_kiekis - pradiniu_virsuniu) <= galutiniu_virsuniu)) {

            for (int x = 0; x < virsuniu_kiekis; x++) {
                for (int y = 0; y < virsuniu_kiekis; y++) {
                    if (x == y) {
                        continue;
                    }

                    if (matrica[x][y] != 0) {
                        continue;
                    }

                    if (idetabriaunu >= pradiniu_virsuniu) {
                        continue;
                    }

                    Random rand = new Random();
                    int sk = rand.nextInt(10) + 1;

                    // if ((sk <= nulis)) {
                    //matrica[x][y] = 0;
                    //} else 
     
                    if ((sk <= nulis)) {
                        matrica[x][y] = 0;
                    } else if (sk <= (nulis + vienetas)) {
                        matrica[x][y] = 1;
                        matrica[y][x] = -1;
                        idetabriaunu++;
                    } else {
                        matrica[x][y] = -1;
                        matrica[y][x] = 1;
                        idetabriaunu++;
                    } 
                }
            }
        }
    }

    public void skaitytiMatricaIsFailo(String failas) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(failas));
        int i = 0;
        int j = 0;
        while (br.ready()) {
            i = 0;
            // j=0;
            String line = br.readLine();
            Scanner sc = new Scanner(line);
            while (sc.hasNext()) {
                matrica[j][i] = sc.nextInt();
                //  System.out.println(matrica[j][i]);
                i++;
            }
            j++;
            //System.out.println(j);
        }

    }

    public void skaitytiMatricaIsEkrano() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = 0;
        int j;

        System.out.println("\n Įveskite eilučių kiekį:");
        j = Integer.parseInt(br.readLine());

        for (int jj = 0; jj < j; jj++) {
            i = 0;

            String line = br.readLine();
            Scanner sc = new Scanner(line);
            while (sc.hasNext()) {
                matrica[jj][i] = sc.nextInt();
                //  System.out.println(matrica[j][i]);
                i++;
            }
        }
        System.out.println("\n Duomenys:");
        for (int s = 0; s < j; s++) {
            for (int ss = 0; ss < j; ss++) {
                System.out.print(matrica[s][ss] + " ");
            }
            System.out.println("");
        }


        System.out.println("\n DONE");

    }

    public int[][] gautiMatrica() {
        return matrica;
    }
}
