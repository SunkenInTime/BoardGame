public class Building extends Square{

    int price;
    Player owner;
    String alias;

    Building(String name, int price){

        super(name, "🏠");
        this.price = price;
        this.name = name;
       
        
    }
    
    Building(String name, int price, String icon){

        super(name, icon);
        this.price = price;
        this.name = name;
       
        
    }

}