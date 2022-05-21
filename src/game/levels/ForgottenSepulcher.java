package game.levels;

import game.*;

import java.awt.Rectangle;

public class ForgottenSepulcher extends Scene
{

    public ForgottenSepulcher(Window window)
    {
        super(window, 16);
        Scene _this = this;

        Interactable i = new Interactable(_this, new Rectangle(1627, 250,300, 600), new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("continue", _this);
                d.setPrompt("It goes further here.");
                d.addOption("Go on", "1", new Runnable(){

                    @Override
                    public void run(){

                        window.setScene(new FracturedShrines(window));

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
        if (!_this.window.main.context.has("receive-torch-dialog")){
            i.setVisible(false);
        };

        final NPC n = new NPC(4, _this, false, new Rectangle(1147, 452, 350, 350), null);
        n.setCallback( new Runnable(){
            @Override
            public void run(){
            
                if (_this.window.main.context.has("receive-torch-dialog")){

                    Dialog d = new Dialog("continue", _this);
                    d.setPrompt("Oh hey! It's you again!");
                    d.addOption("Hey!", "1", new Runnable(){

                        @Override
                        public void run(){

                            d.destroy();

                        }

                    });
                    d.construct();
                    d.setVisible(true);

                } else {

                    Dialog d = new Dialog("continue", _this);
                    d.setPrompt("Greetings traveler, you seem to be lost!");
                    d.addOption("Hey", "1", new Runnable(){

                        @Override
                        public void run(){

                            Dialog d = new Dialog("continue", _this);
                            d.setPrompt("Let me give you a torch, you can't just walk around in the dark!");
                            d.addOption("Thanks!", "1", new Runnable(){

                                @Override
                                public void run(){

                                    d.destroy();
                                    _this.window.main.inventory.add(7);
                                    i.setVisible(true);

                                }

                            });
                            d.addOption("No need to!", "2", new Runnable(){

                                @Override
                                public void run(){

                                    d.destroy();
                                    i.setVisible(true);

                                }

                            });
                            d.construct();
                            d.setVisible(true);

                        }

                    });
                    d.construct();
                    d.setVisible(true);
                    _this.window.main.context.add("receive-torch-dialog");

                }

            }
        });

        final NPC me = new NPC(6, _this, true, new Rectangle(436, 465, 350, 350), null);
        me.setCallback( new Runnable(){
            @Override
            public void run(){
                me.say(Assets.think(window.main));
            }
        });
    }

}
