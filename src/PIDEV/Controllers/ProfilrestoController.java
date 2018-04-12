/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Review;
import PIDEV.Entities.User;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import PIDEV.Entities.Etablissement;
import PIDEV.Services.EditEtablissement;
import PIDEV.Services.GestionReviews;
import PIDEV.Services.ListEtablissement;
import PIDEV.Utils.MyConnexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTabPane;
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
//import javafx.scene.control.Alert;
import com.jfoenix.controls.JFXButton.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import java.util.Calendar;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

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
    @FXML
    private Circle circleuser;
    @FXML
    private Label nomuser;
    @FXML
    private Label dateuser;
    @FXML
    private Label phoneuser;
    @FXML
    private Label addressuser;
    @FXML
    private JFXTabPane tab;
    @FXML
    private Label dejanote;
    @FXML
    private JFXButton reserver;
    @FXML
    private JFXButton listReservation;
    @FXML
    private Label idEtab;
    @FXML
    private StackPane stackpane;
    @FXML
    private StackPane stackpane1;
    @FXML
    private StackPane stackpane2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    public void RestoProfil(int id) throws SQLException {

        User user = PIDEV.Views.FirstFrame.user;
        if (user == null) {
            panecom.setVisible(false);
            dejanote.setText("Connectez-Vous pour noter cet Etablissement");
            dejanote.setVisible(true);
        }

        GestionReviews gr = new GestionReviews();

        tab.getSelectionModel().select(0);
        Connection cn = MyConnexion.getInstance().getConnection();

        ListEtablissement s = new ListEtablissement();

        Etablissement etab = s.ListRestaurant().filtered(e -> e.getId() == id).get(0);
        if (PIDEV.Views.FirstFrame.user != null) {
            if (gr.DejaNote(user, etab) > 0) {
                dejanote.setVisible(true);
                panecom.setVisible(false);
            }
        }

        idEtab.setText(String.valueOf(etab.getId()));
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
        String charname = etab.getIduser().getName().substring(0, 1).toUpperCase();
        String charsurname = etab.getIduser().getSurname().substring(0, 1).toUpperCase();
        nomuser.setText(charname + etab.getIduser().getName().substring(1) + " " + charsurname + etab.getIduser().getSurname().substring(1));

        Calendar cal = Calendar.getInstance();
        cal.setTime(etab.getIduser().getDate());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        String monthx = null;
        if (month == 2) {
            monthx = "Fevrier";
        }
        if (month == 3) {
            monthx = "Mars";
        }
        if (month == 4) {
            monthx = "Avril";
        }

        dateuser.setText("Membre depuis " + monthx + "," + year);
        addressuser.setText(etab.getIduser().getAddress());
        phoneuser.setText(etab.getIduser().getPhone());
        Image imageURIuser = new Image("file:C:/wamp64/www/PIDEV/web/devis/" + etab.getIduser().getDevis_name());
        circleuser.setFill(new ImagePattern(imageURIuser));

        int total3 = gr.NbrReview(etab);
        this.total.setText(String.valueOf(total3));

        qualitelabel.setText(String.format("%.2f", etab.getMoyqualite()));
        servicelabel.setText(String.format("%.2f", etab.getMoyservice()));

        final String imageURI = new File("C://wamp64/www/PIDEV/web/uploads/images/" + etab.getImg1()).toURI().toString();
        image2.setImage(new Image(imageURI));

        final String imageURI2 = new File("C://wamp64/www/PIDEV/web/uploads/images/" + etab.getImg2()).toURI().toString();
        image3.setImage(new Image(imageURI2));
        image3.setOnMouseClicked((event) -> {
             JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setPrefHeight(700);
        dialogLayout.setPrefWidth(1000);
       
        ImageView i = new ImageView(new Image(imageURI2));
        
          
        i.setFitHeight(700);
        i.setFitWidth(1000);
        dialogLayout.setBody(i);
        JFXDialog dialog = new JFXDialog(stackpane1, dialogLayout, JFXDialog.DialogTransition.CENTER);
        dialog.setStyle("-fx-background-color:transparent");
        dialog.setAlignment(Pos.TOP_CENTER);

        stackpane1.setVisible(true);
        stackpane1.toFront();
      
        dialog.show();
        });
        
          

        final String imageURI3 = new File("C://wamp64/www/PIDEV/web/uploads/images/" + etab.getImg3()).toURI().toString();
        image4.setImage(new Image(imageURI3));
        image4.setOnMouseClicked((event) -> {
             JFXDialogLayout dialogLayouta = new JFXDialogLayout();
        dialogLayouta.setPrefHeight(700);
        dialogLayouta.setPrefWidth(1000);
       
        ImageView k = new ImageView(new Image(imageURI3));
        
          
        k.setFitHeight(700);
        k.setFitWidth(1000);
        dialogLayouta.setBody(k);
        JFXDialog dialogk = new JFXDialog(stackpane2, dialogLayouta, JFXDialog.DialogTransition.CENTER);
        dialogk.setStyle("-fx-background-color:transparent");
        dialogk.setAlignment(Pos.TOP_CENTER);

        stackpane2.setVisible(true);
        stackpane2.toFront();
      
        dialogk.show();
        });
        

        final String imageURI4 = new File("C://wamp64/www/PIDEV/web/devis/" + etab.getDevis_name()).toURI().toString();
        image1.setImage(new Image(imageURI4));

        image5.setImage(new Image(imageURI));
        image5.setOnMouseClicked((event) -> {
             JFXDialogLayout dialogLayoutb = new JFXDialogLayout();
        dialogLayoutb.setPrefHeight(700);
        dialogLayoutb.setPrefWidth(1000);
       
        ImageView j = new ImageView(new Image(imageURI4));
        
          
        j.setFitHeight(700);
        j.setFitWidth(1000);
        dialogLayoutb.setBody(j);
        JFXDialog dialog1 = new JFXDialog(stackpane, dialogLayoutb, JFXDialog.DialogTransition.CENTER);
        dialog1.setStyle("-fx-background-color:transparent");
        dialog1.setAlignment(Pos.TOP_CENTER);

        stackpane.setVisible(true);
        stackpane.toFront();
      
        dialog1.show();
        });
        

        ///////map////////
        googleMapView.addMapInializedListener(() -> {

            MapOptions mapOptions = new MapOptions();

            mapOptions.center(new LatLong(etab.getLatitude(), etab.getLongitude()))
                    .mapType(MapTypeIdEnum.ROADMAP)
                    .zoom(15);
            map = googleMapView.createMap(mapOptions, false);
            MarkerOptions markerOptions = new MarkerOptions();

            markerOptions.position(new LatLong(etab.getLatitude(), etab.getLongitude()))
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
                Review R = new Review(comment.getText(), service.getRating(), qualite.getRating(), titre.getText(), etab, sqldate, PIDEV.Views.FirstFrame.user);
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
//                PR.RestoProfil(etab.getId());
//                ButtonType bt1 = new ButtonType("Mrigel");
//
//                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, null, bt1);
//                alert1.setTitle("Evaluation ajoutée");
//                alert1.setContentText("Merci de noter " + etab.getName());
//                alert1.setHeaderText(null);
//
//                Optional<ButtonType> result = alert1.showAndWait();

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

    public void Reviewslist(Etablissement e) throws SQLException {

//        tab.getSelectionModel().select(tabX);
//        GestionReviews gr = new GestionReviews();
//        listreview = gr.ListReviews(e);
//
//        TilePane b = new TilePane();
//        b.setPadding(new javafx.geometry.Insets(30));
//        TilePane c = new TilePane();
//        for (Review rev : listreview) {
//            TilePane a = new TilePane();
//            Label title = new Label(rev.getTitre());
//            Label com = new Label(rev.getCommentaire());
//            title.setStyle("-fx-text-fill: #ff214f;-fx-font-size:20px;-fx-font-weight: bold");
//            FontAwesomeIconView star = new FontAwesomeIconView(FontAwesomeIcon.STAR);
//            FontAwesomeIconView trash = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
//            FontAwesomeIconView edit = new FontAwesomeIconView(FontAwesomeIcon.EDIT);
//            edit.setStyle("-glyph-size:20px;-fx-fill:RED");
//            trash.setStyle("-glyph-size:20px;-fx-fill:GREEN");
//
//            FontAwesomeIconView star2 = new FontAwesomeIconView(FontAwesomeIcon.STAR);
//            star.setStyle("-glyph-size:20;-fx-fill:#FFD700");
//            star2.setStyle("-glyph-size:20px;-fx-fill:#FFD700");
//            Label servic = new Label("Service : " + String.valueOf(rev.getService()));
//            Label qualit = new Label("Qualite : " + String.valueOf(rev.getQualite()));
//            Label dat = new Label();
//            dat.setStyle("-fx-font-weight: bold");
//            qualit.setStyle("-fx-font-size:15px;;-fx-font-weight: bold");
//            servic.setStyle("-fx-font-size:15px;;-fx-font-weight: bold");
//
//            a.setStyle("-fx-background-color: #D8D8D8;-fx-max-height:250px;-fx-min-height:200px;-fx-max-width:25px;-fx-min-witdth:20px;");
//            Label espace = new Label();
//            com.setPrefWidth(550);
//            com.setWrapText(true);
//            a.getChildren().add(new VBox(5, title, dat, com, new HBox(4, qualit, star, servic, star2), new HBox(400, espace, new HBox(20, trash, edit))));
//            a.setPadding(new javafx.geometry.Insets(10, 5, 10, 5));
//            a.setMaxSize(600, 150);
//            a.setPrefColumns(2);
//            c.getChildren().add(a);
//            trash.setOnMouseClicked((MouseEvent event) -> {
//                GestionReviews grev = new GestionReviews();
//                try {
//
//                    int newtotal = gr.NbrReview(e);
//                    e.setTotalqualite(e.getTotalqualite() - rev.getQualite());
//                    e.setTotalservice(e.getTotalservice() - rev.getService());
//                    e.setMoyqualite(e.getTotalqualite() / (newtotal - 1));
//                    e.setMoyservice(e.getTotalservice() / (newtotal - 1));
//                    EditEtablissement EE = new EditEtablissement();
//                    EE.ModifierReview(e, e.getId());
//                    grev.DeleteReveiw(rev.getId());
//                    System.out.println("deleted");
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfilResto.fxml"));
//                    Parent root = loader.load();
//                    ProfilrestoController PR = loader.getController();
//
//                    PR.RestoProfil(e.getId());
//                    PR.Reviewslist(e);
//                    name.getScene().setRoot(root);
//                } catch (SQLException ex) {
//                    Logger.getLogger(ProfilrestoController.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IOException ex) {
//                    Logger.getLogger(ProfilrestoController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            });
//        }
//        c.setPrefColumns(2);
//        c.setPadding(new javafx.geometry.Insets(50, 0, 0, 0));
//        c.setHgap(40);
//        c.setVgap(40);
//        b.getChildren().add(c);
//        fp.getChildren().add(b);
//        scroll.getChildren().add(fp);
        TilePane b = new TilePane();
        tab.getSelectionModel().select(tabX);
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();

        GestionReviews gr = new GestionReviews();
        listreview = gr.ListReviews(e);

        for (Review d : listreview) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/DivReview.fxml"));
                Parent root = (Pane) loader.load();
                DivReviewController DHC = loader.getController();
                DHC.LoadValues(d, e);

                //   c.setVgap(40);
                c.getChildren().removeAll();

                c.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ProfilrestoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setPrefColumns(2);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(25);
        c.setVgap(50);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        scroll.getChildren().add(b);
        

    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ListResto.fxml"));
        Parent root = loader.load();

        address.getScene().setRoot(root);

    }

    @FXML
    private void reserver(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/AddReservationResto.fxml"));
        Parent root = loader.load();

        AddReservationRestoController addRes = loader.getController();

        addRes.setIdEtab(Integer.parseInt(idEtab.getText()));

        reserver.getScene().setRoot(root);
    }

    @FXML
    private void listReservationPage(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ListReservationResto.fxml"));
        Parent root = loader.load();
        ListEtablissement le = new ListEtablissement();
        ListReservationRestoController lrsc = loader.getController();
        Etablissement etab = le.ListRestaurant().filtered(e -> e.getId() == Integer.parseInt(idEtab.getText())).get(0);
        lrsc.listReservation(etab);

        name.getScene().setRoot(root);
    }


}
