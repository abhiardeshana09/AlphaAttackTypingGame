import java.awt.*;

/**
 * Alpha Attack Typing Game
 * Author: Abhi Ardeshana
 * Description: Class that implements a letter that moves down the screen
 */

class LetterSprite {

    // Attributes for the letter's coordinates, width and the character that it displays
    private double ltrX;
    private double ltrY;
    private double ltrWidth;
    private String ltrCharacter;
    // Attributes for the letter's physics engine, event tracker and movement vector
    private PhysicsEngine ltrPhysicsEngine;
    private EventTracker ltrEventTracker;
    private Vector ltrVector;

    // Constructor
    LetterSprite(Insets insets) {
        // Instantiates the letter's physics engine, event tracker and movement vector
        ltrPhysicsEngine = new PhysicsEngine(insets);
        ltrEventTracker = new EventTracker();
        ltrVector = new Vector();
    }

    // This method positions the letter at a random location above the screen and makes it move at a speed and direction
    // determined by the game's difficulty. It accepts parameters for the width and height of the window, the game
    // difficulty, and the character to be displayed.
    void positionLetter(int windowWidth, int windowHeight, int gameDifficulty, String newCharacter) {
        // Positions the letter at a random location above the screen
        ltrX = Math.random() * (windowWidth - 50);
        ltrY = Math.random() * -windowHeight;
        // Sets the character to be displayed
        ltrCharacter = newCharacter;
        // This "if" statement runs if the game's difficulty is set to "Easy"
        if (gameDifficulty == 0) {
            // Sets the direction of the letter's vector to straight down and sets the speed of the letter's vector to
            // a value between 0.1 and 0.2 pixels per millisecond
            ltrVector.setDirection(270);
            ltrVector.setSpeed(Math.random() * 0.1 + 0.1);
        }
        // This "if" statement runs if the game's difficulty is set to "Normal"
        else if (gameDifficulty == 1) {
            // Sets the direction of the letter's vector to straight down and sets the speed of the letter's vector to
            // a value between 0.1 and 0.25 pixels per millisecond
            ltrVector.setDirection(270);
            ltrVector.setSpeed(Math.random() * 0.15 + 0.1);
        }
        // This "else" statement runs if the game's difficulty is set to "Hard"
        else {
            // Sets the direction of the letter's vector to a value between 225 and 315 degrees, and sets the speed of
            // the letter's vector to a value between 0.1 and 0.3 pixels per millisecond
            ltrVector.setDirection(Math.random() * 90 + 225);
            ltrVector.setSpeed(Math.random() * 0.2 + 0.1);
        }
    }

    // This method moves the letter based on the current speed and direction of the movement vector
    void moveLetter() {
        // Moves the letter using the letter's movement vector and the time passed since the last update
        ltrX += ltrVector.getHorizontalMove(ltrEventTracker.getElapsedTime());
        ltrY += ltrVector.getVerticalMove(ltrEventTracker.getElapsedTime());
        // Records the time of the current update so that the letter moves the correct distance at the next update
        ltrEventTracker.recordTimeLastEvent();
    }

    // This method checks if the letter has collided with the sides of the screen
    // It accepts a parameter for the width of the window
    void checkLetterCollision(int windowWidth) {
        // Checks if the letter has collided with the left side of the screen
        if (ltrPhysicsEngine.checkLeftCollision(ltrX)) {
            // Calculates and applies the horizontal and vertical pushback if the letter collided with the left side
            ltrY += ltrPhysicsEngine.getLeftPushbackY(ltrX, ltrVector.getDirection());
            ltrX += ltrPhysicsEngine.getLeftPushbackX(ltrX);
            // Calculates the reflected angle and sets the vector's direction to that angle
            ltrVector.setDirection(ltrPhysicsEngine.getReflectionAngle(ltrVector.getDirection()));
        }
        // Checks if the letter has collided with the right side of the screen
        else if (ltrPhysicsEngine.checkRightCollision(ltrX, windowWidth, ltrWidth)) {
            // Calculates and applies the horizontal and vertical pushback if the letter collided with the right side
            ltrY += ltrPhysicsEngine.getRightPushbackY(ltrX, windowWidth, ltrWidth, ltrVector.getDirection());
            ltrX += ltrPhysicsEngine.getRightPushbackX(ltrX, windowWidth, ltrWidth);
            // Calculates the reflected angle and sets the vector's direction to that angle
            ltrVector.setDirection(ltrPhysicsEngine.getReflectionAngle(ltrVector.getDirection()));
        }
    }

    // This method draws the letter onto the screen
    void drawLetter(Graphics g) {
        // Draws the letter onto the screen using the current coordinates
        g.drawString(ltrCharacter, (int) ltrX, (int) ltrY);
        // Updates the letter's width using the graphics context
        ltrWidth = g.getFontMetrics().stringWidth(ltrCharacter);
    }

    // This method returns the letter's Y coordinate
    double getLetterY() {
        return ltrY;
    }

    // This method returns the character that the letter is displaying
    String getLetterCharacter() {
        return ltrCharacter;
    }
}