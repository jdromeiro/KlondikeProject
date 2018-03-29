package be.ac.ulg.montefiore.oop;

import be.ac.ulg.montefiore.oop.exceptions.DeckTooSmallToDistributeCardsException;

public class Board implements Cloneable{
	StockPile stockPile; //stock pile
	WastePile wastePile; //waste pile
	FoundationPile[] foundationPiles; //array containing all the foundation piles
	TableauPile[] tableauPiles; //array containing all the tableau piles
	
	//method added to check the correct implementation of the game while debugging
	public void startAlmostFinishedGame() throws DeckTooSmallToDistributeCardsException {
		Deck deck = new Deck();
//		deck.shuffleCards();
		
		FoundationPile fp1, fp2, fp3, fp4;
		fp1  = new FoundationPile();
		fp2 = new FoundationPile();
		fp3 = new FoundationPile();
		fp4 = new FoundationPile();
		foundationPiles = new  FoundationPile[]{fp1, fp2, fp3, fp4};

		TableauPile tp1, tp2, tp3, tp4, tp5, tp6, tp7;
		tp1 = new TableauPile();
		tp2 = new TableauPile();
		tp3 = new TableauPile();
		tp4 = new TableauPile();
		tp5 = new TableauPile();
		tp6 = new TableauPile();
		tp7 = new TableauPile();
		
		stockPile = new StockPile();
		wastePile = new WastePile();
		
		try
		{
			//foundation 1
			int numCardsInFoundation;
			numCardsInFoundation = 12;
			for (int j=0; j<numCardsInFoundation; j++){
				fp1.addCardToTop(deck.dealCard());
			}
			fp1.getTopCard().flip();
			
			//add king to stock pile
			stockPile.addCardToTop(deck.dealCard());
			
			//foundation 2
			numCardsInFoundation = 12;
			for (int j=0; j<numCardsInFoundation; j++){
				fp2.addCardToTop(deck.dealCard());
			}
			fp2.getTopCard().flip();
			
			//add King to stock pile
			stockPile.addCardToTop(deck.dealCard());
			
			//foundation 3
			numCardsInFoundation = 12;
			for (int j=0; j<numCardsInFoundation; j++){
				fp3.addCardToTop(deck.dealCard());
			}
			fp3.getTopCard().flip();
			
			//add King to stock pile
			stockPile.addCardToTop(deck.dealCard());
			
			//foundation 4
			numCardsInFoundation = 12;
			for (int j=0; j<numCardsInFoundation; j++){
				fp4.addCardToTop(deck.dealCard());
			}
			fp4.getTopCard().flip();
			
			//add King to stock pile
			stockPile.addCardToTop(deck.dealCard());
			
			tableauPiles = new TableauPile[]{tp1, tp2, tp3, tp4, tp5, tp6, tp7};	
			
			foundationPiles = new FoundationPile[]{fp1, fp2, fp3, fp4};	
//			
//			//put the rest of the cards in the stock pile
//			int cardsLeft = deck.cardsLeft();
//			for (int i = 0; i < cardsLeft; i++) {
//				stockPile.addCardToTop(deck.dealCard());
//			}
		}
		catch (Exception e){
			throw new DeckTooSmallToDistributeCardsException("An error happened while trying to deal the cards");
		}
	}
	
	public void startNewGame() throws DeckTooSmallToDistributeCardsException{ 
		Deck deck = new Deck();
		deck.shuffleCards();
		
		FoundationPile fp1, fp2, fp3, fp4;
		fp1  = new FoundationPile();
		fp2 = new FoundationPile();
		fp3 = new FoundationPile();
		fp4 = new FoundationPile();
		foundationPiles = new  FoundationPile[]{fp1, fp2, fp3, fp4};

		TableauPile tp1, tp2, tp3, tp4, tp5, tp6, tp7;
		tp1 = new TableauPile();
		tp2 = new TableauPile();
		tp3 = new TableauPile();
		tp4 = new TableauPile();
		tp5 = new TableauPile();
		tp6 = new TableauPile();
		tp7 = new TableauPile();
		
		stockPile = new StockPile();
		wastePile = new WastePile();
		
		try
		{
			//tableau 1
			int numCardsInTableau;
			numCardsInTableau = 1;
			for (int j=0; j<numCardsInTableau; j++){
				tp1.addCardToTop(deck.dealCard());
			}
			tp1.getTopCard().flip();
			
			//tableau 2
			numCardsInTableau = 2;
			for (int j=0; j<numCardsInTableau; j++){
				tp2.addCardToTop(deck.dealCard());
			}
			tp2.getTopCard().flip();
			
			//tableau 3
			numCardsInTableau = 3;
			for (int j=0; j<numCardsInTableau; j++){
				tp3.addCardToTop(deck.dealCard());
			}
			tp3.getTopCard().flip();
			
			//tableau 4
			numCardsInTableau = 4;
			for (int j=0; j<numCardsInTableau; j++){
				tp4.addCardToTop(deck.dealCard());
			}
			tp4.getTopCard().flip();
			
			//tableau 5
			numCardsInTableau = 5;
			for (int j=0; j<numCardsInTableau; j++){
				tp5.addCardToTop(deck.dealCard());
			}
			tp5.getTopCard().flip();
			
			//tableau 6
			numCardsInTableau = 6;
			for (int j=0;  j<numCardsInTableau; j++){
				tp6.addCardToTop(deck.dealCard());
			}
			tp6.getTopCard().flip();
			
			//tableau 7
			numCardsInTableau = 7;
			for (int j=0; j<numCardsInTableau; j++){
				tp7.addCardToTop(deck.dealCard());
			}
			tp7.getTopCard().flip();
			tableauPiles = new TableauPile[]{tp1, tp2, tp3, tp4, tp5, tp6, tp7};	
			
			
			//put the rest of the cards in the stock pile
			int cardsLeft = deck.cardsLeft();
			for (int i = 0; i < cardsLeft; i++) {
				stockPile.addCardToTop(deck.dealCard());
			}
		}
		catch (Exception e){
			throw new DeckTooSmallToDistributeCardsException("An error happened while trying to deal the cards");
		}
	
	}
	
	//method to check if all foundations are complete
	public boolean areAllFoundationsComplete() {
		if (foundationPiles[0].isComplete() && foundationPiles[1].isComplete() && foundationPiles[2].isComplete() && foundationPiles[3].isComplete())
			return true;
		return false;
	}
	
	//make a copy of the object
		public Object clone() {
			try {
			//superficial copy
			Board copy = (Board)super.clone();
			
			//copy of stock pile
			copy.stockPile = (StockPile)this.stockPile.clone();
			
			//copy of wastePile
			copy.wastePile = (WastePile)this.wastePile.clone();
			
			//copy of array of foundation piles
			copy.foundationPiles = this.foundationPiles.clone();
			for (int i=0; i< foundationPiles.length; ++i)
				copy.foundationPiles[i] = (FoundationPile)foundationPiles[i].clone();
			
			//copy of array of tableau piles
			copy.tableauPiles = this.tableauPiles.clone();
			for (int i=0; i< tableauPiles.length; ++i)
				copy.tableauPiles[i] = (TableauPile)tableauPiles[i].clone();
			
			return copy;
			}
			catch (CloneNotSupportedException e) {
				throw new InternalError("Unable to Clone");
			}
		}
}
