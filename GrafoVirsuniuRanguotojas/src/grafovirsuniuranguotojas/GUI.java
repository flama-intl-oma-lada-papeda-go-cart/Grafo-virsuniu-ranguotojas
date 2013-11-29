package grafovirsuniuranguotojas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

// perimamas logeris
import java.util.logging.Logger;

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
    protected JMenuBar meniuJuosta;
    protected JMenu ivedimoMeniu;
    protected JMenu isvedimoMeniu;
    protected JMenu progMeniu;
    
    // logeris
    protected final Logger logger = GrafoVirsuniuRanguotojas.LOGGER;
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
        progMeniu.getAccessibleContext().setAccessibleDescription("Informacija apie programą");
        meniuJuosta.add(progMeniu);
        
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
        JMenu isvSub_tekstMeniu = new JMenu("Tekstinis failas");
        isvedimoMeniu.add(isvSub_tekstMeniu);
        
        // paveikslėlių submeniu
        JMenu isbSub_pavMeniu = new JMenu("Paveikslėlis");
        isvedimoMeniu.add(isbSub_pavMeniu);
        
        pagrLangas.setJMenuBar(meniuJuosta);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JMenuItem pasirinkimas = (JMenuItem)(e.getSource());
        //šitas reikalas veikia tik su java7 :)))
        switch(pasirinkimas.getText())
        {
            case "Išeiti":
                logger.info("Programos darbo pabaiga.");
                System.exit(0);
                break;
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
        logger.info("Lango matmenys: " + Integer.toString(m[0]) +"x" + Integer.toString(m[1]));
        pagrLangas.setSize(m[0],m[1]);
        
        // meniu paruošimas
        paruostiMeniu();
        pagrLangas.setVisible(true);
    }
}
