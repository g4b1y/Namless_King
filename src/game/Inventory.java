package game;

import java.util.ArrayList;

public class Inventory {
     
    public ArrayList<Integer> items = new ArrayList<Integer>();
    public Inventory(){

        Debugging.log("game.Inventory initialized.");

    }
 
    public void add(int id){

        items.add(id);

    }

    public void remove(int id){
        
        items.remove(id);

    }

    public boolean has(int id){

        return items.contains(id);

    }

}
