/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafovirsuniuranguotojas;

import javax.swing.JPanel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shookees
 */
public
        class AtvaizdavimasTest
{
    
    public
            AtvaizdavimasTest()
    {
    }
    
    @BeforeClass
    public static
            void setUpClass()
    {
    }
    
    @AfterClass
    public static
            void tearDownClass()
    {
    }
    
    @Before
    public
            void setUp()
    {
    }
    
    @After
    public
            void tearDown()
    {
    }

    /**
     * Test of kurtiPaveiksla method, of class Atvaizdavimas.
     */
    @Test
    public
    void testKurtiPaveiksla()
    {
        System.out.println("kurtiPaveiksla");
        String pavadinimas = "";
        String formatas = "";
        JPanel grafoPanele = null;
        Atvaizdavimas instance = null;
        instance.kurtiPaveiksla(pavadinimas, formatas, grafoPanele);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of kurtiTeksta method, of class Atvaizdavimas.
     */
    @Test
    public
    void testKurtiTeksta()
    {
        //TODO: testuoti formatavima
        System.out.println("kurtiTeksta");
        String pavadinimas = "";
        Atvaizdavimas instance = null;
        instance.kurtiTeksta(pavadinimas);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of kurtiXML method, of class Atvaizdavimas.
     */
    @Test
    public
    void testKurtiXML()
    {
        //TODO: Reikia sudaryti testa formatui palyginti
        System.out.println("kurtiXML");
        String pavadinimas = "";
        Atvaizdavimas instance = null;
        instance.kurtiXML(pavadinimas);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}