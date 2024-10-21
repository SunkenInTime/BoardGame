public abstract class EventSquare extends Square{
    
    EventSquare(String name, String icon, int position){
        super(name, icon, position);
    }
    abstract void performEvent(Player player);

}
