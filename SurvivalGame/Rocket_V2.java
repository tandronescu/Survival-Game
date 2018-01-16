import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 2nd rocket for 2 player game,
 * same functions as first one put with different keys
 * 
 * @author Tibi A.
 * @version 1.0
 */
public class Rocket_V2 extends Mover
{
     // Global variables for Rocket Class
    double speed = 1.0;
    double maxSpeed = 10;
    double acceleration = 0.1;
    Bullet currentBullet;
    Space currentWorld;
    // sound
    GreenfootSound bullet = new GreenfootSound("bullet.mp3");
    
    // set rotation to face other rocket
    public Rocket_V2() {
        setRotation(180);
    }
    /**
     * Act - do whatever the Rocket_V2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        // get the current world
        currentWorld = (Space) getWorld();
        moveSteadily((int) speed);
        // accelerate if A is being pressed
        if (Greenfoot.isKeyDown("A")) { 
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
        
        // decelerate by pressing B key
		if (Greenfoot.isKeyDown("B")) {
			speed = speed - acceleration;
		}
		
        // reset speed to 1.0 by pressing 0
		if (Greenfoot.isKeyDown("0")) {
			speed = 1.0;
		}

		// rotate ship at default speed by pressing K
        if (Greenfoot.isKeyDown("K")) {
            turn(1);
        }
        
        // rotate ship opposite direction at default speed by pressing L
        if (Greenfoot.isKeyDown("L")) {
            // setRotation(180);
            turn(-1);
        }
        
        // rotate ship at rate relative to speed with O
        if (Greenfoot.isKeyDown("O")) {
            turn((int)speed);
        }
        
        // rotate ship at rate relative to speed in opposite direction with P 
        if (Greenfoot.isKeyDown("P")) {
            turn(-(int)speed);
        }

        // shoot bullet with enter key
        if (Greenfoot.isKeyDown("enter")) {
            // sound
            bullet.play();
            bullet.setVolume(35);
			if (currentWorld.getObjects(BulletV2.class).isEmpty()) {
			    currentWorld.addObject( new BulletV2(getRotation()), getX(), getY()); 
			 }
		}
    }    
}
