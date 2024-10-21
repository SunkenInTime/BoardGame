public class Chest extends EventSquare{



    Chest(int position){
        super("Chest", "🎁", position);
    }

    @Override
    void performEvent(Player player) {
        
        throw new UnsupportedOperationException("Unimplemented method 'performEvent'");
    }
    

    // String[] communityChestCards = {
    //     "Bank error in your favor. Collect $200.",
    //     "Doctor's fees. Pay $50.",
    //     "From sale of stock you get $50.",
    //     "Get Out of Jail Free.",
    //     "Go to Jail. Go directly to Jail.",
    //     "Holiday Fund matures. Collect $100.",
    //     "Income tax refund. Collect $20.",
    //     "Life insurance matures. Collect $100.",
    //     "Pay hospital fees of $100.",
    //     "Pay school fees of $150.",
    //     "You have won second prize in a beauty contest. Collect $10.",
    //     "You inherit $100."
    // };
}
