public class Grid{

   
    Square[] locationGrid = {
    new Square("Start", "➡"),
    new Building("Mid Avenue", 200, "Mid Ave"),
    new Chest(),
    new Building("Skibidi Avenue",200, "Skib Ave"),
    new Square("Just Visiting", ""),
    
    new Chance(),
    new Building("Le Hotel", 200, "Luc Ave"),
    new Building("Sunny Boardwalk", 200, "Sun Ave"),

    new Square("Free Parking", ""),
    new IncomeTax(),
    new Building("NYC Street", 0, "NYC St."),
    new Chance(),
    new GoToJail(),

    new Building("Vermont Avenue", 0, "Vmt Ave"),
    new IncomeTax(),
    new Building("Karis Park", 0, "Ks Park")
    
};

    Player[][] playerGrid;
    
    @Override
    public String toString (){
        return null;

    }
}