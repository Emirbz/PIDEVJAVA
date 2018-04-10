/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Etablissement;
import PIDEV.Services.AddEtablissement;
import PIDEV.Services.ListEtablissement;
import PIDEV.Views.MapController;
import PIDEV.Views.MapController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class ListHotelController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private JFXTextField recherchetext;
    @FXML
    private FontAwesomeIconView home;
    @FXML
    private ScrollPane pane;
    @FXML
    private JFXComboBox<String> trisouscat;
    @FXML
    private JFXComboBox<String> tri;

    private ObservableList<Etablissement> data;
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
            for (String s : ae.listsouscathotel()) {
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
        data = LDS.ListHotel();
        for ( Etablissement d : data) {
            
            try {
               
           
       
        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/DivListHotel.fxml"));
                Parent root = (Pane) loader.load();
                DivListHotelController DHC = loader.getController();
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
                    AnchorPane Rev = FXMLLoader.load(getClass().getResource("../Views/ListHotel.fxml"));
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
                    AnchorPane Rev = FXMLLoader.load(getClass().getResource("../Views/ListHotel.fxml"));
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
                    AnchorPane Rev = FXMLLoader.load(getClass().getResource("../Views/ListHotel.fxml"));
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
//            ////////// end 9dima////////////
//
////        editicon.setVisible(false);
////        deleteicon.setVisible(false);
        });
    }

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
    });
                }

    @FXML
    private void home(MouseEvent event) {
    }

    @FXML
    private void openmap(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Map.fxml"));
        Parent root = loader.load();
        MapController m = loader.getController();

        m.getSearchAP().setVisible(false);

        Stage stage = new Stage(StageStyle.DECORATED);

        stage.setTitle("Liste des Hotels");
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public void affichetrie(String scvalue, URL url, ResourceBundle rb, String trisq) throws SQLException {

          ListEtablissement le = new ListEtablissement();
        data = le.ListHotelTrie(scvalue, trisq);
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
        data = le.TriserviceHotel(trisq);
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
