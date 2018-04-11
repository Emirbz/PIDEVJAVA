/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Views.FirstFrame;
import com.jfoenix.controls.JFXButton;
import com.sun.javafx.scene.NodeHelper;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;

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
    private JFXButton myListings;
    @FXML
    private JFXButton reviews;
    @FXML
    private JFXButton profile;
    @FXML
    private JFXButton reservations;
    @FXML
    private JFXButton favoris;
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
    AnchorPane prof,res,edit,profiles,widgets,controls;
    @FXML
    private AnchorPane main;
    @FXML
    private FontAwesomeIconView close;
    @FXML
    private JFXButton editProfileInProfile;
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
        membreDepuis.setText("Member Since "+FirstFrame.user.getDate().toLocalDate().getDayOfMonth()+" "+FirstFrame.user.getDate().toLocalDate().getMonth()+" "+FirstFrame.user.getDate().toLocalDate().getYear());
        nomUser.setText(userNom+" "+userPrenom);
        
        
        try {
            prof = FXMLLoader.load(getClass().getResource("../Views/profileInfoUser.fxml"));
            res = FXMLLoader.load(getClass().getResource("../Views/ReservationProfilUser.fxml"));
             edit = FXMLLoader.load(getClass().getResource("../Views/EditProfile.fxml"));
//             prof = FXMLLoader.load(getClass().getResource("../Views/profileInfoUser.fxml"));
//             prof = FXMLLoader.load(getClass().getResource("../Views/profileInfoUser.fxml"));
            setNode(res);
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

    @FXML
    private void EditProfileUser(ActionEvent event) {
        setNode(edit);
        seperateProf.setStyle("");
        seperateFav.setStyle("");
        seperateList.setStyle("");
        seperateRev.setStyle("");
        seperateReservation.setStyle("");
    }


    
    
}
