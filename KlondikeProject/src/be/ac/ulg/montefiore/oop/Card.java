package be.ac.ulg.montefiore.oop;

public class Card implements Cloneable {

	//Properties
	private int value; //0 for Ace - 12 for King of Spades, 13-25 for Hearts, 26-38 for Diamonds, 39-51 for Clubs
	private boolean hidden; //hidden or visible
	private Card cardBelow; //card below this one
	
	//Constructors
	public Card (int value) {
		this.value = value;
		hidden = true;
		cardBelow = null;
	}

	//gets true if the card is either hearts or diamonds
	public boolean isRed(){
		if (getColor() == 1 || getColor() == 2)
			return true;
		else
			return false;
	}
	
	//gets the number of the card independently of its color
	public int getNumber() {
		return ((value) % 13 );
	}
	
	//gets the color of the card: 0 is for spades, //1 is for hearts, //2 is for diamonds, //3 is for clubs
	public int getColor() {
		return (value) / 13;
	}
	
	//flips the card
	public void flip() {
		hidden = !hidden;
	}
	
	//checks if this card follows another one
	public boolean followsNumber(Card card) {
		if (this.getNumber() - card.getNumber() == 1)
			return true;
		return false;
	}
	
//	public boolean followsValue(Card card) {
//		if ((this.getValue() - card.getValue() == 1)) // && (this.getColor() == card.getColor()))
//			return true;
//		return false;
//	}
	
	public boolean isSameColor(Card card){
		if (this.getColor() == card.getColor())
			return true;
		return false;
	}
	
//	public boolean follows(Card card) {
//		
//	}
	
	//gets if the card has the same color as another one
	public boolean isSameRedOrBlackColorAs(Card card) {
		if (this.isRed() == card.isRed())
			return true;
		else
			return false;
	}

	//sets the reference to the card below this one
	public void setCardBelow(Card cardBelow) {
		this.cardBelow = cardBelow;
	}
	
	//encapsulation for card below
	public Card getCardBelow() {
		return cardBelow;
	}
	
	//check if card has card below
	public boolean hasCardBelow() {
		if (cardBelow != null)
			return true;
		return false;
	}
	
	//check value
	public int getValue() {
		return value;
	}
	
	//check if card is hidden
	public boolean isHidden() {
		return hidden;
	}
	
	//clone object
	public Object clone() {
		try {
			return super.clone();
		}
		catch (CloneNotSupportedException e) {
			throw new InternalError("Unable to Clone");
		}
	}
	
	//THIS METHOD IS NOT BEING USED IN THE FINAL VERSION
	public boolean equals(Object u) {
		if (u == null || !(u instanceof Card))
			return false;
		Card c = (Card)u;
		if (this.value == c.getValue())
			return true;
		return false;
	}
}
