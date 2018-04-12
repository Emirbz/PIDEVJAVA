/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Controllers.EditeventController;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import PIDEV.Services.ParticiperService;
import PIDEV.Services.deleteevent;
import PIDEV.Services.editevent;
import PIDEV.Services.listeevent;
import PIDEV.Utils.MyConnexion;
import PIDEV.Entities.events;
import PIDEV.Services.partage;

/**
 * FXML Controller class
 *
 * @author dahem
 */
public class ProfileventController implements Initializable {

    @FXML
    private AnchorPane partciper;

    @FXML
    private Button editer;
    @FXML
    private Button supprimer;
    @FXML
    private Label description;
    @FXML
    private ImageView nameImage;
    @FXML
    private Label nom;
    @FXML
    private Label date;
    @FXML
    private Label phone;
    @FXML
    private Label adresse;
    @FXML
    private FontAwesomeIconView facebook;
    @FXML
    private FontAwesomeIconView twitter;
    @FXML
    private CheckBox parking;
    @FXML
    private CheckBox wifi;
    @FXML
    private CheckBox famille;
    @FXML
    private CheckBox fumer;
    @FXML
    private CheckBox enfant;
    @FXML
    private CheckBox ascenseur;
    @FXML
    private CheckBox cartecredit;
    @FXML
    private ImageView nameImage1;
    @FXML
    private ImageView nameImage3;
    @FXML
    private ImageView nameImage2;
    @FXML
    private Label place;
    @FXML
    private Label place1;
    int id = 0;
    @FXML
    private Button participer;
    @FXML
    private Label type;
    @FXML
    private Label adressemail;
    @FXML
    private Label nbrplacerestant;
public String partageImane ;


    public String getPartageImane() {
        return partageImane;

    }

    /**
     * Initializes the controller class.
     */
    public void setPartageImane(String partageImane) {
        this.partageImane = partageImane;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Preferences prefs = Preferences.userNodeForPackage(ProfileventController.class);

        int id = prefs.getInt("id", 0);
        System.out.println("" + id);
        ParticiperService ps = new ParticiperService();
        if (ps.getParticip(id)) {
            participer.setVisible(false);
        } else {
            participer.setVisible(true);
        }
    }

    public void profilevent(events e) {

        Preferences prefs = Preferences.userNodeForPackage(ProfileventController.class);

        id = prefs.getInt("id", 0);
        ParticiperService ps = new ParticiperService();
        System.out.println("jjjjjj " + ps.getParticip(id));
        if (ps.getParticip(id)) {
            participer.setVisible(false);
        } else {
            participer.setVisible(true);
        }
        nom.setText(e.getName());
        description.setText(e.getDescription());
        type.setText(e.getType());
        phone.setText(String.valueOf(e.getNumTel()));
        twitter.setText(e.getAdressetwitter());
        facebook.setText(e.getAdressefacebook());
        adressemail.setText(e.getAdressemail());
        place.setText(String.valueOf(e.getNbrplace()));
        nbrplacerestant.setText(String.valueOf(e.getNbrplacerestant()));
        adresse.setText(e.getAdresse());
        parking.setSelected(e.getParking());
        fumer.setSelected(e.getFumer());
        wifi.setSelected(e.getWifi());
        enfant.setSelected(e.getEspaceEenfant());
        famille.setSelected(e.getEspacefamilial());
        ascenseur.setSelected(e.getAscenseur());
//        date.setText(LocalDate.parse(e.getDateEvenement().toString()).toString());
        cartecredit.setSelected(e.getCartebancaire());
         this.setPartageImane(partageImane =e.getDevis_name());
        final String imageURI4 = new File("C://wamp64/www/PIDEV/web/devis/" + e.getDevis_name()).toURI().toString();
        nameImage.setImage(new Image(imageURI4));
        final String image0 = new File("C://wamp64/www/PIDEV/web/uploads/images/" + e.getImage()).toURI().toString();
        nameImage1.setImage(new Image(image0));
        final String image1URI4 = new File("C://wamp64/www/PIDEV/web/uploads/images/" + e.getImage1()).toURI().toString();
        nameImage2.setImage(new Image(image1URI4));
        final String image2URI4 = new File("C:/wamp64/www/PIDEV/web/uploads/images/" + e.getImage2()).toURI().toString();
        nameImage3.setImage(new Image(image2URI4));
        System.out.println(e.getImage2());

        editer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/editevent.fxml"));
                    Parent root = loader.load();
                    EditeventController ee = loader.getController();

                    ee.getoldvalues(e);
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setTitle("Modifier event");
                    stage.setScene(new Scene(root));
                    stage.show();
                    ee.getAjout().setOnMouseClicked((event2) -> {
                        stage.close();

                    });
                } catch (IOException ex) {
                    Logger.getLogger(ProfileventController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        supprimer.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                try {
                    deleteevent de = new deleteevent();
                    de.delete(e.getId());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/AfficheEvent.fxml"));
                    Parent root = loader.load();
                    adresse.getScene().setRoot(root);
                } catch (SQLException | IOException ex) {
                    Logger.getLogger(ProfileventController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        );

        participer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            editevent es = new editevent();

            @Override
            public void handle(MouseEvent event) {

                if (e.getNbrplace() <= e.getNbrparticipant()) {
                    System.out.println("full");
                } else {
                    try {
                        e.setNbrparticipant(e.getNbrparticipant() + 1);
                        e.setNbrplacerestant(e.getNbrplacerestant() - 1);
                        e.setId(id);
                        es.increment(e);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Profilevent.fxml"));
                        Parent root = loader.load();
                        ProfileventController pc = loader.getController();
                        pc.profilevent(e);
                        partciper.getScene().setRoot(root);

                    } catch (SQLException | IOException ex) {
                        Logger.getLogger(ProfileventController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
        );

    }

    @FXML

    private void participerss(ActionEvent event) throws IOException, SQLException {
        String timedate = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        Document doc = new Document();

        try {
            PdfWriter.getInstance(doc, new FileOutputStream("Invitation d'evenement.pdf"));
            doc.open();
            Paragraph p1 = new Paragraph();
            Paragraph p2 = new Paragraph();
            Paragraph p3 = new Paragraph();
            Paragraph p4 = new Paragraph();

            p1.add("     Invitation d'evenement");
            p2.add("     date : " + timedate);
            p3.add("     bienvenu ");

            doc.add(p1);
            doc.add(p2);
            doc.add(p3);
            doc.add(p4);

            doc.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            doc.close();
        }
        System.out.println("done");

        Connection cn = MyConnexion.getInstance().getConnection();
       // try {
            Preferences prefs = Preferences.userNodeForPackage(ProfileventController.class);

            id = prefs.getInt("id", 0);
            System.out.println("fufufu   " + id);
            ParticiperService ps = new ParticiperService();
            Date date = new Date();
            java.sql.Date sqldate = new java.sql.Date(date.getTime());

            ps.participerr(id, 3, sqldate);

            System.out.println("" + id);

//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Profilevent.fxml"));
//            Parent root = loader.load();
//            ascenseur.getScene().setRoot(root);
//        } catch (IOException ex) {
//            System.err.println(ex.getMessage());
//        }

    }

    @FXML
    private void partage(ActionEvent event) {
        partage p=new partage();
        p.partager(nom.getText(), description.getText(), date.getText(),adresse.getText(), this.getPartageImane());
    }

}
