import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class that holds all the 
 * movement functions for all the
 * subclasses like rocket or asteroid
 * 
 * @author Tibi A.
 * @version 1.0
 */
public class Mover extends Actor
{
    // Global Variables for Mover
    double speed = 1.0;
    double maxSpeed = 20;
    double mediumSpeed = 10;
    
    public Mover() {
        
    }
    
    // set direction, random speed constructor
    public Mover(int direction) {
        turn(direction);
        speed = Math.random() * 10;
    }
    
    // set speed, random direction constructor
    public Mover(double speedIn) {
        speed = speedIn;
        turn(Greenfoot.getRandomNumber(360));
    }
    
    // set speed and direction constructor
    public Mover(double speedIn, int direction) {
        speed = speedIn;
        turn(direction);
    }
    
    public void act() 
    {
        moveSteadily();
    }   
    
    /**
     * moves the object and wraps it around the screen
     */
    public void moveSteadily() {
        move ((int)speed);
        // Wrap mover around screen
        if (isAtEdge()) {
            if (getX() > 1600) {
				setLocation(0, getY());
			}
			else if (getX() < 0) {
				setLocation(1600, getY());
        	}
			else if (getY() > 1400) {
				setLocation(getX(), 0);
			}
			else if (getY() < 0) {
				setLocation(getX(), 1400);
			}
    	}   
    }
    
    // same thing as moveSteadily, but takes an input speed
    public void moveSteadily(int speedIn) {
        speed = speedIn;
        moveSteadily();
    }
}
