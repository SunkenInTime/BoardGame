
public class BoardGameDriver {

    public static void main(String[] args) {
        Grid myGrid = new Grid(false);
        myGrid.startGame();
        System.out.println(myGrid.toString());

    }
} 
