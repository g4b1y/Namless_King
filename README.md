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

# The idea
The idea was to create a simple 2D point and click game (as I mentioned before) 
I created a simple BluePrint how the levels should look like: 

<details> 
   <summary> Click to expand the BluePrint </summary>

# The BluePrint

```mermaid
graph TD; 
    PrisonQuarters --> PromenadeOfTheCondemned; 
    
    PromenadeOfTheCondemned --> ToxicSewers; 
    PromenadeOfTheCondemned --> Ramparts; 
    PromenadeOfTheCondemned --> DilapidatedAboretum; 
    
    ToxicSewers --> CorruptedPrison; 
    ToxicSewers -->AcientSewers; 
    
    Ramparts --> Ossuary; 
    Ramparts -->BlackBridge;
    
    DilapidatedAboretum --> PrisonDepths; 
    DilapidatedAboretum --> MorassOfTheBanished; 
    
    CorruptedPrison --> AcientSewers; 
    
    AcientSewers --> BlackBridge; 
    
    Ossuary --> BlackBridge; 
    
    PrisonDepths --> MorassOfTheBanished; 
    
    MorassOfTheBanished --> Nest; 
    
    
    
    BlackBridge --> Graveyard; 
    BlackBridge --> StiltVillage; 
    
    Graveyard --> Cavern; 
    
    StiltVillage --> ClockTower; 
    
    Cavern --> ClockRoom; 
    
    ClockTower --> ClockRoom; 
    
    ClockRoom --> HighPeakCastle; 
    
    HighPeakCastle --> ThroneRoom; 
    
    
    
    Nest --> FracturedShrines; 
    Nest --> ForgottenSepulcher; 
    
    FracturedShrines --> UndyingShores; 
    
    ForgottenSepulcher --> FracturedShrines; 
    
    UndyingShores --> Mausoleum; 
    
    Mausoleum --> HighPeakCastle; 
    
````
</details>
    
# How I started
First of all, I created a simple Window with JAVA Swing, something like this: 

<details> 
    <summary> Click here to expand </summary>
    
 # The Window Class   

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
</details>
    
added a Constructor and displayed an NPC as an Image.
I created a class "Assets" that holds all the information 
I need to display it on the Window I created. The class looks
like this one here (later I added more Features and Functions. 
I also began to add Items but I did not finish them yet. More on that later): 

<details> 
    <summary> Click here to expand </summary>
    
# The Assets Class
    
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
</details>

# Think

After that, I thought I could add some "Thoughts" to the Player. I was on Discord with 
a Friend of mine and we joked about JAVA, so I added the most used Phrase of us "Imagine 
using JAVA, kinda Cringe". 
The function I created is this one here:
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
This function creates a random number and returns a String at the position [ i ] from the thoughts-array.


````JAVA
public static String[] thoughts = {
            "Imagine using Java, kinda cringe",
            "Why are we still here? Just to suffer?",
            "*thinking intensifies*",
            "Sometimes my genius is... It's almost frightening"
        };
````
The function 'think()' gets called in the NPC class from a fuction called say() 

<details>
   <summary> Click here to see the function </summary>

# The say(String text) function
    
````JAVA
 public void say(String text){

        final String _text = text;

        if (speech != null){

            scene.window.getContentPane().remove(speech);
            scene.window.getContentPane().repaint();

        }

        JLabel t = new JLabel("", SwingConstants.CENTER);
        scene.window.add(t);

        t.setVisible(true);
        t.setForeground(Color.WHITE);


        t.setBounds(this.getBounds().x - ((300 - this.getBounds().width) / 2), this.getBounds().y - 50, 300, 50);
        t.setFont(new Font(t.getFont().getFontName(), Font.PLAIN, 20));
        speech = t;

        for (int i = _scheduled_tasks.size() - 1; i >= 0; i--){

            _scheduled_tasks.get(i).cancel(false);
            _scheduled_tasks.remove(i);

        }

        for (int i = 0; i <= text.length(); i++){

            final int _i = i;
            _scheduled_tasks.add(executor.schedule(new Runnable(){
                @Override
                public void run(){
                    if (speech != null){
                        speech.setText(String.format("<html><p style=\"text-align: center;\">%s</p></html>", _text.substring(0, _i)));
                    }
                }
            }, 40 * i, TimeUnit.MILLISECONDS));

        }

    

        if (_thought != null && _thought.isCancelled() == false){

            _thought.cancel(false);

        }

        _thought = executor.schedule(new Runnable(){
            @Override
            public void run(){
                if (speech != null){
                    speech.setText("");
                }
            }
        }, 3000 + (50 * text.length()), TimeUnit.MILLISECONDS);

    }
````
</details>
    
    

