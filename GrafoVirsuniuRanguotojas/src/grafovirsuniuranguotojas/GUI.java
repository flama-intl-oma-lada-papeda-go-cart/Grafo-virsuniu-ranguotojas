package grafovirsuniuranguotojas;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

// perimamas logeris
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import java.awt.image.ImageFilter;
import java.io.File;
import javax.swing.JPanel;

/**
 * Grafinės sąsajos klasė
 * @author Paulius Šukys paulius.sukys@ktu.lt
 */
public class GUI implements ActionListener
{
    // Reikalinga lango matmenų nustatymui
    private static final int MIN_PLOTIS = 640;
    private static final int MIN_AUKSTIS = 480;
    private static final double LANGO_KOEF = 0.7;
    
    // Grafinės sąsajos elementai
    protected JFrame pagrLangas;
    protected GrafoPanele grafoPanele;
    
    protected JMenuBar meniuJuosta;
    protected JMenu ivedimoMeniu;
    protected JMenu isvedimoMeniu;
    protected JMenu progMeniu;
    
    // logeris
    protected static final Logger logger = GrafoVirsuniuRanguotojas.LOGGER;
    
    //langas failų pasirinkimui
    JFileChooser fc;
    
    Virsune[] virsunes;
    /**
     * Grąžina kokius lango matmenis reikia parinkti
     * @return ekrano matmenų matrica: [0] - plotis, [1] - aukstis
     * @since 2013-11-24
     */
    private static int[] gautiLangoMatmenis()
    {       
        int[] matmenys = new int[2];
        matmenys[0] = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
        matmenys[1] = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
        // Jeigu tiek esamo ekrano lango aukštis, tiek plotis atitinka platinimo reikalavimus - platinima.
        if ((matmenys[0] * LANGO_KOEF > MIN_PLOTIS) && (matmenys[1] * LANGO_KOEF > MIN_AUKSTIS))
        {
            matmenys[0] *= LANGO_KOEF;
            matmenys[1] *= LANGO_KOEF;
        }else
        {
            matmenys[0] = MIN_PLOTIS;
            matmenys[1] = MIN_AUKSTIS;
        }
        return matmenys;
    }
    
