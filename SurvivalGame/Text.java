import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * This class creates a textbox
 * 
 * @param x this represents the text displayed
 * @author Tibi A. 
 * @version 1.0 
 */
public class Text extends Actor
{
    // constructor for a text box
    public Text(String x, int y) { 
    GreenfootImage image = new GreenfootImage(x, y, Color.RED, Color.CYAN); 
    setImage(image);
    }
    
    public void act() 
    {
    }    
}
