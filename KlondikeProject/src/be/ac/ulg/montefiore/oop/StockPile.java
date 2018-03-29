package be.ac.ulg.montefiore.oop;

import be.ac.ulg.montefiore.oop.exceptions.*;

public class StockPile extends Pile {
	
	//override method to first flip the cards()
		public void receiveTopCardFrom(Pile pile) throws EmptyPileException, CardPlacementDoesNotFollowRulesException, NullCardException{
			Card cardToPass = pile.getTopCard();
			
			if (cardToPass.isHidden() == false)
				cardToPass.flip();
			super.receiveTopCardFrom(pile);
		}
	
	//override method to don't allow to add cards
	public void addCard() throws CardPlacementDoesNotFollowRulesException {
		throw new CardPlacementDoesNotFollowRulesException("Cannot add a card to the Stock");
	}
}
