/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Views.FirstFrame;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
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
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author ons
 */
public class ProfilUserController implements Initializable {

    private Label membreDepuis;
    private Label nomUser;
    private Separator seperateProf;
    private Separator seperateList;
    private Separator seperateRev;
    private Separator seperateReservation;
    private Separator seperateFav;
    private Circle photoProfil;
    AnchorPane prof,res,pricing,profiles,widgets,controls,list,listrev;
    private AnchorPane main;
    @FXML
    private JFXTabPane tab;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image1;
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
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML
    private ImageView image5;
    @FXML
    private Label total;
    @FXML
    private Label qualitelabel;
    @FXML
    private Label servicelabel;
    @FXML
    private Label evaluation;
    @FXML
    private Label dejaevaluated;
    @FXML
    private AnchorPane panecom;
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextArea comment;
    @FXML
    private JFXButton AddReview;
    @FXML
    private Rating qualite;
    @FXML
    private Rating service;
    @FXML
    private GoogleMapView googleMapView;
    @FXML
    private Circle circleuser;
    @FXML
    private Label nomuser;
    @FXML
    private Label dateuser;
    @FXML
    private Label phoneuser;
    @FXML
    private Label addressuser;
    @FXML
    private Label dejanote;
    @FXML
    private JFXButton reserver;
    @FXML
    private JFXButton listReservation;
    @FXML
    private Label idEtab;
    @FXML
    private Tab tabX;
    @FXML
    private AnchorPane scroll;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        File file = new File("C:/wamp64/www/PIDEV/web/devis/"+FirstFrame.user.getDevis_name());
        try {
            
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            photoProfil.setFill(new ImagePattern(image));
            
        } catch (IOException ex) {
            Logger.getLogger(ProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] userName=FirstFrame.user.getName().split(" ", 0);
        String userNom="";
        for(int i=0; i< userName.length;i++){
            String s=userName[i].toUpperCase().substring(0,1)+""+userName[i].substring(1,userName[i].length());
            userNom=userNom+s;
            if(i!= userName.length){
                userNom=userNom;
            }
        }
        String[] userSurname=FirstFrame.user.getSurname().split(" ", 0);
        String userPrenom="";
        for(int i=0; i< userSurname.length;i++){
            String s = userSurname[i].toUpperCase().substring(0,1)+""+userSurname[i].substring(1,userSurname[i].length());
            userPrenom= userPrenom+s;
            if(i!= userSurname.length){
                userPrenom=userPrenom+" ";
            }
        }
        membreDepuis.setText("Member Since "+FirstFrame.user.getLast_login().toLocalDate().getDayOfMonth()+" "+FirstFrame.user.getLast_login().toLocalDate().getMonth()+" "+FirstFrame.user.getLast_login().toLocalDate().getYear());
        nomUser.setText(userNom+" "+userPrenom);
        
        
        try {
            list = FXMLLoader.load(getClass().getResource("../Views/MyListing.fxml"));
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

    private void reservation(MouseEvent event) {
        setNode(res);
        seperateProf.setStyle("");
        seperateFav.setStyle("");
        seperateList.setStyle("");
        seperateRev.setStyle("");
        seperateReservation.setStyle("-fx-background-color:  #ff214f;");
        
    }

    private void profile(MouseEvent event) {
        setNode(prof);
        seperateReservation.setStyle("");
        seperateProf.setStyle("-fx-background-color:  #ff214f;");
        seperateFav.setStyle("");
        seperateList.setStyle("");
        seperateRev.setStyle("");
    }

    private void closeWindow(MouseEvent event) {
        Platform.exit();
    }

   
        
    
    public void MyListingDeleted(Node node)
    {
        setNode(node);
        seperateReservation.setStyle("");
        seperateProf.setStyle("");
        seperateFav.setStyle("");
        seperateList.setStyle("-fx-background-color:  #ff214f;");
        seperateRev.setStyle("");
    }

    private void myListing(MouseEvent event) {
              setNode(list);
        seperateReservation.setStyle("");
        seperateProf.setStyle("");
        seperateFav.setStyle("");
        seperateList.setStyle("-fx-background-color:  #ff214f;");
        seperateRev.setStyle("");
    }

    private void MyReviews(MouseEvent event) {
          setNode(listrev);
        seperateReservation.setStyle("");
        seperateProf.setStyle("");
        seperateFav.setStyle("");
        seperateList.setStyle("");
        seperateRev.setStyle("-fx-background-color:  #ff214f;");
    }

    @FXML
    private void back(MouseEvent event) {
    }

    @FXML
    private void reserver(MouseEvent event) {
    }

    @FXML
    private void listReservationPage(MouseEvent event) {
    }


    
    
}
