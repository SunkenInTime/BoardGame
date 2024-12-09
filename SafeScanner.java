import java.util.Scanner;

public class SafeScanner {


    String scanString(Scanner myScanner){
        String output = "";
        try{
            output = myScanner.nextLine();

        }catch(Exception e){

            scanString(myScanner);
        }

        return output;
    }

    static int scanInt(Scanner myScanner){
        int output = 0;
        try{
            output = myScanner.nextInt();
            myScanner.nextLine();

        }catch(Exception e){
            myScanner.nextLine();
            ColorPrinter.print("You inputed a chacter that was not a number, please re-enter the data", ColorPrinter.MessageType.WARNING);
            return scanInt(myScanner);
        }

        return output;
    }



}
