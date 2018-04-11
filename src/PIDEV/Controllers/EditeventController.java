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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import PIDEV.Services.editevent;
import PIDEV.Entities.events;

/**
 * FXML Controller class
 *
 * @author dahem
 */
public class EditeventController implements Initializable {

    @FXML
    private JFXTextField nom;
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
    private JFXTextField adresse;
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
    private ImageView pic;

    @FXML
    private ImageView pic1;

    @FXML
    private ImageView pic2;

    @FXML
    private ImageView pic3;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private FontAwesomeIconView image;
    @FXML
    private FontAwesomeIconView image1;
    @FXML
    private FontAwesomeIconView image2;
    @FXML
    private FontAwesomeIconView image21;
    @FXML
    private JFXTextField adressemail;

    public Button getAjout() {
        return ajout;
    }

    public void setAjout(Button ajout) {
        this.ajout = ajout;
    }
    @FXML
    private JFXCheckBox ascenseur;
    @FXML
    private JFXDatePicker date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Tous", "Sport", "Bal", "Cirque", "Culture", "Developpement", "Festival", "Fête privée", "Spectacle", "Sortie", "visite", "Rassemblement");
        type.setItems(list);
    }

    public void getoldvalues(events e) {

        nom.setText(e.getName());
        description.setText(e.getDescription());
        phone.setText(String.valueOf(e.getNumTel()));
        twitter.setText(e.getAdressetwitter());
        facebook.setText(e.getAdressefacebook());
        adressemail.setText(e.getAdressemail());
        place.setText(String.valueOf(e.getNbrplace()));
        adresse.setText(e.getAdresse());
        parking.setSelected(e.getParking());
        cartecredit.setSelected(e.getCartebancaire());
        fumer.setSelected(e.getFumer());
        wifi.setSelected(e.getWifi());
        enfant.setSelected(e.getEspaceEenfant());
        famille.setSelected(e.getEspacefamilial());
        ascenseur.setSelected(e.getAscenseur());
//        date.setValue(LocalDate.parse(e.getDateEvenement().toString()));
        final String image4 = new File("C:\\wamp64\\www\\PIDEV\\web\\devis\\" + e.getDevis_name()).toURI().toString();
        pic.setImage(new Image(image4));
        final String image1 = new File("C:\\wamp64\\www\\PIDEV\\web\\uploads\\images\\" + e.getImage()).toURI().toString();
        pic1.setImage(new Image(image1));
        final String image2 = new File("C:\\wamp64\\www\\PIDEV\\web\\uploads\\images\\" + e.getImage1()).toURI().toString();
        pic2.setImage(new Image(image2));
        final String imagee = new File("C:\\wamp64\\www\\PIDEV\\web\\uploads\\images\\" + e.getImage2()).toURI().toString();
        pic3.setImage(new Image(imagee));
        type.getSelectionModel().select(e.getType());
    }

    @FXML
    private void ModifierEvent(ActionEvent event) throws SQLException, IOException {

        editevent sv = new editevent();
        //// definir cat sous cat forgein key
        events E = new events();
        E.setName(nom.getText());
        E.setDescription(description.getText());
        java.util.Date datee = java.util.Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqldate = new java.sql.Date(datee.getTime());
        E.setDateEvenement(sqldate);
        E.setNbrplace(Integer.parseInt(place.getText()));
        E.setAdresse(adresse.getText());
        E.setAdressemail(adressemail.getText());
        E.setNumTel(Integer.parseInt(phone.getText()));
        E.setAdressefacebook(facebook.getText());
        E.setAdressetwitter(twitter.getText());
        Image image1 = pic1.getImage();
        String nameImage1 = saveToFileImageNormal(image1);
        Image image2 = pic2.getImage();
        String nameImage2 = saveToFileImageNormal(image2);
        Image image3 = pic3.getImage();
        String nameImage3 = saveToFileImageNormal(image3);
        Image image4 = pic.getImage();
        String nameImage4 = saveToFileVich(image4);
        E.setImage(nameImage1);
        E.setImage1(nameImage2);
        E.setImage2(nameImage3);
        E.setDevis_name(nameImage4);
        E.setType(type.getValue());
        E.setParking(parking.isSelected());
        E.setFumer(fumer.isSelected());
        E.setWifi(wifi.isSelected());
        E.setEspaceEenfant(enfant.isSelected());
        E.setAscenseur(ascenseur.isSelected());
        E.setCartebancaire(cartecredit.isSelected());
        E.setEspacefamilial(famille.isSelected());
        E.setType("tous les types");

        sv.modifierevenement(E, E.getId());

//                       
////        int item = Integer.parseInt(id.getText());
////        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
////        alert1.setTitle("Confirmation Dialog");
////        alert1.setContentText("Etes vous sur de vouloir modifier le produit ");
////        alert1.setHeaderText(null);
////        Optional<ButtonType> action = alert1.showAndWait();
////        if (action.get() == ButtonType.OK) {
////            sv.modifierresto(etab, item);
////            FXMLLoader loader = new FXMLLoader(getClass().getResource("listresto.fxml"));
////            Parent root = loader.load();
////            name.getScene().setRoot(root);
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
