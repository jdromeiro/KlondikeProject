package be.ac.ulg.montefiore.oop;

import be.ac.ulg.montefiore.oop.graphics.KlondikeSwingView;
import be.ac.ulg.montefiore.oop.KlondikeHandler;

public class Klondike{
	
	public static void main(String[] args) {
		try
		{
			KlondikeHandler handler = new KlondikeHandler();
			KlondikeSwingView ssv = new KlondikeSwingView(handler);
			handler.setView(ssv);
			handler.launchWindow();
			handler.newGame();
		}
		catch(Exception e)
		{
			System.err.println("An error occurred while trying to launch the application");
		}
	}
}
