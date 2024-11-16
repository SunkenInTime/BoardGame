import java.util.Scanner;
public class Chance extends EventSquare{

    Chance(int position){
        super("Chance", "❓", position);
    }


    interface CardAction{

        Player execute(Player player);
    }


    enum ChanceCard{
    
       

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
        

        ChanceCard(String description, CardAction action){
            this.description = description;
            this.action = action;
        }

        public String getDescription(){
            return description;
        }

        public Player executePlayer(Player player){
            return action.execute(player);
            
        }
    }

    @Override
    Player performEvent(Player player, Grid grid, Scanner myScanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'performEvent'");
    }
    
} 
