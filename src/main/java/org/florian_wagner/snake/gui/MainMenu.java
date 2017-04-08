package org.florian_wagner.snake.gui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.florian_wagner.snake.process.MenuController;

import java.net.URL;

/**
 * Created by Florian on 03.04.2017.
 */
public class MainMenu extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        URL xml = getClass().getResource("/org/florian_wagner/snake/resources/menu.fxml");
        System.out.println(xml == null);
        FXMLLoader loader = new FXMLLoader(xml);
        MenuController controller = loader.getController();
        loader.setController(controller);
        Parent root = loader.load();


        Scene scene = new Scene(root, 400,400);
        primaryStage.setTitle("Hauptmenü");
        primaryStage.setScene(scene);
        primaryStage.setX(120);
        primaryStage.setY(120);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


}
