package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;
import java.util.ArrayList;

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

        this.callback = callback;

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
