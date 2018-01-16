import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button
 * 
 * @author Tibi A. 
 * @version 1.0
 */
public class Button4 extends Button
{
    public Button4(boolean stateIn, String textIn) {
        buttonText = textIn;
        if (stateIn) buttonOn();
        else buttonOff();
    }
    
    public void act() 
    {
    }    
}
