package org.florian_wagner.snake.game;

import de.nrw.schulentwicklung.listenklassen.List;
import de.nrw.schulentwicklung.netzklassen.Server;
import org.florian_wagner.snake.core.*;

import java.util.Random;

/**
 * Created by Florian on 09.04.2017.
 */
public class SnakeServer extends Server {

    private int speed;

    private Thread thread = null;

    private List<UnidentifiedConnection> unidentifiedConnections;
    private List<UserProfile> connectedUsers;

    private Location apple;

    public SnakeServer(int speed)
    {
        super(11121);
        this.speed = speed;
    }

    /**
     * starts the game functionality on the server
     */
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

        // spawn apple
        Random r = new Random();
        apple = new Location(r.nextInt(32),r.nextInt(24));

        thread.start();
    }

    /**
     * this method is called every game tick
     */
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
        for(connectedUsers.toFirst();connectedUsers.hasAccess();connectedUsers.next())
        {
            UserProfile user = connectedUsers.getContent();
            if(user.getSnake() != null)
            {
                List<Location> parts = user.getSnake().getAllLocations(false);
                for(parts.toFirst();parts.hasAccess();parts.next())
                {
                    usedLocations.append(parts.getContent());
                }
            }
        }
        // check if any location is equal to a head location (or the head goes out of bounds)
        for(usedLocations.toFirst();usedLocations.hasAccess();usedLocations.next())
        {
            Location loc = usedLocations.getContent();
            for(connectedUsers.toFirst();connectedUsers.hasAccess();connectedUsers.next())
            {
                UserProfile user = connectedUsers.getContent();
                Snake snake = user.getSnake();
                if(snake != null)
                {
                    if(snake.getHeadLocation().getX() == loc.getX())
                    {
                        if(snake.getHeadLocation().getY() == loc.getY())
                        {
                            // case snake dies
                            gameOver(user);
                            continue;
                        }
                    }
                    // check if head is out of bounds
                    if(snake.getHeadLocation().getX() > 31 || snake.getHeadLocation().getX() < 0)
                    {
                        gameOver(user);
                        continue;
                    }
                    if(snake.getHeadLocation().getY() > 23 || snake.getHeadLocation().getY() < 0)
                    {
                        gameOver(user);
                        continue;
                    }
                }
            }
        }

        // check if snake eats apple
        for(connectedUsers.toFirst();connectedUsers.hasAccess();connectedUsers.next())
        {
            UserProfile user = connectedUsers.getContent();
            if(user.getSnake() != null)
            {
                Snake snake = user.getSnake();
                if(snake.getHeadLocation().getX() == apple.getX())
                {
                    if(snake.getHeadLocation().getY() == apple.getY())
                    {
                        // case apple eat
                        snake.grow();
                        user.setScore(user.getScore() +1);
                        updateScoreboard();

                        // spawn new apple
                        Random r = new Random();
                        apple = new Location(r.nextInt(32),r.nextInt(24));
                    }
                }
            }
        }


        updateMatchfield();
    }

    /**
     * let a user's snake dispose and the scoreboard update
     * @param user
     */
    private void gameOver(UserProfile user)
    {
        user.setScore(0);
        user.setSnake(null);
        updateScoreboard();
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

    /**
     * Event Handler for North Rhine Westphalia's Server.java
     * @param pClientIP IP-Nummer des Clients, der neu angemeldet ist
     * @param pClientPort Port-Nummer des Clients, der neu angemeldet ist
     */
    @Override
    public void processNewConnection(String pClientIP, int pClientPort)
    {
        // first unidentified connection
        UnidentifiedConnection con = new UnidentifiedConnection(pClientIP,pClientPort);
        unidentifiedConnections.append(con);
    }

    /**
     * Event Handler for North Rhine Westphalia's Server.java
     * @param pClientIP IP-Nummer des Clients, der die Nachricht geschickt hat
     * @param pClientPort Port-Nummer des Clients, der die Nachricht geschickt hat
     * @param pMessage Die empfangene Nachricht, die bearbeitet werden soll
     */
    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage)
    {

        String[] split = pMessage.split(";");
        switch(split[0])
        {
            case "1":
                for(unidentifiedConnections.toFirst();unidentifiedConnections.hasAccess();unidentifiedConnections.next())
                {
                    UnidentifiedConnection con = unidentifiedConnections.getContent();
                    if(con.getIp().equalsIgnoreCase(pClientIP) && con.getPort() == pClientPort)
                    {
                        UserProfile profile = new UserProfile( // create new user
                                split[1], // username
                                0, // default score
                                null, // snake
                                pClientIP, // trivial
                                pClientPort, // trivial
                                split[2], // snake color
                                split[3] // head color
                        );
                        connectedUsers.append(profile);
                        updateScoreboard();
                    }
                }
                break;
            case "4":
                // find profile
                for(connectedUsers.toFirst();connectedUsers.hasAccess();connectedUsers.next())
                {
                    UserProfile profile = connectedUsers.getContent();
                    if(profile.getClientIP().equalsIgnoreCase(pClientIP) && profile.getClientPort() == pClientPort)
                    {
                        spawnSnake(profile);
                    }
                }
                break;
            case "5":
                // find profile
                for(connectedUsers.toFirst();connectedUsers.hasAccess();connectedUsers.next())
                {
                    UserProfile profile = connectedUsers.getContent();
                    if(profile.getClientIP().equalsIgnoreCase(pClientIP) && profile.getClientPort() == pClientPort)
                    {
                        Direction dir = Direction.fromInteger(Integer.parseInt(split[1]));

                        if(profile.getSnake() == null)return;
                        // check if direction change is invalid
                        Snake snake = profile.getSnake();
                        if(dir == Direction.NORTH && snake.getDirection() == Direction.SOUTH)return;
                        if(dir == Direction.SOUTH && snake.getDirection() == Direction.NORTH)return;
                        if(dir == Direction.WEST && snake.getDirection() == Direction.EAST)return;
                        if(dir == Direction.EAST && snake.getDirection() == Direction.WEST)return;

                        // else it's ok
                        snake.setDirection(dir);

                    }
                }
                break;
        }



    }

    /**
     * spawn a new snake at a random location + incl. update
     * @param profile
     */
    private void spawnSnake(UserProfile profile)
    {
        Snake snake = new Snake(0,new Random().nextInt(15),5);
        profile.setSnake(snake);
        profile.setScore(5);
        updateScoreboard();
    }

    /**
     * Event Handler for North Rhine Westphalia's Server.java
     * @param pClientIP IP-Nummer des Clients, mit dem die Verbindung beendet wurde
     * @param pClientPort Port-Nummer des Clients, mit dem die Verbindung beendet wurde
     */
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

    /**
     * serializes all the scoreboard data to a string packet and sends it to the client
     */
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

    /**
     * serializes all the graphic data to a string packet and sends it to the client
     */
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
                    toSend = toSend + "'" + loc.getX() + "," + loc.getY();
                }

                // then draw head
                toSend = toSend + ";" + user.getColor_head() + "'" + snake.getHeadLocation().getX() +"," + snake.getHeadLocation().getY();
            }
        }

        toSend = toSend + ";" + "#12ff00" + "'" + apple.getX() + "," + apple.getY();

        // send to all connected clients
        for(connectedUsers.toFirst();connectedUsers.hasAccess();connectedUsers.next())
        {
            UserProfile user = connectedUsers.getContent();
            send(user.getClientIP(),user.getClientPort(),toSend);
        }
    }

}
