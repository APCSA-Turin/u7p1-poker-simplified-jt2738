package com.example.project;
import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){ 
        if (Utility.getHandRanking(p1Hand)>Utility.getHandRanking(p2Hand)) { //lines 7-10 determine which player wins by checking the rank values of each player's hand
            return "Player 1 wins!";
        }else if (Utility.getHandRanking(p1Hand)<Utility.getHandRanking(p2Hand)) {
            return "Player 2 wins!";
        }else{
            if (p1.determineHighest()>p2.determineHighest()) { //if they have equal in value decks, check which one has the higher high card to determine who wins
                return "Player 1 wins!";
            }else if (p1.determineHighest()<p2.determineHighest()) {
                return "Player 2 wins!";
            }
        }
        return "Tie!"; //if all of the instances above are equal in value, there is a tie
    }

    public static void play(){ //simulate card playing
    
    }
        
        

}