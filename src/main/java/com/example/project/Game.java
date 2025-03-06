package com.example.project;
import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        if (Utility.getHandRanking(p1Hand)>Utility.getHandRanking(p2Hand)) {
            return "Player 1 wins!";
        }else if (Utility.getHandRanking(p1Hand)<Utility.getHandRanking(p2Hand)) {
            return "Player 2 wins!";
        }else{
            p1.determineHighest();
            p2.determineHighest();
            if (p1.determineHighest()>p2.determineHighest()) {
                return "Player 1 wins!";
            }else if (p1.determineHighest()<p2.determineHighest()) {
                return "Player 2 wins!";
            }
        }
        return "Tie!";
    }

    public static void play(){ //simulate card playing
    
    }
        
        

}