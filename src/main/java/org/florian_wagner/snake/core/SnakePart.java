package org.florian_wagner.snake.core;

/**
 * Created by Florian on 03.04.2017.
 */
public class SnakePart {

    private SnakePart nextPart;
    private Location loc;

    public SnakePart(Location location)
    {
        nextPart = null;
        this.loc = location;
    }

    public void setNextPart(SnakePart nextPart) {
        this.nextPart = nextPart;
    }

    public SnakePart getNextPart() {
        return nextPart;
    }

    public void setLocation(Location loc) {
        this.loc = loc;
    }

    public Location getLocation() {
        return loc;
    }
}
