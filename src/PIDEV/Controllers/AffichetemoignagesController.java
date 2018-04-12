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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import PIDEV.Services.Edittemoignages;
import PIDEV.Services.Listetemoignages;
import PIDEV.Services.deleteevent;
import PIDEV.Services.editevent;
import PIDEV.Services.listeevent;
import PIDEV.Entities.events;
import PIDEV.Entities.temoignages;

/**
 * FXML Controller class
 *
 * @author dahem
 */
public class AffichetemoignagesController implements Initializable {

    ObservableList<temoignages> listtem;
    @FXML
    private ScrollPane scrollp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            Listetemoignages lt = new Listetemoignages();
            listtem = lt.Listetemoignages();
            TilePane b = new TilePane();
            b.setPadding(new javafx.geometry.Insets(30));
            TilePane c = new TilePane();
            System.out.println(listtem);
            
            for (temoignages d : listtem) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Listetemoignages.fxml"));
                    Parent root = (Pane) loader.load();
                    ListetemoignagesController ltc = loader.getController();
                    ltc.loadValues(d);
                    c.setHgap(15);
                    c.getChildren().removeAll();
                    c.setPadding(new javafx.geometry.Insets(10, 50, 0, 50));
                    c.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(AffichetemoignagesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            c.setPrefColumns(3);
            c.setPadding(new javafx.geometry.Insets(50));
            c.setHgap(40);
            c.setVgap(40);
            b.getChildren().add(c);
            b.setPrefWidth(1000);
            scrollp.setContent(b);
        } catch (SQLException ex) {
            Logger.getLogger(AffichetemoignagesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     



}
