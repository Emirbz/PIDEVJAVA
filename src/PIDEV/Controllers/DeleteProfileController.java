/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Services.UserService;
import PIDEV.Views.FirstFrame;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import PIDEV.Views.FirstFrame;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author ons
 */
public class DeleteProfileController implements Initializable {

    @FXML
    private JFXButton yesButton;
    @FXML
    private JFXButton noButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void deleteAction(ActionEvent event) throws SQLException, IOException, Exception {
        UserService userService = new UserService();   
        userService.deleteUser(FirstFrame.user);
               

        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/HomePage.fxml"));
        AnchorPane accueil =FXMLLoader.load(getClass().getResource("../Views/Accueil.fxml"));
        Parent root = loader.load();
        HomePageController hp=loader.getController();
        hp.setNode(accueil);
        

        
       
               
           
    }

    @FXML
    private void closeindow(ActionEvent event) {
       Stage stage = (Stage) yesButton.getScene().getWindow();
       stage.close();
    }
    
}
