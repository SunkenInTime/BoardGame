import java.util.Scanner;
public class IncomeTax extends EventSquare{

    IncomeTax(int position){
        super("Income Tax", "🏦", position);
    }
    @Override
    Player performEvent(Player player, Grid grid, Scanner myScanner) {
        int totalIncome = 0;
        for(Square[] tileList : grid.locationGrid){
            for(Square tile : tileList){
                
                if(tile instanceof Building){
                    if(((Building) tile).owner.equals(player.name)){
                        totalIncome += ((Building)tile).rent;
                    }
                }
            }
        }
        
        if(totalIncome > 0){
           player.addMoney( - (int) (totalIncome * .2));
           ColorPrinter.print("You make $" + totalIncome + " each roll. Your income will be taxed by 20%. Pay $" + (int) (totalIncome * .2), ColorPrinter.MessageType.MONEY);
        }else{
            
           ColorPrinter.print("You make $0 each roll. Have you ever heard of passive income? You pay nothing", ColorPrinter.MessageType.EVENT);
        }
        return player;
    }
    
}
