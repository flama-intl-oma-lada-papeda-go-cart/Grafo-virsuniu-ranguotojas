package grafovirsuniuranguotojas;

import java.awt.AWTException;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
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
    /**
     * Sukuria paveikslą iš turimo ranguoto sąrašo
     * @param pavadinimas
     * @return 
     */
    String kurtiPaveiksla(String pavadinimas, String formatas, JPanel grafoPanele)
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
        return null;
    }
    /**
     * Sukuria tekstinį failą iš turimo ranguoto sąrašo.
     * @param pavadinimas
     * @return 
     */
    String kurtiTeksta(String pavadinimas)
    {
        try
        {
            
        }catch (Exception e)
        {
            
        }
        return null;
    }
    /**
     * Sukuria java programėlę, kur galima interaktyviai peržiūrėti ranguotą sąrašą.
     * @param pavadinimas
     * @return 
     */
    String kurtiApp(String pavadinimas)
    {
        try
        {
            
        }catch (Exception e)
        {
            
        }
        return null;
    }
}
