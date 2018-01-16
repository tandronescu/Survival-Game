import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * This is the world for the
 * asteroid game
 * 
 * @Tibi Andronescu
 * @1.0 
 */
public class Space extends World
{
    // global variables
    private int gameTime = 0;
    private int waitTime = 0;
    Space currentWorld;
    int gameState;
    
    // variable for shield count and time
    int shieldCount = 0;
    int shieldTime = 0;

	// temp variables to correct scores
	int temp;
	int temp1;

    // sound
    GreenfootSound background = new GreenfootSound("deep_space.mp3.mp3");
    /**
     * Constructor for objects of class Space.
     * 
     */
    public Space()
    {    
        // Create a new world with 1500x1000 cells with a cell size of 1x1 pixels.
        super(1500, 850, 1, false); 
        gameTime = 0;
    }
    
    public void act() {
       background.play();
       background.setVolume(25);
       
       switch (gameState) {
           // Start Screen Initialization
           case 0: setBackground("space1.jpg");
                   addObject(new Button1(false, "Start Game (1 Player)"), 750, 100);
                   addObject(new Button2(false, "Start Game (2 Player)"), 750, 200);
                   gameState = 1;
           
           // Start Screen Loop
           case 1: MouseInfo mouse = Greenfoot.getMouseInfo();
        
                   if (mouse != null) {
                        Actor currentActor = mouse.getActor();
                        if (currentActor != null) {
                            // check for first button (1 player)
                            if (currentActor.getClass() == Button1.class) {
                                Button1 currentButton = (Button1) currentActor;
                                int mouseButtonPressed = mouse.getButton();
                                int mouseClickCount = mouse.getClickCount();
                                if (mouseClickCount == 1) {
                                    currentButton.buttonToggle();
                                    gameState = 2;
                                    removeObject(currentButton);
                                    removeObjects(getObjects(Button2.class));
                                }
                            }
                            
                            // check for second button (2 player)
                            if (currentActor.getClass() == Button2.class) {
                                Button2 currentButton = (Button2) currentActor;
                                int mouseButtonPressed = mouse.getButton();
                                int mouseClickCount = mouse.getClickCount();
                                if (mouseClickCount == 1) {
                                    currentButton.buttonToggle();
                                    gameState = 8;
                                    removeObject(currentButton);
                                    removeObjects(getObjects(Button1.class));
                                }
                            }
                        }
                   }
           break;
           
           case 2: // instruction screen
                   textBox(singlePlayerHeading, 40, 750, 50);
                   textBox(singlePlayerInstructions1, 20, 750, 100);
                   textBox(singlePlayerInstructions2, 20, 750, 125);
                   textBox(singlePlayerInstructions3, 20, 750, 150);
                   textBox(singlePlayerInstructions4, 20, 750, 175);
                   textBox(singlePlayerInstructions5, 20, 750, 200);
                   textBox(singlePlayerInstructions6, 20, 750, 225);
                   textBox(singlePlayerInstructions7, 20, 750, 275);
                   textBox(singlePlayerInstructions8, 20, 750, 325);
                   textBox(singlePlayerInstructions9, 20, 750, 375);
                   textBox(singlePlayerInstructions10, 20, 750, 425);
                   textBox(singlePlayerInstructions11, 20, 750, 475);
                   textBox(singlePlayerInstructions12, 20, 750, 525);
                   textBox(singlePlayerInstructions13, 20, 750, 575);
                   textBox(singlePlayerInstructions14, 20, 750, 625);
                   addObject(new Button3(false, "Proceed"), 750, 750);

                    // press button to proceed

                    MouseInfo mouse1 = Greenfoot.getMouseInfo();

                    if (mouse1 != null) {
                        Actor currentActor = mouse1.getActor();
                        if (currentActor != null) {
                            if (currentActor.getClass() == Button3.class) {
                                Button3 currentButton = (Button3) currentActor;
                                int mouse1ButtonPressed = mouse1.getButton();
                                int mouse1ClickCount = mouse1.getClickCount();
                                if (mouse1ClickCount == 1) {
                                    currentButton.buttonToggle();
                                    removeObjects(getObjects(Button3.class));
                                    removeText();
                                    gameState = 3;
                                }
                            }
                        }
                    }
           break;
           // Game Initialization
           case 3: addObject(new Rocket(), 100, 250);
                   gameTime = 0;
                   // spawn a random number of asteroids b/t 0 and 4
                   spawnNoSplitAsteroids((int)(Math.random() * 4.49));
                   
                   NoSplitAsteroid.asteroidKills = 0;
                   NavalMine.navalMineKills = 0;
                   NoSplitAsteroid.lives = 3;
				   shieldTime = 0;
				   temp = 0;
				   temp1 = 0;
                   gameState = 4;
          
           // Start Gameplay
           case 4: gameTime += 1;
        
                    // spawn an asteroid every 3 secs
                    if ( (gameTime % 180) == 0) {
                        addObject( new NoSplitAsteroid(), (int)(Math.random()*1500), (int)(Math.random()*1000));
                    }
                    
                    // timer
                    /*
                    String timeElapsed = "Time Elapsed: " + Integer.toString(gameTime/60);
                    textBox(timeElapsed, 25, 1400, 10);
                    
                    // shield count display
                    String shieldCountDisplay = "Shields available: " + Integer.toString(shieldCountTemp);
                    textBox(shieldCountDisplay, 25, 1400, 40);
                    */
                   
                    // if there is a shield present, create a shieldTime variable
                    if ( getObjects(Shield.class) != null ) {
                        shieldTime += 1;
                        
                        // remove the shield after 4 seconds 
                        if ( shieldTime % 240 == 0 ) {
                            removeObjects(getObjects(Shield.class));
                        }
                    }
                    
                    // if the player runs out of lives, go to end game case
                    // this variable gets the lives variable from the NoSplitAsteroid class
                    int worldLives = NoSplitAsteroid.lives;
                    if ( worldLives == 0) {
                        gameState = 7;
                    }
                    
                    // if 10 asteroids are destroyed, move to next stage
                    if ( NoSplitAsteroid.asteroidKills == 12) {
                        gameState = 5;
                    }
                    
                    // if 5 or 10 asteroids have been destroyed, grant the user a shield
                    if ( (NoSplitAsteroid.asteroidKills == 5) || (NoSplitAsteroid.asteroidKills == 11) ) {
                        shieldCount += 1;
                        NoSplitAsteroid.asteroidKills += 1; 
                    }
                    
                    // if there is a shield available, press shift to activate
                    if ( (shieldCount >= 1) && ( Greenfoot.isKeyDown("shift")) ) {
                        addObject( new Shield(65), Rocket.x, Rocket.y );
                        shieldCount -= 1;
						shieldTime = 0;
                    }
                    
                    // reset game
                    if (Greenfoot.isKeyDown("R")) {
                        removeObjects(getObjects(Rocket.class));
                        removeObjects(getObjects(NoSplitAsteroid.class));
                        gameState = 3;
                    }
                    break;
           
           // next stage initialization
           case 5: 
                   // change background of world
                   setBackground("water.jpg");
                   
                   // call submarine function for the rocket class, which sets the image to a submarine
                   getObjects(Rocket.class).get(0).submarine();
                   
                   // remove all asteroids
                   removeObjects(getObjects(NoSplitAsteroid.class));
                   
                   // add a number of naval mines b/t 0 and 5
                   spawnNavalMines( ((int)(Math.random() * 5.49)));
                   
                   gameState = 6;
                   
                   
           case 6: gameTime += 1;
           
                   // spawn a naval mine every 3 secs        
                   if ( (gameTime % 180) == 0) {
                        addObject( new NavalMine(), (int)(Math.random()*1500), (int)(Math.random()*1000));
                    }
                    
                    /*
                    // timer
                    String timeElapsed1 = "Time Elapsed: " + Integer.toString(gameTime/60);
                    textBox(timeElapsed1, 25, 1400, 10);
                    */
                   
                   // if there is a shield present, create a shieldTime variable
                    if ( getObjects(Shield.class) != null ) {
                        shieldTime += 1;
                        
                        // remove the shield after 4 seconds 
                        if ( shieldTime % 240 == 0 ) {
                            removeObjects(getObjects(Shield.class));
                        }
                    }
                    
                    // if 5 or 10 naval mineshave been destroyed, grant the user a shield (11 to account for extra added)
                    if ( (NavalMine.navalMineKills == 5) || (NavalMine.navalMineKills == 11) ) {
                        shieldCount += 1;
                        NavalMine.navalMineKills += 1; 
                    }
                    
                    // if there is a shield available, press shift to activate
                    if ( (shieldCount >= 1) && ( Greenfoot.isKeyDown("shift")) ) {
                        addObject( new Shield(65), Rocket.x, Rocket.y );
                        shieldCount -= 1;
						shieldTime = 0;
                    }
                    
                    // reset game
                    if (Greenfoot.isKeyDown("R")) {
                        removeObjects(getObjects(Rocket.class));
                        removeObjects(getObjects(NavalMine.class));
                        gameState = 3;
                    }
                    
                    // if the player runs out of lives, go to end game case   
                    if ( NoSplitAsteroid.lives == 0) {
                        gameState = 7;
                    }
                   break;
                   
           // game over case, remove all objects then display message       
           case 7: removeObjects(getObjects(null));
                   textBox("Game Over", 150, 760, 200);
                   
                   // variable to hold asteroid kill count
                   int worldAsteroidKills = NoSplitAsteroid.asteroidKills;
				
				   // accounts for artificially gained kills from shield
				   if ( worldAsteroidKills >= 5 ) {
						temp = 1;
						
						if ( worldAsteroidKills >= 10 ) {
							temp = 2;
						  }
				    }
                   
                   textBox1(("Asteroids destroyed: " + (Integer.toString(worldAsteroidKills - temp))), 100, 750, 400);
                   
                   // variable to hold naval mine kill count
                   int worldNavalMineKills = NavalMine.navalMineKills;

				   // same thing but for shield in sea world
				   if ( worldNavalMineKills >= 5 ) {
						temp1 = 1;
						
						if ( worldNavalMineKills >= 10 ) {
							temp1 = 2;
						  }
				    }
                   
				   textBox1(("Naval Mines Destroyed: " + (Integer.toString(worldNavalMineKills - temp1))), 100, 750, 600);
                   NoSplitAsteroid.lives = 3;
                   
                   // press button to reset and try again
                   addObject( new Button4(false, "Try Again"), 750, 800);
                   MouseInfo mouse2 = Greenfoot.getMouseInfo();

                    if (mouse2 != null) {
                        Actor currentActor = mouse2.getActor();
                        if (currentActor != null) {
                            if (currentActor.getClass() == Button4.class) {
                                Button4 currentButton = (Button4) currentActor;
                                int mouse2ButtonPressed = mouse2.getButton();
                                int mouse2ClickCount = mouse2.getClickCount();
                                if (mouse2ClickCount == 1) {
                                    currentButton.buttonToggle();
                                    removeObjects(getObjects(Button4.class));
                                    removeText();
                                    removeText1();
                                    NoSplitAsteroid.asteroidKills = 0;
                                    gameState = 0;
                                }
                            }
                        }
                    }
                   break;
           
		   // 2 player instruction screen  
           case 8: textBox(twoPlayerInstructions1, 20, 750, 100);
                   textBox(twoPlayerInstructions2, 20, 750, 125);
                   textBox(twoPlayerInstructions3, 20, 750, 150);
                   textBox(twoPlayerInstructions4, 20, 750, 175);
                   textBox(twoPlayerInstructions5, 20, 750, 200);
                   textBox(twoPlayerInstructions6, 20, 750, 225);
                   textBox(twoPlayerInstructions7, 20, 750, 275);
                   textBox(twoPlayerInstructions8, 20, 750, 300);
                   textBox(twoPlayerInstructions9, 20, 750, 325);
                   textBox(twoPlayerInstructions10, 20, 750, 350);
                   textBox(twoPlayerInstructions11, 20, 750, 375);
                   textBox(twoPlayerInstructions12, 20, 750, 400);
                   textBox(twoPlayerInstructions13, 20, 750, 450);
                   textBox(twoPlayerInstructions14, 20, 750, 475);
				   textBox(twoPlayerInstructions15, 20, 750, 500);
				   addObject(new Button5(false, "Proceed"), 750, 750);

				    // press button to proceed

                    MouseInfo mouse3 = Greenfoot.getMouseInfo();

                    if (mouse3 != null) {
                        Actor currentActor = mouse3.getActor();
                        if (currentActor != null) {
                            if (currentActor.getClass() == Button5.class) {
                                Button5 currentButton = (Button5) currentActor;
                                int mouse1ButtonPressed = mouse3.getButton();
                                int mouse1ClickCount = mouse3.getClickCount();
                                if (mouse1ClickCount == 1) {
                                    currentButton.buttonToggle();
                                    removeObjects(getObjects(Button5.class));
                                    removeText();
                                    gameState = 9;
                                }
                            }
                        }
                    }
		   break;
				   
		   // 2 player game initialization
           case 9: gameTime = 0;
                   addObject(new Rocket(), 100, 250);
                   spawnNoSplitAsteroids((int)(Math.random() * 4.49));
                   
                   // add planet
                   addObject(new Planet(), 300, 100);
                   
                   // add second rocket
                   addObject( new Rocket_V2(), 1400, 500);
                   gameState = 10;
                   
           case 10: gameTime += 1;
                    
                    // spawn an asteroid every 3 secs
                    if ( (gameTime % 180) == 0) {
                        addObject( new NoSplitAsteroid(), (int)(Math.random()*600), (int)(Math.random()*400));
                    }
                    
					// if one of the rockets get destroyed, go to end screen
					if ( (getObjects(Rocket.class).size() == 0) || (getObjects(Rocket_V2.class).size() == 0)) {
							gameState = 11;
					   }
                    // reset game
                    if (Greenfoot.isKeyDown("R")) {
                        removeObjects(getObjects(Rocket.class));
                        removeObjects(getObjects(NoSplitAsteroid.class));
                        removeObjects(getObjects(Rocket_V2.class));
                        removeObjects(getObjects(Planet.class));
                        gameState = 9;
                    }
                    break;
           
		   case 11: // end screen for 2 player
					
					// remove all objects
					removeObjects(getObjects(null));
					textBox("Game Over", 50, 750, 400);
					
					// press button to reset and try again
                   addObject( new Button6(false, "Try Again"), 750, 800);
                   MouseInfo mouse4 = Greenfoot.getMouseInfo();

                    if (mouse4 != null) {
                        Actor currentActor = mouse4.getActor();
                        if (currentActor != null) {
                            if (currentActor.getClass() == Button6.class) {
                                Button6 currentButton = (Button6) currentActor;
                                int mouse4ButtonPressed = mouse4.getButton();
                                int mouse4ClickCount = mouse4.getClickCount();
                                if (mouse4ClickCount == 1) {
                                    currentButton.buttonToggle();
                                    removeObjects(getObjects(Button6.class));
                                    removeText();
                                    gameState = 0;
                                }
                            }
                        }
                    }
                   break;         
           default: gameState=0;
        }
    }
    
