package game.levels;

import game.*;

import java.awt.Rectangle;

public class BlackBridge extends Scene
{

    public BlackBridge(Window window)
    {
        super(window, 6);
        Scene _this = this;

        ImagePuzzle puzzle = new ImagePuzzle(Assets.absolute(Assets.Backgrounds[9]), _this, 700, 3);
        window.add(puzzle);

        Interactable i = new Interactable(_this, new Rectangle(1631, 85, 300, 800), new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("continue", _this);
                d.setPrompt("There are multiple parts here, which one should I choose...");
                d.addOption("Left", "1", new Runnable(){

                    @Override
                    public void run(){

                        window.setScene(new Graveyard(window));

                    }

                });
                d.addOption("Right", "2", new Runnable(){

                    @Override
                    public void run(){

                        window.setScene(new StiltVillage(window));

                    }

                });
                d.construct();
                d.setVisible(true);

            }

        });
        i.setVisible(false);

        Enemy e = new Enemy(15, _this, true, new Rectangle(1099, 312, 600, 600), null);
        e.setCallback(new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("fight", _this);
                d.setPrompt("The ground starts to shake...");
                d.addOption("Continue", "1", new Runnable(){

                    @Override
                    public void run(){
                        d.destroy();
                        Dialog d = new Dialog("fight", _this);
                        d.setPrompt("A huge unhuman being appears before you! He doesn't seem friendly!");
                        d.addOption("Attack", "1", new Runnable(){

                            @Override
                            public void run(){

                                d.destroy();
                                puzzle.start(40000, new Runnable(){
                                    @Override
                                    public void run(){
                                        i.setVisible(true);
                                        e.destroy();
                                        puzzle.destroy();

                                       Dialog d = new Dialog("continue", _this);
                                        d.setPrompt("Continue");
                                        d.addOption("Continue", "1", new Runnable() {
                                            @Override
                                            public void run() {
                                                window.setScene(new PrisonQuarters(window));
                                            }
                                        });
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
                d.construct();
                d.setVisible(true);

            }

        });

        final NPC me = new NPC(6, _this, true, new Rectangle(314, 546, 350, 350), null);
        me.setCallback( new Runnable(){
            @Override
            public void run(){
                me.say(Assets.think(window.main));
            }
        });
    }

}
