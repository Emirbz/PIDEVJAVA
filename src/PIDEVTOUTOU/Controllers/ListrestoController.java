/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEVTOUTOU.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import PIDEVTOUTOU.Entities.etablissement;
import PIDEVTOUTOU.Services.DeleteEtablissement;
import PIDEVTOUTOU.Services.EditEtablissement;
import PIDEVTOUTOU.Services.ListEtablissement;
import PIDEVTOUTOU.Views.DashboardController;
import PIDEVTOUTOU.Views.MapController;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class ListrestoController implements Initializable {

    private TableView<etablissement> table;
    private TableColumn<etablissement, String> nom;
    private TableColumn<etablissement, String> address;
    private TableColumn<etablissement, String> email;
    private TableColumn<etablissement, String> phone;
    private TableColumn<etablissement, String> lundisamedio;
    private TableColumn<etablissement, String> lundisamedif;
    private TableColumn<etablissement, String> dimancheo;
    private TableColumn<etablissement, String> dimanchef;
    private ObservableList<etablissement> data;
    @FXML
    private JFXTextField recherchetext;
    @FXML
    private JFXButton delete;
    private TableColumn<?, ?> souscat;
    @FXML
    private JFXButton edit;
    @FXML
    private FontAwesomeIconView home;
    @FXML
    private JFXButton profil;
    @FXML
    private ScrollPane pane;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListEtablissement le = new ListEtablissement();
        data = le.ListRestaurant();
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();
        for (etablissement etab : data) {
            
            TilePane a = new TilePane();
            Label title = new Label(etab.getName());
            title.setStyle("-fx-font-size:22px;-fx-font-weight: bold");
            Label qualite = new Label("Qualite :");
            qualite.setStyle("-fx-font-size:12px;-fx-font-weight: bold");
           
            Label service = new Label("Service :");
            service.setStyle("-fx-font-size:12px;-fx-font-weight: bold");
           
            HBox p = new HBox();
            for (int i = 1; i < Integer.parseInt(String.valueOf(etab.getMoyqualite()).substring(0, 1)); i++) {
                FontAwesomeIconView star = new FontAwesomeIconView(FontAwesomeIcon.STAR);
                star.setStyle("-glyph-size:20;-fx-fill:#FFD700");
                p.getChildren().add(star);
            }
            HBox pp = new HBox();
            for (int i = 1; i < Integer.parseInt(String.valueOf(etab.getMoyservice()).substring(0, 1)); i++) {
                FontAwesomeIconView star = new FontAwesomeIconView(FontAwesomeIcon.STAR);
                star.setStyle("-glyph-size:20;-fx-fill:#FFD700");
                pp.getChildren().add(star);
            }

            Label id = new Label(String.valueOf(etab.getId()));
            id.setVisible(false);
            final String imageURI = new File("C:/wamp64/www/PIDEV/web/devis/" + etab.getDevis_name()).toURI().toString();
            ImageButton imgetab = new ImageButton(imageURI);
            imgetab.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ListEtablissement le = new ListEtablissement();
                    etablissement selectedetab = le.ListRestaurant().filtered(e -> e.getId() == Integer.parseInt(id.getText())).get(0);
                    edit.setVisible(true);
                    edit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            editetab(etab, url, rb);
                        }
                    });
                    delete.setVisible(true);
                    delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            deleteetab(selectedetab);

                        }
                    });
                    if (event.getClickCount() == 2) {
                        doubleclick(event, selectedetab);
                    }
                }
            });
            a.getChildren().add(new VBox(5, imgetab, id, title, new HBox(2, qualite, p), new HBox(2, service, pp)));
            //   a.setPadding(new javafx.geometry.Insets(10, 5, 10, 5));
            a.setMaxSize(300, 150);
            //  a.setPrefColumns(2);
            c.getChildren().add(a);
        }
        c.setPrefColumns(3);
        c.setPadding(new javafx.geometry.Insets(10));
        c.setHgap(40);
        c.setVgap(40);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);
        HideButtons(c, b);
        edit.setVisible(false);
        delete.setVisible(false);
    }

    public void Listrestaurant(ActionEvent event) throws IOException {
        ListEtablissement le = new ListEtablissement();
        data = FXCollections.observableArrayList(le.ListRestaurant());
        table.setItems(data);
        nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        lundisamedio.setCellValueFactory(new PropertyValueFactory<>("lundisamedio"));
        lundisamedif.setCellValueFactory(new PropertyValueFactory<>("lundisamedif"));
        dimancheo.setCellValueFactory(new PropertyValueFactory<>("dimancheo"));
        dimanchef.setCellValueFactory(new PropertyValueFactory<>("dimanchef"));

        souscat.setCellValueFactory(new PropertyValueFactory<>("souscat"));

    }

