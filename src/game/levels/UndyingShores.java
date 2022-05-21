package game.levels;

import game.*;

import java.awt.Rectangle;

public class UndyingShores extends Scene
{

    public UndyingShores(Window window)
    {
        super(window, 18);
        Scene _this = this;

        ImagePuzzle puzzle = new ImagePuzzle(Assets.absolute(Assets.Backgrounds[19]), _this, 700, 3);
        window.add(puzzle);

        Interactable i = new Interactable(_this, new Rectangle(1627, 250,300, 600), new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("continue", _this);
                d.setPrompt("It goes further here.");
                d.addOption("Go on", "1", new Runnable(){

                    @Override
                    public void run(){

                        window.setScene(new Mausoleum (window));

                    }

                });
                d.addOption("Stay", "2", new Runnable(){

                    @Override
                    public void run(){

                        d.destroy();

                    }

                });
                d.construct();
                d.setVisible(true);

            }

        });
        i.setVisible(false);

        Enemy e = new Enemy(0, _this, true, new Rectangle(980, 524, 350, 350), null);
        e.setCallback(new Runnable(){
         
            @Override
            public void run(){

                Dialog d = new Dialog("fight", _this);
                d.setPrompt("An Aphostate... Shouldn't he be in church?");
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
                                window.main.inventory.add(0);
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

        final NPC me = new NPC(6, _this, true, new Rectangle(380, 524, 350, 350), null);
        me.setCallback( new Runnable(){
            @Override
            public void run(){
                me.say(Assets.think(window.main));
            }
        });
    }
    
}
