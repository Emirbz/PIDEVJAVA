/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

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
import PIDEV.Entities.Etablissement;
import PIDEV.Services.AddEtablissement;
import PIDEV.Services.DeleteEtablissement;
import PIDEV.Services.GestionReviews;
import PIDEV.Services.ListEtablissement;
import PIDEV.Views.MapController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class ListrestoController implements Initializable {

    private TableView<Etablissement> table;
    private TableColumn<Etablissement, String> nom;
    private TableColumn<Etablissement, String> address;
    private TableColumn<Etablissement, String> email;
    private TableColumn<Etablissement, String> phone;
    private TableColumn<Etablissement, String> lundisamedio;
    private TableColumn<Etablissement, String> lundisamedif;
    private TableColumn<Etablissement, String> dimancheo;
    private TableColumn<Etablissement, String> dimanchef;
    private ObservableList<Etablissement> data;
    @FXML
    private JFXTextField recherchetext;
    private TableColumn<?, ?> souscat;
    private FontAwesomeIconView home;
    @FXML
    private ScrollPane pane;
    @FXML
    private AnchorPane ap;
    @FXML
    private JFXComboBox<String> trisouscat;
    @FXML
    private JFXComboBox<String> tri;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tri.getItems().addAll("Meilleur Qualité");
        tri.getItems().addAll("Meilleur Service");
        tri.getItems().addAll("Default order");
        tri.setValue("Default order");
        try {

            trisouscat.getItems().add("Tous");
            AddEtablissement ae = new AddEtablissement();
            for (String s : ae.listsouscat()) {
                trisouscat.getItems().add(s);

            }

            trisouscat.setValue("Tous");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        
        ListEtablissement LDS = new ListEtablissement();
        data = LDS.ListRestaurant();
        for ( Etablissement d : data) {
            
            try {
               
           
       
        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/DivList.fxml"));
                Parent root = (Pane) loader.load();
                DivListController DHC = loader.getController();
                DHC.LoadValues(d);
               
                //   c.setVgap(40);
                c.getChildren().removeAll();
               
                
                c.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setPrefColumns(3);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);
        tri.setOnAction((event) -> {
            String scvalue = trisouscat.getValue();

            String abc = tri.getValue();
             if ("Tous".equals(scvalue)&&("Default order").equals(abc)) {

               
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/HomePage.fxml"));
                    Parent root = loader.load();
                    HomePageController pu = loader.getController();
                    AnchorPane Rev = FXMLLoader.load(getClass().getResource("../Views/ListResto.fxml"));
                    pu.setNode(Rev);
                    recherchetext.getScene().setRoot(root);
                   
                } catch (IOException ex) {
                    Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
             else if (("Tous".equals(scvalue)))  {
                trix(abc, url, rb);
                System.out.println(scvalue);
            }
            else if ("Tous".equals(scvalue)&&"Default order".equals(abc))
                    {
                 try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/HomePage.fxml"));
                    Parent root = loader.load();
                    HomePageController pu = loader.getController();
                    AnchorPane Rev = FXMLLoader.load(getClass().getResource("../Views/ListResto.fxml"));
                    pu.setNode(Rev);
                    
                    recherchetext.getScene().setRoot(root);
                   
                } catch (IOException ex) {
                    Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
                    }


            else if (!("Tous".equals(scvalue))&& "Default order".equals(abc)) 
                { trix( "id", url, rb);}
                
            else
            {   try {
                affichetrie(scvalue, url, rb, abc);
                } catch (SQLException ex) {
                    Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
                }
}
            
        });
        trisouscat.setOnAction((event) -> {
            tri.setValue("Default order");

            String scvalue = trisouscat.getValue();
            if ("Tous".equals(scvalue)) {

                 try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/HomePage.fxml"));
                    Parent root = loader.load();
                    HomePageController pu = loader.getController();
                    AnchorPane Rev = FXMLLoader.load(getClass().getResource("../Views/ListResto.fxml"));
                    pu.setNode(Rev);
                    recherchetext.getScene().setRoot(root);
                   
                } catch (IOException ex) {
                    Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            String trix = tri.getValue();
            if (trix == "Default order") {
                try {
                    affichetrie(scvalue, url, rb, "id");
                } catch (SQLException ex) {
                    Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    affichetrie(scvalue, url, rb, trix);
                } catch (SQLException ex) {
                    Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            ////////// end 9dima////////////

//        editicon.setVisible(false);
//        deleteicon.setVisible(false);
        });
                  
       
        
            
            //////////// 9dima ////////////////
//        ListEtablissement le = new ListEtablissement();
//        data = le.ListRestaurant();
//        TilePane b = new TilePane();
//        b.setPadding(new javafx.geometry.Insets(30));
//        TilePane c = new TilePane();
//
//        for (Etablissement etab : data) {
//
//            TilePane a = new TilePane();
//            Label title = new Label(etab.getName());
//            title.setStyle("-fx-font-size:22px;-fx-font-weight: bold");
//            Label qualite = new Label("Qualite :");
//            qualite.setStyle("-fx-font-size:12px;-fx-font-weight: bold");
//
//            Label service = new Label("Service :");
//            service.setStyle("-fx-font-size:12px;-fx-font-weight: bold");
//
//            HBox p = new HBox();
//            for (int i = 1; i < Integer.parseInt(String.valueOf(etab.getMoyqualite()).substring(0, 1)); i++) {
//                FontAwesomeIconView star = new FontAwesomeIconView(FontAwesomeIcon.THUMBS_UP);
//                star.setStyle("-glyph-size:20;-fx-fill:#FFD700");
//                p.getChildren().add(star);
//            }
//            HBox pp = new HBox();
//            for (int i = 1; i < Integer.parseInt(String.valueOf(etab.getMoyservice()).substring(0, 1)); i++) {
//                FontAwesomeIconView star = new FontAwesomeIconView(FontAwesomeIcon.STAR);
//                star.setStyle("-glyph-size:20;-fx-fill:#FFD700");
//                pp.getChildren().add(star);
//            }
//
//            Label id = new Label(String.valueOf(etab.getId()));
//            id.setVisible(false);
//            final String imageURI = new File("C:/wamp64/www/PIDEV/web/devis/" + etab.getDevis_name()).toURI().toString();
//            ImageButton imgetab = new ImageButton(imageURI);
//            FontAwesomeIconView editicon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
//            editicon.setVisible(false);
//            FontAwesomeIconView deleteicon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
//            deleteicon.setVisible(false);
//            imgetab.setOnMousePressed(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    ListEtablissement le = new ListEtablissement();
//                    Etablissement selectedetab = le.ListRestaurant().filtered(e -> e.getId() == Integer.parseInt(id.getText())).get(0);
//                    editicon.setVisible(true);
//
//                    deleteicon.setVisible(true);
//
//                    if (event.getClickCount() == 2) {
//                        doubleclick(event, selectedetab);
//                    }
//
//                }
//            });
//
//            editicon.setStyle("-glyph-size:20;-fx-fill:#3366cc");
//
//            editicon.setOnMouseClicked((event) -> {
//                editetab(etab, url, rb);
//            });
//
//            deleteicon.setStyle("-glyph-size:20;-fx-fill:ff3333");
//            deleteicon.setOnMouseClicked((event) -> {
//                deleteetab(etab);
//            });
//            a.getChildren().add(new VBox(5, new HBox(270, editicon, deleteicon), imgetab, id, title, new HBox(2, qualite, p), new HBox(2, service, pp)));
//            //   a.setPadding(new javafx.geometry.Insets(10, 5, 10, 5));
//            a.setMaxSize(300, 150);
//            //  a.setPrefColumns(2);
//            c.getChildren().add(a);
//        }
//        c.setPrefColumns(3);
//        c.setPadding(new javafx.geometry.Insets(10));
//        c.setHgap(40);
//        c.setVgap(40);
//        b.getChildren().add(c);
//        b.setPrefWidth(1000);
//        pane.setContent(b);
//        
       
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
//        Etablissement etab = table.getSelectionModel().getSelectedItem();
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
//        Etablissement e = table.getSelectionModel().getSelectedItem();
//
//        d.DeleteEtablissement(e.getId());
//        ActionEvent event1 = null;
//        Listrestaurant(event1);
//    }
    private void home(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Home.fxml"));
        Parent root = loader.load();
        home.getScene().setRoot(root);

    }

//    @FXML
//    private void Profil(ActionEvent event) throws IOException, SQLException {
////      TabPane profilX = FXMLLoader.load(getClass().getResource("../Views/ProfilResto.fxml"));
////      FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../Views/Dashboard.fxml"));
////       Parent root2 = loader2.load(); 
////        DashboardController dc = loader2.getController();
////        dc.setNode(profilX);
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfilResto.fxml"));
//        Parent root = loader.load();
//        ProfilrestoController PR = loader.getController();
//        Etablissement etab = table.getSelectionModel().getSelectedItem();
//        PR.Reviewslist(etab);
//        PR.RestoProfil(etab.getId());
//
//        Stage stage = new Stage(StageStyle.DECORATED);
//
//        stage.setTitle("Modifier deal");
//        stage.setScene(new Scene(root));
//        stage.show();
//
//        //  table.getScene().setRoot(root);
//    }
    @FXML
    private void RechercheDynamique(KeyEvent event) {
        FilteredList<Etablissement> filteredData = new FilteredList<>(data, e -> true);
        recherchetext.setOnKeyReleased(e
                -> {
            recherchetext.textProperty().addListener((ObservableValue<? extends String> ObservableValue, String oldValue, String newValue) -> {
                filteredData.setPredicate((Predicate<? super Etablissement>) new Predicate<Etablissement>() {
                    @Override
                    public boolean test(Etablissement d) {
                        if (newValue == null || newValue.isEmpty()) {
                            
                            return true;

                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        return d.getName().toLowerCase().contains(lowerCaseFilter.subSequence(0, lowerCaseFilter.length()));
                    }
                });
            });
            TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();
        ListEtablissement LDS = new ListEtablissement();
        for ( Etablissement d : filteredData) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/DivList.fxml"));
                Parent root = (Pane) loader.load();
                DivListController DHC = loader.getController();
                DHC.LoadValues(d);
               
                //   c.setVgap(40);
                c.getChildren().removeAll();
               
                
                c.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setPrefColumns(3);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);
        
            ////

            ////
        });
    }

    @FXML
    private void openmap(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Map.fxml"));
        Parent root = loader.load();
        MapController m = loader.getController();

        m.getSearchAP().setVisible(false);

        Stage stage = new Stage(StageStyle.DECORATED);

        stage.setTitle("Liste des restaurant");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public ObservableList<Etablissement> getData() {
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

//    public void HideButtons(Node b, Node c,) {
//        recherchetext.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                d.setVisible(false);
//                e.setVisible(false);
//            }
//        });
//        c.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                d.setVisible(false);
//                e.setVisible(false);
//            }
//        });
//        ap.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                d.setVisible(false);
//                e.setVisible(false);
//            }
//        });
//        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                d.setVisible(false);
//                e.setVisible(false);
//            }
//        });
//        b.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                d.setVisible(false);
//                e.setVisible(false);
//            }
//        });
//    }
  

    public void deleteetab(Etablissement selectedetab) {
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

    public void editetab(Etablissement etab, URL url, ResourceBundle rb) {
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

    public void affichetrie(String scvalue, URL url, ResourceBundle rb, String trisq) throws SQLException {

          ListEtablissement le = new ListEtablissement();
        data = le.ListRestauranttrie(scvalue, trisq);
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();

         for ( Etablissement d : data) {
             System.out.println(d.getSouscat().getNom());
            
            try {
               
           
       
        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/DivList.fxml"));
                Parent root = (Pane) loader.load();
                DivListController DHC = loader.getController();
                
                DHC.LoadValues(d);
               
               
                //   c.setVgap(40);
                c.getChildren().removeAll();
               
                
                c.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setPrefColumns(3);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);

    }

    public void trix(String trisq, URL url, ResourceBundle rb) {

        ListEtablissement le = new ListEtablissement();
        data = le.Triservice(trisq);
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();

         for ( Etablissement d : data) {
             System.out.println(d.getSouscat().getNom());
            
            try {
               
           
       
        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/DivList.fxml"));
                Parent root = (Pane) loader.load();
                DivListController DHC = loader.getController();
                
                DHC.LoadValues(d);
               
               
                //   c.setVgap(40);
                c.getChildren().removeAll();
               
                
                c.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setPrefColumns(3);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);
    }
}
