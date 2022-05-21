package game.levels;

import game.*;

import java.awt.Rectangle;

public class ToxicSewers extends Scene
{

    public ToxicSewers(Window window)
    {
        super(window, 1);
        Scene _this = this;

        ImagePuzzle puzzle = new ImagePuzzle(Assets.absolute(Assets.Backgrounds[2]), _this, 700, 3);
        window.add(puzzle);

        Interactable i = new Interactable(_this, new Rectangle(1627, 200, 300, 500),  new Runnable(){
            @Override
            public void run(){
                Dialog d          = new Dialog("continue", _this);
                d.setPrompt("<html>There's a staircase here... Which way should I go?</html>");
                d.addOption("Up", "1", new Runnable(){
                    @Override
                    public void run()
                    {

                        window.setScene(new CorruptedPrison(window));

                    }

                });
                d.addOption("Down", "2", new Runnable(){
                    @Override
                    public void run()
                    {

                        window.setScene(new AncientSewers(window));

                    }

                });
                d.addOption("Stay", "3", new Runnable(){
                    @Override
                    public void run()
                    {

                        d.destroy();

                    }

                });
                d.construct();
                d.setVisible(true);
            }
        });
        i.setVisible(false);

        final NPC me = new NPC(6, _this, true, new Rectangle(425, 454, 250, 250), null);
        me.setCallback( new Runnable(){
            @Override
            public void run(){
                me.say(Assets.think(window.main));
            }
        });

        Enemy e = new Enemy(11, _this, true, new Rectangle(916, 380, 300, 300), null);
        e.setCallback(new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("fight", _this);
                d.setPrompt("A Ghost attacks you! Spooky...");
                d.addOption("Continue", "1", new Runnable(){

                    @Override
                    public void run(){

                        d.destroy();
                        puzzle.start(50000, new Runnable(){
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

    }

}