    public int getTime() {
        return gameTime;
    }
    
    // function for creating a set amount of no split asteroids, giving them a random position
    public void spawnNoSplitAsteroids(int numberOfAsteroids) {
        for (int i = 1; i <= numberOfAsteroids; i += 1) {
            addObject(new NoSplitAsteroid(), ((int)(Math.random() * 600)), ((int)(Math.random() * 400)));
        }
    }
    
    // function to create NavalMines
    public void spawnNavalMines(int numberOfNavalMines) {
        for (int i = 1; i <= numberOfNavalMines; i += 1) {
            addObject(new NavalMine(), ((int)(Math.random() * 600)), ((int)(Math.random() * 400)));
        }
    }
    
    /**
    This is the method that creates a text box
    Essentially, various pieces of texts will be assigned
    to variables which will then be put into the 
    textBox function when it is called
    
    @param x string with the text being displayed
    @param y size of font
    */
    
    // variables for instruction text boxes single player
    String singlePlayerHeading = "Single Player Instructions";
    String singlePlayerInstructions1 = "Controls: To turn ship use left and right arrow keys";
    String singlePlayerInstructions2 = "Press the up arrow to accelerate your ship";
    String singlePlayerInstructions3 = "Press the down arrow to deccelerate your ship";
    String singlePlayerInstructions4 = "Press the S and D keys to turn at a rate relative to your speed";
    String singlePlayerInstructions5 = "Press the 1 key to reset the speed of your ship to default";
    String singlePlayerInstructions6 = "Press the spacebar to shoot";
    String singlePlayerInstructions7 = "Game Mechanics: Shoot Asteroids to destroy them";
    String singlePlayerInstructions8 = "If you destroy 10 asteroids, you move to the next stage";
    String singlePlayerInstructions9 = "The Sea stage is more difficult, the sea mines are faster";
    String singlePlayerInstructions10 = "For every 5 asteroids and/or sea mines destroyed, you are awarded a shield";
    String singlePlayerInstructions11 = "Press shift to activate the shield, it will last for 4 seconds";
    String singlePlayerInstructions12 = "The shield will protect the rocket from any incoming asteroids or naval mines";
    String singlePlayerInstructions13 = "You have 3 lives, one is lost each time an asteroid or naval mines hits your ship";
    String singlePlayerInstructions14 = "Once your lives have been exhausted, the game will be over";
    
