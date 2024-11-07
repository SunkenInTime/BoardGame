public class GameTimer {  
    
    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public static void quickPause() {
        delay(1250);  // Half second pause
    }
    
    public static void dramaticPause() {
        delay(2000); // Two second pause
    }
    

}