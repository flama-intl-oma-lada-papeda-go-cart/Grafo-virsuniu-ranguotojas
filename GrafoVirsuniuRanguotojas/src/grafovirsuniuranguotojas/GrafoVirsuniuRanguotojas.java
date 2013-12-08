package grafovirsuniuranguotojas;


import java.io.IOException;
import java.io.File;

//loginimui
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;




/**
 * Pagrindinė klasė
 * @since 2013-10-08
 */
public class GrafoVirsuniuRanguotojas
{
    // Skirta loginimui, kad nebūtų nežinojimo dėl ko programa rūksta.
    // protected perduoda subklasėm
    protected final static Logger LOGGER = Logger.getLogger(GrafoVirsuniuRanguotojas.class.getName());
    
    //grafinė sąsaja
    private static GUI g;
    
    /**
     * Logerio paruošimas, saugojama logs aplanke (šalia paleidimo failų) pagal datą ir laiką.
     * @since 2013-11-24
     */
    private static void paruostiLogeri()
    {
        LOGGER.setLevel(Level.INFO);
        DateFormat datosFormatas = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Date data = new Date();
        try
        {
            //patikrina kokioj direktorijoj esame
            //System.out.println(System.getProperty("user.dir"));
            File logDir = new File("logs");
            if (!(logDir.exists() && logDir.isDirectory()))
            {
                logDir.mkdir();
            }
            FileHandler tekstinisFailas = new FileHandler(logDir.getAbsolutePath() + "/" + datosFormatas.format(data) + ".log");
            LOGGER.addHandler(tekstinisFailas);
            LOGGER.info("Logeris sėkmingai paruoštas darbui.");
        }catch(IOException ex)
        {
            System.out.println("Klaida ruošiant logerį!\n" + ex.getMessage());
        }
    }
    
    
    /**
     * Sukuria grafinės sąsajos objekta (g) ir paleidžia grafinę sąsają
     */
    private static void paruostiGUI()
    {
        g = new GUI();
        g.paruostiGUI();
    }
    
    public static void main(String[] args) 
    {
        //FIXME: reikia patobulinti struktūrą duomenų
        paruostiLogeri();
        paruostiGUI();
    }
}
