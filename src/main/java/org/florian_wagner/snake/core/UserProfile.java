package org.florian_wagner.snake.core;

import javafx.scene.paint.Color;

/**
 * Created by Florian on 09.04.2017.
 */
public class UserProfile {

    private String username;
    private int score;
    private Snake snake;
    private String clientIP;
    private int clientPort;
    private String color_snake;
    private String color_head;

    public UserProfile(String username, int score, Snake snake, String clientIP, int clientPort, String color_snake, String color_head) {
        this.username = username;
        this.score = score;
        this.snake = snake;
        this.clientIP = clientIP;
        this.clientPort = clientPort;
        this.color_snake = color_snake;
        this.color_head = color_head;
    }

    public void setColor_snake(String color_snake) {
        this.color_snake = color_snake;
    }

    public void setColor_head(String color_head) {
        this.color_head = color_head;
    }

    public String getColor_snake() {
        return color_snake;
    }

    public String getColor_head() {
        return color_head;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public String getClientIP() {
        return clientIP;
    }

    public int getClientPort() {
        return clientPort;
    }
}
