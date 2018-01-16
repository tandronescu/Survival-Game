import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * Planet class, an obstacle 
 * that orbits the centre
 * and destroys any bullets that touch it
 * 
 * @author Tibi A.
 * @version 1.0
 */
public class Planet extends Mover
{
    public void act() 
    {
        /* did not call moveSteadily since I do not want the planet to jump from side to side if it's on an edge
         * instead I made it move at a constant speed of 2
        */
        move(2); 
        
        // The planet rotates in a circle to simulate an orbit
        turn(1);
        
        // check to see if a bullet is touching it, if so destroy it
        if ( isTouching(Bullet.class)) {
            removeTouching(Bullet.class);
        }
        
        // check to see if bullet V2 is touching it, if so destroy it
        if ( isTouching(BulletV2.class)) {
            removeTouching(BulletV2.class);
        }
    }
    
}
