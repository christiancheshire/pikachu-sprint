/** "Animation"
 * Tallas Goo © 2016
 * v.1.3.3
*/

import java.util.ArrayList;

public class Animation {
	
// Animation Variables
	private ArrayList<EZImage> frames;	// Array list to keep all the animation images
	private EZGroup group;		// Collects all the images into one group
	private long frameRate;		// Speed of the animation
	private int frameCount;		// Number of images in the animation
	private int posX, posY;		// Position x and y
	
// Process Variables
	private long startTime;
	private float normTime;
	private boolean loopIt;
	private boolean starting;
	private boolean visibility;	// Whether animation is showing or hidden
	public boolean isAlive;
	
// Jumping Variables
	private EZImage jumpImage;	// Static image of animation jumping
	private int  jumpY, landY;	// Destination y-coordinates for jumping and landing
	private long durationJ;		// Duration of jump and land
	private long startTimeJ;	// Starting time for whole jump and land
	private boolean interpolationJ;		// Interpolation for jumping
	private boolean interpolationL;		// Interpolation for landing
	private boolean interpolationA;		// Interpolation for air time
	private boolean jumping;	// Indicates whether animation is currently in a jump	
	
	
	
// Constructor
	Animation(String image_name, int total_images, String jumping_image, long animation_speed, int x, int y) {
		
		// Initialize animation variables
		frames = new ArrayList<EZImage>();
		posX = x; posY = y;					// Set position X and Y equal to x and y, respectively
		frameRate = animation_speed*100;	// Set duration equal to animation_speed times 100 (to get to deciseconds)
		frameCount = total_images;			// Set frameCount equal to total_images
		group = EZ.addGroup();				// Creates an EZGroup called group
		
		
		// Creates the list image names (i.e. pikachu-0.png, pikachu-1.png, pikachu-2.png, etc.)
		ArrayList<String> fileNames = new ArrayList<String>();
		for(int k=0; k<frameCount; k++) {
			fileNames.add(image_name+k+".png");
		}
		
		// Adds the EZImages to frames array
		for(int i=0; i<frameCount; i++) {
			frames.add(EZ.addImage(fileNames.get(i), posX, posY));
			frames.get(i).hide();
			group.addElement(frames.get(i));
		}
		
		
		// Initialize process variables
		setLoop(true);
		starting = true;
		visibility = true;
		interpolationJ = false;
		interpolationL = false;
		interpolationA = false;
		isAlive = true;
		
		// Initialize jumping variables
		jumpImage = EZ.addImage(jumping_image, posX, posY);	// Creates still jumping image
		jumpImage.hide();
		durationJ = 250;
		jumping = false;
	}
	
	
// RESTART Function
	public void restartAnimation() {
		if(jumping) {
			jumpImage.hide();
			jumping = false;
			interpolationJ = false;
			interpolationL = false;
			show();
		}
		isAlive = true;
	}
	
	
	
	
// Basic internal functions
	private void setLoop(boolean loop) {
		loopIt = loop;
	}
	
	private void restart() {
		starting = true;
	}
	
	private void hide() {
		visibility = false;
		for(int i=0; i<frameCount; i++) frames.get(i).hide();
	}
	
	private void show() {
		visibility = true;
	}
	
	public boolean jumping() {
		return jumping;
	}
	
	
	
// JUMP Function
	public void jump() {
		hide();
		jumpImage.show();
		jumpY = posY-500;
		landY = posY;
		startTimeJ = System.currentTimeMillis();
		interpolationJ = true;
		jumping = true;
	}
	
	
	
// GO Function
	public void go() {
		// Animate jumping motion
		if(interpolationJ == true) {
			float normTimeJ = (float)(System.currentTimeMillis() - startTimeJ) / (float)durationJ;
			posY = (int)(posY + ((float)(jumpY - posY) * normTimeJ*normTimeJ));
			
			// Set interpolation to false once time is up
			if((System.currentTimeMillis() - startTimeJ) >= durationJ) {
				interpolationJ = false;		// Set interpolationJ to false
				interpolationA = true;
				posY = jumpY;
				startTimeJ = System.currentTimeMillis();	// Set startTimeJ to current system time
			}
			
			jumpImage.translateTo(posX, posY);		// Move the jump image by an increment
			EZ.refreshScreen();	// Refresh graphics
		}
		
		
		// Animate air time
		if(interpolationA == true) {
			if((System.currentTimeMillis() - startTimeJ) > durationJ) {
				interpolationA = false;		// Set interpolationA to false
				interpolationL = true;		// and interpolationL to true
				startTimeJ = System.currentTimeMillis();
			}
			EZ.refreshScreen();		// Refresh graphics
		}
		
		
		// Animate landing motion
		if(interpolationL == true) {
			float normTimeL = (float)(System.currentTimeMillis() - startTimeJ) / (float)durationJ;
			posY = (int)(posY + ((float)(landY - posY) * normTimeL*normTimeL));
			
			// Set interpolation to false once time is up
			if((System.currentTimeMillis() - startTimeJ) >= durationJ) {
				interpolationL = false;		// Set interpolationL and jumping to false
				jumping = false;
				posY = landY;
				jumpImage.hide();	// Hide jumping image
				show();				// and show the animation again
			}
			
			jumpImage.translateTo(posX, posY);		// Move the jump image by an increment
			EZ.refreshScreen();		// Refresh graphics
		}
	}
	
	
	
// ANIMATE Function
	public void animate() {
		// Runs the image animation
		//if(stopped) return false;
		
		if(starting) {
			startTime = System.currentTimeMillis();
			starting = false;
		}
		
		if((System.currentTimeMillis()-startTime) > frameRate) {
			if (loopIt) {
				restart();
				//return true;
			}
			//return false;
		}
		
		normTime = (float)(System.currentTimeMillis()-startTime) / (float)frameRate;
	
		int currentFrame = (int) (((float)frameCount) * normTime);
		if(currentFrame>frameCount-1) {
			currentFrame = frameCount-1;
		}
		
		for (int i=0; i<frameCount; i++) {
			if(i != currentFrame) {
				frames.get(i).hide();
			}
		}
		
		if (visibility) {
			frames.get(currentFrame).show();
		} else {
			frames.get(currentFrame).hide();
		//return true;
		}
		
		EZ.refreshScreen();		// Refresh graphics
	}
	
	
// Returns whether animation is still alive
	public boolean isAlive() {
		return isAlive;
	}
	
	
// Collision function
	public void collision(Obstacle obstacle) {
		if((obstacle.getX()<=posX+200)
			&&(obstacle.getX()>=posX-150)
			&&(obstacle.getY()<=posY+200)
			) {
			isAlive = false;
			
		}
		
	}
	
}
