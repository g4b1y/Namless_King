package game.levels;

import game.*;
import game.Dialog;
import game.Window;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PrisonQuarters extends Scene
{

    public PrisonQuarters(Window window)
    {
        super(window, 22);
        Scene _this = this;

        ImagePuzzle puzzle = new ImagePuzzle(Assets.absolute(Assets.Backgrounds[0]), _this, 700, 3);
        window.add(puzzle);


        Dialog d          = new Dialog("continue", _this);
        d.setPrompt("Hey you! You're finally awake!");
        d.addOption("Continue", "space", () -> {

            d.setVisible(false);

            Dialog d2          = new Dialog("continue", _this);
            d2.setPrompt("Would you like to skip the tutorial?");
            d2.addOption("Yes", "1", () -> {
                Scene next = new PromenadeOfTheCondemned(window);
                window.setScene(next);
            });
            d2.addOption("No", "2", () -> {
                Dialog d1 = new Dialog("continue", _this);
                d1.setPrompt("You fight enemies by solving image puzzles in a limited amount of time.");
                d1.addOption("Continue", "1", () -> {

                    Dialog d11 = new Dialog("continue", _this);
                    d11.setPrompt("However, you can only switch pieces that are directly next to each other.");
                    d11.addOption("Continue", "1", () -> {

                        Dialog d111 = new Dialog("continue", _this);
                        d111.setPrompt("You'll now get a chance to practice fighting!");
                        d111.addOption("Continue", "1", () -> {

                            d111.destroy();
                            puzzle.start(60000, () -> {

                                puzzle.destroy();

                                Dialog d1111 = new Dialog("continue", _this);
                                d1111.setPrompt("Congrats! You just won your first fight!");
                                d1111.addOption("Continue", "1", () -> {

                                    Dialog d11111 = new Dialog("continue", _this);
                                    d11111.setPrompt("Sometimes you'll  require a specific item to enter a new Zone! You'll get these by killing enemies or by interacting with the world or NPCs.");
                                    d11111.addOption("Continue", "1", () -> {

                                        Dialog d111111 = new Dialog("continue", _this);
                                        d111111.setPrompt("You can click specific parts of the map to trigger dialogs or leave rooms. These interactables may only appear after you've already beaten the level!");
                                        d111111.addOption("Continue", "1", () -> window.setScene(new PromenadeOfTheCondemned(window)));
                                        d111111.construct();
                                        d111111.setVisible(true);

                                    });
                                    d11111.construct();
                                    d11111.setVisible(true);

                                });
                                d1111.construct();
                                d1111.setVisible(true);

                            }, () -> {

                                puzzle.destroy();

                                Dialog d1111 = new Dialog("continue", _this);
                                d1111.setPrompt("Damn! Maybe you'll manage to do it next time (hopefully)!");
                                d1111.addOption("Continue", "1", d1111::destroy);


                            });

                        });
                        d111.construct();
                        d111.setVisible(true);

                    });
                    d11.construct();
                    d11.setVisible(true);

                });
                d1.construct();
                d1.setVisible(true);
            });
            d2.setTime(-1);
            d2.construct();

            d2.setVisible(true);

        });
        d.setTime(-1);
        d.construct();
        d.setVisible(true);

        window.repaint();

    }
    
}
