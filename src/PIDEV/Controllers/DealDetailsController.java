/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Deal;
import PIDEV.Services.DealDetailsService;
import PIDEV.Services.ListDealsService;
import PIDEV.Services.UpdateDealService;
import PIDEV.Views.FirstFrame;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Skan
 */
public class DealDetailsController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private HBox rating;
    @FXML
    private ImageView img;
    @FXML
    private Label jourrestants;
    @FXML
    private Label nbrparticipants;
    @FXML
    private Label username;
    @FXML
    private Label tel;
    @FXML
    private Label adr;
    @FXML
    private Label reg;
    @FXML
    private Label jourrestants1;
    @FXML
    private JFXButton achat;
    @FXML
    private Label nbrachat;
    @FXML
    private Circle imguser;
    @FXML
    private StackPane note;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void DealProfil(int id) throws SQLException {
        rating.getChildren().clear();
        ListDealsService LDC = new ListDealsService();
        Deal d = LDC.ListerDeals().filtered(e -> e.getId() == id).get(0);
        description.setText(d.getDescription());
        nom.setText(d.getNom());
        adr.setText(d.getAdresse());
        description.setText(d.getDescription());
        final String imageURI = new File("C:/wamp64/www/PIDEV/web/devis/" + d.getDevisName()).toURI().toString();
        System.out.println(d.getDevisName());
        System.out.println(imageURI);
        img.setImage(new Image(imageURI));
        img.setPreserveRatio(false);
        for (int i = 0; i < d.getRating(); i++) {
            FontAwesomeIconView star = new FontAwesomeIconView(FontAwesomeIcon.STAR);
            star.setStyle("-glyph-size:20;-fx-fill:#FFD700");
            rating.getChildren().add(star);
        }
        DealDetailsService DDS = new DealDetailsService();
        nbrachat.setText(String.valueOf(DDS.NbrusersAchat(id)));
        nbrparticipants.setText(String.valueOf(d.getPlacesdispo()));
        Date dd = new Date();
        if ((d.getDatefin().getTime() - dd.getTime()) / 1000 / 60 / 60 / 24 > 0) {
            jourrestants1.setVisible(false);
            jourrestants.setText(String.valueOf((d.getDatefin().getTime() - dd.getTime()) / 1000 / 60 / 60 / 24));
        } else {
            jourrestants.setVisible(false);
            jourrestants1.setText("Deal expiré");
            achat.setDisable(true);
        }
        tel.setText(d.getIduser().getPhone());
        final String userimage = new File("C:/wamp64/www/PIDEV/web/devis/" + d.getIduser().getDevis_name()).toURI().toString();
        imguser.setFill(new ImagePattern(new Image(userimage)));
        username.setText(d.getIduser().getName() + " " + d.getIduser().getSurname());
        reg.setText(d.getRegion());
        UpdateDealService UDS = new UpdateDealService();
        if (!(FirstFrame.user == null)) {
            achat.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        UDS.achat(d);
                        achat.setDisable(true);
                        DealProfil(d.getId());
                    } catch (SQLException ex) {
                        Logger.getLogger(DealDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            if (UDS.testachat(FirstFrame.user.getId(), id) == false) {
                achat.setDisable(true);
            }
        } else {
            achat.setDisable(true);
        }
        rating.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    if (DDS.testrating(id, FirstFrame.user.getId())) {
                        JFXDialogLayout dialogLayout = new JFXDialogLayout();
                        Text title = new Text("Noter un Deal");
                        dialogLayout.setHeading(title);
                        FXMLLoader loade = new FXMLLoader(getClass().getResource("../Views/AddClaim.fxml"));
                        Parent pe = (StackPane) loade.load();
                        dialogLayout.setBody(pe);
                        JFXDialog dialog = new JFXDialog(note, dialogLayout, JFXDialog.DialogTransition.CENTER);
                        dialogLayout.autosize();
                        dialog.show();
                        dialog.autosize();
                        note.setVisible(true);
                        note.toFront();
                        ap.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                dialog.close();
                                note.toBack();
                            }
                        });
                    }
                } catch (IOException | SQLException ex) {
                    Logger.getLogger(DealDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
}
