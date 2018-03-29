package be.ac.ulg.montefiore.oop;

import be.ac.ulg.montefiore.oop.exceptions.*;
import be.ac.ulg.montefiore.oop.interfaces.*;

public class Pile implements ViewableByKlondikeView, Cloneable {
	
	//Properties
	protected int size; //size of the pile
	protected Card topCard; //reference to the card on top
	protected Card bottomCard; //reference to the card at the bottom
	
	//Constructor
	public Pile(){
		reset();
	}
	
	//resets the pile to an empty one
	public void reset(){
		size=0;
		topCard = null;
		bottomCard = null;
	}
	
	//checks if the pile is empty
	public boolean IsEmpty(){
		return size()==0;
	}
	
	//method from interface ViewableByKlondikeViewer that returns the content of a pile as a readable format by the GUI
	public int[] toView() {
		return null;
	}
	
	//inverts an array of cards
	protected int[] invArray(int[] myArray) {
		for(int i = 0; i < myArray.length / 2; i++)
		{
		    int temp = myArray[i];
		    myArray[i] = myArray[myArray.length - i - 1];
		    myArray[myArray.length - i - 1] = temp;
		}
		return myArray;
	}
	
	//adds a card to the top of the pile
	public void addCardToTop(Card card) throws NullCardException
	{
		if (card == null)
			throw new NullCardException("Cannot add a null card into the pile");
		
//		card.cardBelow = topCard;
		card.setCardBelow(topCard);
		topCard = card;
		
		if (size == 0)
			bottomCard = card;
		
		size++;
	}

	//receives a card from the top of a pile
	public void receiveTopCardFrom(Pile pile) throws EmptyPileException, NullCardException, CardPlacementDoesNotFollowRulesException{
		if (pile.IsEmpty())
			throw new EmptyPileException("Cannot receive card from an empty pile");
		else
		{
				Card cardToPass = pile.removeTopCard();
				addCardToTop(cardToPass);
		}
	}
	
	//removes a card from the top of the pile
	public Card removeTopCard() throws EmptyPileException {
		Card cardToReturn;
		if (IsEmpty())
			throw new EmptyPileException("Cannot remove a card from an empty pile");
		else if (size()==1)
		{
			cardToReturn = topCard;
			reset();
		}
		else	
		{
			cardToReturn = topCard;
			topCard = topCard.getCardBelow();
			if (topCard.isHidden())
				topCard.flip();
			cardToReturn.setCardBelow(null);
			--size;
		}
		return cardToReturn;
	}
	
	//adds another pile and places of top of the pile
	public void ReceiveAllCardsFromAnotherPile(Pile pile) throws EmptyPileException, NullCardException, CardPlacementDoesNotFollowRulesException
	{
		if (pile.IsEmpty())
			throw new EmptyPileException("Cannot receive cards from an empty pile");
		int pileSize = pile.size;
		for (int i = 0; i < pileSize; ++i)
		{
			this.receiveTopCardFrom(pile);
		}
	}
	
	//returns the size of the pile
		public int size(){
			return size;
		}
	
	//gets the card at the top of the pile
	public Card getTopCard() throws EmptyPileException {
		if (IsEmpty())
			throw new EmptyPileException("Cannot check a card from an empty pile");
		return topCard;
	}
	
	//gets the card at the bottom of the pile
	public Card getBottomCard() throws EmptyPileException {
		if (IsEmpty())
			throw new EmptyPileException("Cannot check a card from an empty pile");
		return bottomCard;
	}
	
	//method to make superficial cloning of a object
		public Object clone() {
			try {
				//make superficial copy
				Pile copy = (Pile)super.clone();

				if (!IsEmpty())
				{
					//get hard copy of the topCard
					Card currCard = (Card)topCard.clone();
					copy.topCard = currCard;
					
					//while it has cards below, add reference to the card below to each one of these
					while (currCard.hasCardBelow())
					{
						//hard copy of the card below 
						Card cardBelow = (Card)currCard.getCardBelow().clone();
						
						//set reference to the cardbelow for the current Card
						currCard.setCardBelow(cardBelow);
						
						//change currCard to the card below
						currCard = cardBelow; //currCard.getCardBelow();
					}
					
					//if it is the last card of the pile 
					currCard.setCardBelow(null); //= (Card)bottomCard.clone();
					copy.bottomCard = currCard;
					
				}
				return copy;
			}
			catch (CloneNotSupportedException e) {
				throw new InternalError("Unable to Clone");
			}
		}
}
