/**
 * Alpha Attack Typing Game
 * Author: Abhi Ardeshana
 * Description: Class that implements a directional vector that is used to move a letter.
 */

class Vector {

    // Attributes to store the vector's direction and speed
    private double vecDirection;
    private double vecSpeed;

    // This method returns the current direction of the vector
    double getDirection() {
        return vecDirection;
    }

    // This method sets the direction of the vector and accepts the direction as a parameter
    void setDirection(double direction) {
        vecDirection = direction;
    }

    // This method sets the speed of the vector and accepts the speed as a parameter
    void setSpeed(double speed) {
        vecSpeed = speed;
    }

    // This method calculates the distance that the letter should move horizontally using the cosine trigonometric
    // ratio and accepts a parameter for the time passed since the last update
    double getHorizontalMove(long time) {
        return Math.cos(Math.toRadians(vecDirection)) * time * vecSpeed;
    }

    // This method calculates the distance that the letter should move vertically using the sine trigonometric
    // ratio and accepts a parameter for the time passed since the last update
    double getVerticalMove(long time) {
        return -Math.sin(Math.toRadians(vecDirection)) * time * vecSpeed;
    }
}