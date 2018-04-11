/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Catdeal;
import PIDEV.Entities.Deal;
import PIDEV.Services.UpdateDealService;
import PIDEV.Utils.MyConnexion;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author Skan
 */
public class UpdateDealController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField places;
    @FXML
    private JFXTextField oldprix;
    @FXML
    private JFXTextField promo;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXComboBox<String> region;
    @FXML
    private JFXDatePicker datef;
    @FXML
    private JFXTextArea description;
    @FXML
    private Label id;
    @FXML
    private JFXComboBox<String> catdeal;
    @FXML
    private ImageView img;
    @FXML
    private Button modif;
    @FXML
    private Button modifimg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        region.getItems().addAll("Ariana", "Béja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba",
                "Kairouan", "Kasserine", "Kebili", "La Manouba", "Le Kef", "Mahdia", "Médenine", "Monastir",
                "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan");
        try {
            Connection cn = MyConnexion.getInstance().getConnection();
            String requete = "SELECT nom FROM catdeal";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                catdeal.getItems().add(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void GetOldValues(Deal deal) throws SQLException {
        nom.setText(deal.getNom());
        description.setText(deal.getDescription());
        places.setText(String.valueOf(deal.getPlacesdispo()));
        oldprix.setText(String.valueOf((deal.getOldprix())));
        promo.setText(String.valueOf((deal.getPromotion())));
        Connection cn = MyConnexion.getInstance().getConnection();
        String requete = "SELECT nom FROM catdeal WHERE id='" + deal.getCat().getId() + "'";
        PreparedStatement st = cn.prepareStatement(requete);
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {

            catdeal.setValue(rs.getString(1));

        }
        final String imageURI = new File("C:/wamp64/www/PIDEV/web/devis/" + deal.getDevisName()).toURI().toString();
        img.setImage(new Image(imageURI));
        region.setValue(deal.getRegion());
        adresse.setText(deal.getAdresse());
        datef.setValue(LocalDate.parse(deal.getDatefin().toString()));
        id.setText(deal.getId().toString());
    }

    @FXML
    public void UpdateDeal(ActionEvent event) throws IOException, SQLException {
        UpdateDealService UDC = new UpdateDealService();
        java.util.Date date = java.util.Date.from(datef.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Deal deal = new Deal(nom.getText(), Double.parseDouble(oldprix.getText()), Double.parseDouble(promo.getText()),
                description.getText(), sqlDate, region.getValue(), adresse.getText(), Integer.parseInt(places.getText()));
        int uid = Integer.parseInt(id.getText());
        Connection cn = MyConnexion.getInstance().getConnection();
        String re = "SELECT * FROM catdeal WHERE nom='" + catdeal.getValue() + "'";
        PreparedStatement pst = cn.prepareStatement(re);
        ResultSet rss = pst.executeQuery(re);
        Catdeal cd = new Catdeal();
        while (rss.next()) {
            cd.setId(rss.getInt("id"));
            cd.setNom(rss.getString("nom"));
            cd.setDevisName(rss.getString("devis_name"));
//                   cd.setUpdatedAt((Date)rse.getDate(4));   
        }
        deal.setCat(cd);
        Image image = img.getImage();
        String nameImage = saveToFileImageNormal(image);
        deal.setDevisName(nameImage);
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation Dialog");
        alert1.setContentText("Etes vous sur de vouloir modifier le deal ");
        alert1.setHeaderText(null);
        Optional<ButtonType> action = alert1.showAndWait();
        if (action.get() == ButtonType.OK) {
            UDC.UpdateDeal(deal, uid);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ListDeals.fxml"));
            Parent root = loader.load();
            nom.getScene().setRoot(root);
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
}
