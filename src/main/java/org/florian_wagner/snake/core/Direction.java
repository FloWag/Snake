package org.florian_wagner.snake.core;

/**
 * Created by Florian on 03.04.2017.
 */
public enum Direction {
    NORTH(0), EAST(1), SOUTH(2), WEST(3);

    private int value;

    Direction(int asInt)
    {
        this.value = asInt;
    }

    public int toInteger()
    {
        return value;
    }

    public static Direction fromInteger(int r)
    {
        for(Direction dir : values())
        {
            if(dir.toInteger() == r)
            {
                return dir;
            }
        }
        return NORTH;
    }

}
