package game.levels;

import game.*;

import java.awt.Rectangle;

public class PromenadeOfTheCondemned extends Scene
{

    public PromenadeOfTheCondemned(Window window)
    {
        super(window, 0);
        Scene _this = this;

        new Interactable(_this, new Rectangle(858, 314, 200, 400),  new Runnable(){
            @Override
            public void run(){

                Dialog d          = new Dialog("continue", _this);
                if (!window.main.context.has("potc-collar-dialog")){

                    d.setPrompt("<html>Looks like that guy wasn't as lucky</html>");
                    d.addOption("Check for loot", "1", new Runnable(){
                        @Override
                        public void run()
                        {

                            window.main.context.add("potc-collar-dialog");
                            window.getContentPane().remove(d);
                            window.getContentPane().repaint();
                            Dialog d          = new Dialog("continue", _this);
                            d.setPrompt("<html>A Prison Collar... Guess that's mine now?!</html>");
                            d.addOption("Take", "1", new Runnable(){
                                @Override
                                public void run()
                                {

                                    window.main.inventory.add(4);
                                    window.getContentPane().remove(d);
                                    window.getContentPane().repaint();

                                }

                            });
                            d.addOption("Leave", "2", new Runnable(){
                                @Override
                                public void run()
                                {

                                    window.getContentPane().remove(d);
                                    window.getContentPane().repaint();

                                }

                            });
                            d.construct();
                            d.setVisible(true);

                        }

                    });
                    d.addOption("Leave", "2", new Runnable(){
                        @Override
                        public void run()
                        {

                            window.main.context.add("potc-collar-dialog");
                            window.getContentPane().remove(d);
                            window.getContentPane().repaint();

                        }

                    });

                } else {

                    d.setPrompt("I've already checked that.");
                    d.addOption("Continue", "1", new Runnable(){
                        @Override
                        public void run()
                        {

                            window.getContentPane().remove(d);
                            window.getContentPane().repaint();

                        }

                    });

                }
                d.construct();
                d.setVisible(true);
            }
        });

        final NPC me = new NPC(6, _this, true, new Rectangle(49, 373, 280, 280), null);
        me.setCallback( new Runnable(){
            @Override
            public void run(){
                me.say(Assets.think(window.main));
            }
        });

        new Interactable(_this, new Rectangle(1564, 140, 400, 500),  new Runnable(){
            @Override
            public void run(){
                Dialog d          = new Dialog("continue", _this);
                d.setPrompt("<html>It looks like it goes further here...</html>");
                d.addOption("Go further", "1", new Runnable(){
                    @Override
                    public void run()
                    {


                        window.getContentPane().remove(d);
                        window.getContentPane().repaint();

                        Dialog d          = new Dialog("continue", _this);
                        d.setPrompt("<html>The way splits here... Where should I go?</html>");
                        d.addOption("Up", "1", new Runnable(){
                            @Override
                            public void run()
                            {

                                window.setScene(new ToxicSewers(window));

                            }

                        });
                        d.addOption("Straight", "2", new Runnable(){
                            @Override
                            public void run()
                            {

                                window.setScene(new Ramparts(window));

                            }

                        });
                        d.addOption("Down", "3", new Runnable(){
                            @Override
                            public void run()
                            {

                                window.setScene(new DilapidatedArboretum(window));

                            }

                        });
                        d.construct();
                        d.setVisible(true);

                    }

                });
                d.addOption("Stay", "2", new Runnable(){
                    @Override
                    public void run()
                    {

                        window.getContentPane().remove(d);
                        window.getContentPane().repaint();

                    }

                });
                d.construct();
                d.setVisible(true);
            }
        });

    }

}
