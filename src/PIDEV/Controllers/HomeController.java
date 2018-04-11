/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import PIDEV.Views.FirstFrame;
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
   
    @FXML
    private AnchorPane home;
    @FXML
    private Label idUsername;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idUsername.setText(FirstFrame.user.getName());
    }  
    @FXML
    public void ajout() throws IOException
    {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/AjoutResto.fxml"));
            Parent root = loader.load();
            ajout.getScene().setRoot(root);
    }
     @FXML
    public void list() throws IOException
    {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ListResto.fxml"));
            Parent root = loader.load();
             
            ajout.getScene().setRoot(root);
    }
   
    public void setUser(String name)throws InvocationTargetException{
        this.idUsername.setText(name);
    }
}
