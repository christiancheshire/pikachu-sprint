/** "Pikachu Sprint Menu"
 * Christian Cheshire © 2016
 * v.2016.12.3.2000
 */

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Menu {
	
	// Menu variables
	private boolean gameRunning;     // To determine if game is running
	private boolean idling;     // To determine if game is idling
	private int winW;     // Window width
	private int winH;     // Window height
	private EZRectangle rectangle;     // Menu background
	private EZText text;      // Menu text
	private EZText subText;     // Sub text
		
	
	// Constructor
	Menu (int x, int y) {
		winW = x; winH = y;
		rectangle = EZ.addRectangle(winW/2, winH/2, winW/2, winH/3, Color.GRAY, true);      // Rectangle
		text = EZ.addText(winW/2, winH/2, "Press Spacebar to begin", Color.ORANGE, winH/10-10);      // Text
	    gameRunning = false;
	    idling = true;
	}
	
	// Checks if game is running or idling
	boolean gameRunning() {
		return gameRunning;
	}
	
	boolean idling() {
		return idling;
	}
	
	// Starts game
    void start() {
		if(EZInteraction.wasKeyPressed(KeyEvent.VK_SPACE)) {	
			rectangle.hide();     // Hides menu background
			text.hide();      // Hides menu text
			gameRunning = true;
			idling = false;
		}
		EZ.refreshScreen();      // Refresh screen
	}
	
    // Shows text
	void show() {
		rectangle.show();     // Show menu background
		text.setMsg("Play again?");     // Play again message
		subText = EZ.addText(winW/2, winH/2+70, "(Yes - Space | No - Esc)", Color.ORANGE, winH/10-30);     // Sub text
		text.show();     // Show text
		EZ.refreshScreen();
	}
	
	// Hides text
	void hide() {
		rectangle.hide();
		text.hide();
		subText.hide();
		EZ.refreshScreen();
	}
	
	// Play again option
	void playAgain() {
		
		idling = true;
		
		if(EZInteraction.wasKeyPressed(KeyEvent.VK_SPACE)) {     // If spacebar was pressed, idling is false
			idling = false;
		} else if(EZInteraction.wasKeyPressed(KeyEvent.VK_ESCAPE)) {     // If escape was pressed, gameRunning is false
			gameRunning = false;
			idling = false;
		}
		
		EZ.refreshScreen();     // Refresh screen
		
	}
	
}
