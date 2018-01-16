import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Rocket class, movement code
 * 
 * @author Tibi A.
 * @version 1.0
 */
public class Rocket extends Mover
{
    // Global variables for Rocket Class
    double speed = 1.0;
    double maxSpeed = 10;
    double acceleration = 0.1;
    int numberBullets = 1;
    Bullet currentBullet;
    Space currentWorld;
    
    // location variables to use in world
    public static int x;
    public static int y;
    
    // sound
    GreenfootSound bullet = new GreenfootSound("bullet.mp3");
    
    public void act() 
    {
        // get the current world
        currentWorld = (Space) getWorld();
        moveSteadily((int) speed);
        // accelerate if up arrow is being pressed
        if (Greenfoot.isKeyDown("up")) { 
			// is the speed is too high, reset gradually to 1
			if (speed >= 30.0) {
				speed = 10.0;
				speed = 5.0;
				speed = 1.0;
			}
			else {
				speed = speed + acceleration;
				move((int)speed);
			}
        }
        
        // decelerate by pressing down key
		if (Greenfoot.isKeyDown("down")) {
			speed = speed - acceleration;
		}
		
		// reset speed to default by pressing 1
		if (Greenfoot.isKeyDown("1")) {
		    speed = 1.0;
		}

		// rotate ship at default speed by pressing right arrow
        if (Greenfoot.isKeyDown("right")) {
            turn(1);
        }
        
        // rotate ship opposite direction at default speed by pressing left arrow
        if (Greenfoot.isKeyDown("left")) {
            // setRotation(180);
            turn(-1);
        }

		// rotate ship relative to the speed by pressing D 
        if (Greenfoot.isKeyDown("D")) {
            turn((int)speed);
        }
        
        // rotate ship opposite relative to the speed by pressing S
        if (Greenfoot.isKeyDown("S")) {
            turn(-(int)speed);
        }
        
        // shoot bullet with space
        if (Greenfoot.isKeyDown("space")) {
            // sound effect
            bullet.play();
            bullet.setVolume(35);
			if ((currentWorld.getObjects(Bullet.class).size())< numberBullets) {
			    currentWorld.addObject( new Bullet(getRotation()), getX(), getY()); 
			 }
		}
		
		// get x and y coordinates
		x = getX();
		y = getY();
	} 
	
	// change image to submarine pic
	public void submarine() {
	    setImage("submarine.jpg");
	}
}

