package game;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.TimerTask;
import java.util.Timer;

class DialogOption {

    public String label;
    public String key;
    public Runnable callback;

    public DialogOption(String label, String key, Runnable callback){

        this.label    = label;
        this.key      = key;
        this.callback = callback;

    }

}

public class Dialog extends JPanel {

    //private ArrayList<ArrayList<String>> options = new ArrayList<>(); 
    private ArrayList<DialogOption> options = new ArrayList<>();
    private String text;
    private String type;
    private Scene scene;
    private int time  = -1;
    private Timer timer;
    private Runnable timerCallback;

    public Dialog(String type, Scene scene)
    {
        super();

        this.scene = scene;

        switch (type){

            case "fight":
            case "conversation":
            case "continue":

                this.type = type;
                break;

            default:

                Debugging.warn("game.Dialog has been initated without a valid type!");
                break;

        }
    }

    public void addOption(String label, String key, Runnable callback){

        options.add(new DialogOption(label, key, callback));

        /*
        options.add(new ArrayList<String>(){{
            add(label);
            add(key);
        }});
        */

    }

    public void setPrompt(String text){

        this.text = text;

    }

    public void construct(){

        Debugging.log(String.format("Created game.Dialog (Type: %s)", type));

        for (int i = scene.window.getContentPane().getComponents().length - 1; i >= 0; i--){

            if (scene.window.getContentPane().getComponents()[i].getClass().getSimpleName() == "game.Dialog"){

                scene.window.getContentPane().remove(scene.window.getContentPane().getComponents()[i]);

            }

        }

        setLayout(null);
        setVisible(false);

        GridLayout gridLayout = new GridLayout(3,1);
        JPanel optionCon = new JPanel();
        optionCon.setLayout(gridLayout);
        gridLayout.setVgap(2);
        gridLayout.setHgap(2);
    
        add(optionCon);

        JLabel prompt = new JLabel(String.format("<html><p>%s</p></html>", this.text), SwingConstants.CENTER);
        prompt.setBounds(0,0,400, 75);
        optionCon.setBounds(0,75,400, 94);
        add(prompt);

        optionCon.setOpaque(false);

        prompt.setForeground(Color.white);
        prompt.setOpaque(false);
        prompt.setFont(new Font(prompt.getFont().getName(), Font.PLAIN, 16));
        setOpaque(false);

        if (this.time > 0){

            long finishTime = System.currentTimeMillis() + this.time;
            int fullSize    = 400;

            JLabel progressBar = new JLabel();
            progressBar.setBounds(0, 0, 0, 3);
            progressBar.setBackground(Color.white);
            progressBar.setVisible(true);
            progressBar.setOpaque(true);

            prompt.add(progressBar);

            Timer t = new Timer();
            timer = t;
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    long currentTime = System.currentTimeMillis();
                    if (finishTime - currentTime <= 0L){
                        progressBar.setBounds(0, 0, 0, 3);

                        if (timerCallback != null){

                            timerCallback.run();

                        }

                        t.cancel();
                    } else {

                        long timeLeft = finishTime - currentTime;
                        int prog      = (int) Math.floor((double) fullSize / time * timeLeft);
                        progressBar.setBounds(0, 0, prog, 3);

                    }
                }
            }, 0, 10);

        }

        for (int i = 0; i < options.size(); i++){

            String label = options.get(i).label;
            //String key   = options.get(i).key;
            Runnable cb  = options.get(i).callback;
            JButton btn  = new JButton(String.format("%s", label));

            btn.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
            btn.setBackground(scene.floorColor);
            btn.setForeground(Color.WHITE);

            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(Color.GRAY);
                }
            
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(scene.floorColor);
                }
                @Override
                public void mousePressed(MouseEvent arg0) {
                    if (cb != null){
                        cb.run();
                    }
                    if (timer != null){
                        timer.cancel();
                    }
                    super.mousePressed(arg0);
                }
                
            });

            optionCon.add(btn);
            btn.setVisible(true);

        }

        setBounds(scene.window.getWidth() / 2  - 200,scene.window.getRootPane().getHeight() - 175,400, 175);
        scene.window.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                setBounds(scene.window.getWidth() / 2  - 200,scene.window.getRootPane().getHeight() - 175,400, 175);
            }
        });

        this.scene.window.repaint();

        this.scene.window.add(this);
        this.scene.dialog = this;

        optionCon.setVisible(true);
        for (Component c : optionCon.getComponents()){

            c.setVisible(true);

        }

    }

    public void setTime(int milliseconds){

        this.time = milliseconds;

    }

    public void onTimeExpire(Runnable callback){

        this.timerCallback = callback;

    }

    public void destroy(){

        this.getParent().remove(this);
        scene.window.repaint();

    }

}
