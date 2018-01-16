import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Buttom
 * 
 * @author Tibi A. 
 * @version 1.0
 */
public class Button6 extends Button
{
    public Button6(boolean stateIn, String textIn) {
        buttonText = textIn;
        if (stateIn) buttonOn();
        else buttonOff();
    }
    
    public void act() 
    {
    }    
}
