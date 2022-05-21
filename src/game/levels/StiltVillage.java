package game.levels;

import game.*;

import java.awt.Rectangle;

public class StiltVillage extends Scene
{

    public StiltVillage(Window window)
    {
        super(window, 9);
        Scene _this = this;

        ImagePuzzle puzzle = new ImagePuzzle(Assets.absolute(Assets.Backgrounds[10]), _this, 700, 3);
        window.add(puzzle);
        
        Interactable i = new Interactable(_this, new Rectangle(1619, 465,300,400), new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("continue", _this);
                d.setPrompt("It goes further here.");
                d.addOption("Go further", "1", new Runnable(){

                    @Override
                    public void run(){

                        window.setScene(new ClockTower(window));

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

        Enemy e = new Enemy(0, _this, true, new Rectangle(1162, 512,350, 350), null);
        e.setCallback(new Runnable(){
            
            @Override
            public void run(){

                Dialog d = new Dialog("fight", _this);
                d.setPrompt("A wild aphostate appears! Shouldn't he be in church?");
                d.addOption("Continue", "1", new Runnable(){

                    @Override
                    public void run(){

                        d.destroy();
                        puzzle.start(27500, new Runnable(){
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
