/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import static PIDEV.Controllers.AddUserController.saveToFile;
import PIDEV.Entities.Reservation;
import PIDEV.Entities.User;
import PIDEV.Services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import PIDEV.Views.FirstFrame;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
/**
 * FXML Controller class
 *
 * @author ons
 */
public class EditProfileController implements Initializable {

    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField surname;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField facebook;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXComboBox<String> role;
    @FXML
    private JFXButton image;
    @FXML
    private Circle imageIn;
    @FXML
    private JFXButton ajouter;

    public JFXButton getAjouter() {
        return ajouter;
    }

    public void setAjouter(JFXButton ajouter) {
        this.ajouter = ajouter;
    }
    @FXML
    private ImageView pic;
    @FXML
    private JFXSpinner spinner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User user = FirstFrame.user;
  final String imageURI4 = new File("C://wamp64/www/PIDEV/web/devis/" + user.getDevis_name()).toURI().toString();
       pic.setImage(new Image(imageURI4));
   
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());
        address.setText(user.getAddress());
        String roleUser ;
        if("pro".equals(user.getRole())){
              roleUser="Professionnel"  ;
         }else{
              roleUser="Utilisateur Simple"; 
            }
         role.getItems().addAll("Professionnel", "Utilisateur Simple");
        role.setValue(roleUser);
        facebook.setText(user.getFacebook());
        surname.setText(user.getSurname());
        name.setText(user.getName());
        File file=new File("c:/wamp64/www/pidev/web/devis/"+user.getDevis_name());
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageIn.setFill(new ImagePattern(image));
        } catch (IOException ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }    

    @FXML
    private void addImage(ActionEvent event) {
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image2 = SwingFXUtils.toFXImage(bufferedImage, null);
            imageIn.setFill(new ImagePattern(image2));
            
             pic.setImage(image2);
        } catch (IOException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

     public static String saveToFile(Image image) {

        String ext = "jpg";
        File dir = new File("C:/wamp64/www/PIDEV/web/devis");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    }

    @FXML
    private void editUser(ActionEvent event) throws IOException {
        ajouter.setVisible(false);
        spinner.setVisible(true);
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(2));
        pauseTransition.setOnFinished(ev -> {
            spinner.setVisible(false);
            Image image3 = pic.getImage();
            String nameImage = saveToFile(image3);
            String roleUser;
            if(role.getValue()=="Professionnel"){
                roleUser = "pro";
            }else{
                roleUser ="simple";
            }
            User userx = new User(username.getText(), email.getText(),FirstFrame.user.getPassword(), name.getText(), surname.getText(), phone.getText(),facebook.getText(),address.getText(), nameImage, roleUser);
            UserService us = new UserService();
            try {
               
                us.editUser(userx);
                
                
                //  FXMLLoader loader =  new FXMLLoader(getClass().getResource("../Views/Login.fxml"));
            } catch (SQLException ex) {
                Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
});
            pauseTransition.play();
            
            
       
                
    }
    
    
    
}
