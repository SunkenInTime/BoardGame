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
    
     Grid(boolean isDebug){
        this.isDebug = isDebug;
     }
    
    Scanner myScanner = new Scanner(System.in);
    boolean isFinished = false;
    boolean isDebug;
    ArrayList<Player> listOfPlayers = new ArrayList<Player>();
    
    int minPlayers = 2;
    int maxPlayers = 4;
    ArrayList<String> playerIcons = new ArrayList<>(Arrays.asList("🐔", "😼", "🦅", "🤑"));


    void startGame() {
        // Ask for amount of players 4 max

        

        System.out.println("Welcome to Monopoly Lite");
        
        if(isDebug){
            addDebugPlayers();
        }else{
            addPlayers();

            
        }
        
        gameLoop();
        
        myScanner.close();

    }

    void gameLoop() {

        
        while (isFinished == false) {



            for(int i = 0; i < listOfPlayers.size(); i++){
                Player player = listOfPlayers.get(i);
                makeTurn(player);
                displayInfo(player);



                print("Press enter to move to next player");
                myScanner.nextLine();
                
                

            }
            
        }

        
    }

    void displayInfo(Player player) {
        // Display the current turn
        // Display players money
        // Display status
        // Display other plays positions

        print("\nName: " + player.name + " " + player.icon);
        print("Money: " + player.money);
        print("Current Tile: " + player.position );
        print("Status: " + ((player.sentenceLength > 0) ? "In Jail" : "Free") );
        print("Properties owned: ");

        
        for(Square[] tileList : locationGrid){
            for(Square tile : tileList){
                
                if(tile.name.equals(player.name)){

                };
            }
        }
    }

    Player makeTurn(Player player){
        
        Player gamePlayer = player;
        print(player.name + " " + player.icon + " is rolling the dice");
        int diceNum = rollDice();
        print(player.name + " " + player.icon + " rolled " + diceNum);
        player.addPosition(diceNum);
        

        while (!checkIfSquareEmpty(player)) {
            player.addPosition(-1);
            print("A player is on that square, you have been moved back once");
        }
        print(toString());
        
        int[] squareIndex = linearTo2D(player.getPosition());
        Square square = locationGrid[squareIndex[0]][squareIndex[1]];
        
        
        if(square instanceof EventSquare){
            try{
            gamePlayer = ((EventSquare)square).performEvent(player, this, myScanner);
            } catch(UnsupportedOperationException e){
                print("Feature isn't available yet");
            }
        }
        
        return gamePlayer;
        
    }

    boolean checkIfSquareEmpty(Player myPlayer){


        for(Player player : listOfPlayers){
            if(player.position == myPlayer.position && !player.name.equals(myPlayer.name)){
                return false;
            }
        }
        return true;
    }
    void locationGridTest(){

        for(int i = 0; i < locationGrid.length; i++){

            for(int k = 0; k < locationGrid[i].length; k++){
                
                Square square = locationGrid[i][k];
                if(square.position > 0){
                    
                   int[] location = linearTo2D(square.position);

                   if(location[0] == i && location[1] == k){
                    System.out.println("Number " + square.position+ " W orks");
                   }else{
                    System.out.println("Number " + square.position+ " No Works here is the returned location by the function" + Arrays.toString(location));
                   }
                }
            }
        }
    }

    // The code is meant to turn the outer layer of 5x5 grid into a numbered list one being 0,0 and then the last being 1,0

    int[] linearTo2D(int num){
        int[] position = {0,0};
        if(num <= 5){
            position[0] = 0;
            position[1] = num-1;
            
        }else if (num < 10){
            position[0] = num - 5;
            position[1] = 4;
        }else if ( num < 14){
            position [0] = 4;
            position [1] = 13-num;
            // 4, 3
            // have 5-2 (15 - num )
            //linear 10
        }else{
            position[0] = 17 - num ;
            position[1] = 0;
        }
        return position;
    }

   

    int rollDice(){
        Random random = new Random();
        int result;

        for(int i = 0; i < 20; i++) {
            System.out.print("\r" + (i % 2 == 0 ? "/" : "\\"));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            
        }
        print(" \n");

        
        result = random.nextInt(4) + 1;

        
        return result;
    }

    void handlePlayerPayments(String payerName, String recipient, int amt){
        
        for(int i = 0; i < listOfPlayers.size(); i++){
            if(listOfPlayers.get(i).name.equals(recipient)){
                listOfPlayers.get(i).addMoney(amt);
                System.out.println(payerName + " has paid " + amt + " to " + recipient);
                return;
            }
        }
        System.out.println("Player does not exist");
    }

    void addDebugPlayers(){
        String[] debugNames = {
            "Bob",
            "Rachel",
            "Luc",
            "Sristi"
        };

        for(String playerName : debugNames){
            String icon =   getPlayerIcon();         
            Player tempPlayer = new Player(playerName, icon);

            listOfPlayers.add(tempPlayer);
        }

    }
    void addPlayers() {
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

    void print(String input){
        System.out.println(input);
    }

    // I have to figure out how to translate player positional data into the vidual interface
    // if there is /5
}