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
import org.florian_wagner.snake.process.OfflineController;
import org.florian_wagner.snake.game.OfflineGame;

import java.net.URL;

/**
 * Created by Florian on 08.04.2017.
 */
public class OfflineGUI {

    private OfflineController controller;

    /**
     * JavaFX stuff
     * @param primaryStage
     * @param keyEventHandler
     * @throws Exception
     */
    public void start(Stage primaryStage,OfflineGame keyEventHandler) throws Exception {
        URL xml = getClass().getResource("/org/florian_wagner/snake/resources/offlinegame.fxml");
        FXMLLoader loader = new FXMLLoader(xml);

        Parent root = loader.load();
        controller = loader.getController();

        int width = 1129;
        int height = 720;

        Scene scene = new Scene(root, width,height);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyEventHandler.handleKeyEvent(event);
            }
        });

        primaryStage.setTitle("Offline Spiel");
        primaryStage.setScene(scene);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - width) / 2);
        primaryStage.setY((screenBounds.getHeight() - height) / 2);
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                keyEventHandler.dispose(); // stop the thread
                System.exit(0);
            }
        });
    }

    public OfflineController getController() {
        return controller;
    }
}
