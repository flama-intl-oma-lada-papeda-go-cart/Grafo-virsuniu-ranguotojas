package grafovirsuniuranguotojas;

import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



/**
 * Grafų atvaizdavimo panelės klasė
 * @date 2013-11-29
 * @author Paulius Šukys
 */
public class GrafoPanele extends JPanel
{
    private Virsune[] virsunes;
    
    /**
     * Paruošia grafų atvaizdavimo panelę
     * @since 2013-11-29
     */
    public GrafoPanele()
    {
        addMouseListener(new MouseAdapter()
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
                        int x = e.getX() - 10;
                        int y = e.getY() - 10;
                        for (Virsune vk : virsunes) 
                        {
                            if (!vk.isMoving()) 
                            {
                                int dx = vk.getX() - x;
                                int dy = vk.getY() - y;
                                if ((dx*dx+dy*dy<=20*20)) 
                                {
                                    move = false;
                                    break;
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
        });
    }
    
    private void paruostiVirsunes()
    {
        
    }
}
