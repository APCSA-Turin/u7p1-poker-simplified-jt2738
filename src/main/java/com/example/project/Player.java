package com.example.project;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){      
        sortAllCards();
        // boolean sameSuit=true;
        String currentSuit= allCards.get(0).getSuit();
        String currentRank=allCards.get(0).getRank();
        boolean consecOrder= true;
        int findPairs=0;
        Card highestCard= allCards.get(0);
        ArrayList<String> highCards= new ArrayList<String>();
        Collections.addAll(highCards, "10", "J", "Q", "A", "K");
        ArrayList<String> ranks=new ArrayList<String>();
        
        for(int i=0; i<allCards.size();i++){
            ranks.add(allCards.get(i).getRank());
        }
        
        for(int i=0; i<allCards.size();i++){

        }
        for(int j=1;j<allCards.size();j++){ //determines if the given cards are in consec order
            if (Utility.getRankValue(allCards.get(j).getRank())!= Utility.getRankValue(currentRank)+1) {
                consecOrder=false;
                break; 
            }
        }
        
        // for(int i=1; i<allCards.size();i++){ //determines if given cards all have the same suit
        //     if (!allCards.get(i).getSuit().equals(currentSuit)) {
        //         sameSuit=false;
        //         break;
        //     }
        // }
        for(int i=0; i<findRankingFrequency().size();i++){ //keeps track of how many pairs are in the given hand
            if (findRankingFrequency().get(i)==2) {
                findPairs++;
            }
        }

        if (ranks.equals(highCards) && findSuitFrequency().contains(5)) {
            return "Royal Flush";
        }else if(findSuitFrequency().contains(5) && consecOrder){
            return "Straight Flush";
        }else if(findRankingFrequency().contains(4)){
            return "Four of a Kind";
        }else if (findRankingFrequency().contains(3)&&findRankingFrequency().contains(2)) { //BOOKMARK-- Start doing this portion of the code
            return "Full House";
        }else if (findSuitFrequency().contains(5) && !consecOrder) {
            return "Flush";
        }else if (consecOrder) {
            return "Straight";
        }else if (findRankingFrequency().contains(3)) {
            return "Three of a Kind";
        }else if (findPairs==2) {
            return "Two Pair";
        }else if (findPairs==1) {
            return "One Pair";
        }else{ //How are we supposed to implement both high card and nothing??
        return "Nothing";
        }
    }

    public void sortAllCards(){
        // int count=0;
        for(int i=1; i<allCards.size(); i++){
            int currentVal= Utility.getRankValue(allCards.get(i).getRank());
            int idx=i;
            while(idx>0 && currentVal<Utility.getRankValue(allCards.get(i-1).getRank())){
                // count++;
                allCards.set(i, allCards.get(i-1));
                // elements[idx]= elements[idx-1]; //this code shifts
                idx--;
            }
            allCards.set(idx, allCards.get(currentVal));
            // elements[idx]=currentVal; //this code inserts
        } 

    } 

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> freq= new ArrayList<Integer>(); //array list lsting the frequences at each idx of list
        ArrayList<String> ranks= new ArrayList<String>("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"); //array list showing all ranks
        for(int i=0; i<ranks.size();i++){ //iterate through ranks list to see how many times that rank shows up in freq
            int count=0; //count to show freq of each rank
            for(int j=0; j<allCards.size();i++){
                if (ranks.get(i).equals(allCards.get(j).getRank())) {
                    count++;
                }
            }
            freq.add(count); //freq are added in order 
        }
        return freq; 
    }

    public ArrayList<Integer> findSuitFrequency(){ //finds the frequency of each suit in the given deck. same as rank but w/ suit commands instead
        ArrayList<Integer> freq= new ArrayList<Integer>(); //sets as new, empty list
        ArrayList<String> suits= new ArrayList<String>("♠", "♥", "♣", "♦"); //array list that's being used to check if each of the items are in the given deck
        for(int i=0; i<suits.size();i++){ 
            int count=0;
            for(int j=0; j<allCards.size();j++){
                if (suits.get(i).equals(allCards.get(j).getSuit())) {
                    count++; //count to keep track of how many times that suit shows up in deck
                }
            }
            freq.add(count); //adds that frequency into the empty list
        }
        return freq; 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }

    public int compare(Card i1, Card i2)
    {
        if (Utility.getRankValue(i1.getRank())==Utility.getRankValue(i2.getRank()))
            return 0;
        else if (Utility.getRankValue(i1.getRank())>Utility.getRankValue(i2.getRank()))
            return 1;
        else
            return -1;
    }


}
