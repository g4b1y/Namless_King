    package game;

    import java.util.HashMap;
    import java.awt.Rectangle;
    import java.net.*;
    import java.io.*;
    import java.awt.image.*;
    import javax.imageio.*;

    public class Assets
    {

        public static URL absolute(String relative){

            try {
                URL r = Main.class.getClassLoader().getResource(relative).toURI().toURL();
                return r;
            } catch (Exception e) {
                Debugging.error(e.getMessage());
                return null;
            }

        }

        public static BufferedImage getBufferedImage(String relative){

            try {
                InputStream in = Main.class.getClassLoader().getResourceAsStream(relative); 
                return ImageIO.read(in);
            } catch (Exception e){
                return null;
            }

        }

        public static boolean hitboxes       = false;
        public static boolean level_editor   = false;
        public static Rectangle refrenceRect = new Rectangle(0,0,1936,882);

        public static String[] Backgrounds   = {
            "game/assets/backdrops/Promenade of the Condemned.png",   // 0
            "game/assets/backdrops/Toxic Sewers.png",                 // 1
            "game/assets/backdrops/Corrupted Prison.png",             // 2
            "game/assets/backdrops/Ancient Sewers.png",               // 3
            "game/assets/backdrops/Ramparts.png",                     // 4
            "game/assets/backdrops/Ossuary.png",                      // 5
            "game/assets/backdrops/Black Bridge.png",                 // 6
            "game/assets/backdrops/Graveyard.png",                    // 7
            "game/assets/backdrops/Cavern.png",                       // 8
            "game/assets/backdrops/Stilt Village.png",                // 9
            "game/assets/backdrops/Clock Tower.png",                  // 10
            "game/assets/backdrops/Clock Room.png",                   // 11
            "game/assets/backdrops/Dilapidated Arboretum.png",        // 12
            "game/assets/backdrops/Prison Depths.png",                // 13
            "game/assets/backdrops/Morass of the Banished.png",       // 14
            "game/assets/backdrops/Nest.png",                         // 15
            "game/assets/backdrops/Forgotten Sepulcher.png",          // 16
            "game/assets/backdrops/Fractured Shrines.png",            // 17
            "game/assets/backdrops/Undying Shores.png",               // 18
            "game/assets/backdrops/Mausoleum.png",                    // 19
            "game/assets/backdrops/High Peak Castle.png",             // 20
            "game/assets/backdrops/Throne Room.png",                  // 21
            "game/assets/backdrops/Prisoners' Quarters.png",          // 22
            "game/assets/backdrops/youdied.gif",                      // 23
            "game/assets/backdrops/Distillery.png",                   // 24
            "game/assets/backdrops/Botanical Beaker.png"              // 25

        };

        public static String[] Enemies   = {
            "game/assets/enemies/apostate.png",                       // 0
            "game/assets/enemies/cannibal.png",                       // 1
            "game/assets/enemies/caster.png",                         // 2
            "game/assets/enemies/cold_blooded_guardian.png",          // 3
            "game/assets/enemies/demolisher.png",                     // 4
            "game/assets/enemies/giant_tick.png",                     // 5
            "game/assets/enemies/golem.png",                          // 6
            "game/assets/enemies/ground_shaker.png",                  // 7
            "game/assets/enemies/infected_worker.png",                // 8
            "game/assets/enemies/stone_warden.png",                   // 9
            "game/assets/enemies/demon.png",                          // 10
            "game/assets/enemies/ghost.png",                          // 11
            "game/assets/enemies/slasher.png",                        // 12
            "game/assets/enemies/toxic_miasma.png",                   // 13
            "game/assets/enemies/yeeter.png",                         // 14

            "game/assets/bosses/concierge.png",                       // 15
            "game/assets/bosses/time_keeper.png",                     // 16
            "game/assets/bosses/scarecrow.png",                       // 17
            "game/assets/bosses/hand_of_king.png",                    // 18
        };

        public static String[] NPCS      = {
            "game/assets/npcs/blacksmith.png",                       // 0
            "game/assets/npcs/king.png",                             // 1
            "game/assets/npcs/masker.png",                           // 2
            "game/assets/npcs/royal_guard.png",                      // 3
            "game/assets/npcs/the_spider.png",                       // 4
            "game/assets/npcs/tutorial_knight.png",                  // 5
            "game/assets/npcs/main.png"                              // 6
        };

        public static String[] Items     = {
            "game/assets/enemies/amulet.png",                         // 0
            "game/assets/enemies/bow.png",                            // 1
            "game/assets/enemies/crowbar.png",                        // 2
            "game/assets/enemies/grenade.png",                        // 3
            "game/assets/enemies/prisoners_collar.png",               // 4
            "game/assets/enemies/spiked_boots.png",                   // 5
            "game/assets/enemies/tombstone.png",                      // 6
            "game/assets/enemies/torch.png"                           // 7
        };
        
        public static String[] thoughts = {
            "Imagine using Java, kinda cringe",
            "Why are we still here? Just to suffer?",
            "*thinking intensifies*",
            "Sometimes my genius is... It's almost frightening"
        };

        public static String think(Main m){
            int i = (int) Math.round(Math.random() * (thoughts.length - 1));
            if (i == m.lastThought){
                return think(m);
            }
            m.lastThought = i;
            return thoughts[i];

        }

        public static HashMap<String, Integer> Scalings = new HashMap<String, Integer>(){{
            put("titlebar", 30);
            put("dialog-section", 175);
        }};

    }
