/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Etablissement;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class DivListHotelController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private Pane sq;
    @FXML
    private Label name;
    @FXML
    private Label souscat;
    @FXML
    private FontAwesomeIconView souscaticon;
    @FXML
    private Label id;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Rating etoile;
    
    @FXML
    private Label prixmoy;
    @FXML
    private FontAwesomeIconView souscaticon1;
    @FXML
    private Label nbrchambre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
      public void LoadValues(Etablissement e) throws IOException {
        name.setText(e.getName());
        
        souscat.setText(e.getSouscat().getNom());
        etoile.setRating(Double.valueOf(e.getEtoile()));

        prixmoy.setText(String.valueOf(e.getPrixmoy())+" DT");
        id.setText(String.valueOf(e.getId()));
        nbrchambre.setText(String.valueOf(e.getNbrchambre()));
        HBox qu = new HBox();
        Label lq = new Label("Qualite :");
        lq.setStyle("-fx-text-fill:#73879c;-fx-font-weight: bold;-fx-font-size:16px");
        qu.getChildren().add(lq);
        for (int i = 1; i <= Integer.parseInt(String.valueOf(e.getMoyqualite()).substring(0, 1)); i++) {
            FontAwesomeIconView starq = new FontAwesomeIconView(FontAwesomeIcon.STAR);
            starq.setStyle("-glyph-size:20;-fx-fill:#FFD700");
            qu.getChildren().add(starq);

        }

        HBox se = new HBox();
        Label ls = new Label("Service :");
        ls.setStyle("-fx-text-fill:#73879c;-fx-font-weight: bold;-fx-font-size:16px");
        se.getChildren().add(ls);
        for (int i = 1; i <= Integer.parseInt(String.valueOf(e.getMoyservice()).substring(0, 1)); i++) {
            FontAwesomeIconView stars = new FontAwesomeIconView(FontAwesomeIcon.STAR);
            stars.setStyle("-glyph-size:20;-fx-fill:#FFD700");
            se.getChildren().add(stars);
        }
        sq.getChildren().add(new HBox(15, qu, se));
//        sq.setPadding(new Insets(-10, -10, -10, -10));

        Image imageURI = new Image("file:C:/wamp64/www/PIDEV/web/devis/" + e.getDevis_name());
//        circle.setFill(new ImagePattern(imageURI));
//       qualite.setRating(e.getMoyqualite());
        Image imageURI2 = new Image("file:C://wamp64/www/PIDEV/web/uploads/images/" + e.getImg1());
        rectangle.setFill(new ImagePattern(imageURI2));
      
        ap.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    doubleclick(event, e);
                }

            }
        });
    

      }
        public void doubleclick(MouseEvent event, Etablissement selectedetab) {
        if (event.getClickCount() == 2) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfilHotel.fxml"));
                Parent root = loader.load();
                ProfilHotelController DDC = loader.getController();
                DDC.Reviewslist(selectedetab);
                DDC.RestoProfil(selectedetab.getId());
                FXMLLoader loade = new FXMLLoader(getClass().getResource("../Views/HomePage.fxml"));
                Parent roo = loade.load();
                HomePageController Dhp = loade.getController();
                Stage ss=new Stage();
                Scene sc = new Scene(root);
                ss.setScene(sc);
                ss.setWidth(1288);
                ss.setHeight(750);
                
                
                ss.show();

            } catch (IOException | SQLException ex) {
                Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
