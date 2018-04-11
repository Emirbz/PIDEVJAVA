/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import static PIDEV.Controllers.AjoutrestoController.saveToFileImageNormal;
import static PIDEV.Controllers.AjoutrestoController.saveToFileVich;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import PIDEV.Entities.Categorie;
import PIDEV.Entities.SousCategorie;
import PIDEV.Entities.Etablissement;
import PIDEV.Services.EditEtablissement;
import PIDEV.Utils.MyConnexion;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class EditrestoController implements Initializable {
     Connection cn = MyConnexion.getInstance().getConnection();

    @FXML
    private JFXComboBox<String> souscat;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField website;
    @FXML
    private JFXTextField facebook;
    @FXML
    private JFXTextField place;
    @FXML
    private JFXTextField address;
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
    private JFXComboBox<String> lundisamedio;
    @FXML
    private JFXComboBox<String> lundisamedif;
    @FXML
    private JFXComboBox<String> dimancheo;
    @FXML
    private JFXComboBox<String> dimanchef;
    @FXML
    private Button edit;
    @FXML
    private Label id;
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
    private FontAwesomeIconView image21;
    @FXML
    private ImageView pic3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Connection cn = MyConnexion.getInstance().getConnection();
    MyConnexion cnx = MyConnexion.getInstance();
      lundisamedio.getItems().addAll("10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00");
       lundisamedif.getItems().addAll("18:00","19:00","20:00","21:00","22:00","23:00","00:00");
      
       dimancheo.getItems().addAll("Fermé","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00");
       dimanchef.getItems().addAll("Fermé","18:00","19:00","20:00","21:00","22:00","23:00","00:00");
      
    
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

//    private void modificationProduitStock(ActionEvent event) throws SQLException {
//        System.out.println("aaaa");
//           EditEtablissement sv = new EditEtablissement();
//           etablissement p = new etablissement(name.getText(), address.getText(), description.getText(), phone.getText(), email.getText());
//            int item = 1;
//            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
//            alert1.setTitle("Confirmation Dialog");
//            alert1.setContentText("Etes vous sur de vouloir modifier le produit ");
//            alert1.setHeaderText(null);
//            Optional<ButtonType> action = alert1.showAndWait();
//            if (action.get() == ButtonType.OK) {
//                sv.modifierProduitStock(p, item);
//
//            }
//           
//
//        
//
//    }
    @FXML
    public void EditRestaurant(ActionEvent event) throws IOException, SQLException {
        Connection cn = MyConnexion.getInstance().getConnection();
    
       //// definir cat sous cat forgein key
     
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
       ////
        Image image = pic1.getImage();
            String nameImage = saveToFileImageNormal(image);
            Image image2 = pic2.getImage();
            String nameImage2 = saveToFileImageNormal(image2);
            Image image3 = pic3.getImage();
            String nameImage3 = saveToFileImageNormal(image3);
            Image image4 = pic.getImage();
            String nameImage4 = saveToFileVich(image4);
       
        EditEtablissement sv = new EditEtablissement();
        Etablissement etab = new Etablissement(
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
                    alcool.isSelected(),nameImage, nameImage2, nameImage3,nameImage4);
//                       
        int item = Integer.parseInt(id.getText());
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation Dialog");
        alert1.setContentText("Etes vous sur de vouloir modifier le produit ");
        alert1.setHeaderText(null);
        Optional<ButtonType> action = alert1.showAndWait();
        if (action.get() == ButtonType.OK) {
            sv.ModifierResto(etab, item);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ListResto.fxml"));
            Parent root = loader.load();
            name.getScene().setRoot(root);

        }

    }

    public void showresto(Etablissement etab) {
        address.setText(etab.getAddress());
        name.setText(etab.getName());
        phone.setText(etab.getPhone());
        email.setText(etab.getEmail());
        facebook.setText(etab.getFacebook());
        website.setText(etab.getWebsite());
        id.setText(String.valueOf((etab.getId())));
        lundisamedif.setValue(etab.getLundisamedif());
        lundisamedio.setValue(etab.getLundisamedio());
        dimancheo.setValue(etab.getDimancheo());
        dimanchef.setValue(etab.getDimanchef());
        place.setText(String.valueOf(etab.getPlace()));
        description.setText(etab.getDescription());
        parking.setSelected(etab.isParking());
        cartecredit.setSelected(etab.isCartecredit());
        chaiseroulante.setSelected(etab.isChaiseroulante());
        fumer.setSelected(etab.isFumer());
        alcool.setSelected(etab.isAlcool());
        terasse.setSelected(etab.isTerasse());
        wifi.setSelected(etab.isWifi());
        animaux.setSelected(etab.isAnimaux());
        livraison.setSelected(etab.isLivraison());
        reservation.setSelected(etab.isReservations());
        
        
        climatisation.setSelected(etab.isClimatisation());
        
         try {

            String requete = "SELECT nom from sous__categorie where nom='" + etab.getSouscat().getNom() + "'";
              PreparedStatement pre = cn.prepareStatement(requete);

               ResultSet rs = pre.executeQuery(requete);
             while (rs.next()) {
           souscat.setValue(rs.getString(1));
             }   
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     final String imageURI = new File("C://wamp64/www/PIDEV/web/uploads/images/"+etab.getImg1()).toURI().toString();
  pic1.setImage(new Image(imageURI));
        
  final String imageURI2 = new File("C://wamp64/www/PIDEV/web/uploads/images/"+etab.getImg2()).toURI().toString();
  pic2.setImage(new Image(imageURI2));
  
  final String imageURI3 = new File("C://wamp64/www/PIDEV/web/uploads/images/"+etab.getImg3()).toURI().toString();
  pic3.setImage(new Image(imageURI3));
  
  
  final String imageURI4 = new File("C://wamp64/www/PIDEV/web/devis/"+etab.getDevis_name()).toURI().toString();
 pic.setImage(new Image(imageURI4));

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
    private void AddImage4(MouseEvent event) {
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
    private void AddImage(MouseEvent event) {
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
    private void AddImage2(MouseEvent event) {
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
    private void AddImage3(MouseEvent event) {
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

   
  

}
