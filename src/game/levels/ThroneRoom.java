package game.levels;

import game.*;

import java.awt.Rectangle;

public class ThroneRoom extends Scene
{

    public ThroneRoom(Window window)
    {
        super(window, 21);
        Scene _this = this;

        ImagePuzzle puzzle = new ImagePuzzle(Assets.absolute(Assets.Backgrounds[25]), _this, 700, 6);
        window.add(puzzle);
        puzzle.prepTime = 5000;

        Enemy e = new Enemy(18, _this, true, new Rectangle(873, 391, 800, 500), null);
        e.setCallback(new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("fight", _this);
                d.setPrompt("The Hand of the King rises as he prepares to attack you!");
                d.addOption("Fight", "1", new Runnable(){

                    @Override
                    public void run(){

                        d.destroy();
                        puzzle.start(195000, new Runnable(){
                            @Override
                            public void run(){
                                e.destroy();
                                puzzle.destroy();
                            }
                        }, new Runnable(){
                            @Override
                            public void run(){
                                window.setScene(new YouDied(window));
                            }
                        });

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
