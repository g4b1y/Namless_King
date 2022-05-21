package game;

public class Debugging
{
    
    private static boolean enabled = true;
    public static void warn (String message){
     
        if (!enabled){
            return;
        }
        System.out.println(String.format("[!] %s", message));
        
    }
    
    public static void log (String message){
     
        if (!enabled){
            return;
        }
        System.out.println(String.format("[?] %s", message));
        
    }
    
    public static void error (String message){
     
        if (!enabled){
            return;
        }
        System.out.println(String.format("<!> %s", message));
        
    }
    
}
