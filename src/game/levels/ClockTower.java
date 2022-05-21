package game.levels;

import game.*;

import java.awt.Rectangle;

public class ClockTower extends Scene
{

    public ClockTower(Window window)
    {
        super(window,10);
        Scene _this = this;

        ImagePuzzle puzzle = new ImagePuzzle(Assets.absolute(Assets.Backgrounds[11]), _this, 700, 3);
        window.add(puzzle);
        
        Interactable i = new Interactable(_this, new Rectangle(1574, 385,350,500), new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("continue", _this);
                d.setPrompt("Oh, a ladder... Should I climb up?");
                d.addOption("Climb up", "1", new Runnable(){
                    
                    public void run(){

                        window.setScene(new ClockRoom(window));

                    }

                });
                d.addOption("Stay", "2", new Runnable(){

                    public void run(){

                        d.destroy();

                    }

                });
                d.construct();
                d.setVisible(true);

            }

        });
        i.setVisible(false);

        Enemy e = new Enemy(10, _this, true, new Rectangle(1200, 882 - 340,250,150), null);
        e.setCallback(new Runnable(){
            
            @Override
            public void run(){

                Dialog d = new Dialog("fight", _this);
                d.setPrompt("A pesky demon attacks you! Make sure to banish it to the depths of hell!");
                d.addOption("Continue", "1", new Runnable(){

                    @Override
                    public void run(){

                        d.destroy();
                        puzzle.start(40000, new Runnable(){
                            @Override
                            public void run(){
                                i.setVisible(true);
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
