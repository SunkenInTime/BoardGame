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

    public static void loading(int second){

        int length = second * 20;

        for(int i = 0; i < length; i++) {
            System.out.print("\r" + (i % 2 == 0 ? "/" : "\\"));
            GameTimer.delay(50);
    
        }
        System.out.println("\n");
    }
    

}