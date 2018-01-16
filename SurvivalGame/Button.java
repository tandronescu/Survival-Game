import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button class, press it
 * to advance to next 
 * intended screen
 * 
 * @author Tibi A.
 * @version 1.0
 */
public class Button extends Actor
{
    boolean buttonState = false;
    String buttonText = "Hello Bob";
    
    public Button() {
        buttonOff();
    }
    
    // constructor, make button off or on and input the text you want displayed
    public Button(boolean stateIn, String textIn) {
        buttonText = textIn;
        if (stateIn) buttonOn();
        else buttonOff();
    }

    public void act() 
    {
    }
    
    // button appearance when off
    public void buttonOff() {
        GreenfootImage buttonImage = new GreenfootImage(buttonText, 20, Color.CYAN, Color.BLACK);
        setImage(buttonImage);
        buttonState = false;
    }
    
    // different button appearance for when on
     public void buttonOn() {
        GreenfootImage buttonImage = new GreenfootImage(buttonText, 20, Color.BLACK, Color.GREEN);
        setImage(buttonImage);
        buttonState = true;
    }
    
    // if the button is off, turn on ----- if on, turn off
    public void buttonToggle() {
        if (buttonState) {
            buttonOff();
        }
        else {
            buttonOn();
        }
    }
}
