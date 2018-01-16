import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * This class creates a textbox
 * 
 * @param x this represents the text displayed
 * @author Tibi 1.0
 * @version 1.0 
 */
public class Text1 extends Actor
{
    // constructor for a text box
    public Text1(String x, int y) { 
    GreenfootImage image = new GreenfootImage(x, y, Color.BLUE, Color.GREEN); 
    setImage(image);
    }
    
    public void act() 
    {
    }    
}
