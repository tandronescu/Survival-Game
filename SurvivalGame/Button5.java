import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button
 * 
 * @author Tibi A.
 * @version 1.0
 */
public class Button5 extends Button
{
    public Button5(boolean stateIn, String textIn) {
        buttonText = textIn;
        if (stateIn) buttonOn();
        else buttonOff();
    }
    
    public void act() 
    {
    }    
}
