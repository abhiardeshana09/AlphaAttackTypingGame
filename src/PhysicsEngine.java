import java.awt.*;

/**
 * Alpha Attack Typing Game
 * Author: Abhi Ardeshana
 * Description: Class that implements a physics engine used to perform calculations needed in the program.
 */

class PhysicsEngine {

    // Attribute to store the frame's insets, for use in the collision detection and pushback algorithms
    private Insets frameInsets;

    // Constructor
    PhysicsEngine(Insets insets) {
        frameInsets = insets;
    }

    // This method checks if the letter has collided with the left side of the screen
    // It accepts parameters for the x coordinate of the letter
    boolean checkLeftCollision(double x) {
        return (x < frameInsets.left);
    }

    // This method calculates the vertical pushback when the letter collides with the left side of the screen
    // It accepts parameters for the x coordinate of the letter and the current direction of the movement vector
    double getLeftPushbackY(double x, double direction) {
        return Math.tan(Math.toRadians(direction)) * (x - frameInsets.left);
    }

    // This method calculates the horizontal pushback when the letter collides with the left side of the screen
    // It accepts parameters for the x coordinate of the letter
    double getLeftPushbackX(double x) {
        return -(x - frameInsets.left);
    }

    // This method checks if the letter has collided with the right side of the screen
    // It accepts parameters for the x coordinate of the letter, the width of the window, and the width of the letter
    boolean checkRightCollision(double x, double windowWidth, double letterWidth) {
        return (x > windowWidth - letterWidth - frameInsets.right);
    }

    // This method calculates the vertical pushback when the letter collides with the right side of the screen
    // It accepts parameters for the x coordinate of the letter, the width of the window, the width of the letter, and
    // the current direction of the movement vector
    double getRightPushbackY(double x, double windowWidth, double letterWidth, double direction) {
        return Math.tan(Math.toRadians(direction)) * (x - (windowWidth - letterWidth - frameInsets.right));
    }

    // This method calculates the horizontal pushback when the letter collides with the right side of the screen
    // It accepts parameters for the x coordinate of the letter, the width of the window, and the width of the letter
    double getRightPushbackX(double x, double windowWidth, double letterWidth) {
        return -(x - (windowWidth - letterWidth - frameInsets.right));
    }

    // This method calculates the angle of reflection when a collision occurs with the sides of the screen
    // It accepts parameters for the current direction of the movement vector
    double getReflectionAngle(double direction) {
        return 180 - direction;
    }
}