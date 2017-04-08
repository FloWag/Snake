package org.florian_wagner.snake.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.florian_wagner.snake.process.MenuController;

import java.net.URL;

/**
 * Created by Florian on 08.04.2017.
 */
public class OfflineGUI {

    public void start(Stage primaryStage) throws Exception {
        URL xml = getClass().getResource("/org/florian_wagner/snake/resources/offlinegame.fxml");
        System.out.println(xml == null);
        FXMLLoader loader = new FXMLLoader(xml);
        /*
        MenuController controller = loader.getController();
        loader.setController(controller);
        */
        Parent root = loader.load();


        Scene scene = new Scene(root, 1280,720);
        primaryStage.setTitle("Offline Game");
        primaryStage.setScene(scene);
        primaryStage.setX(120);
        primaryStage.setY(120);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
