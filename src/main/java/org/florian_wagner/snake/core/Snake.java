package org.florian_wagner.snake.core;

import de.nrw.schulentwicklung.listenklassen.List;

/**
 * Created by Florian on 03.04.2017.
 */
public class Snake {

    private SnakePart head;
    private SnakePart tail;

    private Direction direction;

    private boolean moveAfterDirectionChange = true; // the snake is only allowed to change the direction one time per move-tick (to fix a bug)

    /**
     *
     * @param x x-position of the snake head
     * @param y y-position of the snake head
     * @param length default length of the snake
     */
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
                part.setPreviousPart(previousPart);
            }
            previousPart = part;
        }
        direction = Direction.EAST;
    }

    /**
     *
     * @param includeHead when true head is included, else not
     * @return a location-list
     */
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

    /**
     * let the snake move one step
     */
    public void move()
    {

        SnakePart current = tail;

        //collect all snake parts (but the head) as list
        List<SnakePart> array = new List<SnakePart>();
        int i = 0;
        while(current != null)
        {
            array.append(current);
            current = current.getPreviousPart();
            i++;
        }

        // now move every part one step ahead
        for(array.toFirst();array.hasAccess();array.next())
        {
            SnakePart sp = array.getContent();
            if(sp.getPreviousPart() != null)
            {
                sp.setLocation(sp.getPreviousPart().getLocation());
            }
        }
        // move the head to the next position (depending on the direction)
        head.setLocation(head.getLocation().getRelative(direction));

        moveAfterDirectionChange = true;

    }

    public Location getHeadLocation()
    {
        return head.getLocation();
    }

    /**
     * let the snake length increase (1 unit)
     */
    public void grow()
    {
        SnakePart part = new SnakePart(tail.getLocation());
        tail.setNextPart(part);
        part.setPreviousPart(tail);
        tail = part;
    }

    public void setDirection(Direction dir)
    {
        if(moveAfterDirectionChange)
        {
            this.direction = dir;
            moveAfterDirectionChange = false;
        }
    }

}