    /**
     * Paruošia meniu juostą
     */
    private void paruostiMeniu()
    {
        meniuJuosta = new JMenuBar();
        
        // informacijos apie programą meniu
        progMeniu = new JMenu("Programa");
        progMeniu.getAccessibleContext().setAccessibleDescription("Programos meniu");
        meniuJuosta.add(progMeniu);
        
        //Vartotojo pagalba
        JMenuItem pagalIrasas = new JMenuItem("Pagalba");
        pagalIrasas.addActionListener((ActionListener)this);
        progMeniu.add(pagalIrasas);
        
        //Apie
        JMenuItem apieIrasas = new JMenuItem("Apie");
        apieIrasas.addActionListener((ActionListener)this);
        progMeniu.add(apieIrasas);
        
        //baigti programos darbą
        JMenuItem isejimoIrasas = new JMenuItem("Išeiti");
        isejimoIrasas.addActionListener((ActionListener)this);
        progMeniu.add(isejimoIrasas);
        
        // Duomenų įvedimas ir jo elementai
        ivedimoMeniu = new JMenu("Duomenų įvedimas");
        ivedimoMeniu.getAccessibleContext().setAccessibleDescription("Duomenų įvedimo meniu");
        meniuJuosta.add(ivedimoMeniu);
        
        // Rezultatų išvedimas ir jo elementai
        isvedimoMeniu = new JMenu("Rezultatų išvedimas");
        isvedimoMeniu.getAccessibleContext().setAccessibleDescription("Rezultatų išvedimo meniu");
        meniuJuosta.add(isvedimoMeniu);
        
        // tekstinių failų submeniu
        JMenuItem isvSub_tekstIrasas = new JMenuItem("Tekstinis failas");
        isvSub_tekstIrasas.addActionListener((ActionListener)this);
        isvedimoMeniu.add(isvSub_tekstIrasas);
        
        // tekstinių failų submeniu
        JMenuItem isvSub_xmlIrasas = new JMenuItem("XML failas");
        isvSub_xmlIrasas.addActionListener((ActionListener)this);
        isvedimoMeniu.add(isvSub_xmlIrasas);
        
        // paveikslėlių submeniu
        JMenuItem isvSub_pavIrasas = new JMenuItem("Paveikslėlis");
        isvSub_pavIrasas.addActionListener((ActionListener)this);
        isvedimoMeniu.add(isvSub_pavIrasas);
        
        
        pagrLangas.setJMenuBar(meniuJuosta);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JMenuItem pasirinkimas = (JMenuItem)(e.getSource());
        //šitas reikalas veikia tik su java7 :)))
        Atvaizdavimas atv;
        JPanel tmp;
        switch(pasirinkimas.getText())
        {
            case "Išeiti":
                logger.log(Level.INFO, "Programos darbo pabaiga.");
                System.exit(0);
                break;
            case "Paveikslėlis":
                logger.log(Level.INFO, "Išsaugojamas grafas į paveikslėlį");
                atv = new Atvaizdavimas(virsunes);
                fc = new JFileChooser();
                javax.swing.filechooser.FileFilter imageFilter = new javax.swing.filechooser.FileFilter()
                {
                    @Override
                    public
                    boolean accept(File pathname)
                    {
                        return pathname.getName().endsWith(".jpg") || pathname.getName().endsWith(".png") || pathname.getName().endsWith(".gif") || pathname.isDirectory();
                    }

                    @Override
                    public
                    String getDescription()
                    {
                        return "PNG, GIF paveikslėlis";
                    }
                };
                fc.addChoosableFileFilter(imageFilter);
                fc.setFileFilter(imageFilter);
                fc.setAcceptAllFileFilterUsed(false);
                tmp = new JPanel();
                int saugojimoAts = fc.showSaveDialog(tmp);
                if (saugojimoAts == JFileChooser.APPROVE_OPTION)
                {
                    logger.log(Level.INFO, "Pasirinkta saugoti į {0}", fc.getSelectedFile());
                    String formatas = gautiFormata(fc.getSelectedFile().toString());
                    atv.kurtiPaveiksla(fc.getSelectedFile().toString(), formatas, grafoPanele);
                }
                break;
            case "Tekstinis failas":
                logger.log(Level.INFO, "Išsaugojamas grafas į tekstinį failą");
                fc = new JFileChooser();
                atv = new Atvaizdavimas(virsunes);
                tmp = new JPanel();
                saugojimoAts = fc.showSaveDialog(tmp);
                if (saugojimoAts == JFileChooser.APPROVE_OPTION)
                {
                    logger.log(Level.INFO, "Pasirinkta saugoti į {0}", fc.getSelectedFile());
                    atv.kurtiTeksta(fc.getSelectedFile().toString());
                }
                break;
            case "XML failas":
                logger.log(Level.INFO, "Išsaugojamas grafas į XML failą");
                fc = new JFileChooser();
                atv = new Atvaizdavimas(virsunes);
                tmp = new JPanel();
                saugojimoAts = fc.showSaveDialog(tmp);
                if (saugojimoAts == JFileChooser.APPROVE_OPTION)
                {
                    logger.log(Level.INFO, "Pasirinkta saugoti į {0}", fc.getSelectedFile());
                    atv.kurtiXML(fc.getSelectedFile().toString());
                }
                break;
        }
    }
    
    /**
     * Nuskaito failo pavadinimą
     * @param file failo pavadinimas
     * @return formatas gif, png arba jpg, (jpg taip pat, jeigu nenurodytas)
     * @since 2013-12-01
     */
    private String gautiFormata(String file)
    {
        if (file.contains("."))
        {
            return file.substring(file.lastIndexOf(".")+1, file.length());
        }else
        {
            return "gif";
        }
    }
    
    /**
     * Paruošia grafinę sąsają, tačiau jos neįjungia
     */
    public void paruostiGUI()
    {
        logger.info("Ruošiama grafinė sąsaja.");
        pagrLangas = new JFrame("Grafo viršūnių ranguotojas");
        pagrLangas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // lango matmenu paruošimas
        int[] m = gautiLangoMatmenis();
        logger.log(Level.INFO, "Lango matmenys: {0}x{1}", new Object[]{Integer.toString(m[0]), Integer.toString(m[1])});
        pagrLangas.setSize(m[0],m[1]);
        
        // meniu paruošimas
        paruostiMeniu();
        grafoPanele = new GrafoPanele(m[0], (int)(m[1]-meniuJuosta.getSize().getHeight()));
        virsunes = new Virsune[]{new Virsune(0,0,0,new int[]{1}, false), 
                                           new Virsune(0,0,1,new int[]{2}, false), 
                                           new Virsune(0,0,2,new int[]{0}, false)};
        grafoPanele.atvaizduotiVirsunes(virsunes);
        pagrLangas.add(grafoPanele);
        pagrLangas.addComponentListener(new ComponentAdapter() 
                {
                    @Override
                    public void componentResized(ComponentEvent e)
                    {
                        int nx = pagrLangas.getWidth();
                        int ny = pagrLangas.getHeight();
                        logger.log(Level.INFO, "Kei\u010diami lango matmenys \u012f {0}x{1}, meniu aukštis: {2}", new Object[]{nx, ny, meniuJuosta.getSize().getHeight()});
                        grafoPanele.keistiDydi(nx, (int)(ny-meniuJuosta.getSize().getHeight()));
                    }
                });
        pagrLangas.setVisible(true);
    }
}
