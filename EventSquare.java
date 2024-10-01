public abstract class EventSquare extends Square{
    
    EventSquare(String name, String icon){
        super(name, icon);
    }
    abstract void performEvent(Player player);

}
