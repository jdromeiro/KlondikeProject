package be.ac.ulg.montefiore.oop;

import be.ac.ulg.montefiore.oop.exceptions.*;

public class FoundationPile extends Pile{
	
	//implementation of the method toView for the Foundation Pile
	public int[] toView() {
		int sizeArray = Math.min(2, size);
		int[] cardsAsInt = new int[sizeArray];
		Card card = topCard;
		int i = 0;
		while (card != null && i < 2)
		{
			if (card.isHidden())
				cardsAsInt[i] = 52;
			else
				cardsAsInt[i] = card.getValue();
			card = card.getCardBelow();
			i++;
		}
		int[] cardsInCorrectOrder =  invArray(cardsAsInt);
		
		return cardsInCorrectOrder;
	}
	
	//checks if the foundation is complete
	public boolean isComplete() {
		if (size == 13)
			return true;
		return false;
	}
	
	//override method to first check if the placement of the card respects the rules
		public void receiveTopCardFrom(Pile pile) throws EmptyPileException, CardPlacementDoesNotFollowRulesException, NullCardException{
			Card cardToPass = pile.getTopCard();
			
			if ((this.IsEmpty() == false))
			{
				//if card doesn't follow the other or are not the same color
				if  (!cardToPass.followsNumber(getTopCard()) || !(cardToPass.isSameColor(getTopCard()))) //(!cardToPass.follows(getTopCard()) || !cardToPass.isSameRedOrBlackColorAs(getTopCard()))
					throw new CardPlacementDoesNotFollowRulesException("Cannot add a card into a Foundation that does not follow the other");
				super.receiveTopCardFrom(pile);
			}
			else
			{
				if (!(cardToPass.getNumber() == 0))
					throw new CardPlacementDoesNotFollowRulesException("Cannot add a card into an empty Foundation that is not an Ace");
				super.receiveTopCardFrom(pile);
				
			}
		}
}
