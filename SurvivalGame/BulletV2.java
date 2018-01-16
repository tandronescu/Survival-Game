import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Same as other bullet class, but
 * instead destroys other rocket 
 * (for 2 player game)
 * 
 * @author Tibi A. 
 * @version 1.0
 */
public class BulletV2 extends Mover
{
    Space currentWorld;
    int bulletLife = 1*60;
    int creationTime = -1;
    public BulletV2( int direction) {
        turn(direction);
        speed = maxSpeed;
    }
    /**
     * Act - do whatever the BulletV2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
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
        if ( isTouching(Rocket.class) ) {
            removeTouching(Rocket.class);
        }
    }    
}
