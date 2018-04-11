/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import static PIDEV.Controllers.AjoutrestoController.saveToFileImageNormal;
import static PIDEV.Controllers.AjoutrestoController.saveToFileVich;
import PIDEV.Entities.Categorie;
import PIDEV.Entities.Review;
import PIDEV.Entities.SousCategorie;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import PIDEV.Entities.Etablissement;
import PIDEV.Services.AddEtablissement;
import PIDEV.Services.GestionReviews;
import PIDEV.Services.ListEtablissement;
import PIDEV.Utils.MyConnexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class ProfilrestoController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private Label description;
    @FXML
    private JFXCheckBox parking;
    @FXML
    private JFXCheckBox cartecredit;
    @FXML
    private JFXCheckBox chaiseroulante;
    @FXML
    private JFXCheckBox fumer;
    @FXML
    private JFXCheckBox alcool;
    @FXML
    private JFXCheckBox terasse;
    @FXML
    private JFXCheckBox wifi;
    @FXML
    private JFXCheckBox animaux;
    @FXML
    private JFXCheckBox livraison;
    @FXML
    private JFXCheckBox reservation;
    @FXML
    private JFXCheckBox climatisation;
    @FXML
    private Label lundisamedio;
    @FXML
    private Label lundisamedif;
    @FXML
    private Label dimancheo;
    @FXML
    private Label dimanchef;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML
    private ImageView image5;
    @FXML
    private JFXTextField service;
    @FXML
    private JFXTextField qualite;
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextArea comment;
    @FXML
    private JFXButton AddReview;
    @FXML
    private JFXButton reserver;
    @FXML
    private JFXButton listReservation;
    @FXML
    private Label idEtab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setIdEtab(int id) {
        this.idEtab.setText(String.valueOf(id));
    }

    public void RestoProfil(int id) {
        ListEtablissement s = new ListEtablissement();
        Etablissement etab = s.ListRestaurant().filtered(e -> e.getId() == id).get(0);
        description.setText(etab.getDescription());
        name.setText(etab.getName());
        address.setText(etab.getAddress());
        phone.setText(etab.getPhone());
        email.setText(etab.getEmail());
        lundisamedio.setText(etab.getLundisamedio());
        lundisamedif.setText(etab.getLundisamedif());
        dimancheo.setText(etab.getDimancheo());
        dimanchef.setText(etab.getDimanchef());
        parking.setSelected(etab.isParking());
        cartecredit.setSelected(etab.isCartecredit());
        chaiseroulante.setSelected(etab.isChaiseroulante());
        fumer.setSelected(etab.isFumer());
        alcool.setSelected(etab.isAlcool());
        terasse.setSelected(etab.isTerasse());
        wifi.setSelected(etab.isWifi());
        animaux.setSelected(etab.isAnimaux());
        reservation.setSelected(etab.isReservations());
        climatisation.setSelected(etab.isClimatisation());
        livraison.setSelected(etab.isLivraison());

        final String imageURI = new File("C://wamp64/www/PIDEV/web/uploads/images/" + etab.getImg1()).toURI().toString();
        image2.setImage(new Image(imageURI));

        final String imageURI2 = new File("C://wamp64/www/PIDEV/web/uploads/images/" + etab.getImg2()).toURI().toString();
        image3.setImage(new Image(imageURI2));

        final String imageURI3 = new File("C://wamp64/www/PIDEV/web/uploads/images/" + etab.getImg3()).toURI().toString();
        image4.setImage(new Image(imageURI3));

        final String imageURI4 = new File("C://wamp64/www/PIDEV/web/devis/" + etab.getDevis_name()).toURI().toString();
        image1.setImage(new Image(imageURI4));

        image5.setImage(new Image(imageURI));

        AddReview.setOnMouseClicked((event) -> {
            GestionReviews gr = new GestionReviews();
            Review R = new Review(comment.getText(), Double.parseDouble(service.getText()), Double.parseDouble(qualite.getText()), titre.getText(), etab);

            try {
                gr.AddReview(R);
                Alert alert = new Alert(AlertType.CONFIRMATION);

                alert.setTitle("Evaluation de " + etab.getName());
                alert.setHeaderText(null);
                alert.setContentText("Merci de noter " + etab.getName());
                alert.showAndWait();

            } catch (SQLException ex) {
                Logger.getLogger(ProfilrestoController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

    @FXML
    private void reserver(MouseEvent event) throws IOException {
        //Platform.exit();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/AddReservationResto.fxml"));
        Parent root = loader.load();
        
        AddReservationRestoController addRes = loader.getController();
        
        addRes.setIdEtab(Integer.parseInt(idEtab.getText()));
        
        reserver.getScene().setRoot(root);
    }

    @FXML
    private void listReservationPage(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ListReservationResto.fxml"));
        Parent root = loader.load();
        ListEtablissement le = new ListEtablissement();
        ListReservationRestoController lrsc = loader.getController();
        Etablissement etab = le.ListRestaurant().filtered(e -> e.getId() == Integer.parseInt(idEtab.getText())).get(0);
        lrsc.listReservation(etab);
        
        name.getScene().setRoot(root);
    }

}
