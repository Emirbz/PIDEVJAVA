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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Rating;

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
    @FXML
    JFXButton edit;
    AnchorPane myfavs,prof,res,pricing,profiles,widgets,controls,list,listrev;
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
    @FXML
    private FontAwesomeIconView Home;
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
           myfavs = FXMLLoader.load(getClass().getResource("../Views/MyFavoris.fxml"));
           prof = FXMLLoader.load(getClass().getResource("../Views/profileInfoUser.fxml"));
//        edit = FXMLLoader.load(getClass().getResource("../Views/EditProfile.fxml"));
           listrev = FXMLLoader.load(getClass().getResource("../Views/MyReviews.fxml"));
//             prof = FXMLLoader.load(getClass().getResource("../Views/profileInfoUser.fxml"));
//             prof = FXMLLoader.load(getClass().getResource("../Views/profileInfoUser.fxml"));
            setNode(prof);
            seperateProf.setStyle("-fx-background-color:  #ff214f;");
        } catch (IOException ex) {
            Logger.getLogger(ProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/EditProfile.fxml"));
//            Parent root = loader.load();
//            EditProfileController ep = loader.getController();
//            ep.getAjouter().setOnMouseClicked((event) -> {
//            
//           
//            });
//            AnchorPane profilex = FXMLLoader.load(getClass().getResource("../Views/ProfilUser.fxml"));
//              FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../Views/HomePage.fxml"));
//            Parent root2 = loader2.load();
//            HomePageController hc = loader2.getController();
//            hc.setNode(profilex);
//            
//            
//           
//            membreDepuis.getScene().setRoot(root);


edit.setOnMouseClicked((event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/EditProfile.fxml"));
                Parent root = loader.load();
                EditProfileController ec = loader.getController();
                
                AnchorPane r = new AnchorPane(root);
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Modifier user");
                stage.setScene(new Scene(r));
                stage.show();
                ec.getAjouter().setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        
                        stage.close();
                        initialize(url, rb);
                        
                    }
                });
            } catch (IOException ex) {
                Logger.getLogger(ProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
});
       
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
    private void reservation(MouseEvent event) throws IOException {
     res = FXMLLoader.load(getClass().getResource("../Views/ReservationProfilUser.fxml"));
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

   
        
    
    public void MyListingDeleted(Node node)
    {
        setNode(node);
        seperateReservation.setStyle("");
        seperateProf.setStyle("");
        seperateFav.setStyle("");
        seperateList.setStyle("-fx-background-color:  #ff214f;");
        seperateRev.setStyle("");
    }

    @FXML
    private void myListing(MouseEvent event) {
              setNode(list);
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

    @FXML
    private void MyFavs(ActionEvent event) {
        setNode(myfavs);
        seperateReservation.setStyle("");
        seperateProf.setStyle("");
        seperateFav.setStyle("-fx-background-color:  #ff214f;");
        seperateList.setStyle("");
        seperateRev.setStyle("");
        
    }

    @FXML
    private void Home(MouseEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/HomePage.fxml"));
                Parent root = loader.load();
                HomePageController ec = loader.getController();
               edit.getScene().setRoot(root);
    }




    
    
}
