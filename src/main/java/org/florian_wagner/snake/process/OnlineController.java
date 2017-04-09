package org.florian_wagner.snake.process;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Florian on 09.04.2017.
 */
public class OnlineController implements Initializable {


    @FXML  private ListView scoreboard;

    @FXML private Canvas game_screen;

    private GraphicsContext gc;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = game_screen.getGraphicsContext2D();
    }

    /**
     * cleans all the rendered objects on the matchfield
     */
    public void cleanGC()
    {
        gc.setFill(Color.SILVER);
        gc.fillRect(0,0,1280,720);
    }

    /**
     * use this to draw an apple or a snake part
     * @param x the x-location of the point
     * @param y the y-location of the point
     * @param color the new color of the point
     */
    public void fillVirtualPixel(int x, int y, Color color)
    {
        int size = 30;

        gc.setFill(color);
        gc.fillRect(x * size, y * size, size, size);
    }

    public void updateScoreboard(String[] array)
    {
        ObservableList<String> items = FXCollections.observableArrayList();
        for(String s : array)
        {
            items.add(s);
        }
        scoreboard.setItems(items);
    }
}
