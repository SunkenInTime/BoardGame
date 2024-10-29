import java.util.Scanner;

public class Building extends EventSquare{

    int price;
    String owner;
    String alias;
    int rent;


    
    Building(String name, int price, String icon, int position){

        super(name, icon, position);
        this.price = price;
        this.name = name;
        rent = (int) (price* .2);
        
    }

//Less that 100 sell
// Sell
// Pay
// Buy
    @Override
    Player performEvent(Player player, Grid grid) {
        Player tempPlayer = player;
        Scanner myScanner = new Scanner(System.in);


        // buying
        if(owner.isEmpty()){
            System.out.println("Do you wish to buy this building? Y or N");
            String response = myScanner.nextLine();
            if(response.equalsIgnoreCase("y")){

                if(tempPlayer.money < price){
                    myScanner.close();
                    return tempPlayer;
                }

                owner = tempPlayer.name;
                tempPlayer.payMoney(price);

                myScanner.close();
                return tempPlayer;
            } 
        }


        // Paying rent
        if (!owner.isEmpty() && !tempPlayer.name.equals(owner)) {
            System.out.println("You landed on " + name + " owned by " + owner);
            System.out.println("Paying rent of " + rent + " to " + owner);
            if(tempPlayer.money < rent){
                tempPlayer.addPosition(-1);
                System.out.println("Yer dirt poor");
                myScanner.close();
                return tempPlayer;
                //TODO: re-iterate again
            }
            tempPlayer.payMoney(rent);
            grid.handlePlayerPayments(tempPlayer.name, name, rent);
        }

        
        //Selling
        if(tempPlayer.name.equals(owner)){
            
            // give option to sell
            System.out.println("Do you wish to sell this house? Y or N");
            String response = myScanner.nextLine();
            if(response.equalsIgnoreCase("y")){
                owner = "";
                
                tempPlayer.addMoney(price - (int)(price * .2));
            } 
            myScanner.close();
            return tempPlayer;           
        }

        
        myScanner.close();
        return tempPlayer;
    }



}