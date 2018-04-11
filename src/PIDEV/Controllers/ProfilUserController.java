/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Deal;
import PIDEV.Services.ListDealsService;
import PIDEV.Views.FirstFrame;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author ons
 */
public class ProfilUserController implements Initializable {

    @FXML
    private Label membreDepuis;
    @FXML
    private Label nomUser;
    @FXML
    private Separator seperateProf;
    @FXML
    private Separator seperateList;
    @FXML
    private Separator seperateRev;
    @FXML
    private Separator seperateReservation;
    @FXML
    private Separator seperateFav;
    @FXML
    private Circle photoProfil;
    AnchorPane prof, res, pricing, profiles, widgets, controls, list, listrev, deal;
    @FXML
    private AnchorPane main;
    @FXML
    private FontAwesomeIconView close;
    @FXML
    private JFXButton myListings;
    @FXML
    private JFXButton reviews;
    @FXML
    private JFXButton profile;
    @FXML
    private JFXButton reservations;
    @FXML
    private JFXButton favoris;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        File file = new File("C:/wamp64/www/PIDEV/web/devis/" + FirstFrame.user.getDevis_name());
        try {

            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            photoProfil.setFill(new ImagePattern(image));

        } catch (IOException ex) {
            Logger.getLogger(ProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] userName = FirstFrame.user.getName().split(" ", 0);
        String userNom = "";
        for (int i = 0; i < userName.length; i++) {
            String s = userName[i].toUpperCase().substring(0, 1) + "" + userName[i].substring(1, userName[i].length());
            userNom = userNom + s;
            if (i != userName.length) {
                userNom = userNom;
            }
        }
        String[] userSurname = FirstFrame.user.getSurname().split(" ", 0);
        String userPrenom = "";
        for (int i = 0; i < userSurname.length; i++) {
            String s = userSurname[i].toUpperCase().substring(0, 1) + "" + userSurname[i].substring(1, userSurname[i].length());
            userPrenom = userPrenom + s;
            if (i != userSurname.length) {
                userPrenom = userPrenom + " ";
            }
        }
        membreDepuis.setText("Member Since " + FirstFrame.user.getLast_login().toLocalDate().getDayOfMonth() + " " + FirstFrame.user.getLast_login().toLocalDate().getMonth() + " " + FirstFrame.user.getLast_login().toLocalDate().getYear());
        nomUser.setText(userNom + " " + userPrenom);

        try {
            deal = FXMLLoader.load(getClass().getResource("../Views/DivDeal.fxml"));
            prof = FXMLLoader.load(getClass().getResource("../Views/profileInfoUser.fxml"));
            res = FXMLLoader.load(getClass().getResource("../Views/ReservationProfilUser.fxml"));
            listrev = FXMLLoader.load(getClass().getResource("../Views/MyReviews.fxml"));
//             prof = FXMLLoader.load(getClass().getResource("../Views/profileInfoUser.fxml"));
//             prof = FXMLLoader.load(getClass().getResource("../Views/profileInfoUser.fxml"));
            setNode(prof);
            seperateProf.setStyle("-fx-background-color:  #ff214f;");
        } catch (IOException ex) {
            Logger.getLogger(ProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setNode(Node node) {
        main.getChildren().clear();
        main.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void reservation(MouseEvent event) {
        setNode(res);
        seperateProf.setStyle("");
        seperateFav.setStyle("");
        seperateList.setStyle("");
        seperateRev.setStyle("");
        seperateReservation.setStyle("-fx-background-color:  #ff214f;");

    }

    @FXML
    private void profile(MouseEvent event) {
        setNode(prof);
        seperateReservation.setStyle("");
        seperateProf.setStyle("-fx-background-color:  #ff214f;");
        seperateFav.setStyle("");
        seperateList.setStyle("");
        seperateRev.setStyle("");
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        Platform.exit();
    }

    public void MyListingDeleted(Node node) {
        setNode(node);
        seperateReservation.setStyle("");
        seperateProf.setStyle("");
        seperateFav.setStyle("");
        seperateList.setStyle("-fx-background-color:  #ff214f;");
        seperateRev.setStyle("");
    }

    @FXML
    private void myListing(MouseEvent event) {
        setNode(deal);
        seperateReservation.setStyle("");
        seperateProf.setStyle("");
        seperateFav.setStyle("");
        seperateList.setStyle("-fx-background-color:  #ff214f;");
        seperateRev.setStyle("");
    }

    @FXML
    private void MyReviews(MouseEvent event) {
        setNode(listrev);
        seperateReservation.setStyle("");
        seperateProf.setStyle("");
        seperateFav.setStyle("");
        seperateList.setStyle("");
        seperateRev.setStyle("-fx-background-color:  #ff214f;");
    }

}
