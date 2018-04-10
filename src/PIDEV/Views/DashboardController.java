/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Views;

import PIDEV.Entities.Etablissement;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import PIDEV.Controllers.ProfilrestoController;
import java.sql.SQLException;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class DashboardController implements Initializable {

    @FXML
    private JFXButton btnContacts;
    @FXML
    private JFXButton btnWidgets;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnAlerts;
    @FXML
    private JFXButton btnControls;
    @FXML
    private AnchorPane holderPane;
    AnchorPane list,ajout;
   
           
           
            
       
    
    
    @FXML
    private JFXButton btnAjout;
    @FXML
    private JFXButton btnList;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
             
           
        
    } 
  
    public void setNode(Node node) throws IOException, SQLException {
        holderPane.getChildren().clear();
       
       
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(750));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void ajout(ActionEvent event) throws IOException, SQLException {
        ajout = FXMLLoader.load(getClass().getResource("AjoutResto.fxml"));
           
        setNode(ajout);
    }

    @FXML
    private void list(ActionEvent event) throws IOException, SQLException {
         list = FXMLLoader.load(getClass().getResource("ListResto.fxml"));
        setNode(list);
    }
    @FXML
   public void hiplease ()
    {
        System.out.println("hi");
    }

   
}
