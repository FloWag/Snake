package org.florian_wagner.snake.process;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Florian on 08.04.2017.
 */
public class OfflineController implements Initializable {

    @FXML private Canvas game_screen;

    private GraphicsContext gc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = game_screen.getGraphicsContext2D();
    }

    public void cleanGC()
    {
        gc.setFill(Color.SILVER);
        gc.fillRect(0,0,1280,720);
    }

    public void fillVirtualPixel(int x, int y, Color color)
    {
        int size = 30;

        gc.setFill(color);
        gc.fillRect(x * size, y * size, size, size);
    }

}
