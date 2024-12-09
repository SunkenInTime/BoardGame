import java.util.Scanner;
import java.util.Random;
public class Chance extends EventSquare{

    Chance(int position){
        super("Chance", "❓", position);
    }


    interface CardAction{

        Player execute(Player player);
    }


    enum ChanceCard{
    
       

        CRYPTO_INVESTMENT(
            "You invested in a new cryptocurrency that definitely isn't a scam. Roll dice: Either you double your bet or you lose it all.", 
            player -> {
                Random rand = new Random();
                GameTimer.loading(2);
                int roll = rand.nextInt(6) + 1;
                if(roll % 2 == 0) {
                    
                    ColorPrinter.print("Wow... I'm suprised that worked. Good on ya! You earned $" + player.money * 2, ColorPrinter.MessageType.MONEY);
                    player.addMoney(player.money * 2);
                    return player;
                } else {
                    ColorPrinter.print("Your money would've been better spent on valorant skins. You lost it all", ColorPrinter.MessageType.WARNING);
                    player.addMoney(-player.money);
                    return player;
                }
            }
        ),

        VIRAL_VIDEO(
            "Your cat video went viral! Collect $150 in ad revenue.",
            player -> {
                player.addMoney(150);
                return player;
            }
        ),

        UBER_SURGE(
            "Uber surge pricing kicks in. Pay double fare: -$100",
            player -> {
                player.addMoney(-100);
                return player;
            }
        ),

        STARTUP_INVESTOR(
            "A startup you invested in got acquired! Advance to Start and collect $200",
            player -> {
                
                player.setPlayerPosition(1); // Assuming 1 is Start
                player.addMoney(200);
                return player;
            }
        ),

        SOCIAL_MEDIA_CANCEL(
            "You got cancelled on social media. Move back 3 spaces.",
            player -> {
                player.addPosition(-3);
                return player;
            }
        ),

        REMOTE_WORK(
            "Your company went fully remote! Save on commute - collect $50",
            player -> {
                player.addMoney(50);
                return player;
            }
        ),

        PHONE_REPAIR(
            "Cracked phone screen needs repair. Pay $75",
            player -> {
                player.addMoney(-75);
                return player;
            }
        ),

        MEME_STOCK(
            "Your meme stock investment paid off! Collect $250",
            player -> {
                player.addMoney(250);
                return player;
            }
        ),

        SUBSCRIPTION_SERVICES(
            "Forgot to cancel free trials. Pay $50",
            player -> {
                player.addMoney(-50);
                return player;
            }
        ),

        TIKTOK_DANCE(
            "Your TikTok dance challenge went viral! Move to any property on the board.",
            player -> {
                ColorPrinter.print("Choose a position to move to (1-16):", ColorPrinter.MessageType.SYSTEM);
                player.customPlayerPosition();

                
                // player.setPosition(newPos);
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
        Random random = new Random();
        GameTimer.loading(2);
        ChanceCard card = ChanceCard.values()[random.nextInt(ChanceCard.values().length)];
        ColorPrinter.print(card.getDescription(), ColorPrinter.MessageType.EVENT);
        return card.executePlayer(player);

    }
    
} 
