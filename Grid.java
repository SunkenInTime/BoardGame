import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Grid {

    // Game values

    Square[][] locationGrid = {
            {
                    new Square("Start", "➡ ", 1),
                    new Building("Circuit Avenue", 200, "⚡", 2),
                    new Chest(3),
                    new Building("Skibidi Avenue", 200, "🚽", 4),
                    new Square("Just Visiting", "✈️", 5),
            },
            {
                    new Building("Karis Park", 0, "🏞️ ", 16),
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
                    new Building("Le Hotel", 200, "🏨", 7),

            },
            {
                    new Building("Elden Avenue", 0, "🧓", 14),
                    new Square(),
                    new Square(),
                    new Square(),
                    new Building("Sunny Boardwalk", 200, "🏖️", 8),
            },
            {
                    new GoToJail(13),
                    new Chance(12),
                    new Building("NYC Street", 0, "🌆", 11),
                    new IncomeTax(10),
                    new Square("Free Parking", "🅿️", 9),
            }

    };

    Grid(boolean isDebug) {
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

        if (isDebug) {
            addDebugPlayers();
        } else {
            addPlayers();
        }

        gameLoop();

        myScanner.close();

    }

    void gameLoop() {

        while (isFinished == false) {

            for (int i = 0; i < listOfPlayers.size(); i++) {
                Player player = listOfPlayers.get(i);
                displayInfo(player);
                GameTimer.quickPause();
                makeTurn(player);
                GameTimer.quickPause();
                displayInfo(player);

                ColorPrinter.print("Press enter to move to next player", ColorPrinter.MessageType.SYSTEM);
                myScanner.nextLine();

            }

        }

    }

    void displayInfo(Player player) {
        // Display the current turn
        // Display players money
        // Display status
        // Display other plays positions
        int[] squareIndex = linearTo2D(player.getPosition());
        Square square = locationGrid[squareIndex[0]][squareIndex[1]];

        String listOfProperties = "";
        for (Square[] tileList : locationGrid) {
            for (Square tile : tileList) {

                if (tile instanceof Building) {

                    if (((Building) tile).owner.equals(player.name)) {
                        listOfProperties += tile.name + " " + tile.icon + ", " + tile.position + ", ";
                    }
                }
            }
        }
        if (!listOfProperties.isEmpty()) {
            listOfProperties = listOfProperties.substring(0, listOfProperties.length() - 2);

        } else {
            listOfProperties = "none :(";
        }

        ColorPrinter.print("\nName: " + player.name + " " + player.icon, ColorPrinter.MessageType.PLAYER);
        ColorPrinter.print("Money: " + player.money, ColorPrinter.MessageType.PLAYER);
        ColorPrinter.print("Current Tile: " + square.name + " " + square.icon + ", " + player.getPosition(),
                ColorPrinter.MessageType.PLAYER);
        ColorPrinter.print("Status: " + ((player.sentenceLength > 0) ? "In Jail" : "Free"),
                ColorPrinter.MessageType.PLAYER);
        ColorPrinter.print("Properties owned: " + listOfProperties, ColorPrinter.MessageType.PLAYER);

    }

    Player makeTurn(Player player) {

        Player gamePlayer = player;
        String playerDisplayName = player.name + " " + player.icon;
        ColorPrinter.print(playerDisplayName + " is rolling the dice", ColorPrinter.MessageType.DICE);
        int diceNum = rollDice();
        ColorPrinter.print(playerDisplayName + " rolled " + diceNum, ColorPrinter.MessageType.DICE);
        player.addPosition(diceNum);

        GameTimer.quickPause();

        while (!checkIfSquareEmpty(player)) {
            player.addPosition(-1);
            GameTimer.quickPause();
            ColorPrinter.print("A player is on that square, you have been moved back once",
                    ColorPrinter.MessageType.SYSTEM);
        }

        int[] squareIndex = linearTo2D(player.getPosition());
        Square square = locationGrid[squareIndex[0]][squareIndex[1]];

        ColorPrinter.print(playerDisplayName + "landed on " + square.name + " " + square.icon,
                ColorPrinter.MessageType.PLAYER);
        GameTimer.quickPause();
        ColorPrinter.print("\n" + toString());

        if (square instanceof EventSquare) {
            try {
                gamePlayer = ((EventSquare) square).performEvent(player, this, myScanner);
            } catch (UnsupportedOperationException e) {
                ColorPrinter.print("Feature isn't available yet", ColorPrinter.MessageType.WARNING);
            }
        }

        return gamePlayer;

    }

    boolean checkIfSquareEmpty(Player myPlayer) {

        for (Player player : listOfPlayers) {
            if (player.getPosition() == myPlayer.getPosition() && !player.name.equals(myPlayer.name)) {
                return false;
            }
        }
        return true;
    }

    // void locationGridTest(){

    // for(int i = 0; i < locationGrid.length; i++){

    // for(int k = 0; k < locationGrid[i].length; k++){

    // Square square = locationGrid[i][k];
    // if(square.position > 0){

    // int[] location = linearTo2D(square.position);

    // if(location[0] == i && location[1] == k){
    // System.out.println("Number " + square.position+ " W orks");
    // }else{
    // System.out.println("Number " + square.position+ " No Works here is the
    // returned location by the function" + Arrays.toString(location));
    // }
    // }
    // }
    // }
    // }

    // void linearTo2DOverflowTest(int num){
    // System.out.println((linearTo2D(num))[0]);
    // System.out.println((linearTo2D(num))[1]);

    // }

    // The code is meant to turn the outer layer of 5x5 grid into a numbered list
    // one being 0,0 and then the last being 1,0

    int[] linearTo2D(int num) {
        int[] position = { 0, 0 };
        if (num <= 5) {
            position[0] = 0;
            position[1] = num - 1;

        } else if (num < 10) {
            position[0] = num - 5;
            position[1] = 4;
        } else if (num < 14) {
            position[0] = 4;
            position[1] = 13 - num;
        } else {
            position[0] = 17 - num;
            position[1] = 0;
        }
        return position;
    }

    int rollDice() {
        Random random = new Random();
        int result;

        GameTimer.loading(2);
        ColorPrinter.print("");

        result = random.nextInt(4) + 1;

        return result;
    }

    void handlePlayerPayments(String payerName, String recipient, int amt) {

        for (int i = 0; i < listOfPlayers.size(); i++) {
            if (listOfPlayers.get(i).name.equals(recipient)) {
                listOfPlayers.get(i).addMoney(amt);
                ColorPrinter.print(payerName + " has paid " + amt + " to " + recipient, ColorPrinter.MessageType.MONEY);
                return;
            }
        }
        System.out.println("Player does not exist");
    }

    void addDebugPlayers() {
        String[] debugNames = {
                "Bob",
                "Rachel",
                "Luc",
                "Sristi"
        };

        for (String playerName : debugNames) {
            String icon = getPlayerIcon();
            Player tempPlayer = new Player(playerName, icon, myScanner);

            listOfPlayers.add(tempPlayer);
        }

    }

    void addPlayers() {
        int playerCount;
        // Grabbing Player Count
        while (true) {
            ColorPrinter.print("How many people are playing(4 players max)?", ColorPrinter.MessageType.SYSTEM);

            try {
                playerCount = myScanner.nextInt();
                myScanner.nextLine();
                if (playerCount >= minPlayers && playerCount <= maxPlayers) {
                    break;
                } else if (playerCount < minPlayers) {
                    ColorPrinter.print("You don't have enough players", ColorPrinter.MessageType.WARNING);
                } else if (playerCount > maxPlayers) {
                    ColorPrinter.print("You have too many players", ColorPrinter.MessageType.WARNING);
                }
            } catch (Exception e) {
                myScanner.nextLine();
                ColorPrinter.print("An error occured, please re-enter data", ColorPrinter.MessageType.WARNING);
            }
        }

        // Getting the player
        for (int i = 0; i < playerCount; i++) {
            String playerName;
            String icon;
            
            ColorPrinter.print("Name?", ColorPrinter.MessageType.SYSTEM);
            playerName = myScanner.nextLine().trim();

            while (playerName.isEmpty()) {
                ColorPrinter.print("Name cannot be empty. Please enter a name:", ColorPrinter.MessageType.WARNING);
                playerName = myScanner.nextLine().trim();
            }
            icon = getPlayerIcon();
            Player tempPlayer = new Player(playerName, icon, myScanner);

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

    Player returnPlayerInPosition(int position) {
        Player result = new Player("null", "", myScanner);
        for (Player player : listOfPlayers) {
            if (player.getPosition() == position) {
                return player;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < locationGrid.length; i++) {

            for (int k = 0; k < locationGrid[i].length; k++) {
                Square tempSquare = locationGrid[i][k];
                if (!tempSquare.name.equals("Placeholder")) {
                    Player temPlayer = returnPlayerInPosition(tempSquare.position);
                    if (!temPlayer.name.equals("null")) {
                        result += temPlayer.icon;
                    } else {
                        result += tempSquare.icon;
                    }
                } else {
                    result += tempSquare.icon;
                }
            }
            result += "\n";
        }

        return result;

    }

    // I have to figure out how to translate player positional data into the vidual
    // interface
    // if there is /5
}