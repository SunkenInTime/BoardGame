public class Player{
    String icon;
    String name;
    int money;
    int position;

    Player(String name, String icon){
        this.name = name;
        this.icon = icon;
        this.money = 0;
        position = 0;
    }


    int getPosition(){
        return position;
    }
}