//    @FXML
//    private void ModifierRestaurant(ActionEvent event) throws IOException, SQLException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/EditResto.fxml"));
//        Parent root = loader.load();
//        EditrestoController editrest = loader.getController();
//        etablissement etab = table.getSelectionModel().getSelectedItem();
//        editrest.showresto(etab);
//        Stage stage = new Stage(StageStyle.DECORATED);
//
//        stage.setTitle("Modifier deal");
//        stage.setScene(new Scene(root));
//        stage.show();
////        table.getScene().setRoot(root);
//    }
//    @FXML
//    private void DeleteRestaurant(MouseEvent event) throws SQLException, IOException {
//
//        DeleteEtablissement d = new DeleteEtablissement();
//
//        etablissement e = table.getSelectionModel().getSelectedItem();
//
//        d.DeleteEtablissement(e.getId());
//        ActionEvent event1 = null;
//        Listrestaurant(event1);
//    }
    @FXML
    private void home(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Home.fxml"));
        Parent root = loader.load();
        home.getScene().setRoot(root);

    }

    @FXML
    private void Profil(ActionEvent event) throws IOException, SQLException {
//      TabPane profilX = FXMLLoader.load(getClass().getResource("../Views/ProfilResto.fxml"));
//      FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../Views/Dashboard.fxml"));
//       Parent root2 = loader2.load(); 
//        DashboardController dc = loader2.getController();
//        dc.setNode(profilX);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfilResto.fxml"));
        Parent root = loader.load();
        ProfilrestoController PR = loader.getController();
        etablissement etab = table.getSelectionModel().getSelectedItem();
        PR.Reviewslist(etab);
        PR.RestoProfil(etab.getId());

        Stage stage = new Stage(StageStyle.DECORATED);

        stage.setTitle("Modifier deal");
        stage.setScene(new Scene(root));
        stage.show();

        //  table.getScene().setRoot(root);
    }

    @FXML
    private void RechercheDynamique(KeyEvent event) {
        FilteredList<etablissement> filteredData = new FilteredList<>(data, e -> true);
        recherchetext.setOnKeyReleased(e
                -> {
            recherchetext.textProperty().addListener((ObservableValue<? extends String> ObservableValue, String oldValue, String newValue) -> {
                filteredData.setPredicate((Predicate<? super etablissement>) new Predicate<etablissement>() {
                    @Override
                    public boolean test(etablissement d) {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;

                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        return d.getName().toLowerCase().contains(lowerCaseFilter.subSequence(0, lowerCaseFilter.length()));
                    }
                });
            });
            TilePane test = new TilePane();
            test.setPadding(new javafx.geometry.Insets(30));
            TilePane ca = new TilePane();
            filteredData.forEach((f) -> {
                TilePane a = new TilePane();
                Label title = new Label(f.getName());
                Label id = new Label(String.valueOf(f.getId()));
                id.setVisible(false);
                final String imageURI = new File("C:/wamp64/www/PIDEV/web/devis/" + f.getDevis_name()).toURI().toString();
                ImageButton imgetab = new ImageButton(imageURI);
                a.getChildren().add(new VBox(5, imgetab, title, id));
                a.setMaxSize(300, 150);
                ca.getChildren().add(a);

            });
            ca.setPrefColumns(3);
            ca.setPadding(new javafx.geometry.Insets(10));
            ca.setHgap(40);
            ca.setVgap(40);
            if (ca.getChildren().isEmpty()) {
                Label NotFound = new Label("Aucun résultats trouvés");
                ca.getChildren().add(NotFound);
            }
            test.getChildren().add(ca);
            test.setPrefWidth(1000);
            pane.setContent(test);
            ////

            ////
        });
    }

    @FXML
    private void openmap(MouseEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Map.fxml"));
        Parent root = loader.load();
        MapController m = loader.getController();
       
         m.etabmarkers(data);

        Stage stage = new Stage(StageStyle.DECORATED);

        stage.setTitle("Liste des restaurant");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public  ObservableList<etablissement> getData() {
        return data;
    }

    

    class ImageButton extends Button {

        int s = 0;
        private final String STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 5, 5, 5, 5;";
        private final String STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 6 4 4 6;";

        public ImageButton(String imageurl) {
            ImageView img = new ImageView(new Image(imageurl));
            img.setFitHeight(150);
            img.setFitWidth(300);
            setGraphic(img);
            setStyle(STYLE_NORMAL);
        }

    }

    public void HideButtons(Node b, Node c) {
        recherchetext.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                edit.setVisible(false);
                delete.setVisible(false);
            }
        });
        c.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                edit.setVisible(false);
                delete.setVisible(false);
            }
        });
        ap.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                edit.setVisible(false);
                delete.setVisible(false);
            }
        });
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                edit.setVisible(false);
                delete.setVisible(false);
            }
        });
        b.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                edit.setVisible(false);
                delete.setVisible(false);
            }
        });

    }

    public void doubleclick(MouseEvent event, etablissement selectedetab) {
        if (event.getClickCount() == 2) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfilResto.fxml"));
                Parent root = loader.load();
                ProfilrestoController DDC = loader.getController();
                 DDC.Reviewslist(selectedetab);
                DDC.RestoProfil(selectedetab.getId());
               
                recherchetext.getScene().setRoot(root);
            } catch (IOException | SQLException ex) {
                Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteetab(etablissement selectedetab) {
        try {
            DeleteEtablissement de = new DeleteEtablissement();
            de.DeleteEtablissement(selectedetab.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ListResto.fxml"));
            Parent root = loader.load();
            recherchetext.getScene().setRoot(root);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editetab(etablissement etab, URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/EditResto.fxml"));
            Parent root = loader.load();
            EditrestoController ec = loader.getController();
            ec.showresto(etab);
            ScrollPane r = new ScrollPane(root);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Modifier etab");
            stage.setScene(new Scene(r));
            stage.show();
            ec.getEdit().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    stage.close();
                    initialize(url, rb);

                }
            });
        } catch (IOException ex) {
            Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
