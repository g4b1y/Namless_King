package game.levels;

import game.*;

import java.awt.Rectangle;

public class Graveyard extends Scene
{

    public Graveyard(Window window)
    {
        super(window, 7);
        Scene _this = this;
        
        ImagePuzzle puzzle = new ImagePuzzle(Assets.absolute(Assets.Backgrounds[8]), _this, 700, 3);
        window.add(puzzle);

        Interactable i = new Interactable(_this, new Rectangle(1622, 367, 300, 500), new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("continue", _this);
                d.setPrompt("There's a steep cliff here. (Requires Spiked Boots)");
                d.addOption("Climb", "1", new Runnable(){

                    @Override
                    public void run(){

                        window.setScene(new Cavern(window));

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

        Enemy e = new Enemy(2, _this, true, new Rectangle(1189, 424, 300, 300), null);
        e.setCallback(new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("fight", _this);
                d.setPrompt("There's a Caster! Didn't I beat enough of them already?");
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
                                _this.window.main.inventory.add(6);
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
