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

## BluePrint Namless_King

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
    
 ## The Window Class   

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
    
## The Assets Class
    
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

After that, I thought I could add some "Thoughts" to the Player.  
Here is the function: 
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

## The say(String text) function
    
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
    
I'll explain this function. First I check if anything is written there. If it is, I clear it. 
Then I add a new JLable to the screen, make it visible, and add a white foreground (so that 
the text is written appears white). After that, I set the Bounds and the Font I want to use. 
The first for-loop you see is for displayed text gets removed first before another text is 
written otherwise, the new text gets smashed into the old one. 
The second for-loop is the "animation" that looks written and doesn't get displayed as a whole thing. 
After that, the Thought gets canceled after 50 milliseconds. 
Last but not least, I make the function executable in the run()-function.
    
# Resize
The resize fuction in NPC is the most complex, so I will explain this function (the other resize() function working similar).

   
# Level Editor
   The LevelEditor is a class that makes it possible to drag and drop enteties on the window and get the coordinates from the object moved around. 
   There are a few functions, most of them selfexplaining. 
   
   * mouseDragged(MouseEvent e), changes the value of the entity. 
   * mouseReleased(MouseEvent e), calculates the new Position of the entity and prints it on the console.
   * startDragging(Component c, int x, int y), tells where the Image starts getting dragged and dropped.
   * LevelEditor(Window win), activates on the given Window and prints a simple message in the console.  
  
<details> 
  
   <summary> Click to expand the LevelEditor</summary>
      
      
```JAVA
          public class LevelEditor extends MouseInputAdapter {

          public Component element;
          public boolean dragging;

          private Window win;

          public int startX;
          public int startY;
          public int endX;
          public int endY;
          public int offsetX;
          public int offsetY;

          public void mouseDragged(MouseEvent e) {
              int x = e.getX();
              int y = e.getY();

              if (dragging){

                  element.setBounds(x - offsetX,(y - offsetY) - 30,element.getWidth(),element.getHeight());

              }
          }

          public void mouseReleased(MouseEvent e) {

              if (dragging){

                  int x = e.getX();
                  int y = e.getY();

                  endX  = x;
                  endY  = y;

                  double mx = (double) Assets.refrenceRect.width /  (double) win.getBounds().width;
                  double my = (double) Assets.refrenceRect.height / (double) win.getBounds().height;

                  dragging = false;
                  Debugging.log(String.format("New Position of %s: %s, %s", element.getClass().getSimpleName(), (int) Math.floor(element.getBounds().x * mx), (int)        Math.floor((element.getBounds().y + 110) * my)));

              }

          }

          public void startDragging(Component c, int x, int y){

              Debugging.log(String.format("Started Dragging element (%s)", c.getClass().getSimpleName()));

              dragging = true;
              element  = c;
              startX   = x;
              startY   = y;
              offsetX  = x;
              offsetY  = y;

          }

          public LevelEditor (Window win){

              this.win = win;
              Debugging.log("Level Editor initilized.");

          }

      }
 ```
