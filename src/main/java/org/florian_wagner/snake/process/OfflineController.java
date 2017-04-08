package org.florian_wagner.snake.process;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Florian on 08.04.2017.
 */
public class OfflineController implements Initializable {

    @FXML private Canvas game_screen;

    @FXML private ColorPicker color_snake;

    @FXML private ColorPicker color_head;

    @FXML private TextField speed;

    @FXML private TitledPane settings;

    @FXML private VBox rootbox;

    @FXML private Text statuslabel;


    private GraphicsContext gc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = game_screen.getGraphicsContext2D();

        // set default settings
        speed.setText("10");
        color_snake.setValue(Color.web("#008aff"));
        color_head.setValue(Color.web("#1995fe"));

        speed.setFocusTraversable(false);
        color_snake.setFocusTraversable(false);
        color_head.setFocusTraversable(false);

        settings.setFocusTraversable(false);

        rootbox.requestFocus();

        statuslabel.setText("Neues Spiel: Enter");
    }

    /**
     * make the boxes (to select the color etc) disabled
     */
    public void disableSettings()
    {
        rootbox.requestFocus();
        speed.setDisable(true);
        color_snake.setDisable(true);
        color_head.setDisable(true);
    }

    /**
     * enable all the settings
     */
    public void enableSettings()
    {
        speed.setDisable(false);
        color_snake.setDisable(false);
        color_head.setDisable(false);
    }

    /**
     * change the text of the label below the settings box
     * @param text the text you wish to say
     */
    public void setStatusText(String text)
    {
        statuslabel.setText(text);
    }

    /**
     * resets the text below the settings box to the standard value
     */
    public void resetStatusText()
    {
        statuslabel.setText("Neues Spiel: Enter");
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

    /**
     * get the content from the speed setting. if the user entered an invalid value -> default value = 10
     * @return the speed
     */
    public int getSpeed()
    {
        int sp = 10;
        try
        {
            sp = Integer.parseInt(speed.getText());
        }catch(NumberFormatException e)
        {
            speed.setText("10");
        }
        return sp;
    }

    /**
     *
     * @return the snake color
     */
    public Color getColorSnake()
    {
        return color_snake.getValue();
    }

    /**
     *
     * @return the head color
     */
    public Color getColorHead()
    {
        return color_head.getValue();
    }

}
