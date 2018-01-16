import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bullet Class, movement code 
 * + code to remove 2nd rocket
 * in 2 player game
 * 
 * @author Tibi A. 
 * @version 1.0
 */
public class Bullet extends Mover
{
    Space currentWorld;
    int bulletLife = 1*60;
    int creationTime = -1;
    public Bullet( int direction) {
        turn(direction);
        speed = maxSpeed;
    }

    public void act() 
    {
        currentWorld = (Space) getWorld();
        moveSteadily();
        
        if (creationTime == -1) {
            creationTime = currentWorld.getTime();
        }
        if ( currentWorld.getTime() > (creationTime + bulletLife)) {
            // destroy object
            currentWorld.removeObject( this );
            return;
        }    
        
        // destroy other rocket if it hits
        if ( isTouching(Rocket_V2.class) ) {
            removeTouching(Rocket_V2.class);
        }
    }    
}
