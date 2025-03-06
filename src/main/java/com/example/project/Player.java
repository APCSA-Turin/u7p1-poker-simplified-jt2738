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
    boolean sameSuit=true;
    public Player(){
        hand = new ArrayList<>();
    }
    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){      
        allCards= new ArrayList<Card>();
        allCards.addAll(hand);
        allCards.addAll(communityCards); //lines 27-30: declare value of all cards, then sorts 
        sortAllCards();
        
        String currentSuit= allCards.get(0).getSuit(); 
        String currentRank=allCards.get(0).getRank();
        
        boolean consecOrder= true; //variable to help determine if cards are in consecutive order AFTER being sorted
        boolean highestUrs= true; //variable tp help determine if the highest card is in your hand
        
        int findPairs=0; //variable that helps find pairs
        
        ArrayList<String> highCards= new ArrayList<String>();
        Collections.addAll(highCards, "10", "J", "Q", "K", "A"); // declares highest cards
        ArrayList<String> ranks=new ArrayList<String>(); //helps states what the ranks in the deck are 
        
        int maxHand= Utility.getRankValue(hand.get(0).getRank()); //lines 40-55 determine if the highest card is in hand or community cards
            for(int i=1; i<hand.size();i++){
                if (Utility.getRankValue(hand.get(i).getRank())>maxHand) {
                    maxHand=Utility.getRankValue(hand.get(i).getRank());
                }
            }
        int maxCommCards= Utility.getRankValue(communityCards.get(0).getRank()); //lines 50-58 help determine if your hand has the highest card
            for(int j=1; j<communityCards.size();j++){
                if (Utility.getRankValue(communityCards.get(j).getRank())>maxCommCards) {
                    maxCommCards=Utility.getRankValue(communityCards.get(j).getRank());
                }
            }
        if (maxCommCards>maxHand) {
                highestUrs=false;
            }
        
        
            for(int i=0; i<allCards.size();i++){  //determines what the ranks in your deck are and puts them into a list 
            ranks.add(allCards.get(i).getRank());
        }
        
        for(int j=1;j<allCards.size();j++){ //determines if the given cards are in consec order
            if (Utility.getRankValue(allCards.get(j).getRank())!= Utility.getRankValue(currentRank)+1) {
                consecOrder=false;
                break; 
            }else{
                currentRank=allCards.get(j).getRank();
            }
        }
        
        for(int i=1; i<allCards.size();i++){ //determines if given cards all have the same suit
            if (!allCards.get(i).getSuit().equals(currentSuit)) {
                sameSuit=false;
                break;
            }
        }
        for(int i=0; i<findRankingFrequency().size();i++){ //keeps track of how many pairs are in the given hand
            if (findRankingFrequency().get(i)==2) {
                findPairs++;
            }
        }

        if (ranks.equals(highCards) && containsSuitFreq(5)) { 
            return "Royal Flush";
        }else if(containsSuitFreq(5) && consecOrder){
            return "Straight Flush";
        }else if(containsRankFreq(4)){
            return "Four of a Kind";
        }else if (containsRankFreq(3)&&containsRankFreq(2)) { 
            return "Full House";
        }else if (findSuitFrequency().contains(5) && !consecOrder) {
            return "Flush";
        }else if (consecOrder) {
            return "Straight";
        }else if (containsRankFreq(3)) {
            return "Three of a Kind";
        }else if (findPairs==2) {
            return "Two Pair";
        }else if (findPairs==1) {
            return "A Pair";
        }else if(highestUrs){
            System.out.println(highestUrs);
            return "High Card";
        }else{ 
        return "Nothing";
        }
    }

    public void sortAllCards(){ //sorts through all the cards in the allcards deck in order
        for(int i=0; i<allCards.size();i++){
            int min=i;
            for(int j=i+1; j<allCards.size();j++){
                if (Utility.getRankValue(allCards.get(min).getRank())>Utility.getRankValue(allCards.get(j).getRank())) {
                   min=j;
                }
            }
            Card temp= allCards.get(i);
            allCards.set(i, allCards.get(min));
            allCards.set(min, temp);
        }

    } 

    public ArrayList<Integer> findRankingFrequency(){ //finds frequency of all the ranks and returns a list showing those frequencies
        ArrayList<Integer> freq= new ArrayList<Integer>(); //array list lsting the frequences at each idx of list
        ArrayList<String> ranks= new ArrayList<String>(); //array list showing all ranks
        Collections.addAll(ranks, "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");
        for(int i=0; i<ranks.size();i++){ //iterate through ranks list to see how many times that rank shows up in freq
            int count=0; //count to show freq of each rank
            for(int j=0; j<allCards.size();j++){
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
        ArrayList<String> suits= new ArrayList<String>(); //array list that's being used to check if each of the items are in the given deck
        Collections.addAll(suits, "♠", "♥", "♣", "♦");
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
    public boolean containsRankFreq(int num){ //checks to see if there's a rank that shows up given a number of times
        for(int i=0; i<findRankingFrequency().size();i++){
            if (findRankingFrequency().get(i)==num) {
                return true;
            }
        }
        return false;
    }
    public boolean containsSuitFreq(int num){ //same as rank but w/ suits 
        for(int i=0; i<findSuitFrequency().size();i++){ 
            if (findSuitFrequency().get(i)==num) {
                return true;
            }
        }
        return false;
    }
    public int determineHighest(){ //this method returns the value of the highest card in the player's OWN hand deck (NOT from all cards)
        int highest= Utility.getRankValue(hand.get(0).getRank());
        for(int i=1; i<hand.size();i++){
            if (Utility.getRankValue(hand.get(i).getRank())>highest){
                highest=Utility.getRankValue(hand.get(i).getRank());
            }
        }
        return highest;
    }
}
