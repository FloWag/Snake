package org.florian_wagner.snake.gui;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.florian_wagner.snake.game.OnlineGame;
import org.florian_wagner.snake.process.OnlineController;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Florian on 09.04.2017.
 */
public class OnlineGUI {

    private OnlineController controller;

    /**
     * JavaFX stuff
     * @param primaryStage
     * @param keyEventHandler
     * @throws IOException
     */
    public void start(Stage primaryStage, OnlineGame keyEventHandler) throws IOException {
        URL xml = getClass().getResource("/org/florian_wagner/snake/resources/onlinegame.fxml");
        System.out.println(xml == null);
        FXMLLoader loader = new FXMLLoader(xml);

        Parent root = loader.load();
        controller = loader.getController();

        int width = 1129;
        int height = 720;

        Scene scene = new Scene(root, width,height);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("Ja");
                keyEventHandler.handleKeyEvent(event);
            }
        });

        primaryStage.setTitle("Online Spiel");
        primaryStage.setScene(scene);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - width) / 2);
        primaryStage.setY((screenBounds.getHeight() - height) / 2);
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("hi man");
                System.exit(0);
            }
        });

    }

    public OnlineController getController() {
        return controller;
    }
}
