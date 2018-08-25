/** "Pikachu Sprint Setup"
 * Tallas Goo © 2016
 * v.1.1.1
 */

public class Setup {
	
	private static int winW;
	private static int winH;
	private static EZImage sky1;
	private static EZImage sky2;
	private static EZImage sky3;
	private static EZImage ground1;
	private static EZImage ground2;
	
	
// Constructor
	Setup(int window_width, int window_height) {
		winW = window_width; winH = window_height;
		
		EZ.initialize(winW, winH);	// Create window
		
		// Initialize background images
		sky1 = EZ.addImage("clouds.jpg", 450, 260);
		sky2 = EZ.addImage("clouds.jpg", 1350, 260);
		sky3 = EZ.addImage("clouds.jpg", 2250, 260);
		ground1 = EZ.addImage("ground.png", 1032, 700);
		ground2 = EZ.addImage("ground.png", 3095, 700);
	}
	
	
// moveBackgrounds function to animate background images
	public void moveBackgrounds() {
		// Move backgrounds
		sky1.moveForward(-5);
		sky2.moveForward(-5);
		sky3.moveForward(-5);
		
		if (sky1.getXCenter()<-450) {
			sky1.moveForward(2700);
		}
		if (sky2.getXCenter()<-450) {
			sky2.moveForward(2700);
		}
		if (sky3.getXCenter()<-450) {
			sky3.moveForward(2700);
		}
		
		ground1.moveForward(-45);
		ground2.moveForward(-45);
		
		if (ground1.getXCenter()<-1032) {
			ground1.moveForward(4126);
		}
		if (ground2.getXCenter()<-1032) {
			ground2.moveForward(4126);
		}
		
		EZ.refreshScreen();		// Refresh graphics
		
	}
	
	public void close() {
		EZ.closeWindowWithIndex(0);
	}
	
}