    // variables for 2 player instruction text boxes
    String twoPlayerHeading = "Two Player Instructions";
    String twoPlayerInstructions1 = "Player 1 Controls: To turn ship use left and right arrow keys";
    String twoPlayerInstructions2 = "Press the up arrow to accelerate your ship";
    String twoPlayerInstructions3 = "Press the down arrow to deccelerate your ship";
    String twoPlayerInstructions4 = "Press the S and D keys to turn at a rate relative to your speed";
    String twoPlayerInstructions5 = "Press the 1 key to reset the speed of your ship to default";
    String twoPlayerInstructions6 = "Press the spacebar to shoot";
    String twoPlayerInstructions7 = "Player 2 Controls: To turn ship use the K and L keys";
    String twoPlayerInstructions8 = "Press the A key to accelerate your ship";
    String twoPlayerInstructions9 = "Press the B key to deccelerate your ship";
    String twoPlayerInstructions10 = "Press the O and P keys to turn at a rate relative to your speed";
    String twoPlayerInstructions11 = "Press the 0 key to reset the speed of your ship to default";
    String twoPlayerInstructions12 = "Press enter to shoot";
    String twoPlayerInstructions13 = "Game Mechanics: The objective of the game is to shoot the opposing rocket";
    String twoPlayerInstructions14 = "Shooting or running to asteroids will destroy them";
    String twoPlayerInstructions15 = "The planet will orbit around an arbitrary point and engulf any bullets";
    
    public void textBox(String x, int y, int z, int zz) {
        addObject(new Text(x, y), z, zz);
    }
    
    // same textBox creator but with colour customization
    public void textBox1(String x, int y, int z, int zz) {
        addObject(new Text1(x, y), z, zz);
    }
    
    // removes a textbox
    public void removeText() {
        removeObjects(getObjects(Text.class));
       }
       
    public void removeText1() {
        removeObjects(getObjects(Text1.class));
    }
    
    
}
