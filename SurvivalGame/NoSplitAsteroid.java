import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the class for the 
 * version of the asteroid that does not 
 * split, ie it is destroyed from 
 * one shot of a bullet
 * 
 * @author Tibi A.
 * @version 1.0
 */
public class NoSplitAsteroid extends Mover
{
    Space currentWorld;
    private int gameTime = 0;
    public static int lives = 3;
    public static int asteroidKills;
    
    // constructor random speed and direction
    public NoSplitAsteroid() {
        turn( (int)(Math.random() * 360));
        
        // medium speed for lower difficulty
        speed = Math.random() * mediumSpeed;
    }
    /**
     * Act - do whatever the NoSplitAsteroid wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        currentWorld = (Space)getWorld();
        moveSteadily();
        
        // increment gameTime for each iteration
        gameTime += 1;
        
        // remove a bullet once it hits the asteroid
        Actor bullet;
        bullet = getOneIntersectingObject(Bullet.class);
        
        if ( bullet != null) {
            currentWorld.removeObject(bullet);
            currentWorld.removeObject(this);
            
            // increment asteroid kill count
            asteroidKills += 1;
            return;
        }
        
        // remove the other bullet once it hits the asteroid (2 player)
        Actor bullet2;
        bullet2 = getOneIntersectingObject(BulletV2.class);
        
        if ( bullet2 != null) {
            currentWorld.removeObject(bullet2);
            currentWorld.removeObject(this);
            return;
        }
        
        // remove a life if asteroid hits spaceship, and remove the asteroid that hit it 
        Actor Rocket;
        Rocket = getOneIntersectingObject(Rocket.class);
        
        if ( (Rocket != null) && (gameTime > 180)) {
            currentWorld.removeObject(this);
            lives -= 1;
            return;
        }
        
        // remove the asteroid that hits 2nd rocket (2 player)
        Actor Rocket2;
        Rocket2 = getOneIntersectingObject(Rocket_V2.class);
        
        if ( (Rocket2 != null) && (gameTime > 180)) {
            currentWorld.removeObject(this);
            return;
        }
        
        // after 3 seconds the asteroid changes images to signify it is volatile
        if ( gameTime > 180) {
            setImage(new GreenfootImage("rock.png"));
        }
        
    }
}
