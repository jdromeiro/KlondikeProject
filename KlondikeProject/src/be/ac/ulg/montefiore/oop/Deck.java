package be.ac.ulg.montefiore.oop;

import be.ac.ulg.montefiore.oop.exceptions.*;

public class Deck {
	
    private Card[] cards; //array of cards
    private int cardsDealt; //number of cards already dealt
    
    //constructor for deck builds a deck of cards with 52 cards
    public Deck() {
        cards = new Card[52];
        for (int i = 0; i < 52; i++ )
        	cards[i] = new Card(i);
        cardsDealt = 0;
    }
    
    //method to shuffle cards
    public void shuffleCards() {
        for (int i = cards.length-1; i > 0; i-- ) {
            int rand = (int)(Math.random()*(i+1));
            Card temp = cards[i];
            cards[i] = cards[rand];
            cards[rand] = temp;
        }
        cardsDealt = 0;
    }

    //returns size of the deck
    public int size() {
        return cards.length;
    }
    
    //returns number of cards that were not dealt yet
    public int cardsLeft() {
        return cards.length - cardsDealt;
    }

    //deals the next card that was not dealt yet
    public Card dealCard() throws EmptyDeckException {
        if (cardsDealt == cards.length)
            throw new EmptyDeckException("No cards are left in the deck.");
        cardsDealt++;
        return cards[cardsDealt - 1];
    }
}
