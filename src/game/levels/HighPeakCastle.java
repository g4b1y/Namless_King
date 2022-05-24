package game.levels;

import game.*;

import java.awt.Rectangle;

public class HighPeakCastle extends Scene
{

    public HighPeakCastle(Window window)
    {
        super(window, 20);
        Scene _this = this;
        
        new NPC(3, _this, true, new Rectangle(1262, 525,350,350), new Runnable(){
            
            @Override
            public void run(){

                Dialog d = new Dialog("continue", _this);
                d.setPrompt("Something weird is going on in the Throne Room");
                d.addOption("Enter (Requires Amulet)", "1", new Runnable(){

                    @Override
                    public void run(){

                        window.setScene(new ThroneRoom(window));

                    }

                });
                d.addOption("Back", "2", new Runnable(){

                    @Override
                    public void run(){

                        d.destroy();
                        window.setScene(new PromenadeOfTheCondemned(window));
                    }

                });
                d.construct();
                d.setVisible(true);


            }

        });

        final NPC me = new NPC(6, _this, true, new Rectangle(380, 882 - 340, 350, 350), null);
        me.setCallback( new Runnable(){
            @Override
            public void run(){
                me.say(Assets.think(window.main));
            }
        });
    }

}
