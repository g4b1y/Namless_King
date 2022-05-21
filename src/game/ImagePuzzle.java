package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.net.URL;
import java.util.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;

class PuzzlePiece extends JButton {

    private Image image;
    public  int gx;
    public  int gy;
    private int divisions;
    private PuzzlePiece _this;
    public  boolean disabled;

    public int px;
    public int py;

    public void unselect(){

        setBorder(BorderFactory.createLineBorder(Color.gray, 2));

    }

    public void select(){

        setBorder(BorderFactory.createLineBorder(Color.yellow, 2));

    }

    public PuzzlePiece(Image image, int gx, int gy, int divisions, ImagePuzzle puzzle){

        this.image      = image;
        this.gx         = gx;
        this.gy         = gy;
        this.divisions  = divisions;

        this.px         = gx;
        this.py         = gy;

        this._this      = this;
        this.disabled   = false;

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {

                if (!_this.disabled){

                    puzzle.select(_this);
                    super.mousePressed(arg0);

                }
            }
            
        });

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        int pieceHeight   = (int) super.getHeight();
        int pieceWidth    = (int) super.getWidth();
        int imageHeight   = (int) image.getHeight(null);
        int imageWidth    = (int) image.getWidth(null);

        BufferedImage img = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D    bGr = img.createGraphics();
        bGr.drawImage(this.image, 0, 0, null);
        bGr.dispose();
        img = img.getSubimage(imageWidth / 2 - imageHeight / 2, 0, imageHeight, imageHeight);

        BufferedImage pieceImg = new BufferedImage(img.getWidth() / divisions, img.getHeight() / divisions, BufferedImage.TYPE_INT_RGB);
        pieceImg = img.getSubimage(img.getWidth() / divisions * this.gx, img.getHeight() / divisions * this.gy, img.getWidth() / divisions, img.getHeight() / divisions);

        g.drawImage(pieceImg, 0, 0, pieceWidth, pieceHeight, this); 

    }

}

public class ImagePuzzle extends JPanel{
 
    private boolean       started = false;

    private int           size;
    private Scene         scene;
    private Image         image;
    private PuzzlePiece[] pieces;
    private int           divisions;

    private PuzzlePiece   piece1;
    private PuzzlePiece   piece2;
    private Timer         timer;

    private double        percent;

    private JPanel        progress;
    private Runnable      win;
    private ImagePuzzle   _this;

    public int prepTime = 2000;

