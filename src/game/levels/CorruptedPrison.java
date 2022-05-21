package game.levels;

import game.*;

import java.awt.Rectangle;

public class CorruptedPrison extends Scene
{

    public CorruptedPrison(Window window)
    {
        super(window, 2);
        Scene _this = this;
        
        ImagePuzzle puzzle = new ImagePuzzle(Assets.absolute(Assets.Backgrounds[3]), _this, 700, 3);
        window.add(puzzle);

        Interactable i = new Interactable(_this, new Rectangle(1009, 731,500,300), new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("continue", _this);
                d.setPrompt("This hole leads into a sewer...");
                d.addOption("Jump", "1", new Runnable(){

                    @Override
                    public void run(){

                        window.setScene(new AncientSewers(window));

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

        Enemy e = new Enemy(2, _this, true, new Rectangle(838, 430, 250, 250), null);
        e.setCallback(new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("fight", _this);
                d.setPrompt("A Caster attacks you! Watch your step!");
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

        final NPC me = new NPC(6, _this, true, new Rectangle(212, 450, 250, 250), null);
        me.setCallback( new Runnable(){
            @Override
            public void run(){
                me.say(Assets.think(window.main));
            }
        });

    }

}
