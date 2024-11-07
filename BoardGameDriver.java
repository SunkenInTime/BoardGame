
public class BoardGameDriver {

    public static void main(String[] args) {
        Grid myGrid = new Grid(true);
        myGrid.startGame();
        System.out.println(myGrid.toString());

    }
} 