package game.levels;

import game.*;

import java.awt.Rectangle;

public class MorassOfTheBanished extends Scene
{

    public MorassOfTheBanished(Window window)
    {
        super(window, 14);
        Scene _this = this;

        ImagePuzzle puzzle = new ImagePuzzle(Assets.absolute(Assets.Backgrounds[15]), _this, 700, 3);
        window.add(puzzle);

        Interactable i = new Interactable(_this, new Rectangle(1634, 100,300, 600), () -> {

            Dialog d = new Dialog("continue", _this);
            d.setPrompt("It goes further here.");
            d.addOption("Go on", "1", () -> window.setScene(new Nest(window)));
            d.addOption("Stay", "2", d::destroy);
            d.construct();
            d.setVisible(true);

        });
        i.setVisible(false);

        Enemy e = new Enemy(4, _this, true, new Rectangle(1351, 445,250,250), null);
        e.setCallback(() -> {

            Dialog d = new Dialog("fight", _this);
            d.setPrompt("A demolisher! You should demolish him!");
            d.addOption("Continue", "1", () -> {

                d.destroy();
                puzzle.start(45000, () -> {
                    i.setVisible(true);
                    e.destroy();
                    puzzle.destroy();
                }, () -> window.setScene(new YouDied(window)));

            });
            d.construct();
            d.setVisible(true);

        });

        final NPC me = new NPC(6, _this, true, new Rectangle(436, 465, 250, 250), null);
        me.setCallback(() -> me.say(Assets.think(window.main)));
    }
    

}
