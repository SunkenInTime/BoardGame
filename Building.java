public class Building extends Square{

    int price;
    Player owner;
    String alias;


    
    Building(String name, int price, String icon, int position){

        super(name, icon, position);
        this.price = price;
        this.name = name;
       
        
    }

}