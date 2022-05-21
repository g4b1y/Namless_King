package game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
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
}
