package game.levels;

import game.Dialog;
import game.Scene;
import game.Window;

import java.awt.Color;

public class YouDied extends Scene
{

    public YouDied(Window window)
    {
        super(window, 23);

        window.getRootPane().setBackground(Color.black);
        floorColor = Color.black;

        Scene _this = this;
        Dialog d          = new Dialog("continue", _this);
        d.setPrompt("<html>You died!</html>");
        d.addOption("Continue", "space", new Runnable(){
            @Override
            public void run()
            {

                Scene start = new PrisonQuarters(window);
                window.setScene(start);

            }

        });
        d.addOption("Exit Game", "esc", new Runnable(){
            @Override
            public void run()
            {

                System.exit(0);

            }

        });
        d.setTime(5000);
        d.onTimeExpire(new Runnable(){
            @Override
            public void run()
            {
                Scene start = new PrisonQuarters(window);
                window.setScene(start);
            }
        });
        d.construct();
        d.setVisible(true);
    }
    
    public void resize(){}

}
