import java.util.Scanner;
public abstract class EventSquare extends Square{
    
    EventSquare(String name, String icon, int position){
        super(name, icon, position);
    }
    abstract Player performEvent(Player player, Grid grid, Scanner myScanner);
    
}
