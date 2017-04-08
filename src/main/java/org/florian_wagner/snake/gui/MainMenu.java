package org.florian_wagner.snake.gui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.florian_wagner.snake.process.MenuController;

import java.net.URL;

/**
 * Created by Florian on 03.04.2017.
 */
public class MainMenu extends Application {


    /**
     * JavaFX stuff
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL xml = getClass().getResource("/org/florian_wagner/snake/resources/menu.fxml");
        System.out.println(xml == null);
        FXMLLoader loader = new FXMLLoader(xml);
        MenuController controller = loader.getController();
        loader.setController(controller);
        Parent root = loader.load();

        int width = 400;
        int height = 400;

        // set icon
        primaryStage.getIcons().add(new Image("/org/florian_wagner/snake/resources/abikalypse.png"));

        Scene scene = new Scene(root, width,height);
        primaryStage.setTitle("Hauptmenü");
        primaryStage.setScene(scene);

        //make stage in center of the screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - width) / 2);
        primaryStage.setY((screenBounds.getHeight() - height) / 2);

        primaryStage.setResizable(false);
        primaryStage.show();
    }


}
