/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Views;

import PIDEV.Entities.User;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Emir
 */
public class FirstFrame extends Application {
    public static User user = null;
    public static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(root);
       primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setWidth(1300);
        primaryStage.setHeight(655);
        primaryStage.setScene(scene);
            
        
        primaryStage.show();
    }
    static public Stage getPrimaryStage() {
        return FirstFrame.primaryStage;
    }
    private void setPrimaryStage(Stage stage) {
        FirstFrame.primaryStage = stage;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
