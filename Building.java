public class Building extends Square{

    int price;
    Player owner;
    String alias;

    Building(String name, int price, String alias){

        super(name, "");
        this.price = price;
        this.name = name;
        this.alias = alias;
        
    }

}