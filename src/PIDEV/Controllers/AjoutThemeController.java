/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Theme;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import PIDEV.Services.myCroud;

/**
 * FXML Controller class
 *
 * @author Slim sl
 */
public class AjoutThemeController implements Initializable {

    @FXML
    private TextField Nomthemebutton;
    @FXML
    private TextField DescriptionNameButton;
    @FXML
    private Button AjoutThemeButton;
    @FXML
    private Button AnnuleThemeButton;
    myCroud cr = new myCroud();
    @FXML
    private ImageView img;
    @FXML
    private Button ajoutimg;
    @FXML
    private Button ListeTheme;
    @FXML
    private Button addarticle;
    @FXML
    private Button showarticle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjoutTheme(ActionEvent event) {
//        if (DescriptionNameButton.getText() == ""||Nomthemebutton.getText() == "" || PIDEV.Views.FirstFrame.user.getRole()!="pro"){
//            System.out.println("impossible");   
//        } else {
        
        Theme a = new Theme(Nomthemebutton.getText(), DescriptionNameButton.getText());
        Image image = img.getImage();
        String nameImage = saveToFileImageNormal(image);
        a.setImage(nameImage);
        cr.ajoutertheme(a,3);
    }

    public void modifier(Theme theme) {
        Nomthemebutton.setText(theme.getName());
        DescriptionNameButton.setText(theme.getDescription());
        Theme a = new Theme(theme.getId(), Nomthemebutton.getText(), DescriptionNameButton.getText());
        cr.modifierTheme(a);
    }

    @FXML
    private void Annuler(ActionEvent event) {
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
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            img.setImage(image);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String saveToFileImageNormal(Image image) {

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
    private void AfficherTheme(ActionEvent event) {
         try {
             Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/AffichageTheme.fxml"));

                        Scene tableViewScene = new Scene(tableViewParent);

                        //This line gets the Stage information
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        window.setScene(tableViewScene);

                        window.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AffichageThemeController.class.getName()).log(Level.SEVERE, null, ex);
                    
        }
    }

    private void Contacte(ActionEvent event) throws IOException {
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/Mail.fxml"));

                        Scene tableViewScene = new Scene(tableViewParent);

                        //This line gets the Stage information
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        window.setScene(tableViewScene);

                        window.show();
    }
    

    private void Abonnement(ActionEvent event) throws IOException {
 Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/Abonement.fxml"));

                        Scene tableViewScene = new Scene(tableViewParent);

                        //This line gets the Stage information
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        window.setScene(tableViewScene);

                        window.show();
    
    }

    @FXML
    private void adarticle(ActionEvent event) {
         try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/AjouterArticle.fxml"));

            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);

            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageThemeController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void showarticle(ActionEvent event) {
         try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/AffichageArticleADmin.fxml"));

            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);

            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageThemeController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
