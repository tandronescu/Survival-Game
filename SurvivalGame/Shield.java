import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Shield class, essentially
 * protects the rocket from 
 * any obstacles
 * 
 * @author Tibi A.
 * @version 1.0
 */
public class Shield extends Actor
{
    // world variable
    Space currentWorld;
    // set transparency
    public Shield(int x) {
        GreenfootImage image = new GreenfootImage("shield.png");
        setImage(image);
        image.setTransparency(x);
    }

    public void act() 
    {
        currentWorld = (Space)getWorld();
        
        // set location to be on top of rocket
        setLocation( Rocket.x, Rocket.y );
        
        // removing any asteroids that touch the shield
        Actor NoSplitAsteroid;
        NoSplitAsteroid = getOneIntersectingObject(NoSplitAsteroid.class);
        
        if ( NoSplitAsteroid != null ) {
            currentWorld.removeObject(NoSplitAsteroid);
        }
        
        // removing any naval mines that touch the shield
        Actor NavalMine;
        NavalMine = getOneIntersectingObject(NavalMine.class);
        
        if ( NavalMine != null ) {
            currentWorld.removeObject(NavalMine);
        }
    }    
}
