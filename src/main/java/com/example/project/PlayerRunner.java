package com.example.project;

import java.util.ArrayList;

public class PlayerRunner{
    public static void main(String[] args) { //IN TESTING PHASE-- Continue from here
        Player player = new Player();
        player.addCard(new Card("3", "♠"));
        player.addCard(new Card("6", "♦"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("5", "♣"));
        communityCards.add(new Card("2", "♠"));
        communityCards.add(new Card("A", "♠"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        System.out.println(handResult);
    }
}