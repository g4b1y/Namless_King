package game;

import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;


public class LevelEditor extends MouseInputAdapter {

    public Component element;
    public boolean dragging;

    private Window win;

    public int startX;
    public int startY;
    public int endX;
    public int endY;
    public int offsetX;
    public int offsetY;

    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (dragging){

            element.setBounds(x - offsetX,(y - offsetY) - 30,element.getWidth(),element.getHeight());

        }
    }

    public void mouseReleased(MouseEvent e) {

        if (dragging){

            int x = e.getX();
            int y = e.getY();

            endX  = x;
            endY  = y;

            double mx = (double) Assets.refrenceRect.width /  (double) win.getBounds().width;
            double my = (double) Assets.refrenceRect.height / (double) win.getBounds().height;

            dragging = false;
            Debugging.log(String.format("New Position of %s: %s, %s", element.getClass().getSimpleName(), (int) Math.floor(element.getBounds().x * mx), (int) Math.floor((element.getBounds().y + 110) * my)));
        
        }

    }

    public void startDragging(Component c, int x, int y){

        Debugging.log(String.format("Started Dragging element (%s)", c.getClass().getSimpleName()));

        dragging = true;
        element  = c;
        startX   = x;
        startY   = y;
        offsetX  = x;
        offsetY  = y;

    }

    public LevelEditor (Window win){

        this.win = win;
        Debugging.log("Level Editor initilized.");

    }

}
