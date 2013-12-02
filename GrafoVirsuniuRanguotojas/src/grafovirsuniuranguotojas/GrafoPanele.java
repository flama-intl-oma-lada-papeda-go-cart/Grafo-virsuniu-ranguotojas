package grafovirsuniuranguotojas;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.MouseInputAdapter;


/**
 * Grafų atvaizdavimo panelės klasė
 * @date 2013-11-29
 * @author Paulius Šukys
 */
public class GrafoPanele extends JPanel
{
    int plotis;
    int ilgis;
    Virsune[] virsunes;
    protected static final Logger logger = GrafoVirsuniuRanguotojas.LOGGER;
    /**
     * Paruošia grafų atvaizdavimo panelę
     * @since 2013-11-29
     */
    public GrafoPanele(int plo, int ilg)
    {
        plotis = plo;
        ilgis = ilg;
        MouseInputAdapter mia;
        mia = new MouseInputAdapter()
{
@Override
public void mousePressed(MouseEvent e) 
{
for (Virsune v : virsunes) 
{
  if (new java.awt.Rectangle(v.getX(), v.getY(),20,20).contains(e.getX(), e.getY())) 
  {
      v.setMoving(true);
  }
}
}

@Override
public void mouseReleased(MouseEvent e) 
{
for (Virsune v : virsunes) 
{
  v.setMoving(false);
}
}

@Override
public void mouseDragged(MouseEvent e) 
{

for (Virsune v : virsunes) 
{
  if (v.isMoving()) 
  {
      boolean move = true;
      int x = e.getX();
      int y = e.getY();
      if(x < 0 || x > plotis-20 || y < 0 || y > ilgis-20)//20 - virsunes skersmuo atvaizduojant
      {
          logger.log(Level.WARNING, "Bandoma viršūnę nustumti už ribų");
          move = false;
      }else
      {
          for (Virsune vk : virsunes) 
          {
              if (!vk.isMoving()) 
              {
                  int dx = vk.getX() - x;//centras
                  int dy = vk.getY() - y;//centras
                  if ((dx*dx+dy*dy<=20*20)) 
                  {
                      logger.log(Level.WARNING, "Bandoma viršūnę uždėti ant kitos viršūnės");
                      move = false;
                      break;
                  }
              }
          }
      }
      if (move) 
      {
          v.setX(x);
          v.setY(y);
          repaint();
      }
      break;
  }
}
}
};
        addMouseListener(mia);
        addMouseMotionListener(mia);
    }
    
    public void keistiDydi(int plo, int ilg)
    {
        plotis=plo;
        ilgis=ilg;
    }
    
    public void atvaizduotiVirsunes(Virsune[] virs)
    {
        //virsuniu manipuliavimui veliau prireiks
        virsunes=virs;
        int i = 0;
        for (Virsune v : virsunes)
        {
            v.setX((int) ((plotis/2-100) * Math.cos((2*Math.PI/virsunes.length)*(i+1)) + plotis/2));
            v.setY((int) ((ilgis/2-100) * Math.sin((2*Math.PI/virsunes.length)*(i+1)) + ilgis/2));
            i++;
        }
    }
    
    @Override
    public Dimension getPreferredSize() 
    {
       return new Dimension(plotis, ilgis);
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);       
        //nupaiso rysius
        for (Virsune v : virsunes) 
        {
            for (int rysys : v.getRysiai()) 
            {
                g.setColor(Color.BLACK);
                g.drawLine(v.getX()+10, v.getY()+10, virsunes[rysys].getX()+10, virsunes[rysys].getY()+10);
                piestiRodykle(g, v.getX()+10, v.getY()+10, virsunes[rysys].getX()+10, virsunes[rysys].getY()+10);
            }
        }
        int i = 0;
        for (Virsune v : virsunes) 
        {
            g.setColor(Color.DARK_GRAY);
            g.fillOval(v.getX(), v.getY(), 20, 20);
            g.setColor(Color.WHITE);
            int raidziuPlotis = 3*((int)(v.getRangas()/10)+1);
            g.drawString(Integer.toString(i), v.getX()+((20-raidziuPlotis)/2), v.getY()+15);
            i++;
        }

    } 
    
    /**
     * 
     * @param g grafikos modelis
     * @param teta tangente
     * @param x0 galo koordinate x
     * @param y0 galo koordinate y
     */
    private void piestiRodykle(Graphics g, int x0, int y0, int x1, int y1)
    {
        final int RODYKLES_ILGIS = 20;
        double RODYKLES_KAMPAS = Math.toRadians(30);
        int dx = x1 - x0;
        int dy = y1 - y0;

        double theta = Math.atan2(dy, dx);

        double x = x1 - RODYKLES_ILGIS * Math.cos(theta + RODYKLES_KAMPAS);
        double y = y1 - RODYKLES_ILGIS * Math.sin(theta + RODYKLES_KAMPAS);

        RODYKLES_KAMPAS = Math.toRadians(-30);//-35 angle, can be adjusted
        double x2 = x1 - RODYKLES_ILGIS * Math.cos(theta + RODYKLES_KAMPAS);
        double y2 = y1 - RODYKLES_ILGIS * Math.sin(theta + RODYKLES_KAMPAS);

        int[] arrowYs = new int[3];
        arrowYs[0] = y1;
        arrowYs[1] = (int) y;
        arrowYs[2] = (int) y2;

        int[] arrowXs = new int[3];
        arrowXs[0] = x1;
        arrowXs[1] = (int) x;
        arrowXs[2] = (int) x2;

        g.fillPolygon(arrowXs, arrowYs, 3);
    }
}
