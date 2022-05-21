package game.levels;

import game.*;

import java.awt.Rectangle;

public class DilapidatedArboretum extends Scene
{

    public DilapidatedArboretum(Window window)
    {
        super(window, 12);
        Scene _this = this;

        ImagePuzzle puzzle = new ImagePuzzle(Assets.absolute(Assets.Backgrounds[13]), _this, 700, 3);
        window.add(puzzle);

        Interactable i = new Interactable(_this, new Rectangle(1627, 76,300, 800), new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("continue", _this);
                d.setPrompt("The way splits here.");
                d.addOption("Left", "1", new Runnable(){

                    @Override
                    public void run(){

                        window.setScene(new MorassOfTheBanished(window));

                    }

                });
                d.addOption("Right", "2", new Runnable(){

                    @Override
                    public void run(){

                        window.setScene(new PrisonDepths(window));

                    }

                });
                d.construct();
                d.setVisible(true);

            }

        });
        i.setVisible(false);

        Enemy e = new Enemy(14, _this, true, new Rectangle(963, 515,350,350), null);
        e.setCallback(new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("fight", _this);
                d.setPrompt("That's a yeeter! Watch out, it wouldn't be good if he got ahold of you!");
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

        final NPC me = new NPC(6, _this, true, new Rectangle(380, 882 - 340, 350, 350), null);
        me.setCallback( new Runnable(){
            @Override
            public void run(){
                me.say(Assets.think(window.main));
            }
        });
    }

}
