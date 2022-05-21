package game;

import javax.swing.*;
import java.awt.*;

class ImagePanel extends JComponent {
    private Image image;
    private int originalImageWidth;
    private int originalImageHeight;
    public ImagePanel(Image image, int imageWidth, int imageHeight) {
        this.image = image;
        originalImageHeight = imageHeight;
        originalImageWidth  = imageWidth;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int windowHeight  = (int) super.getHeight() - Assets.Scalings.get("dialog-section");
        int windowWidth   = (int) super.getWidth();
        int imageHeight   = (int) originalImageHeight;
        int imageWidth    = (int) originalImageWidth;
        double multiplier = (double) windowHeight / imageHeight;

        int imageOverflow = (int) Math.floor((imageWidth * multiplier) - windowWidth);
        if (windowHeight <= 0 || imageHeight <= 0){

            Debugging.warn("Invalid Sized for Background Scaling!");
            return;

        } else {

            g.drawImage(image, -((int) imageOverflow / 2), 0, (int) Math.round(imageWidth * multiplier), (int) Math.round(imageHeight * multiplier),  this);
           
        }
    }
}