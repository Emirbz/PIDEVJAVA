/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import PIDEV.Services.Edittemoignages;
import PIDEV.Services.deleteevent;
import PIDEV.Services.editevent;
import PIDEV.Entities.temoignages;

/**
 * FXML Controller class
 *
 * @author dahem
 */
public class ListetemoignagesController implements Initializable {
    
    @FXML
    private Circle imguser;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    Label id;
    @FXML
    private Label date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void loadValues(temoignages t) {
        date.setText(t.getDatetemoignage().toString());
        id.setText(String.valueOf(t.getId()));
        title.setText(t.getTitre());
        description.setText(t.getDescription());
        
       Image imageURI = new Image("file:C:/wamp64/www/PIDEV/web/devis/" + t.getIduser().getDevis_name());
        imguser.setFill(new ImagePattern(imageURI));
        title.setOnMouseClicked((event) -> {
            modifier.setVisible(true);
            modifier.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Edittemoignages.fxml"));
                        Parent root = loader.load();
                        Edittemoignages et = new Edittemoignages();
                        EdittemoignagesController ec = loader.getController();
                        ec.getoldvalues(t);
                        ScrollPane r = new ScrollPane(root);
                        Stage stage = new Stage(StageStyle.DECORATED);
                        stage.setTitle("Modifier témoignages");
                        stage.setScene(new Scene(r));
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ListetemoignagesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            supprimer.setVisible(true);
            supprimer.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        deleteevent de = new deleteevent();
                        de.delete(t.getId());
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/affichetemoignages.fxml"));
                        Parent root = loader.load();
                        description.getScene().setRoot(root);
                    } catch (SQLException | IOException ex) {
                        Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });
            
        });
    }
}