</details>
      
   
# Level 
```mermaid
   
   graph TD; 
   
   JFrame --> Window; 
   Scene --> Window; 
   Window --> Scene; 
   Scene --> SomeLevel; 
````
This is a simplyfied version of a Level. A Level extends Scene. In Scene is an Object from Window and in Window is an 
Object of Scene. Window extends JFrame (from JAVA Swing).  
   
## Window Class   
In the Window class I created a function setScene(Scene scene)  
that helps me to load Scenes into my Window (more on that later). It also checks if the Key ESCAPE is pressed and if, the I close 
the game-window. 
   

````JAVA
   public void setScene(Scene scene){
        this.scene = scene;
        scene.load();
        this.revalidate();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
    }
````
In the Constructor from the Window-class we set a title for the game-window. 

   * make the window Fullscreen
   * load the NPC
   * give the window a width and a height
   * set it visible
   * define a minimum size 
   * add a Background 
   * and set the Layout of the ContentPane to null
   
After that I added a ComponentListener that tells me if the window gets resized. 
I also check (if the level-editor is used) if an object is moved.
   
<details>
   <summary> Click to expand and see the Window Constructor</summary>

## Window Class
   
   ````Java
   public Window(Main m)
    {
        super("The Nameless King"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        this.main = m;
        setUndecorated(true); // Fullscreen
        setIconImage(new ImageIcon(Assets.absolute(Assets.NPCS[6])).getImage());
        setSize(900, 600);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(900, 600));
        getContentPane().setLayout(null);
        setBackground(Color.black);



        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                 if (scene != null){
                     scene.resize();
                 } else {
                     Debugging.warn("No Scene loaded!");
                 }
            }
        });


        if (Assets.level_editor == true){

            Debugging.log("Creating level editor.");
            le = new LevelEditor(this);
            this.addMouseListener(le);
            this.addMouseMotionListener(le);

        }
       
    }
   ````

</details>   

In the Window class is also a function that makes it easier to set a Scene to my window.

````JAVA
   public void setScene(Scene scene){
        this.scene = scene;
        scene.load();
        this.revalidate();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
    }
````

## Scene class
   
   In the Scene class the constructor has two arguments. The first is the window, on wich window the scene should be loaded. 
   The second is the backgorund. 
   There are also some functions like: 
   
  
   * add(), makes it possible to add components
   * load()
   * loadBackground()

   
 ````Java
   public void loadBackground(URL imgPath){

            Image originalImg = new ImageIcon(imgPath).getImage();
            Debugging.log(String.format("Loading Background: %s",Assets.absolute(Assets.Backgrounds[_stage])));
            ((JFrame) window).setContentPane(new ImagePanel(originalImg, originalImg.getWidth(null), originalImg.getHeight(null)));

            try {
                BufferedImage bim = Assets.getBufferedImage(Assets.Backgrounds[_stage]); // Color
                int sample        = bim.getRGB(0, bim.getHeight() - 1);
                Color floor       = new Color(sample, true);
                floorColor        = floor;
                this.window.getRootPane().setBackground(floor);
            } catch (Exception e) {
                Debugging.error(String.format("Can't change floor color! (%s)", e.getMessage()));
            }

        }
 ````
   The loadBackground() function firsts gets the imagePath. Writes a message in the console. 
   Creates a new element and adds the Picture. After that it changes the backgroundcolor to the lowest left pixel of
   the loaded image. 
   
## Level
   
   First we set the window and the stage of a scene.  Than we create a Puzzle. 
   After that we add a simple Diaolog and add a option to continue or else. 
   Than we display the puzzle and destroy the dialog. If you lose and you did not 
   solve the puzzle, a deathscene gets displayed. Otherwise you can go on to another 
   level. I also add an Enemy with its position and call the think() function if you click 
   on the Player. 
   Here is an example for a level: 
   
   <details>
      <summary> Click to expand </summary>
   
   ````Java
   public class AncientSewers extends Scene
{

    public AncientSewers(Window window)
    {
        super(window, 3);
        Scene _this = this;

        ImagePuzzle puzzle = new ImagePuzzle(Assets.absolute(Assets.Backgrounds[6]), _this, 700, 3);
        window.add(puzzle);

        Interactable i = new Interactable(_this, new Rectangle(1666, 338,250,300), new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("continue", _this);
                d.setPrompt("It seems like there's an exit over there!");
                d.addOption("Go on", "1", new Runnable(){

                    @Override
                    public void run(){

                        window.setScene(new BlackBridge(window));

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

        Enemy e = new Enemy(8, _this, true, new Rectangle(505, 493,250,250), null);
        e.setCallback(new Runnable(){

            @Override
            public void run(){

                Dialog d = new Dialog("fight", _this);
                d.setPrompt("A infected worker attacks you! He doesn't look to good...");
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

        final NPC me = new NPC(6, _this, true, new Rectangle(95, 609, 250, 250), null);
        me.setCallback( new Runnable(){
            @Override
            public void run(){
                me.say(Assets.think(window.main));
            }
        });

    }
   }
   ````
  </details>
   
   
# NPC and Enemy
   
   ## NPC
   
   We have following functions in NPC: 
   * NPC(int, Scene, boolean, Rectangle, Runnable), constructor
   * destroy(), destroys the NPC 
   * flip(boolean), flips the NPC to the right or left
   * paintComponent(Graphics), gets called(from JAVA) if something changes on the screen. The function repaints the NPC after an event.  
   * resize(), resizes the NPC and Speech if the window gets resized (if it is not fullscreen) 
   * say(String), gets called by a "Thought" 
   * setCallback(Runnable), handels input when a button is clicked
   
   
   
<details> 
   <summary>Click to expand the NPC class</summary>

      ### NPC

````JAVA
public class NPC extends JButton
{

    private int npcid;
    private Scene scene;
    private NPC _this;
    private boolean flipImg;

    private int originalImageHeight;
    private int originalImageWidth;
    private Rectangle originalRect;

    private JLabel speech;

    private Image image;
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(64);
    private ScheduledFuture<?> _thought;
    private ArrayList<ScheduledFuture<?>> _scheduled_tasks = new ArrayList<ScheduledFuture<?>>();

    public Runnable callback;
    
    @Override
    protected paintComponent(Graphics g) {
        super.paintComponent(g);
        int windowHeight  = (int) super.getHeight();
        int windowWidth   = (int) super.getWidth();
        int imageHeight   = (int) originalImageHeight;
        int imageWidth    = (int) originalImageWidth;
        double multiplier = (double) windowHeight / imageHeight;

        int imageOverflow = (int) Math.floor((imageWidth * multiplier) - windowWidth);
        if (windowHeight <= 0 || imageHeight <= 0){

            Debugging.warn("Invalid Sized for Background Scaling!");
            return;

        } else {

            if (flipImg){
                g.drawImage(image, -((int) imageOverflow / 2) + (int) Math.round(imageWidth * multiplier), 0, -((int) Math.round(imageWidth * multiplier)), ((int) Math.round(imageHeight * multiplier)),  this); 
            } else {
                g.drawImage(image, -((int) imageOverflow / 2), 0, (int) Math.round(imageWidth * multiplier), (int) Math.round(imageHeight * multiplier),  this);
            }
           
        }
    }

    private void resize(){
        
        double xMultiplier     = scene.window.getWidth() / Assets.refrenceRect.getWidth();
        double yMultiplier     = (scene.window.getRootPane().getHeight() - Assets.Scalings.get("dialog-section")) / Assets.refrenceRect.getHeight();

        int newX               = (int) (originalRect.getX() * xMultiplier);
        int newY               = (int) (originalRect.getY() * yMultiplier);

        int newW               = (int) (originalRect.getWidth()  * yMultiplier);
        int newH               = (int) (originalRect.getHeight() * yMultiplier);

        Rectangle relativeRect = new Rectangle(newX, newY, newW, newH);
        setBounds(relativeRect);

        if (speech != null){

            int snx = (int) (this.getBounds().x - (((300 * xMultiplier) - this.getBounds().width) / 2));
            int sny = (int) (this.getBounds().y - 50);

            int snw = (int) (300 * xMultiplier);
            int snh = (int) (50  * yMultiplier); 

            speech.setBounds(snx, sny, snw, snh);
            speech.setFont(new Font(speech.getFont().getFontName(), Font.PLAIN, (int) (20 * xMultiplier)));

        }

    }

    public NPC(int eid, Scene scene, boolean flip, Rectangle rect, Runnable callback)
    {

        Image img                = new ImageIcon(Assets.absolute(Assets.NPCS[eid])).getImage();
        this.originalImageHeight = img.getHeight(null);
        this.originalImageWidth  = img.getWidth(null);
        this.image               = img;
        _this                    = this;
        this.npcid               = eid;
        this.scene               = scene;
        this.flipImg             = flip;
        this.originalRect        = rect;
        this.callback            = callback;
        
        Debugging.log(String.format("Loading game.NPC Sprite: %s (ID: %s)",Assets.absolute(Assets.NPCS[eid]), this.npcid));

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {

                if (Assets.level_editor && arg0.getButton() == 1){
                    scene.window.le.startDragging(_this, arg0.getX(), arg0.getY());
                    scene.window.dispatchEvent(arg0);
                } else {
            
                    if (!scene.interactionsDisabled){

                        if (_this.callback != null){
                            _this.callback.run();
                        }

                    }
                    
                }
                super.mousePressed(arg0);
            }
            
        });

        setOpaque(false);
        setFocusable(false);
        if (!Assets.hitboxes){

            setBorder(null);

        }
        setContentAreaFilled(false);

        scene.window.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                resize();
            }
        });

        resize();
        scene.add(this);

    }

    public void setCallback(Runnable callback){

        this.callback = callback; // 

    }

    public void destroy(){

        this.getParent().remove(this);
        scene.window.repaint();

    }

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

    public void flip(boolean flip){

        this.flipImg = flip;
        this.repaint();

    }
}
````
   </details>
   
   ## Enemy
   The Enemy class is very simmilar to the NPC class but an Enemy can not think like the NPC. Both entities are drawn behind 
   a transparent button. The Enemy class has following functions: 
      
   * Enemy(int, Scene, boolean, Rectangle, Runnable), constructor
   * destroy(), destroys the Enemy after an Event and gets repainted by the paintComponent(Graphics) function
   * paintComponent(Graphics), paints the component. In this case the entity
   * resize(), resizes the enemy after the window is resized
   * setCallback(Runnable), handles input after the button on the Enemy is clicked 
   
      
   <details>
      <summary> Click to expand the Enemy class</summary>
      
### Enemy
      
````JAVA
public class Enemy extends JButton
{

    private int eid;
    private Scene scene;
    private Enemy _this;
    private boolean flipImg;

    private int originalImageHeight;
    private int originalImageWidth;
    private Rectangle originalRect;

    private Image image;
    private Runnable callback; //pressed ene
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int windowHeight  = (int) super.getHeight();
        int windowWidth   = (int) super.getWidth();
        int imageHeight   = (int) originalImageHeight;
        int imageWidth    = (int) originalImageWidth;
        double multiplier = (double) windowHeight / imageHeight;

        int imageOverflow = (int) Math.floor((imageWidth * multiplier) - windowWidth);
        if (windowHeight <= 0 || imageHeight <= 0){

            Debugging.warn("Invalid Sized for Background Scaling!");
            return;

        } else {

            if (flipImg){
                g.drawImage(image, -((int) imageOverflow / 2) + (int) Math.round(imageWidth * multiplier), 0, -((int) Math.round(imageWidth * multiplier)), ((int) Math.round(imageHeight * multiplier)),  this); 
            } else {
                g.drawImage(image, -((int) imageOverflow / 2), 0, (int) Math.round(imageWidth * multiplier), (int) Math.round(imageHeight * multiplier),  this);
            }
           
        }
    }

    private void resize(){
        
        double xMultiplier     = scene.window.getWidth() / Assets.refrenceRect.getWidth();
        double yMultiplier     = (scene.window.getRootPane().getHeight() - Assets.Scalings.get("dialog-section")) / Assets.refrenceRect.getHeight();

        int newX               = (int) (originalRect.getX() * xMultiplier);
        int newY               = (int) (originalRect.getY() * yMultiplier);

        int newW               = (int) (originalRect.getWidth()  * yMultiplier);
        int newH               = (int) (originalRect.getHeight() * yMultiplier);

        Rectangle relativeRect = new Rectangle(newX, newY, newW, newH);
        setBounds(relativeRect);

    }

    public void setCallback(Runnable callback){

        this.callback = callback;

    }

    public void destroy(){

        this.getParent().remove(this);
        scene.window.repaint();

    }

    public Enemy(int eid, Scene scene, boolean flip, Rectangle rect, Runnable callback)
    {

        Image img                = new ImageIcon(Assets.absolute(Assets.Enemies[eid])).getImage();
        this.originalImageHeight = img.getHeight(null);
        this.originalImageWidth  = img.getWidth(null);
        this.image               = img;
        _this                    = this;
        this.eid                 = eid;
        this.scene               = scene;
        this.flipImg             = flip;
        this.originalRect        = rect;
        this.callback            = callback;

        Debugging.log(String.format("Loading game.Enemy Sprite: %s (ID: %s)",Assets.absolute(Assets.Enemies[eid]), this.eid));
        
        final Scene _scene = scene;
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {

                if (Assets.level_editor && arg0.getButton() == 1){
                    _scene.window.le.startDragging(_this, arg0.getX(), arg0.getY());
                    _scene.window.dispatchEvent(arg0);
                } else {
                    if (!_scene.interactionsDisabled){

                        if (_this.callback != null){
                            _this.callback.run();
                        }

                    }
                }
                super.mousePressed(arg0);
            }
            
        });

        setOpaque(false);
        setFocusable(false);
        if (!Assets.hitboxes){

            setBorder(null);

        }
        setContentAreaFilled(false);

        scene.window.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                resize();
            }
        });
        resize();

        scene.add(this);

    }

}

````
</details>
      
   
# Puzzle
      
## ImagePuzzle
* ImagePuzzle(URL, Scene, int, int), constructor. Arguments are [ImageURL], mostly the actual scene, width of the puzzle, height of the puzzle  
* check(), checks if the puzzle is solved
* destroy(), destroys the puzzle  
* disable(), "unclicks" all buttons/pieces at the beginnig 
* resize(), resizes the puzzle after the window gets resized
* revisualize(), "repaints" the puzzle (e.g. after a piece is swapped)  
* select(), selects a piece
* shuffle(), shuffle the puzzle after the inspcetion phase
* start(int, Runnable, Runnable), starts the puzzle and creates a time bar below the puzzle
* switchPieces(), switches two pieces next to each other (not over cross) after they got clicked  
      
## PuzzlePiece

* PuzzlePiece(Image, int, int, int, ImagePuzzle), constructor
* paintComponent(Graphics), 
* select(), makes the border yellow after recieving the information about the clicked piece from ImagePuzzle.select() function
* unselect(), makes the border normal (grey) after recieving the information about the clicked piece from ImagePuzzle.select() function. 

<details>     
   <summary> Click to expand class diagram </summary>
    
    
````mermaid
classDiagram
class PuzzlePiece {
      -Image image
      - int divisions
      - PuzzlePiece _this
      
      + boolean disabled
      + int gx
      + int gy
      + int px
      + int py
      }
      
      PuzzlePiece : +unselect()
      PuzzlePiece : +selcect()
      PuzzlePiece : PuzzlePiece(Image image, int gx, int gy, int divisions, ImagePuzzle puzzle) ~ctor~
      PuzzlePiece : #paintComponent(Graphics g) 
      
      
class ImagePuzzle {
      - boolean started
      - int size
      - int scene 
      - Image image
      - PuzzlePieces[] pieces
      - int divisions
      - PuzzlePiece piece1
      - PuzzlePiece piece2
      - Timer timer
      - double percent
      - JPanel progress
      - Runnable win
      - ImagePuzzle _this
      
      + int prepTime
      }
      
      ImagePuzzle : +destroy()
      ImagePuzzle : -resize()
      ImagePuzzle : +disable()
      ImagePuzzle : +check() boolean
      ImagePuzzle : -revisualize()
      ImagePuzzle : -shuffle()
      ImagePuzzle : -switchPieces()
      ImagePuzzle : +select(PuzzlePiece piece)
      ImagePuzzle : +start(int time, Runnable winCallback, Runnable loseCallback)
      ImagePuzzle : +ImagePuzzle(URL image, Scene scene, int size, int divisions) ~ctor~ 
      
      
      class Level {
         Enemy e    
         final NPC me
      }
      
      Level : +Level(Window window) ~ctor~
      
      class Scene {
         +Window window
         +ArrayList~Component~ components
         +JLabel background
         +Dialog dialog
         +Color floorColor
         -int _stage
         
         +boolean interactionsDisabled
      }
      
      Scene : +Scene(Window window) ~ctor~
      Scene : +add(Component component) 
      Scene : +load()
      Scene : +resize()
      Scene : +loadBackground(URL imgPath) 
      
      class Enemy{
          - int eid
          - Scene scene
          - Enemy _this
          - boolean flipImg

          - int originalImageHeight
          - int originalImageWidth
          - Rectangle originalRect
      
          - Image image
          - Runnable callback
      }
      
      Enemy : #paintComponent(Graphics g) 
      Enemy : -resize()
      Enemy : +setCallback(Runnable callback)
      Enemy : +destroy()
      Enemy : Enemy(int eid, Scene scene, boolean flip, Rectangle rect, Runnable callback) ~ctor~
      
      class NPC {
          - int npcid
          - Scene scene
          - NPC _this
          - boolean flipImg

          - int originalImageHeight
          - int originalImageWidth
          - Rectangle originalRect

          - JLabel speech

          - Image image
          - final ScheduledThreadPoolExecutor executor 
          - ScheduledFuture~?~ _thought
          - ArrayList~ScheduledFuture ~?~~ _scheduled_tasks 

          + Runnable callback;
      }
      
      NPC : #paintComponent(Graphics g) 
      NPC : -resize()
      NPC : +NPC(int eid, Scene scene, boolean flip, Rectangle rect, Runnable callback) ~ctor~
      NPC : +setCallback(Runnable callback) 
      NPC : + destroy()
      NPC : +say(String text)
      NPC : +flip(boolean flip) 
   
      Level -- NPC
      Level -- Enemy
      Scene <-- Level
      PuzzlePiece -- ImagePuzzle
      Level -- ImagePuzzle
 ````     
</details>
                
# Goals

# Problems
   
# Source
