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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import PIDEV.Entities.Categorie;
import PIDEV.Entities.SousCategorie;
import PIDEV.Entities.Etablissement;
import PIDEV.Services.AddEtablissement;
import PIDEV.Utils.MyConnexion;
import com.jfoenix.controls.JFXButton;

import java.awt.image.BufferedImage;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javafx.scene.image.Image;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class AjoutrestoController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField description;
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
    @FXML
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

            String requete = "SELECT nom from sous__categorie where idcategorie=4";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                souscat.getItems().add(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @FXML
    private void AjoutRestaurant(ActionEvent event) throws IOException, SQLException {
        Connection cn = MyConnexion.getInstance().getConnection();
        try {
            String scvalue = souscat.getValue();
            String requete = "SELECT * from sous__categorie where nom='" + scvalue + "'";
            PreparedStatement st = cn.prepareStatement(requete);

            ResultSet rs = st.executeQuery(requete);

            SousCategorie sc = new SousCategorie();
            while (rs.next()) {
                sc.setId(rs.getInt(1));
                int i = rs.getInt(2);
                String req = "SELECT * from Categorie where id='" + i + "'";
                PreparedStatement st1 = cn.prepareStatement(req);
                ResultSet rs1 = st1.executeQuery(req);
                Categorie c = new Categorie();
                while (rs1.next()) {
                    c.setId(1);
                    c.setNom(rs.getString(2));
                    c.setDescription(rs.getString(3));
                }
                sc.setIdcategorie(c);
                sc.setNom(rs.getString(3)); // oubien 3 

            }

            Image image = pic1.getImage();
            String nameImage = saveToFileImageNormal(image);
            Image image2 = pic2.getImage();
            String nameImage2 = saveToFileImageNormal(image2);
            Image image3 = pic3.getImage();
            String nameImage3 = saveToFileImageNormal(image3);
            Image image4 = pic.getImage();
            String nameImage4 = saveToFileVich(image4);
            Etablissement E = new Etablissement(
                    sc,
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
                    nameImage, nameImage2, nameImage3,nameImage4
            );

            AddEtablissement addetab = new AddEtablissement();

            addetab.AddRestaurant(E);

            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);

            alert1.setTitle("Confirmation Dialog");
            alert1.setContentText("Restaurant Ajoutée ");
            alert1.setHeaderText(null);
            Optional<ButtonType> action = alert1.showAndWait();
            if (action.get() == ButtonType.OK) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/PIDEVTOUTOU/Views/ListResto.fxml"));
                Parent root = loader.load();
                name.getScene().setRoot(root);

            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void home(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Home.fxml"));
        Parent root = loader.load();
        home.getScene().setRoot(root);

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


}

//FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutresto.fxml"));
//Parent root = loader.load();
//DetailspersonneController detailspersonne = loader.getController();
//detailspersonne.setNomlabel(nom.getText());
//detailspersonne.setPrenomlabel(prenom.getText());
//nom.getScene().setRoot(root);    

