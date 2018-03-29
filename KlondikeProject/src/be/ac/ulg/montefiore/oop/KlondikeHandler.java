package be.ac.ulg.montefiore.oop;

import java.util.Vector;
import be.ac.ulg.montefiore.oop.exceptions.*;
import be.ac.ulg.montefiore.oop.graphics.*;

public class KlondikeHandler implements KlondikeEventsHandler {

	private KlondikeSwingView ssv;
	private Board board; //board Object containing all the elements of the board
	private int numMoves; //number of moves performed
	Vector<Board> boardHistory; //vector containing all states of the board 
	
	//method to set the KlondikeView object reference
	public void setView(KlondikeSwingView ssv) {
		this.ssv = ssv;
	}
	
	//method to make the window visible
	public void launchWindow(){
		ssv.refreshWindow();
	}
	
	//method to instantiate a new board
	public KlondikeHandler() {
		board = new Board();
	}
	
	//updates the GUI according to the movement performed by the user
		private void updateView() {
			try {
				ssv.tableauCards(KlondikeView.TABLEAU_1, board.tableauPiles[0].toView());
				ssv.tableauCards(KlondikeView.TABLEAU_2, board.tableauPiles[1].toView());
				ssv.tableauCards(KlondikeView.TABLEAU_3, board.tableauPiles[2].toView());
				ssv.tableauCards(KlondikeView.TABLEAU_4, board.tableauPiles[3].toView());
				ssv.tableauCards(KlondikeView.TABLEAU_5, board.tableauPiles[4].toView());
				ssv.tableauCards(KlondikeView.TABLEAU_6, board.tableauPiles[5].toView());
				ssv.tableauCards(KlondikeView.TABLEAU_7, board.tableauPiles[6].toView());
				ssv.foundationCards(KlondikeView.FOUNDATION_1, board.foundationPiles[0].toView());
				ssv.foundationCards(KlondikeView.FOUNDATION_2, board.foundationPiles[1].toView());
				ssv.foundationCards(KlondikeView.FOUNDATION_3, board.foundationPiles[2].toView());
				ssv.foundationCards(KlondikeView.FOUNDATION_4, board.foundationPiles[3].toView());
				ssv.stockEmpty(board.stockPile.size == 0);
				ssv.wasteCards(board.wastePile.toView());
				ssv.updateMoves(numMoves);
			} catch (NegativeNumberException | UnknownTableauException | NullArrayException | TooManyCardsException | UnknownCardException | UnknownFoundationException e) {
				e.printStackTrace();
			}
			ssv.refreshWindow();
		}
		
		//checks if the player has won the game
		private void hasThePlayerWonTheGame(){
			if (board.areAllFoundationsComplete() == true)
			{
				System.out.println("Congratulations, you won the game!!");
				ssv.win();
			}
		}
	
	//overriden method from interface KlondikeEventsHandler
	public void newGame() {
		try {
			board.startNewGame();
			numMoves = 0;
			boardHistory = new Vector<Board>();
			updateView();
			ssv.refreshWindow();
		}
		catch (Exception e) {
			System.err.println("An error occurred when tryng to create a new game");
		}
	}

	//overriden method from interface KlondikeEventsHandler
	public void clickStock() {
		addStateOfBoardToList(board);
		try {
			if ( board.stockPile.size > 0)
				board.wastePile.receiveUpToTopThreeCardsFrom(board.stockPile);  
			else if ( board.wastePile.size > 0)
			{
				board.stockPile.ReceiveAllCardsFromAnotherPile(board.wastePile);
				board.wastePile.receiveUpToTopThreeCardsFrom(board.stockPile);
			}
				numMoves++;
		} catch (Exception e) {
			e.printStackTrace();
			removeLastStateOfTheBoard();
		}
		updateView();
		ssv.refreshWindow();
	}

	//overriden method from interface KlondikeEventsHandler
	public void moveWasteToTableau(int tableau) {
		addStateOfBoardToList(board);
		try {
			board.tableauPiles[tableau].receiveTopCardFrom(board.wastePile);
			numMoves++;
		} catch (NullCardException | CardPlacementDoesNotFollowRulesException | EmptyPileException e) {
			e.printStackTrace();
			removeLastStateOfTheBoard();
		}
		updateView();
		ssv.refreshWindow();
	}

	//overriden method from interface KlondikeEventsHandler
	public void moveWasteToFoundation(int foundation) {
		addStateOfBoardToList(board);
		try {
			board.foundationPiles[foundation].receiveTopCardFrom(board.wastePile);
			numMoves++;
		} catch (NullCardException | CardPlacementDoesNotFollowRulesException | EmptyPileException e) {
			e.printStackTrace();
			removeLastStateOfTheBoard();
		}
		updateView();
		ssv.refreshWindow();
		hasThePlayerWonTheGame();
	}

	//overriden method from interface KlondikeEventsHandler
	public void moveTableauToFoundation(int tableau, int foundation) {
		addStateOfBoardToList(board);
		try {
			board.foundationPiles[foundation].receiveTopCardFrom(board.tableauPiles[tableau]);
			numMoves++;
		} catch (NullCardException | CardPlacementDoesNotFollowRulesException | EmptyPileException e) {
			e.printStackTrace();
			removeLastStateOfTheBoard();
		}
		updateView();
		ssv.refreshWindow();
		hasThePlayerWonTheGame();
	}

	//overriden method from interface KlondikeEventsHandler
	public void moveTableauToTableau(int tableauSrc, int numCards, int tableauDst) {
		addStateOfBoardToList(board);
		try {
			board.tableauPiles[tableauDst].receiveCardsFromAnotherTableau(board.tableauPiles[tableauSrc], numCards);
			numMoves++;
		} catch (NullCardException | CardPlacementDoesNotFollowRulesException | EmptyPileException | OutOfPileBoundsException e) {
			e.printStackTrace();
			removeLastStateOfTheBoard();
		}
		updateView();
		ssv.refreshWindow();
	}

	//overriden method from interface KlondikeEventsHandler
	public void moveFoundationToTableau(int foundation, int tableau) {
		addStateOfBoardToList(board);
		try {
			board.tableauPiles[tableau].receiveTopCardFrom(board.foundationPiles[foundation]);
			numMoves++;
		} catch (NullCardException | CardPlacementDoesNotFollowRulesException | EmptyPileException e) {
			e.printStackTrace();
			removeLastStateOfTheBoard();
		}
		updateView();
		ssv.refreshWindow();
	}

	//overriden method from interface KlondikeEventsHandler
	public void undo() {
		restorePreviousStateOfBoard();
		numMoves--;
		updateView();
		ssv.refreshWindow();
	}

	//overriden method from interface KlondikeEventsHandler
	public String getName() {
		return "Joao Daniel Romeiro Ferreira";
	}
	
	//save a hard copy of the state of the board0
	private void addStateOfBoardToList(Board board){
		Board cloned = (Board)board.clone();
		boardHistory.add(cloned);
	}
	
	//load the last saved hard copy of the state of the board
	private void restorePreviousStateOfBoard(){
		board = (Board)boardHistory.lastElement();
		boardHistory.remove(boardHistory.size()-1);
	}

	//removes the last saved state of the board from the boardHistory
	private void removeLastStateOfTheBoard(){
		boardHistory.remove(boardHistory.size()-1);
	}
	
}
