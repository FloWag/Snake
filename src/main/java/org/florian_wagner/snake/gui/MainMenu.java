package org.florian_wagner.snake.gui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created by Florian on 03.04.2017.
 */
public class MainMenu extends Application {


    public MainMenu()
    {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL xml = getClass().getResource("/org/florian_wagner/snake/resources/menu.fxml");
        System.out.println(xml == null);
        FXMLLoader loader = new FXMLLoader(xml);
        Parent root = loader.load();
        Scene scene = new Scene(root, 400,600);
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(scene);
        primaryStage.setX(120);
        primaryStage.setY(120);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


}
