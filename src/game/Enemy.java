package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    private Runnable callback;
    
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
