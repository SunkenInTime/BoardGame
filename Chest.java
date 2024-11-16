import java.util.Scanner;
import java.util.Random;
public class Chest extends EventSquare{
    

    Chest(int position){
        super("Chest", "🎁", position);
    }
    
    interface CardAction{

        Player execute(Player player);
    }

       // String[] communityChestCards = {
    //     "Bank error in your favor. Collect $200.",
    //     "Doctor's fees. Pay $50.",
    //     "From sale of stock you get $50.",
    //     "Get Out of Jail Free.",
    //     "Go to Jail. Go directly to Jail.",
    //     "Holiday Fund matures. Collect $100.",
    //     "Income tax refund. Collect $20.",
    //     "Pay hospital fees of $100.",
    //     "Pay school fees of $150.",
    //     "You have won second prize in a beauty contest. Collect $10.",
    //     "You inherit $100."
    // };


    enum ChestCard{
                // Properties for each enum constant
       

        BANK_ERROR(
            "Bank error in your favor. Collect $200.",
            player -> {
                player.addMoney(200);
                return player;
            }
        ),


        DOCTOR_FEES(
            "Doctor's fee. Pay $50",
            player -> {
                player.addMoney(-50);
                return player;
            }
        ),

        TAX_WRITEOFF(
            "You bought a Cybertruck and earn a tax right off. Collect $100",
            player ->{
                player.addMoney(100);
                return player;
            }
        ),

        PAY_TUITION(
            "You decided to go to college. Pay $200",
            player -> {
                player.addMoney(-200);
                return player;
            }
        ),

        LIFE_INSURANCE_FRAUD(
            "You defrauded the IRS and actually got away with it. Collect $200",
            player -> {
                player.addMoney(200);
                return player;
            }
        ),

        INHHERITANCE(
            "Your father has a serious gambling addiction and he bet your lives saving on Jake Paul. You lose all your money.",
            player -> {
                player.addMoney(-player.money);
                return player;
            }
        ),

        GO_TO_JAIL(
            "Go to Jail. Go directly to Jail.",
            player -> {
                player.addSentence(1);
                return player;
            }
        );



        private final String description;
        private final CardAction action;
        

        ChestCard(String description, CardAction action){
            this.description = description;
            this.action = action;
        }

        public String getDescription(){
            return description;
        }

        public Player executePlayer(Player player){
            return action.execute(player);
            
        }
        // public Player doCardAction(Player player) {
        //     switch(this) {
        //         case BANK_ERROR:
        //             player.addMoney(200);
        //             break;
        //         case DOCTOR_FEES:
        //             player.addMoney(-50);
        //             break;
        //     }
        //     return player;
        // }
    }

    @Override
    Player performEvent(Player player, Grid grid, Scanner myScanner) {
        Random random = new Random();
        GameTimer.loading(2);
        ChestCard card = ChestCard.values()[random.nextInt(ChestCard.values().length)];
        ColorPrinter.print(card.getDescription(), ColorPrinter.MessageType.EVENT);
        return card.executePlayer(player);
        
    }
    

}
