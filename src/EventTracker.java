/**
 * Alpha Attack Typing Game
 * Author: Abhi Ardeshana
 * Description: Class that implements an event tracker that is used to move a letter at regular intervals.
 */

class EventTracker {

    // Attribute to store the time of the last event
    private long timeLastEvent;

    // Constructor
    EventTracker() {
        recordTimeLastEvent();
    }

    // This method records the time of an event
    void recordTimeLastEvent() {
        timeLastEvent = System.currentTimeMillis();
    }

    // This method returns the time elapsed since the last event
    long getElapsedTime() {
        return System.currentTimeMillis() - timeLastEvent;
    }
}