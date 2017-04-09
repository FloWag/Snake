package org.florian_wagner.snake.gui;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Florian on 09.04.2017.
 */
public class MatchupGUI {


    public void start(Stage primaryStage) throws IOException {
        URL xml = getClass().getResource("/org/florian_wagner/snake/resources/matchup.fxml");
        System.out.println(xml == null);
        FXMLLoader loader = new FXMLLoader(xml);
        Parent root = loader.load();

        int width = 377;
        int height = 383;

        // set icon
        primaryStage.getIcons().add(new Image("/org/florian_wagner/snake/resources/abikalypse.png"));

        Scene scene = new Scene(root, width,height);
        primaryStage.setTitle("Mehrspieler Matchup");
        primaryStage.setScene(scene);

        //make stage in center of the screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - width) / 2);
        primaryStage.setY((screenBounds.getHeight() - height) / 2);

        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
