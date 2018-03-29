package be.ac.ulg.montefiore.oop;

import be.ac.ulg.montefiore.oop.exceptions.*;

public class TableauPile  extends Pile  {
	
	//implementation of the method toView for the Tableau Pile
	public int[] toView() {
		int[] cardsAsInt = new int[size];
		Card card = topCard;
		int i = 0;
		while (card != null)
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
	
	//override method to first check if the placement of the card respects the rules
	public void receiveTopCardFrom(Pile pile) throws EmptyPileException, CardPlacementDoesNotFollowRulesException, NullCardException{
		Card cardToPass = pile.getTopCard();
		
		if (!(this.IsEmpty() == true))
		{
			if (!topCard.followsNumber(cardToPass) || topCard.isSameRedOrBlackColorAs(cardToPass))
				throw new CardPlacementDoesNotFollowRulesException("Cannot add a card into the Tableau that does not follow the other");
		}
		
		super.receiveTopCardFrom(pile);
	}
		
	//receive several cards at the same time from another tableau
	public void receiveCardsFromAnotherTableau(Pile pile, int numbOfCards) throws EmptyPileException, NullCardException, CardPlacementDoesNotFollowRulesException, OutOfPileBoundsException{
		//check if pile size is at least as big as number of cards to pass
		if (pile.size() < numbOfCards)
			throw new OutOfPileBoundsException("Number of cards tried to move is bigger than the size of their pile");
		
		//iterate trough all cards to find the last in the selection
		Card cardAtBottomOfPileToPass = pile.getTopCard();
		for (int i =0 ; i < numbOfCards - 1; i++)	
		{		
			cardAtBottomOfPileToPass = cardAtBottomOfPileToPass.getCardBelow();
		}
		
		//throw exception in case the card placement doesn't follow rules
		if ((this.IsEmpty() == false))
		{
			if (!topCard.followsNumber(cardAtBottomOfPileToPass) || topCard.isSameRedOrBlackColorAs(cardAtBottomOfPileToPass))
				throw new CardPlacementDoesNotFollowRulesException("Cannot add a card to a Tableau that does not follow the other");
		}
		if (this.IsEmpty() == true)
		{
			if (cardAtBottomOfPileToPass.getNumber() != 12)
				throw new CardPlacementDoesNotFollowRulesException("Cannot add a card to an empty Tableau that is not a King");
		}	
		
		//else pass all cards
		Pile pileOfCardsToPass = new Pile();
		for (int i =0 ; i < numbOfCards; i++)
		{	
			pileOfCardsToPass.receiveTopCardFrom(pile); 
		}
		numbOfCards = pileOfCardsToPass.size();
		for (int i =0 ; i < numbOfCards; i++)
		{
			this.receiveTopCardFrom(pileOfCardsToPass);
		}		
	}
}
