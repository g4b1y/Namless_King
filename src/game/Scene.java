    package game;

    import java.awt.*;
    import java.util.ArrayList;
    import java.awt.Image;
    import java.awt.image.BufferedImage;
    import javax.swing.*;
    import java.net.*;


    public class Scene
    {
        
        public Window window;
        public ArrayList<Component> components;
        public JLabel background;
        public Dialog dialog;
        public Color floorColor = Color.BLACK;
        private int _stage;

        public boolean interactionsDisabled = false;

        public Scene(Window window, int stage)
        {

            this.window  = window;
            this._stage  = stage;
            components   = new ArrayList<Component>();
            loadBackground(Assets.absolute(Assets.Backgrounds[stage]));

        }

        public void add(Component c){

            components.add(c);

        }

        public void load(){

            for (Component c : window.getRootPane().getComponents()){

                window.remove(c);

            }

            for (Component c : components){

                window.add(c);
                c.repaint();

            }

        }
        
        public void resize(){}

        public void loadBackground(URL imgPath){

            Image originalImg = new ImageIcon(imgPath).getImage();
            Debugging.log(String.format("Loading Background: %s",Assets.absolute(Assets.Backgrounds[_stage])));
            ((JFrame) window).setContentPane(new ImagePanel(originalImg, originalImg.getWidth(null), originalImg.getHeight(null)));

            try {
                BufferedImage bim = Assets.getBufferedImage(Assets.Backgrounds[_stage]);
                int sample        = bim.getRGB(0, bim.getHeight() - 1);
                Color floor       = new Color(sample, true);
                floorColor        = floor;
                this.window.getRootPane().setBackground(floor);
            } catch (Exception e) {
                Debugging.error(String.format("Can't change floor color! (%s)", e.getMessage()));
            }

        }

        public void clearDialog(){

            if (this.dialog != null){

                this.window.remove(this.dialog);

            }

        }

    }
