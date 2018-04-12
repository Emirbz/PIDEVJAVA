/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.User;
import PIDEV.Services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import org.apache.commons.lang3.RandomStringUtils;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 * FXML Controller class
 *
 * @author ons
 */
public class AddUserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password;

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
    private JFXButton ajouter;

    @FXML
    private JFXButton image;
      @FXML
    private Circle imageIn;
    @FXML
    private Label login;
    @FXML
    private JFXPasswordField confPassword;
    @FXML
    private JFXComboBox<String> role;
    @FXML
    private ImageView pic;
    @FXML
    private JFXSpinner spinner;

    @FXML
    private void addImage(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageIn.setFill(new ImagePattern(image));
            
             pic.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void addUser(ActionEvent event) throws IOException {
        
        ajouter.setVisible(false);
        login.setVisible(false);
        spinner.setVisible(true);
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(2));
        pauseTransition.setOnFinished(ev -> {
            try {
                spinner.setVisible(false);
                    Image image = pic.getImage();
                    String nameImage = saveToFile(image);
                    String roleUser;
                    if(role.getValue()=="Professionnel"){
                        roleUser = "pro";
                    }else{
                        roleUser ="simple";
                    }
                    User user = new User(username.getText(), email.getText(), password.getText(), name.getText(), surname.getText(), phone.getText(), facebook.getText(), address.getText(), nameImage,roleUser);
                    UserService userService = new UserService();

                    
                    userService.addUser(user);
                    

                    FXMLLoader loader =  new FXMLLoader(getClass().getResource("../Views/Login.fxml"));
                    Parent root;

                    root = loader.load();

                    Stage stage = new Stage(StageStyle.UNDECORATED);
                    stage.setWidth(700);
                    stage.setHeight(400);

                    stage.setScene(new Scene(root));
                     stage.show();
                    Stage pr=(Stage) login.getScene().getWindow();
                     pr.close();

                 } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }});
        pauseTransition.play();
      }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /**image **/
        File dir = new File("C:/wamp64/www/PIDEV/web/devis/profilePic.png");
       BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(dir);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
              imageIn.setFill(new ImagePattern(image));
        } catch (IOException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /** end image**/    
      
        role.getItems().addAll("Professionnel", "Utilisateur Simple");
      
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
    private void loginPage(MouseEvent event) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("../Views/Login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setWidth(700);
        stage.setHeight(400);
                         
        stage.setScene(new Scene(root));
         stage.show();
        Stage pr=(Stage) login.getScene().getWindow();
         pr.close();
        
    }

}
