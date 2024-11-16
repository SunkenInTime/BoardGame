public class Player{
    String icon;
    String name;
    int money;
    int position;
    
    int sentenceLength = 0;

    Player(String name, String icon){
        this.name = name;
        this.icon = icon;
        this.money = 500;
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

    
    void addMoney(int money){
        this.money += money;
    }

    void addSentence(int length){
        this.sentenceLength += length;
    }
    
    
}