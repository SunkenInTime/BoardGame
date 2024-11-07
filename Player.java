public class Player{
    String icon;
    String name;
    int money;
    int position;
    
    int sentenceLength = 0;

    Player(String name, String icon){
        this.name = name;
        this.icon = icon;
        this.money = 0;
        position = 1;
    }


    int getPosition(){
        return position;
    }
    void addPosition(int increment){

        
       

        int tempPosition = position + increment;

        if(tempPosition < 1){
            tempPosition += 16;
        }
        if(position > 16){
            tempPosition -= 16;
        }

        position = tempPosition;

    }

    void payMoney(int money){
        this.money -= money;
    }
    void addMoney(int money){
        this.money += money;
    }

    
    
}