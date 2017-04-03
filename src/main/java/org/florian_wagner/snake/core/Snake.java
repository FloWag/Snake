package org.florian_wagner.snake.core;

import de.nrw.schulentwicklung.listenklassen.List;

/**
 * Created by Florian on 03.04.2017.
 */
public class Snake {

    private SnakePart head;
    private SnakePart tail;

    private Direction direction;

    public Snake(int x, int y, int length)
    {

        // create default snake
        SnakePart previousPart = null;
        for(int i = 0; i < length; i++)
        {
            SnakePart part = new SnakePart(new Location(length -i + x, y));
            if(i == 0)
            {
                // case head
                head = part;
            }else if(i == length-1)
            {
                // case tail
                tail = part;
            }
            if(previousPart != null)
            {
                previousPart.setNextPart(part);
            }
            previousPart = part;
        }
    }

    public List<Location> getAllLocations(boolean includeHead)
    {
        List<Location> list = new List<Location>();
        SnakePart currentPart = head;
        if(!includeHead)
        {
            currentPart = currentPart.getNextPart();
        }
        while(currentPart != null)
        {
            list.append(currentPart.getLocation());
            currentPart = currentPart.getNextPart();
        }
        return list;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void move()
    {
        SnakePart current = head;
        List<SnakePart> array = new List<SnakePart>();
        // move every element to the next position
        while(current != null)
        {
            array.append(current);
            if(current.getNextPart() != null)
            {
                current.getNextPart().setLocation(current.getLocation());
            }
            current = current.getNextPart();
        }
        // move the head to the next position (depending on the direction)
        head.setLocation(head.getLocation().getRelative(direction));

    }

    public void grow()
    {
        SnakePart part = new SnakePart(tail.getLocation());
        tail.setNextPart(part);
        tail = part;
    }

    public void setDirection(Direction dir)
    {
        this.direction = dir;
    }

}
