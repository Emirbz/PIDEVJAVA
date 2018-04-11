/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;
import PIDEV.Views.FirstFrame;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.HostServices;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author ons
 */
public class ProfileInfoUserController implements Initializable {

    @FXML
    private Rectangle photo;
    @FXML
    private Label pseudo;
    @FXML
    private Label since;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FontAwesomeIconView f;
    @FXML
    private JFXButton fcb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("C:/wamp64/www/PIDEV/web/devis/"+FirstFrame.user.getDevis_name());
        try {
            
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            photo.setFill(new ImagePattern(image));
            
        } catch (IOException ex) {
            Logger.getLogger(ProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pseudo.setText(FirstFrame.user.getUsername_canonical().toUpperCase().substring(0,1)+""+FirstFrame.user.getUsername_canonical().substring(1, FirstFrame.user.getUsername_canonical().length()));
        since.setText("Member since "+FirstFrame.user.getLast_login().toLocalDate().getMonth()+","+FirstFrame.user.getLast_login().toLocalDate().getYear());
        address.setText(FirstFrame.user.getAddress().toUpperCase().substring(0,1)+""+FirstFrame.user.getAddress().substring(1,FirstFrame.user.getAddress().length()));
        email.setText(FirstFrame.user.getEmail_canonical().toUpperCase().substring(0,1)+""+FirstFrame.user.getEmail_canonical().substring(1,FirstFrame.user.getEmail_canonical().length()));
    }    

   

    

    @FXML
    private void colorFcb(MouseEvent event) {
        f.setFill(Color.WHITE);
        fcb.setStyle("-fx-background-color:  #ff214f;");
    }

    @FXML
    private void backFcb(MouseEvent event) {
        f.setFill(Color.web("#aaaaaa"));
        fcb.setStyle("-fx-background-color:  transparent");
    }

    @FXML
    private void facebookLink(MouseEvent event) throws IOException {
            URI link = URI.create(FirstFrame.user.getFacebook()) ;
            if (Desktop.isDesktopSupported()){
              Desktop desktop = Desktop.getDesktop();
    if (desktop.isSupported(Desktop.Action.BROWSE)){
        desktop.browse(link);
    }
    }
      
               
    }
    
}
