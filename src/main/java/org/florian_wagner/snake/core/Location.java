package org.florian_wagner.snake.core;

/**
 * Created by Florian on 03.04.2017.
 */
public class Location {

    private int x,y;

    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Location getRelative(Direction dir)
    {
        if(dir == Direction.NORTH)
        {
            return new Location(x,y-1);
        }else if(dir == Direction.EAST)
        {
            return new Location(x+1,y);
        }else if(dir == Direction.SOUTH)
        {
            return new Location(x,y+1);
        }else if(dir == Direction.WEST)
        {
            return new Location(x-1,y);
        }
        return null;
    }
}
