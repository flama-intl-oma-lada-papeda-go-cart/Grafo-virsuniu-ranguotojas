package grafovirsuniuranguotojas;


import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * Pagrindinė atvaizdavimo klasė
 * @date 2013-10-10
 * @author Paulius Šukys
 */
public class Atvaizdavimas
{
    protected static final Logger logger = GrafoVirsuniuRanguotojas.LOGGER;
    private Virsune[] virsunes;
    
    public Atvaizdavimas(Virsune v[])
    {
        virsunes = v;
    }
    
    /**
     * Sukuria paveikslą iš turimo ranguoto sąrašo
     * @param pavadinimas kelias iki paveiksliuko
     * @param formatas paveiksliuko formatas
     * @param grafoPanele panelė, kurios atvaizdas bus saugojamas
     * @return 
     * @since 2013-10-10
     */
    void kurtiPaveiksla(String pavadinimas, String formatas, JPanel grafoPanele)
    {
        try
        {
            File failas = new File(pavadinimas);
            if (!failas.exists())
            {
                failas.createNewFile();
            }
            BufferedImage pav = new BufferedImage(grafoPanele.getWidth(),
                grafoPanele.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
            grafoPanele.paint(pav.getGraphics());
            ImageIO.write(pav, formatas, failas);
        }catch (IOException e)
        {
            logger.log(Level.ALL, "Klaida kuriant paveikslėlį: {0}\n{1}", new Object[]{e.getCause(), e.getStackTrace()});
        }
    }
    /**
     * Sukuria tekstinį failą iš turimo ranguoto sąrašo.
     * @param pavadinimas kelias iki failo
     * @return 
     * @since 2013-10-10
     */
    void kurtiTeksta(String pavadinimas)
    {
        try
        {
            File failas = new File(pavadinimas);
            BufferedWriter failoSrautas = new BufferedWriter(new FileWriter(failas));
            int i = 1;
            for(Virsune v : virsunes)
            {
                failoSrautas.write("Viršūnė #" + i + ", rangas: " + v.getRangas() + "\n");
                i++;
            }
            failoSrautas.close();
        }catch (IOException e)
        {
            logger.log(Level.ALL, "Klaida įrašant duomenis į failą: {0}\n{1}", new Object[]{e.getCause().toString(), e.getStackTrace().toString()});
        }
    }
    
    /**
     * Sukuria tekstinį failą, kur duomenys išdėstyti XML formatu
     * @param pavadinimas kelias iki failo
     */
    void kurtiXML(String pavadinimas)
    {
        try
        {
            int pavGalas = pavadinimas.length();
            if (!pavadinimas.substring(pavGalas-3, pavGalas).equals("xml"))
            {
                logger.info("Nenurodyta xml galūnė - pridedame");
                pavadinimas += ".xml";
            }
            File failas = new File(pavadinimas);
            BufferedWriter failoSrautas = new BufferedWriter(new FileWriter(failas));
            int i = 1;
            failoSrautas.write("<grafas>\n");
            for(Virsune v : virsunes)
            {
                failoSrautas.write("    <virsune numeris=\"" + i + "\" rangas=\"" + v.getRangas() + "\">\n");
                failoSrautas.write("         <rysiai>\n");
                for(int r : v.getRysiai())
                {
                    failoSrautas.write("             <rysys>\n");
                    failoSrautas.write("                 " + r + "\n");
                    failoSrautas.write("             </rysys>\n");
                }
                failoSrautas.write("         </rysiai>\n");
                failoSrautas.write("    </virsune>\n");
                i++;
            }
            failoSrautas.write("</grafas>\n");
            failoSrautas.close();
        }catch (IOException e)
        {
            logger.log(Level.ALL, "Klaida įrašant duomenis į failą: {0}\n{1}", new Object[]{e.getCause().toString(), e.getStackTrace().toString()});
        }
    }
}
