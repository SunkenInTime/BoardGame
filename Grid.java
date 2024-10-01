public class Grid{

   
    Square[] locationGrid = {
    new Square("Start", "➡"),
    new Building("Circuit Avenue", 200, "⚡"),
    new Chest(),
    new Building("Skibidi Avenue",200, "🚽"),
    new Square("Just Visiting", "✈️"),
    
    new Chance(),
    new Building("Le Hotel", 200, "🏨"),
    new Building("Sunny Boardwalk", 200, "🏖️"),

    new Square("Free Parking", "🅿️"),
    new IncomeTax(),
    new Building("NYC Street", 0 ,"🌆"),
    new Chance(),
    new GoToJail(),

    new Building("Elden Avenue", 0, "🧓"),
    new IncomeTax(),
    new Building("Karis Park", 0, "🏞️")
    
};

    Player[][] playerGrid;
    
    @Override
    public String toString (){
        String result = "";
        int counter = 0;
        for(int i = 0; i < locationGrid.length; i++){
            
           result += locationGrid[i].icon;
           counter++;
           if (counter >= 5){
            result += "\n";
            counter = 0;
           }
        }
        return result;

    }
}