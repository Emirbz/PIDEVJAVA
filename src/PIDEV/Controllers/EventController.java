/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import PIDEV.Services.eventService;
import PIDEV.Utils.MyConnexion;
import PIDEV.Entities.events;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dahem
 */
public class EventController implements Initializable {

    @FXML
    private JFXCheckBox ascenseur;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField twitter;
    @FXML
    private JFXTextField facebook;
    @FXML
    private JFXTextField place;
    @FXML
    private JFXCheckBox parking;
    @FXML
    private JFXCheckBox cartecredit;
    @FXML
    private JFXCheckBox fumer;
    @FXML
    private JFXCheckBox wifi;
    @FXML
    private JFXCheckBox enfant;
    @FXML
    private JFXCheckBox famille;
    @FXML
    private Button ajout;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXDatePicker date;
//////    @FXML
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private FontAwesomeIconView image;
    @FXML
    private ImageView pic;
    @FXML
    private FontAwesomeIconView image1;
    @FXML
    private ImageView pic1;
    @FXML
    private FontAwesomeIconView image2;
    @FXML
    private ImageView pic2;
    @FXML
    private ImageView pic3;
    @FXML
    private FontAwesomeIconView image21;
    @FXML
    private JFXTextField adressemail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Tous", "Sport", "Bal", "Cirque", "Culture", "Developpement", "Festival", "Fête privée", "Spectacle", "Sortie", "visite", "Rassemblement");
        type.setItems(list);
        // TODO
    }

    @FXML
    private void ajoutevent(ActionEvent event) throws IOException, SQLException {
        Connection cn = MyConnexion.getInstance().getConnection();
        try {
              DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate localDate = LocalDate.now();
if(Date.valueOf(date.getValue()).after(Date.valueOf(localDate)) || Date.valueOf(date.getValue()).compareTo(Date.valueOf(localDate))==0) {
            java.util.Date datee = java.util.Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqldate = new java.sql.Date(datee.getTime());
            Image image1 = pic1.getImage();
            String nameImage1 = saveToFileImageNormal(image1);
            Image image2 = pic2.getImage();
            String nameImage2 = saveToFileImageNormal(image2);
            Image image3 = pic3.getImage();
            String nameImage3 = saveToFileImageNormal(image3);
            Image image4 = pic.getImage();
            String nameImage4 = saveToFileVich(image4);
            events E = new events();
            E.setName(nom.getText());
            E.setDescription(description.getText());
            E.setDateEvenement(sqldate);
            E.setNbrplace(Integer.parseInt(place.getText()));
            E.setAdresse(adresse.getText());
            E.setAdressemail(adressemail.getText());
            E.setNumTel(Integer.parseInt(phone.getText()));
            E.setAdressefacebook(facebook.getText());
            E.setAdressetwitter(twitter.getText());
            E.setParking(parking.isSelected());
            E.setFumer(fumer.isSelected());
            E.setWifi(wifi.isSelected());
            E.setEspaceEenfant(enfant.isSelected());
            E.setAscenseur(ascenseur.isSelected());
            E.setCartebancaire(cartecredit.isSelected());
            E.setEspacefamilial(famille.isSelected());
            E.setType(type.getValue());
            E.setDevis_name(nameImage4);
            E.setImage(nameImage1);
            E.setNbrplacerestant(Integer.parseInt(place.getText()));
            E.setIduser(PIDEV.Views.FirstFrame.user);

            E.setImage1(nameImage3);
            E.setImage2(nameImage2);
            

            System.out.println(E.toString() + "");

            eventService addetab = new eventService();
            addetab.addevent(E);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/afficheevent.fxml"));
            Parent root = loader.load();
            ascenseur.getScene().setRoot(root);
}
else {
    JOptionPane.showMessageDialog(null, "Date ghalta");
}
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
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
            System.out.println(ex.getMessage());
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
            System.out.println(ex.getMessage());
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
            System.out.println(ex.getMessage());
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
            System.out.println(ex.getMessage());
        }
    }

}
