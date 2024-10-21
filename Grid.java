import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Grid {

    // Game values
   
    Square[][] locationGrid = { 
            { 
                new Square("Start", "➡ ",1),
                new Building("Circuit Avenue", 200, "⚡",2),
                new Chest(3),
                new Building("Skibidi Avenue", 200, "🚽",4),
                new Square("Just Visiting", "✈️",5), 
            }, 
            {
                new Building("Karis Park", 0, "🏞️ ",16),
                new Square(),
                new Square(), 
                new Square(), 
                new Chance(6)
            },
            {
                new IncomeTax(15),
                new Square(),
                new Square(), 
                new Square(), 
                new Building("Le Hotel", 200, "🏨",7),

            },
            {
                new Building("Elden Avenue", 0, "🧓",14),
                new Square(),
                new Square(), 
                new Square(), 
                new Building("Sunny Boardwalk", 200, "🏖️",8),  
            },
            {
                new GoToJail(13),
                new Chance(12),
                new Building("NYC Street", 0, "🌆",11),
                new IncomeTax(10),
                new Square("Free Parking", "🅿️",9),
            }
        
            };
         

    boolean isFinished = false;
    ArrayList<Player> listOfPlayers = new ArrayList<Player>();

    int minPlayers = 2;
    int maxPlayers = 4;
    ArrayList<String> playerIcons = new ArrayList<>(Arrays.asList("🐔", "😼", "🦅", "🤑"));

    void startGame() {
        // Ask for amount of players 4 max

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Welcome to Monopoly Lite");
        addPlayers(myScanner);

        myScanner.close();

    }

    void gameLoop() {

        while (isFinished == false) {

            // TODO: how to link the values in various areas
        }
    }

    void displayInfo() {
        // Display the current turn
        // Display players money
        // Display status
        // Display other plays positions
    }

    void addPlayers(Scanner myScanner) {
        int playerCount;

        // Grabbing Player Count
        while (true) {
            System.out.println("How many people are playing(4 players max)");
            playerCount = myScanner.nextInt();
            myScanner.nextLine();
            if (playerCount > minPlayers && playerCount <= maxPlayers) {
                break;
            } else if (playerCount < minPlayers) {
                System.out.println("You don't have enough players");
            } else if (playerCount > maxPlayers) {
                System.out.println("You have too many players");
            }
        }

        

        // Getting the player
        for (int i = 0; i < playerCount; i++) {
            String playerName;
            String icon;

            System.out.println("Name?");
            playerName = myScanner.nextLine().trim();

            while (playerName.isEmpty()) {
                System.out.println("Name cannot be empty. Please enter a name:");
                playerName = myScanner.nextLine().trim();
            }
            icon = getPlayerIcon();
            Player tempPlayer = new Player(playerName, icon);

            listOfPlayers.add(tempPlayer);

        }
    }

    String getPlayerIcon() {
        String icon;

        Random random = new Random();
        int num = random.nextInt(playerIcons.size());
        icon = playerIcons.get(num);
        playerIcons.remove(num);

        return icon;
    }

    Player returnPlayerInPosition(int position){
        Player result = new Player("null", "" );
        for(Player player : listOfPlayers){
            if(player.getPosition() == position){
                return player;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
       


        for (int i = 0; i < locationGrid.length; i++){

            for(int k = 0; k < locationGrid[i].length; k++){
                Square tempSquare = locationGrid[i][k];
                if(!tempSquare.name.equals("Placeholder")){
                    Player temPlayer = returnPlayerInPosition(tempSquare.position);
                    if(!temPlayer.name.equals("null")){
                        result += temPlayer.icon;
                    }else{
                        result += tempSquare.icon;
                    }
                }else{
                    result += tempSquare.icon;
                }
            }
            result += "\n";
        }
        
        return result;

    }

    // I have to figure out how to translate player positional data into the vidual interface
    // if there is /5
}