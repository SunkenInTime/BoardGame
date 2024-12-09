import java.util.Scanner;

public class Player {

    String icon;
    String name;
    int money;
    private int position;
    Scanner myScanner;

    int sentenceLength = 0;

    Player(String name, String icon, Scanner myScanner) {
        this.name = name;
        this.icon = icon;
        this.money = 500;
        this.myScanner = myScanner;
        position = 1;
    }

    int getPosition() {
        return position;
    }

    void addPosition(int increment) {
        int tempPosition = position + increment;

        if (tempPosition < 1) {
            tempPosition += 16;
        }
        if (tempPosition > 16) {
            tempPosition -= 16;
            ColorPrinter.print("You passed Go! Collet $50", ColorPrinter.MessageType.EVENT);
            addMoney(50);
        }

        this.position = tempPosition;

    }

    void setPlayerPosition(int position) {

        this.position = position;
    }

    void customPlayerPosition() {
        int newPos = SafeScanner.scanInt(myScanner);
        boolean isValid = false;
        while (!isValid) {

            if (newPos > 0 && newPos < 17) {
                isValid = true;
            } else {
                ColorPrinter.print("Wrong input, needs to be a number between 1-16.", ColorPrinter.MessageType.WARNING);
                newPos = SafeScanner.scanInt(myScanner);
            }

        }

        this.position = newPos;
    }

    void addMoney(int money) {
        this.money += money;
    }

    void addSentence(int length) {
        this.sentenceLength += length;
    }

}