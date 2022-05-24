package game;

import game.levels.BlackBridge;
import game.levels.HighPeakCastle;
import game.levels.PrisonQuarters;
import game.levels.ThroneRoom;

public class Main
{
    
    public int lastThought;
    public Inventory inventory = new Inventory();
    public Context context     = new Context();

    public static void main(String args[]){
    
        new Main();
        
    }
    
    public Window window;
    public Main()
    {
        this.window       = new Window(this);
        Scene start       = new ThroneRoom(this.window);
        window.setScene(start);
    }
}

