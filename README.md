# Namless_King
2D Game from Scratch 

# About the Game 
The Nameless King is a 2D Game created with JAVA Swing.
As IDE i used IntelliJ from JetBrains. The project took me around 1/2 week to plan 
and around 4 weeks to program (without writing documentation). 
The enemies and the player images are "stolen" 
and inspired by the game Dead Cells from Motion Twin (a company from France) and Evil Empires 
(a Team of ex-Motion Twin developers and Graphic Designers but also new Recruits). 

I did not come as far as I wanted. I wanted to make it possible to move the player and fight 
against enemies (invent an Inventory). But it took a lot of time to create a level editor 
(that makes it possible to drag and drop entities, get the coordinates and create a function that 
makes it possible to change the grid size), but more on that later.  

Dead Cells inspired me. I wanted to try and make my own version of the game. 
Dead cells were made with the Framework "Haxe and Heaps" 
but I don't have the resources and not the motivation to learn a new language 
(and I don't want to steal the game completely). 

# How I started
First of all I created a simple Window with JAVA Swing, something like this: 

```JAVA
public class Window extends JFrame
{

    private Scene scene;
    public Main main;
    public LevelEditor le;

    public Window(Main m)
    {
        super("The Nameless King"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        this.main = m;
        setUndecorated(true);
        setIconImage(new ImageIcon(Assets.absolute(Assets.NPCS[6])).getImage());
        setSize(900, 600);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(900, 600));
        getContentPane().setLayout(null);
        setBackground(Color.black);
        setFocusable(true);
        }
}

```
added a Constructor and displayed a NPC as Image.
I created a class "Assets" that holds all the information 
I need to display it on the Window I created. The class looks
something like this : 
```JAVA
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
}
```
After that I thought I could add some "Thoughts" to the Player. I was on Discord with 
a Friend of mine and we joked about JAVA, so I added the most used Phrase of us "Imagine 
using JAVA, kinda Cringe". 
The function I created looks like this:
````JAVA
public static String think(Main m){
            int i = (int) Math.round(Math.random() * (thoughts.length - 1));
            if (i == m.lastThought){
                return think(m);
            }
            m.lastThought = i;
            return thoughts[i];

        }
````

