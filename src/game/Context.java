package game;

import java.util.ArrayList;

public class Context {
    
    private ArrayList<String> context = new ArrayList<>();
    public boolean has(String key){

        return context.contains(key);

    }

    public void add(String key){

        context.add(key);

    }

}
