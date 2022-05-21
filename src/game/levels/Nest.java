package game.levels;

import game.*;

import java.awt.Rectangle;

public class Nest extends Scene
{

    public Nest(Window window)
    {
        super(window, 15);
        Scene _this = this;

        ImagePuzzle puzzle = new ImagePuzzle(Assets.absolute(Assets.Backgrounds[15]), _this, 700, 3);
        window.add(puzzle);

        Interactable i = new Interactable(_this, new Rectangle(1620, 310,300, 600), new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("continue", _this);
                d.setPrompt("The way splits here.");
                d.addOption("Left", "1", new Runnable(){

                    @Override
                    public void run(){

                        window.setScene(new FracturedShrines(window));

                    }

                });
                d.addOption("Right", "2", new Runnable(){

                    @Override
                    public void run(){

                        window.setScene(new ForgottenSepulcher(window));

                    }

                });
                d.construct();
                d.setVisible(true);

            }

        });
        i.setVisible(false);

        Enemy e = new Enemy(5, _this, true, new Rectangle(1339, 632,350,250), null);
        e.setCallback(new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("fight", _this);
                d.setPrompt("A Giant Tick! Icky...");
                d.addOption("Continue", "1", new Runnable(){

                    @Override
                    public void run(){

                        d.destroy();
                        puzzle.start(45000, new Runnable(){
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

        final NPC me = new NPC(6, _this, true, new Rectangle(361, 635, 250, 250), null);
        me.setCallback( new Runnable(){
            @Override
            public void run(){
                me.say(Assets.think(window.main));
            }
        });
    }

}
