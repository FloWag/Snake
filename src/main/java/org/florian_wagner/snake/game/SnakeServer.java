package org.florian_wagner.snake.game;

import de.nrw.schulentwicklung.listenklassen.List;
import de.nrw.schulentwicklung.netzklassen.Server;
import org.florian_wagner.snake.core.Location;
import org.florian_wagner.snake.core.Snake;
import org.florian_wagner.snake.core.UnidentifiedConnection;
import org.florian_wagner.snake.core.UserProfile;

/**
 * Created by Florian on 09.04.2017.
 */
public class SnakeServer extends Server {

    private int speed;

    private Thread thread = null;

    private List<UnidentifiedConnection> unidentifiedConnections;
    private List<UserProfile> connectedUsers;

    public SnakeServer(int speed)
    {
        super(11121);
        this.speed = speed;
    }

    public void start()
    {

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    try {
                        Thread.sleep(1000 / speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    update();
                }
            }
        });
        unidentifiedConnections = new List<UnidentifiedConnection>();
        connectedUsers = new List<UserProfile>();


        thread.start();
    }

    private void update()
    {
        // move every snake
        for(connectedUsers.toFirst();connectedUsers.hasAccess();connectedUsers.next())
        {
            UserProfile user = connectedUsers.getContent();
            if(user.getSnake() != null)
            {
                user.getSnake().move();
            }
        }

        // check collision with another snakepart
        // first build a giant list with all snake part locations
        List<Location> usedLocations = new List<Location>();


        updateMatchfield();
    }

    public void stop()
    {
        if(thread != null)
        {
            thread.stop();
        }
        close();
    }


    // server methods

    @Override
    public void processNewConnection(String pClientIP, int pClientPort)
    {
        // first unidentified connection
        UnidentifiedConnection con = new UnidentifiedConnection(pClientIP,pClientPort);
        unidentifiedConnections.append(con);
    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage)
    {

    }

    @Override
    public void processClosedConnection(String pClientIP, int pClientPort)
    {
        // check if connection is unidentified
        for(unidentifiedConnections.toFirst();unidentifiedConnections.hasAccess();unidentifiedConnections.next())
        {
            UnidentifiedConnection con = unidentifiedConnections.getContent();
            if(con.getIp().equalsIgnoreCase(pClientIP) && con.getPort() == pClientPort)
            {
                unidentifiedConnections.remove();
            }
        }

        // check if user is connected
        for(connectedUsers.toFirst();connectedUsers.hasAccess();connectedUsers.next())
        {
            UserProfile profile = connectedUsers.getContent();
            if(profile.getClientIP().equalsIgnoreCase(pClientIP) && profile.getClientPort() == pClientPort)
            {
                connectedUsers.remove();
                updateScoreboard();
            }
        }
    }

    private void updateScoreboard()
    {

        // build string to send
        String toSend = "2"; // packetID
        for(connectedUsers.toFirst();connectedUsers.hasAccess();connectedUsers.next())
        {
            UserProfile user = connectedUsers.getContent();
            toSend = toSend + ";" + user.getUsername() + "," + user.getScore();
        }

        // send to all connected clients
        for(connectedUsers.toFirst();connectedUsers.hasAccess();connectedUsers.next())
        {
            UserProfile user = connectedUsers.getContent();
            send(user.getClientIP(),user.getClientPort(),toSend);
        }

    }

    private void updateMatchfield()
    {

        //build string to send
        String toSend = "3";
        for(connectedUsers.toFirst();connectedUsers.hasAccess();connectedUsers.next())
        {
            UserProfile user = connectedUsers.getContent();
            if(user.getSnake() != null)
            {
                Snake snake = user.getSnake();
                // first draw snake body
                toSend = toSend + ";" + user.getColor_snake();
                List<Location> bodylocations = snake.getAllLocations(false);
                for(bodylocations.toFirst();bodylocations.hasAccess();bodylocations.next())
                {
                    Location loc = bodylocations.getContent();
                    toSend = toSend + "|" + loc.getX() + "," + loc.getY();
                }

                // then draw head
                toSend = toSend + ";" + user.getColor_head() + "|" + snake.getHeadLocation().getX() +"," + snake.getHeadLocation().getY();
            }
        }

        // send to all connected clients
        for(connectedUsers.toFirst();connectedUsers.hasAccess();connectedUsers.next())
        {
            UserProfile user = connectedUsers.getContent();
            send(user.getClientIP(),user.getClientPort(),toSend);
        }
    }

}
