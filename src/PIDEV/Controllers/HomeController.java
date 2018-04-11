/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Views.FirstFrame;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class HomeController implements Initializable {

    @FXML
    private JFXButton ajout;
    @FXML
    private JFXButton list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    @FXML
    public void ajout() throws IOException
    {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/AjoutResto.fxml"));
             Parent root = loader.load();
                        Stage stage = (Stage) ajout.getScene().getWindow();
                        stage.close();
                        Scene sc=new Scene(root);
                        Stage stage1=new Stage(StageStyle.DECORATED);
                        stage1.setScene(sc);
                        stage1.show();
            
    }
     @FXML
    public void list() throws IOException
    {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ListResto.fxml"));
            
                        
                        
                        Parent root = loader.load();
                        Stage stage = (Stage) ajout.getScene().getWindow();
                        stage.close();
                        Scene sc=new Scene(root);
                        Stage stage1=new Stage(StageStyle.DECORATED);
                        stage1.setScene(sc);
                        stage1.show();
    }
}
