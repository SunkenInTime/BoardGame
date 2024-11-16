import java.util.Scanner;

public class Building extends EventSquare{

    int price;
    String owner = "";
   
    int rent;


    
    Building(String name, int price, String icon, int position){

        super(name, icon, position);
        this.price = price;
        this.name = name;
        rent = (int) (price* .2);
        
    }


    @Override
    Player performEvent(Player player, Grid grid, Scanner myScanner) {
        Player tempPlayer = player;
        


        // buying
        if(owner.isEmpty()){
            ColorPrinter.print("Do you wish to buy this building? Y or N", ColorPrinter.MessageType.SYSTEM);
            String response = myScanner.nextLine();
            if(response.equalsIgnoreCase("y")){

                if(tempPlayer.money < price){
                   
                    return tempPlayer;
                }

                owner = tempPlayer.name;
                tempPlayer.payMoney(price);

                
                return tempPlayer;
            } 
        }


        // Paying rent
        if (!owner.isEmpty() && !tempPlayer.name.equals(owner)) {
            ColorPrinter.print("You landed on " + name + " owned by " + owner, ColorPrinter.MessageType.SYSTEM);
            ColorPrinter.print("Paying rent of " + rent + " to " + owner, ColorPrinter.MessageType.MONEY);
            if(tempPlayer.money < rent){
                tempPlayer.addPosition(-1);
                ColorPrinter.print("Yer dirt poor", ColorPrinter.MessageType.WARNING);
                
                return tempPlayer;
                //TODO: re-iterate again
            }
            tempPlayer.payMoney(rent);
            grid.handlePlayerPayments(tempPlayer.name, name, rent);
        }

        
        //Selling
        if(tempPlayer.name.equals(owner)){
            
            // give option to sell
            ColorPrinter.print("Do you wish to sell this house? Y or N", ColorPrinter.MessageType.SYSTEM);
            String response = myScanner.nextLine();
            if(response.equalsIgnoreCase("y")){
                owner = "";
                
                tempPlayer.addMoney(price - (int)(price * .2));
            } 
            
            return tempPlayer;           
        }

        
       
        return tempPlayer;
    }



}