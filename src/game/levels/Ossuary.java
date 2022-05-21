package game.levels;

import game.*;

import java.awt.Rectangle;

public class Ossuary extends Scene
{

    public Ossuary(Window window)
    {
        super(window, 5);
        Scene _this = this;

        ImagePuzzle puzzle = new ImagePuzzle(Assets.absolute(Assets.Backgrounds[6]), _this, 700, 3);
        window.add(puzzle);
        
        Interactable i = new Interactable(_this, new Rectangle(1574, 385,350,500), new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("continue", _this);
                d.setPrompt("It goes further here.");
                d.addOption("Go on", "1", new Runnable(){
                    
                    public void run(){

                        window.setScene(new BlackBridge(window));

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

        Enemy e = new Enemy(2, _this, true, new Rectangle(1177, 468,350,350), null);
        e.setCallback(new Runnable(){
            
            @Override
            public void run(){

                Dialog d = new Dialog("fight", _this);
                d.setPrompt("You get attacked by a Caster!! Watch your head!");
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
