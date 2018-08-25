
/** "Pikachu Sprint"
 * Christian Cheshire and Tallas Goo © 2016
 * v.2016.12.3.2000
 */

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Main { 
    
	// Game window width and height
	final static int WIDTH = 1400;
	final static int HEIGHT = 700;
	
	
	public static void main(String[] args) {
		
		// Creates setup
		Setup setup = new Setup(WIDTH, HEIGHT);
		//Creates menu
		Menu menu = new Menu (WIDTH, HEIGHT);
		// Adds sound
		EZSound music = EZ.addSound("music.wav");
		// Creates animation
		Animation Pikachu = new Animation("pikachu-", 8, "pikachu-5.png", 4, 270, HEIGHT-200);
		// Creates obstacles
		Obstacle obstacles = new Obstacle (WIDTH+500, HEIGHT-70, "boulder.png", "evergreen.png");
		
		music.loop();
		
		do {     // Starts menu
			menu.start();
		} while(menu.idling() == true);
		
		int score;     // Keeps track of score
		
		while(menu.gameRunning() == true) {     // Starts main while loop
			
			score = 0;
			EZText text = EZ.addText(WIDTH-250, 70, "", Color.ORANGE, 90);     // Sets text
			
			
			while(Pikachu.isAlive()) {
				
				// Run background images
				setup.moveBackgrounds();
				
				
				// Set scoreboard
				text.show();
				text.setMsg("Score: "+score+"");
				score++;
								
				
				// Obstacle movement and changes
				obstacles.move();
			    if (obstacles.getX()<-10) {
			        obstacles.goBack();
					obstacles.changeImage();
				}
				
			    
				// Runs Pikachu loop animation
				Pikachu.animate();
				
				// Pikachu jumps if spacebar is pressed and not currently in mid-jump
				if(EZInteraction.wasKeyPressed(KeyEvent.VK_SPACE) && Pikachu.jumping()==false) {
					Pikachu.jump();
				} 
				Pikachu.go();
				
				// Detect collisions with obstacles
				Pikachu.collision(obstacles);
				
				
				EZ.refreshScreen();		// Refresh graphics
			}
			
			menu.show();     // Shows menu
			
			do {     // Shows play again option
				menu.playAgain();
			} while(menu.idling()==true);
			
			menu.hide();
			text.hide();
			Pikachu.restartAnimation();     // Restarts animation
			obstacles.restartObstacle();     // Restarts obstacle
		}
		
		music.stop();      // Stops music
		setup.close();		// Close game window
		
	}
		
}
