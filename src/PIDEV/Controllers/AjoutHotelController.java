/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import static PIDEV.Controllers.AjoutrestoController.saveToFileImageNormal;
import static PIDEV.Controllers.AjoutrestoController.saveToFileVich;
import PIDEV.Entities.Etablissement;
import PIDEV.Services.AddEtablissement;
import PIDEV.Utils.MyConnexion;
import PIDEV.Views.MapController;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class AjoutHotelController implements Initializable {

    @FXML
    private JFXComboBox<String> souscat;
    @FXML
    private FontAwesomeIconView souscatw;

    public void setLatitudeText(String latitude) {
        this.latitude.setText(latitude);
    }

    public void setLongitudeText(String longitude) {
        this.longitude.setText(longitude);
    }

    public void setaddressText(String address) {
        this.address.setText(address);
    }
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextArea description;
    @FXML
    private FontAwesomeIconView emailw;
    @FXML
    private FontAwesomeIconView libellew;
    @FXML
    private FontAwesomeIconView descriptionw;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField website;
    @FXML
    private JFXTextField facebook;
    @FXML
    private JFXTextField nbrchambre;
    @FXML
    private FontAwesomeIconView telephonew;
    @FXML
    private FontAwesomeIconView placew;
    @FXML
    private JFXTextField prixmoy;
    @FXML
    private JFXTextField etoile;
    @FXML
    private JFXCheckBox lpd;
    @FXML
    private JFXCheckBox dp;
    @FXML
    private JFXCheckBox allinclusive;
    @FXML
    private JFXCheckBox pc;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField latitude;
    @FXML
    private JFXTextField longitude;
    @FXML
    private JFXComboBox<String> checkin;
    @FXML
    private JFXComboBox<String> checkout;
    @FXML
    private Button ajout;
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
    private AnchorPane an1;
    @FXML
    private FontAwesomeIconView image;
    @FXML
    private ImageView pic;
    @FXML
    private FontAwesomeIconView image1w;
    @FXML
    private FontAwesomeIconView image1;
    @FXML
    private ImageView pic1;
    @FXML
    private FontAwesomeIconView image2w;
    @FXML
    private FontAwesomeIconView image2;
    @FXML
    private ImageView pic2;
    @FXML
    private FontAwesomeIconView image3w;
    @FXML
    private FontAwesomeIconView image21;
    @FXML
    private ImageView pic3;
    @FXML
    private FontAwesomeIconView image4w;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection cn = MyConnexion.getInstance().getConnection();
        MyConnexion cnx = MyConnexion.getInstance();
        checkin.getItems().addAll("10:00", "11:00", "12:00", "13:00", "14:00", "15:00");
                checkout.getItems().addAll("10:00", "11:00", "12:00", "13:00", "14:00", "15:00");
       checkin.setValue("14:00");
checkout.setValue("12:00");
        try {
            souscat.getItems().add("Sous Categorie");
            AddEtablissement ae = new AddEtablissement();
            for (String s : ae.listsouscathotel()) {
                souscat.getItems().add(s);

            }

            souscat.setValue("Sous Categorie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        // TODO
    }    

    @FXML
    private void openmap(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Map.fxml"));
        Parent root = loader.load();
        MapController m = loader.getController();

        Stage stage = new Stage(StageStyle.DECORATED);

        stage.setTitle("Choisir votre adresse");
        stage.setScene(new Scene(root));
        stage.show();
        m.getSave().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setLatitudeText(m.getLatitudeLabel().getText().replace(',', '.'));
                setLongitudeText(m.getLongitudeLabel().getText().replace(',', '.'));
                setaddressText(m.getAddress().getText());

                stage.close();
            }
        });
    }

    @FXML
    private void AjoutRestaurant(ActionEvent event) throws SQLException, IOException {
         AddEtablissement ae = new AddEtablissement();
//        if (controleSaisie()) {
            Connection cn = MyConnexion.getInstance().getConnection();

            String scvalue = souscat.getValue();

            Image imagex = pic1.getImage();
            String nameImage = saveToFileImageNormal(imagex);
            Image imagexx = pic2.getImage();
            String nameImage2 = saveToFileImageNormal(imagexx);
            Image image3 = pic3.getImage();
            String nameImage3 = saveToFileImageNormal(image3);
            Image image4 = pic.getImage();
            String nameImage4 = saveToFileVich(image4);
            Etablissement E = new Etablissement(
                    ae.getsouscat(scvalue),
                    name.getText(),
                    address.getText(),
                    description.getText(),
                    phone.getText(),
                    email.getText(),
                    website.getText(),
                    facebook.getText(),
                    "Hotel",
                    
                    parking.isSelected(),
                    cartecredit.isSelected(),
                    chaiseroulante.isSelected(),
                    fumer.isSelected(),
                    terasse.isSelected(),
                    wifi.isSelected(),
                    reservation.isSelected(),
                    
                    livraison.isSelected(),
                    climatisation.isSelected(),
                    animaux.isSelected(),
                    alcool.isSelected(),
                    nameImage,
                    nameImage2, 
                    nameImage3, 
                    nameImage4, 
                    Double.valueOf(latitude.getText()),
                    Double.valueOf(longitude.getText()),
                    PIDEV.Views.FirstFrame.user,
                    lpd.isSelected(),
                    dp.isSelected(),
                    pc.isSelected(),
                    allinclusive.isSelected(),
                   Integer.parseInt(prixmoy.getText()),
                    Integer.parseInt(nbrchambre.getText()),
                    Integer.parseInt(etoile.getText()),
                    checkin.getValue(),
                    checkout.getValue());
                    
                    
                    
                    
                    
                   
            
            
            AddEtablissement addetab = new AddEtablissement();

            addetab.AddHotel(E);
             Notifications.create()
                        .title(null)
                        .text("Vote Etablissement a été ajouée en attente la confirmation de l'admin")
                        .graphic(pic)
                        
                        .hideAfter(Duration.seconds(8))
                         .position(Pos.BOTTOM_RIGHT)
                        
                        .show();

//            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);

//            alert1.setTitle("Confirmation Dialog");
//            alert1.setContentText("Restaurant Ajoutée ");
//            alert1.setHeaderText(null);
//            Optional<ButtonType> action = alert1.showAndWait();
//            if (action.get() == ButtonType.OK) {

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/HomePage.fxml"));
                    Parent root = loader.load();
                    HomePageController pu = loader.getController();
                    AnchorPane Rev = FXMLLoader.load(getClass().getResource("../Views/ListResto.fxml"));
                    pu.setNode(Rev);
                    name.getScene().setRoot(root);
                   
                } catch (IOException ex) {
                    Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
                }

//            }

//        }
 
    }
     public static String saveToFileImageNormal(Image image) {

        String ext = "jpg";
        File dir = new File("C:/wamp64/www/PIDEV/web/uploads/images");
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

    public static String saveToFileVich(Image image) {

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
    private void addImage4(MouseEvent event) {
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            pic.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(AjoutrestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addImage(MouseEvent event) {
         FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            pic1.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(AjoutrestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addImage2(MouseEvent event) {
         FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            pic2.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(AjoutrestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addImage3(MouseEvent event) {
         FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            pic3.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(AjoutrestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void home(MouseEvent event) {
    }
    
}
