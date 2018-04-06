/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEVTOUTOU.Controllers;

import PIDEVTOUTOU.Entities.Review;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import PIDEVTOUTOU.Entities.etablissement;
import PIDEVTOUTOU.Services.EditEtablissement;
import PIDEVTOUTOU.Services.GestionReviews;
import PIDEVTOUTOU.Services.ListEtablissement;
import PIDEVTOUTOU.Utils.Myconnexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import static jdk.nashorn.internal.objects.NativeArray.map;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class ProfilrestoController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private Label description;
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
    private Label lundisamedio;
    @FXML
    private Label lundisamedif;
    @FXML
    private Label dimancheo;
    @FXML
    private Label dimanchef;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML
    private ImageView image5;
    @FXML
    private Rating service;
    @FXML
    private Rating qualite;
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextArea comment;
    @FXML
    private JFXButton AddReview;
    @FXML
    private Label total;
    @FXML
    private Label qualitelabel;
    @FXML
    private Label servicelabel;
    @FXML
    private AnchorPane scroll;
    private ObservableList<Review> listreview;
    private FlowPane fp = new FlowPane();
    @FXML
    private TabPane tab;
    @FXML
    private Tab tabX;
    @FXML
    private AnchorPane panecom;
    @FXML
    private Label evaluation;
    @FXML
    private Label dejaevaluated;
    @FXML
    private GoogleMapView googleMapView;
    private GoogleMap map;

    private DecimalFormat formatter = new DecimalFormat("###.00000");
    private GeocodingService geocodingService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         

        // TODO
    }

    public void RestoProfil(int id) throws SQLException {
        GestionReviews gr = new GestionReviews();
        tab.getSelectionModel().select(0);
        Connection cn = Myconnexion.getInstance().getConnection();

        ListEtablissement s = new ListEtablissement();

        etablissement etab = s.ListRestaurant().filtered(e -> e.getId() == id).get(0);

        description.setText(etab.getDescription());
        name.setText(etab.getName());
        address.setText(etab.getAddress());
        phone.setText(etab.getPhone());
        email.setText(etab.getEmail());
        lundisamedio.setText(etab.getLundisamedio());
        lundisamedif.setText(etab.getLundisamedif());
        dimancheo.setText(etab.getDimancheo());
        dimanchef.setText(etab.getDimanchef());
        parking.setSelected(etab.isParking());
        cartecredit.setSelected(etab.isCartecredit());
        chaiseroulante.setSelected(etab.isChaiseroulante());
        fumer.setSelected(etab.isFumer());
        alcool.setSelected(etab.isAlcool());
        terasse.setSelected(etab.isTerasse());
        wifi.setSelected(etab.isWifi());
        animaux.setSelected(etab.isAnimaux());
        reservation.setSelected(etab.isReservations());
        climatisation.setSelected(etab.isClimatisation());
        livraison.setSelected(etab.isLivraison());
        
        

        int total3 = gr.NbrReview(etab);
        this.total.setText(String.valueOf(total3));

        qualitelabel.setText(String.format("%.2f", etab.getMoyqualite()));
        servicelabel.setText(String.format("%.2f", etab.getMoyservice()));

        final String imageURI = new File("C://wamp64/www/PIDEV/web/uploads/images/" + etab.getImg1()).toURI().toString();
        image2.setImage(new Image(imageURI));

        final String imageURI2 = new File("C://wamp64/www/PIDEV/web/uploads/images/" + etab.getImg2()).toURI().toString();
        image3.setImage(new Image(imageURI2));

        final String imageURI3 = new File("C://wamp64/www/PIDEV/web/uploads/images/" + etab.getImg3()).toURI().toString();
        image4.setImage(new Image(imageURI3));

        final String imageURI4 = new File("C://wamp64/www/PIDEV/web/devis/" + etab.getDevis_name()).toURI().toString();
        image1.setImage(new Image(imageURI4));

        image5.setImage(new Image(imageURI));
        
        ///////map////////
        googleMapView.addMapInializedListener(() -> {
            
        
         MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(etab.getLatitude(),etab.getLongitude()))
                .mapType(MapTypeIdEnum.ROADMAP)
                .zoom(15);
        map = googleMapView.createMap(mapOptions, false);
        MarkerOptions markerOptions = new MarkerOptions();
        
            markerOptions.position(new LatLong(etab.getLatitude(),etab.getLongitude()))
                    .visible(Boolean.TRUE);

            Marker marker = new Marker(markerOptions);
            
            map.addMarker(marker);
        });
        ////// map wfé//////

        AddReview.setOnMouseClicked((event) -> {

            try {

                int total2 = gr.NbrReview(etab);

                java.util.Date d = new java.util.Date();
                java.sql.Date sqldate = new java.sql.Date(d.getTime());
                Review R = new Review(comment.getText(), service.getRating(), qualite.getRating(), titre.getText(), etab, sqldate);
                // calcul de moy qualite et service
                etab.setTotalqualite(etab.getTotalqualite() + qualite.getRating());
                etab.setTotalservice(etab.getTotalservice() + service.getRating());
                if (etab.getTotalqualite() == 0) {
                    etab.setMoyqualite(qualite.getRating());
                } else {
                    etab.setMoyqualite((etab.getTotalqualite()) / (total2 + 1));

                }
                if (etab.getTotalservice() == 0) {
                    etab.setMoyservice(service.getRating());
                } else {
                    etab.setMoyservice((etab.getTotalservice()) / (total2 + 1));

                }

                EditEtablissement EE = new EditEtablissement();

                EE.ModifierReview(etab, etab.getId());
                
                gr.AddReview(R);
FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfilResto.fxml"));
                    Parent root = loader.load();
                    ProfilrestoController PR = loader.getController();
PR.Reviewslist(etab);
                PR.RestoProfil(etab.getId());
                ButtonType bt1 = new ButtonType("Mrigel");
               
               Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, null, bt1);
            alert1.setTitle("Evaluation ajoutée");
            alert1.setContentText("Merci de noter "+etab.getName());
            alert1.setHeaderText(null);
            
            Optional<ButtonType> result = alert1.showAndWait();

//            if (result.isPresent() && result.get() == bt2) {
//            
//                }
//                 PR.panecom.setVisible(false);
//                 PR.dejaevaluated.setVisible(true);
//                 PR.dejaevaluated.setText("ti mok deja notit na3n ****");
                   
                    name.getScene().setRoot(root);
                   
                   
                    
                

            } catch (SQLException ex) {
                Logger.getLogger(ProfilrestoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ProfilrestoController.class.getName()).log(Level.SEVERE, null, ex);
            } 

        });

    }

    public void Reviewslist(etablissement e) throws SQLException {

        tab.getSelectionModel().select(tabX);
        GestionReviews gr = new GestionReviews();
        listreview = gr.ListReviews(e);

        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();
        for (Review rev : listreview) {
            TilePane a = new TilePane();
            Label title = new Label(rev.getTitre());
            Label com = new Label(rev.getCommentaire());
            title.setStyle("-fx-text-fill: #ff214f;-fx-font-size:20px;-fx-font-weight: bold");
            FontAwesomeIconView star = new FontAwesomeIconView(FontAwesomeIcon.STAR);
            FontAwesomeIconView trash = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            FontAwesomeIconView edit = new FontAwesomeIconView(FontAwesomeIcon.EDIT);
            edit.setStyle("-glyph-size:20px;-fx-fill:RED");
            trash.setStyle("-glyph-size:20px;-fx-fill:GREEN");

            FontAwesomeIconView star2 = new FontAwesomeIconView(FontAwesomeIcon.STAR);
            star.setStyle("-glyph-size:20;-fx-fill:#FFD700");
            star2.setStyle("-glyph-size:20px;-fx-fill:#FFD700");
            Label servic = new Label("Service : " + String.valueOf(rev.getService()));
            Label qualit = new Label("Qualite : " + String.valueOf(rev.getQualite()));
            Label dat = new Label(String.valueOf(rev.getDate()));
            dat.setStyle("-fx-font-weight: bold");
            qualit.setStyle("-fx-font-size:15px;;-fx-font-weight: bold");
            servic.setStyle("-fx-font-size:15px;;-fx-font-weight: bold");

            a.setStyle("-fx-background-color: #D8D8D8;-fx-max-height:250px;-fx-min-height:200px;-fx-max-width:25px;-fx-min-witdth:20px;");
            Label espace = new Label();
            com.setPrefWidth(550);
            com.setWrapText(true);
            a.getChildren().add(new VBox(5, title, dat, com, new HBox(4, qualit, star, servic, star2), new HBox(400, espace, new HBox(20, trash, edit))));
            a.setPadding(new javafx.geometry.Insets(10, 5, 10, 5));
            a.setMaxSize(600, 150);
            a.setPrefColumns(2);
            c.getChildren().add(a);
            trash.setOnMouseClicked((MouseEvent event) -> {
                GestionReviews grev = new GestionReviews();
                try {

                    int newtotal = gr.NbrReview(e);
                    e.setTotalqualite(e.getTotalqualite() - rev.getQualite());
                    e.setTotalservice(e.getTotalservice() - rev.getService());
                    e.setMoyqualite(e.getTotalqualite() / (newtotal - 1));
                    e.setMoyservice(e.getTotalservice() / (newtotal - 1));
                    EditEtablissement EE = new EditEtablissement();
                    EE.ModifierReview(e, e.getId());
                    grev.DeleteReveiw(rev.getId());
                    System.out.println("deleted");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfilResto.fxml"));
                    Parent root = loader.load();
                    ProfilrestoController PR = loader.getController();

                    PR.RestoProfil(e.getId());
                    PR.Reviewslist(e);
                    name.getScene().setRoot(root);
                } catch (SQLException ex) {
                    Logger.getLogger(ProfilrestoController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ProfilrestoController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
        }
        c.setPrefColumns(2);
        c.setPadding(new javafx.geometry.Insets(50, 0, 0, 0));
        c.setHgap(40);
        c.setVgap(40);
        b.getChildren().add(c);
        fp.getChildren().add(b);
        scroll.getChildren().add(fp);
        
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ListResto.fxml"));
        Parent root = loader.load();
        
        address.getScene().setRoot(root);
        
    }
   

        }



