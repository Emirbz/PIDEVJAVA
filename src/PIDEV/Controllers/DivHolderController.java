/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Deal;
import PIDEV.Services.ListDealsService;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Skan
 */
public class DivHolderController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Label nom;
    @FXML
    private ImageView img;
    @FXML
    private Label desc;
    @FXML
    private Label prix;
    @FXML
    private JFXButton details;
    @FXML
    private Label id;
    @FXML
    private Label oldprix;
    @FXML
    private HBox rt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void LoadValues(Deal d) {
        nom.setText(d.getNom());
        desc.setText(d.getDescription());
        desc.setPrefWidth(100);
        prix.setText(String.valueOf(d.getNewprix()) + "Dt");
        oldprix.setText(String.valueOf(d.getOldprix()) + "Dt");
        id.setText(String.valueOf(d.getId()));
        final String imageURI = new File("C:/wamp64/www/PIDEV/web/devis/" + d.getDevisName()).toURI().toString();
        img.setImage(new Image(imageURI));
        img.setPreserveRatio(false);
        rt.getChildren().add(new Label("Rating : "));
        for (int i = 0; i < d.getRating(); i++) {
            FontAwesomeIconView star = new FontAwesomeIconView(FontAwesomeIcon.STAR);
            star.setGlyphStyle("-glyph-size:20;-fx-fill:gold");
            rt.getChildren().add(star);
        }
        details.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    ListDealsService LDC = new ListDealsService();
                    Deal selecteddeal = LDC.ListerDeals().filtered(e -> e.getId() == Integer.parseInt(id.getText())).get(0);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/DealDetails.fxml"));
                    Parent root = loader.load();
                    DealDetailsController DDC = loader.getController();
                    DDC.DealProfil(selecteddeal.getId());
                    pane.getScene().setRoot(root);
                } catch (IOException | SQLException ex) {
                    Logger.getLogger(DivHolderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
