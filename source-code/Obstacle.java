/** "Obstacle"
 * Christian Cheshire © 2016
 * v.2016.12.3.2000
 */

public class Obstacle {
	
	// Class variables
	private int X, Y;
	private int xPos;     // X position of obstacle
	private int yPos;     // Y position of obstacle
	private int startX, startY;  
	//private int speed;     // Speed at which obstacle moves
	private EZImage image;     // Obstacle image 
	private String fileN;     // String for first obstacle image
	private String fileM;     // String for second obstacle image
	private int num;     // Used to determine when to change images
	
	// Obstacle constructor
	Obstacle (int x, int y, String fn, String fm) {
		xPos = x;
		yPos = y;
		startX = x; startY = y;
		fileN = fn;
		fileM = fm;
		image = EZ.addImage(fileN, xPos, yPos);
		num = 0;
		obstacleInit();
	}
	
	
	// Restart obstacle function
	void restartObstacle() {
		image.translateTo(startX, startY);
	}
	
	// Change image function - changes obstacle type
	void changeImage() {
		if (num==4) {
			image = EZ.addImage(fileM, xPos, yPos);
		}
		if (num==1) {
			image = EZ.addImage(fileN, xPos, yPos);
		}
		if(num>6) {
			num=0;
		}
		num++;
	}
    
	// Move function
	void move() {
		image.moveForward(-50);     
	}
	
	// Go back function - returns obstacle to beginning
	void goBack() {
		image.moveForward(1700);
	}
	
	// Initializes the obstacle and its starting position on the screen
    private void obstacleInit() {
		int distance = xPos + 900; 
		setPosition(distance, yPos);
	}
	
	// Accesses x and y positions of obstacle
	int getX() {
		return image.getXCenter();
	}

	int getY() {
		return image.getYCenter();
	}
	    
	// Sets the position of the obstacle.
	private void setPosition(int posx, int posy) {
		X = posx;
		Y = posy;
		setObstacleImagePosition(X, Y);
	}

	// Set the position of the obstacle image
	private void setObstacleImagePosition(int posx, int posy) {
		image.translateTo(posx, posy);
	}
}
