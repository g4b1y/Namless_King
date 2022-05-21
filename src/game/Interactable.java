package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Interactable extends JButton
{

    private Scene scene;
    private Interactable _this;
    private Rectangle originalRect;
    private Runnable callback;

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

    public void destroy(){

        this.getParent().remove(this);
        scene.window.repaint();

    }

    public Interactable(Scene scene, Rectangle rect, Runnable callback)
    {

        _this                    = this;
        this.scene               = scene;
        this.originalRect        = rect;
        this.callback            = callback;
        
        final Scene _scene = scene;
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {

                if (Assets.level_editor && arg0.getButton() == 1){
                    _scene.window.le.startDragging(_this, arg0.getX(), arg0.getY());
                    _scene.window.dispatchEvent(arg0);
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

}
