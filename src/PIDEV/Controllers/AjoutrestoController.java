/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import PIDEV.Entities.Categorie;
import PIDEV.Entities.SousCategorie;
import PIDEV.Entities.Etablissement;
import PIDEV.Services.AddEtablissement;
import PIDEV.Utils.MyConnexion;
import PIDEV.Views.MapController;
import com.jfoenix.controls.JFXTextArea;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import static java.awt.Color.red;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.commons.lang3.RandomStringUtils;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class AjoutrestoController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private JFXTextArea description;
    @FXML
    private TextField email;
    @FXML
    private TextField place;
    @FXML
    private TextField facebook;
    @FXML
    private TextField website;
    @FXML
    private CheckBox parking;
    @FXML
    private CheckBox cartecredit;
    @FXML
    private CheckBox chaiseroulante;
    @FXML
    private CheckBox fumer;
    @FXML
    private CheckBox alcool;
    @FXML
    private CheckBox terasse;
    @FXML
    private CheckBox wifi;
    @FXML
    private CheckBox animaux;
    @FXML
    private CheckBox livraison;
    @FXML
    private CheckBox reservation;
    @FXML
    private CheckBox climatisation;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;

    @FXML
    private JFXComboBox<String> lundisamedio;
    @FXML
    private JFXComboBox<String> lundisamedif;
    @FXML
    private JFXComboBox<String> dimancheo;
    @FXML
    private JFXComboBox<String> dimanchef;
    @FXML
    private JFXComboBox<String> souscat;
    @FXML
    private Button ajout;
    private FontAwesomeIconView home;
    @FXML
    private FontAwesomeIconView image;
    @FXML
    private ImageView pic;
    @FXML
    private ImageView pic1;
    @FXML
    private FontAwesomeIconView image1;
    @FXML
    private FontAwesomeIconView image2;
    @FXML
    private ImageView pic2;
    @FXML
    private ImageView pic3;
    @FXML
    private FontAwesomeIconView image21;
    @FXML
    private JFXTextField latitude;
    @FXML
    private JFXTextField longitude;
    private GoogleMapView googleMapView;
    private Label latitudeLabel;
    private Label longitudeLabel;

    private GoogleMap map;

    private DecimalFormat formatter = new DecimalFormat("###.00000");
    private GeocodingService geocodingService;
    @FXML
    private FontAwesomeIconView emailw;
    @FXML
    private FontAwesomeIconView telephonew;
    @FXML
    private FontAwesomeIconView placew;
    @FXML
    private FontAwesomeIconView souscatw;
    @FXML
    private FontAwesomeIconView libellew;
    @FXML
    private FontAwesomeIconView descriptionw;
    @FXML
    private FontAwesomeIconView image1w;
    @FXML
    private FontAwesomeIconView image2w;
    @FXML
    private FontAwesomeIconView image3w;
    @FXML
    private FontAwesomeIconView image4w;
    @FXML
    private AnchorPane an1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Connection cn = MyConnexion.getInstance().getConnection();
        MyConnexion cnx = MyConnexion.getInstance();
        lundisamedio.getItems().addAll("10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00");
        lundisamedif.getItems().addAll("18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00");
        lundisamedio.setValue("10:00");
        lundisamedif.setValue("23:00");
        dimancheo.getItems().addAll("Fermé", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00");
        dimanchef.getItems().addAll("Fermé", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00");
        dimancheo.setValue("Fermé");
        dimanchef.setValue("Fermé");

        try {
            souscat.getItems().add("Sous Categorie");
            AddEtablissement ae = new AddEtablissement();
            for (String s : ae.listsouscat()) {
                souscat.getItems().add(s);

            }

            souscat.setValue("Sous Categorie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @FXML
    private void AjoutRestaurant(ActionEvent event) throws IOException, SQLException {
        AddEtablissement ae = new AddEtablissement();
        if (controleSaisie()) {
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
                    "Restaurant",
                    lundisamedio.getValue(),
                    lundisamedif.getValue(),
                    dimancheo.getValue(),
                    dimanchef.getValue(),
                    parking.isSelected(),
                    cartecredit.isSelected(),
                    chaiseroulante.isSelected(),
                    fumer.isSelected(),
                    terasse.isSelected(),
                    wifi.isSelected(),
                    reservation.isSelected(),
                    Integer.parseInt(place.getText()),
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
                    PIDEV.Views.FirstFrame.user
            );

            AddEtablissement addetab = new AddEtablissement();

            addetab.AddRestaurant(E);
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

        }
    }

    @FXML
    private void home(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ListResto.fxml"));
        Parent root = loader.load();

        address.getScene().setRoot(root);
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

    public void setLatitudeText(String latitude) {
        this.latitude.setText(latitude);
    }

    public void setLongitudeText(String longitude) {
        this.longitude.setText(longitude);
    }

    public void setaddressText(String address) {
        this.address.setText(address);
    }

    public boolean controleSaisie() {
        if (souscat.getValue() == "Sous Categorie") {
            souscatw.setVisible(true);
            souscat.setStyle("-jfx-focus-color:red");
            souscat.requestFocus();
            PopOver pop = new PopOver();
            pop.setContentNode(new Label("Veuillez choisir une sous categorie"));

            souscatw.setOnMouseEntered((event) -> {
                pop.show(souscatw);
            });
            souscatw.setOnMouseExited((event) -> {
                pop.hide();
            });
            souscat.setOnKeyTyped((event2) -> {
                souscatw.setVisible(false);
                souscat.setStyle("-jfx-focus-color:green");
            });
            return false;

        }

        if (name.getText().isEmpty()) {
            libellew.setVisible(true);
            name.setStyle("-jfx-focus-color:red");
            name.requestFocus();
            PopOver pop = new PopOver();
            pop.setContentNode(new Label("Veuillez saisir un libelle valide"));

            libellew.setOnMouseEntered((event) -> {
                pop.show(libellew);
            });
            libellew.setOnMouseExited((event) -> {
                pop.hide();
            });
            name.setOnKeyTyped((event2) -> {
                libellew.setVisible(false);
                name.setStyle("-jfx-focus-color:green");
            });
            return false;

        }
        if ((email.getText().indexOf("@")==-1|| email.getText().isEmpty())) {
            emailw.setVisible(true);
            email.setStyle("-jfx-focus-color:red");
            email.requestFocus();
            PopOver pop = new PopOver();
            pop.setContentNode(new Label("Veuillez saisir un email valide"));

            emailw.setOnMouseEntered((event) -> {
                pop.show(emailw);
            });
            emailw.setOnMouseExited((event) -> {
                pop.hide();
            });
            email.setOnKeyTyped((event2) -> {
                emailw.setVisible(false);
                email.setStyle("-jfx-focus-color:green");
            });
            return false;

        }
        if (description.getText().isEmpty()) {
            descriptionw.setVisible(true);
            description.setStyle("-jfx-focus-color:red");
            description.requestFocus();
            PopOver pop = new PopOver();
            pop.setContentNode(new Label("Description obligatoire"));

            descriptionw.setOnMouseEntered((event) -> {
                pop.show(descriptionw);
            });
            descriptionw.setOnMouseExited((event) -> {
                pop.hide();
            });
            description.setOnKeyTyped((event2) -> {
                descriptionw.setVisible(false);
                description.setStyle("-jfx-focus-color:green");
            });
            return false;

        }
        if ((!(phone.getText().matches("[0-9]+"))) || phone.getText().isEmpty()) {
            telephonew.setVisible(true);
            phone.setStyle("-jfx-focus-color:red");
            phone.requestFocus();
            PopOver pop = new PopOver();
            pop.setContentNode(new Label("Veuillez saisir un numero de telephone valide"));

            telephonew.setOnMouseEntered((event) -> {
                pop.show(telephonew);
            });
            telephonew.setOnMouseExited((event) -> {
                pop.hide();
            });
            phone.setOnKeyTyped((event2) -> {
                telephonew.setVisible(false);
                phone.setStyle("-jfx-focus-color:green");
            });
            return false;

        }
        if ((!(place.getText().matches("[0-9]+"))) || place.getText().isEmpty()) {
            placew.setVisible(true);
            place.setStyle("-jfx-focus-color:red");
            place.requestFocus();
            PopOver pop = new PopOver();
            pop.setContentNode(new Label("Veuillez saisir un nombre de place valide"));

            placew.setOnMouseEntered((event) -> {
                pop.show(placew);
            });
            placew.setOnMouseExited((event) -> {
                pop.hide();
            });
            place.setOnKeyTyped((event2) -> {
                placew.setVisible(false);
                place.setStyle("-jfx-focus-color:green");
            });
            return false;

        }  if ((pic.getImage()==null)) {
            image1w.setVisible(true);
            pic.setStyle("-jfx-focus-color:red");
            pic.requestFocus();
            PopOver pop = new PopOver();
            pop.setContentNode(new Label("Veuillez choisir une photo de profil"));

            image1w.setOnMouseEntered((event) -> {
                pop.show(image1w);
            });
            image1w.setOnMouseExited((event) -> {
                pop.hide();
            });
             pic.setOnMouseClicked((event2) -> {
                image1w.setVisible(false);
                 addImage4(event2);
            });
           
            return false;

        }
        if ((pic1.getImage()==null)) {
            image2w.setVisible(true);
            pic1.setStyle("-jfx-focus-color:red");
            pic1.requestFocus();
            PopOver pop = new PopOver();
            pop.setContentNode(new Label("Veuillez choisir une photo de profil"));

            image2w.setOnMouseEntered((event) -> {
                pop.show(image2w);
            });
            image2w.setOnMouseExited((event) -> {
                pop.hide();
            });
            pic1.setOnMouseClicked((event2) -> {
                image2w.setVisible(false);
                addImage(event2);
            });
            return false;

        }
        if ((pic2.getImage()==null)) {
            image3w.setVisible(true);
            pic2.setStyle("-jfx-focus-color:red");
            pic2.requestFocus();
            PopOver pop = new PopOver();
            pop.setContentNode(new Label("Veuillez choisir une photo de profil"));

            image3w.setOnMouseEntered((event) -> {
                pop.show(image3w);
            });
            image3w.setOnMouseExited((event) -> {
                pop.hide();
            });
            pic2.setOnMouseClicked((event2) -> {
                image3w.setVisible(false);
                addImage2(event2);
            });
            
            return false;

        }
        if ((pic3.getImage()==null)) {
            image4w.setVisible(true);
            pic3.setStyle("-jfx-focus-color:red");
            pic3.requestFocus();
            PopOver pop = new PopOver();
            pop.setContentNode(new Label("Veuillez choisir une photo de profil"));

            image4w.setOnMouseEntered((event) -> {
                pop.show(image4w);
            });
            image4w.setOnMouseExited((event) -> {
                pop.hide();
            });
            pic3.setOnMouseClicked((event2) -> {
                image4w.setVisible(false);
                addImage3(event2);
            });
            return false;

        }

        return true;
    }

//    private FontAwesomeIconView image1w;
//    @FXML
//    private FontAwesomeIconView image2w;
//    @FXML
//    private FontAwesomeIconView image3w;
//    @FXML
//    private FontAwesomeIconView image4w;
}