    public  void destroy(){

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        _this.setVisible(false);
                        progress.setVisible(false);
                        _this.getParent().remove(_this);
                        progress.getParent().remove(progress);
                    }
                });
                t.cancel();

            }
        }, 500);

    }

    private void resize(){
        
        double yMultiplier     = (scene.window.getRootPane().getHeight() - Assets.Scalings.get("dialog-section")) / Assets.refrenceRect.getHeight();

        int newS               = (int) (size  * yMultiplier);

        Rectangle relativeRect = new Rectangle((scene.window.getWidth() / 2) - newS / 2, (scene.window.getHeight() / 2) - newS / 2, newS, newS);
        progress.setBounds(new Rectangle((scene.window.getWidth() / 2) - newS / 2, ((scene.window.getHeight() / 2) - newS / 2) + newS + 20, (int) ((newS / 100) * percent), 10));

        setBounds(relativeRect);
        setVisible(false);
        repaint();
        setVisible(true);

    }

    public void disable(){

        for (PuzzlePiece p : pieces){

            p.setBorder(null);
            p.disabled = true;

        }

    }

    public boolean check(){

        for (int i = 0; i < pieces.length; i++){

            if (pieces[i].px != pieces[i].gx || pieces[i].py != pieces[i].gy){

                return false;

            }

        }
        return true;

    }

    private void revisualize(){

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                removeAll();
                for (int i = 0; i < pieces.length; i++){

                    add(pieces[i]);

                }
                revalidate();
                repaint();
            }
        });

    }

    private void shuffle(){

        Debugging.log("Shuffeling Image Puzzle");

        PuzzlePiece[] oPieces = pieces;
        ArrayList<PuzzlePiece> sPieces = new ArrayList<>();

        for (int i = 0; i < oPieces.length; i++){

            sPieces.add(oPieces[i]);

        }

        Collections.shuffle(sPieces);


        int i = 0;
        for (int y = 0; y < divisions; y++){

            for (int x = 0; x < divisions; x++){

                oPieces[i] = sPieces.get(i);
                oPieces[i].px = x;
                oPieces[i].py = y;
                i++;

            }

        }

        if (check()){

            shuffle();

        } else {

            revisualize();

        }

    }

    private void switchPieces(){

        int i1 = -1;
        int i2 = -1;
        for (int i = 0; i < pieces.length; i++){

            if (pieces[i] == piece1){

                i1 = i;

            } else if (pieces[i] == piece2){

                i2 = i;

            }

        }

        if (i1 >= 0 && i2 >= 0){

            int opx    = piece1.px;
            int opy    = piece1.py;

            piece1.px  = piece2.px;
            piece1.py  = piece2.py;
            
            piece2.px  = opx;
            piece2.py  = opy;

            pieces[i1] = piece2;
            pieces[i2] = piece1;

            piece1.unselect();
            piece2.unselect();

            piece1     = null;
            piece2     = null;

        }

        revisualize();
        boolean correct = check();
        if (correct){

            disable();
            timer.cancel();
            this.win.run();

        }

    }

    public void select(PuzzlePiece piece){

        if (piece1 == null){

            piece1 = piece;
            piece.select();

        } else {

            boolean xfits = false;
            boolean yfits = false;

            if (piece.px - 1 == piece1.px && piece1.py == piece.py){

                xfits = true;

            } else if (piece.px + 1 == piece1.px && piece1.py == piece.py){

                xfits = true;

            } else if (piece.px == piece1.px){

                xfits = true;

            }

            if (piece.py - 1 == piece1.py && piece1.px == piece.px){

                yfits = true;

            } else if (piece.py + 1 == piece1.py && piece1.px == piece.px){

                yfits = true;

            } else if (piece.py == piece1.py){

                yfits = true;

            }

            piece2 = piece;
            if (xfits && yfits){

                switchPieces();

            }

        }
        
    }

    public void start(int time, Runnable winCallback, Runnable loseCallback){

        if (started == true){

            Debugging.warn("Tried to start puzzle that already had been started prior.");
            return;

        }
        started = true;

        setVisible(true);
        progress.setVisible(true);

        Timer t1 = new Timer();
        t1.schedule(new TimerTask() {
            @Override
            public void run() {
                
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        shuffle();
                    }
                });
                t1.cancel();
                Timer t = new Timer();
                timer   = t;
                long finishTime = System.currentTimeMillis() + time;
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        long currentTime = System.currentTimeMillis();
                        if (finishTime - currentTime <= 0L){
                            progress.setBounds((int) progress.getBounds().getX(), (int) (getBounds().getY() + getBounds().getHeight() + 20), 0, 10);

                            if (loseCallback != null){

                                loseCallback.run();
                                disable();

                            }

                            t.cancel();
                            
                        } else {

                            long timeLeft = finishTime - currentTime;
                            percent       = 100d / time * (double) timeLeft;
                            int prog      = (int) Math.floor((double) getBounds().getWidth() / time * timeLeft);
                            progress.setBounds((int) progress.getBounds().getX(), (int) (getBounds().getY() + getBounds().getHeight() + 20), prog, 10);

                        }
                    }
                }, 0, 10);

            }
        }, prepTime);
        this.win = winCallback;

    }

    public ImagePuzzle(URL image, Scene scene, int size, int divisions){

        this.image = new ImageIcon(image).getImage();
        this.scene = scene;
        this.size  = size;
        this.divisions = divisions;
        this.progress  = new JPanel();
        this._this     = this;

        this.progress.setVisible(false);

        this.progress.setBackground(Color.white);
        scene.window.add(this.progress);

        setLayout(new GridLayout(divisions,divisions));
        pieces = new PuzzlePiece[divisions * divisions];

        int i = 0;
        for (int x = 0; x < divisions; x++){

            for (int y = 0; y < divisions; y++){

                PuzzlePiece piece = new PuzzlePiece(this.image, y, x, divisions, this);
                pieces[i] = piece;
                add(piece);
                i++;

            }

        }

        scene.window.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                resize();
            }
        });

        resize();
        setVisible(false);

    }

}
