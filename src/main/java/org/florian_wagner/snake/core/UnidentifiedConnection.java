package org.florian_wagner.snake.core;

/**
 * Created by Florian on 09.04.2017.
 */
public class UnidentifiedConnection
{

    private String ip;
    private int port;

    public UnidentifiedConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
