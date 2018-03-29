package be.ac.ulg.montefiore.oop;

import be.ac.ulg.montefiore.oop.exceptions.*;

public class WastePile extends Pile {
	
	//implementation of the method toView for the Waste Pile
	public int[] toView() {
		int sizeArray = Math.min(3, size);
		int[] cardsAsInt = new int[sizeArray];
		Card card = topCard;
		int i = 0;
		while (card != null && i < sizeArray)
		{
			cardsAsInt[i] = card.getValue();
			card = card.getCardBelow();
			i++;
		}
		
		int[] cardsInCorrectOrder =  invArray(cardsAsInt);
		return cardsInCorrectOrder;
	}
	
	//override method to first flip the cards if they are hidden()
	public void receiveTopCardFrom(Pile pile) throws EmptyPileException, CardPlacementDoesNotFollowRulesException, NullCardException
	{
		Card cardToPass = pile.getTopCard();
		
		if (cardToPass.isHidden() == true)
			cardToPass.flip();
		super.receiveTopCardFrom(pile);
	}

	//receive up to three cards from a pile
	public void receiveUpToTopThreeCardsFrom(Pile pile) throws EmptyPileException, NullCardException, CardPlacementDoesNotFollowRulesException
	{
			int numbOfCards = Math.min(3, pile.size());
			
			for (int i =0 ; i < numbOfCards; i++)
			{
				this.receiveTopCardFrom(pile);
			}
	}
}
