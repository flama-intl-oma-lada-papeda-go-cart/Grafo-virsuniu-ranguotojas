package grafovirsuniuranguotojas;

import java.util.LinkedList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Pagrindinė atvaizdavimo klasė
 * @date 2013-10-10
 * @author Paulius Šukys
 */
public class Atvaizdavimas
{
    LinkedList r = gautiRanguotaGrafa();
    
    String konvertuoti_txt(String failas)
    {
        try
        {
            BufferedWriter f = new BufferedWriter(new FileWriter(failas));
        }catch (IOException e)
        {
            //Klaida įrašyme!
        }
        return null;
    }
    
    LinkedList gautiRanguotaGrafa()
    {
        return null;
    }
}
