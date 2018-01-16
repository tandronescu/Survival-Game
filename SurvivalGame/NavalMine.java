import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Essentially a copy of the 
 * NoSplitAsteroid class but
 * with a naval sea mine theme
 * 
 * @author Tibi A.
 * @version 1.0
 */
public class NavalMine extends Mover
{
    Space currentWorld;
    private int gameTime = 0;
    public static int navalMineKills;
    
    // constructor random speed and direction
    public NavalMine() {
        turn( (int)(Math.random() * 360));
        
        // max speed for higher difficulty
        speed = Math.random() * maxSpeed;
    }
    /**
     * Act - do whatever the NavalMine wants to do. This method is called whenever
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
            
            // increment naval mine kill count
            navalMineKills += 1;
            return;
        }
        
        // remove a life if asteroid hits spaceship, and remove the asteroid that hit it 
        Actor Rocket;
        Rocket = getOneIntersectingObject(Rocket.class);
        
        if ( (Rocket != null) && (gameTime > 180)) {
            // currentWorld.removeObject(Rocket);
            currentWorld.removeObject(this);
            NoSplitAsteroid.lives -= 1;
            return;
        }
        
        // after 3 seconds the Naval Mine changes to red background to signify it is volatile
        
        if ( gameTime > 180) {
            setImage(new GreenfootImage("NavalMine2.png"));
        }
        
    }
}
