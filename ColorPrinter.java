public class ColorPrinter {
    // Color codes
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    // Message types
    public enum MessageType {
        SYSTEM(BLUE),      // For game system messages
        DICE(YELLOW),      // For dice rolls
        MONEY(GREEN),      // For financial transactions
        WARNING(RED),      // For warnings/errors
        PLAYER(PURPLE),    // For player actions/status
        EVENT(CYAN),       // For special event messages
        DEFAULT(WHITE);    // Default color
        
        private final String color;
        
        MessageType(String color) {
            this.color = color;
        }
        
        public String getColor() {
            return color;
        }
    }

    public static void print(String message, MessageType type) {
        System.out.println(type.getColor() + message + RESET);
    }

    // Overloaded method for default color
    public static void print(String message) {
        print(message, MessageType.DEFAULT);
    }
}