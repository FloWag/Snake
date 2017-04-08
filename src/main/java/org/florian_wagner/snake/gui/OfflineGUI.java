package org.florian_wagner.snake.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.florian_wagner.snake.process.MenuController;
import org.florian_wagner.snake.process.OfflineController;
import org.florian_wagner.snake.process.OfflineGame;

import java.net.URL;

/**
 * Created by Florian on 08.04.2017.
 */
public class OfflineGUI {

    private OfflineController controller;

    public void start(Stage primaryStage,OfflineGame keyEventHandler) throws Exception {
        URL xml = getClass().getResource("/org/florian_wagner/snake/resources/offlinegame.fxml");
        System.out.println(xml == null);
        FXMLLoader loader = new FXMLLoader(xml);

        Parent root = loader.load();
        controller = loader.getController();

        Scene scene = new Scene(root, 1280,720);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyEventHandler.handleKeyEvent(event);
            }
        });
        primaryStage.setTitle("Offline Game");
        primaryStage.setScene(scene);
        primaryStage.setX(120);
        primaryStage.setY(120);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public OfflineController getController() {
        return controller;
    }
}
