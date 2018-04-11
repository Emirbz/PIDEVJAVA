/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;
import PIDEV.Controllers.EditeventController;
import PIDEV.Views.FirstFrame;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import PIDEV.Services.deleteevent;
import PIDEV.Services.editevent;
import PIDEV.Services.listeevent;

import PIDEV.Entities.events;

/**
 * FXML Controller class
 *
 * @author dahem
 */
public class AfficheEventController implements Initializable {
    
    @FXML
    private JFXTextField recherchetext;

    @FXML
    private JFXButton edit;
    @FXML
    private JFXButton delete;
    @FXML
    private ScrollPane pane;

    ObservableList<events> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listeevent ls = new listeevent();
        list = ls.ListeEvent();

        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();
        for (events e : list) {
            TilePane a = new TilePane();
            Label title = new Label(e.getName());
            Label id = new Label(String.valueOf(e.getId()));
            id.setVisible(false);
            final String imageURI = new File("C:\\wamp64\\www\\PIDEV\\web\\devis\\" + e.getDevis_name()).toURI().toString();
            ImageButton imgevent = new ImageButton(imageURI);
            imgevent.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    listeevent ls = new listeevent();
                    events selectedevent = ls.ListeEvent().filtered(e -> e.getId() == Integer.parseInt(id.getText())).get(0);
                    edit.setVisible(true);
                   
                    edit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/editevent.fxml"));
                                Parent root = loader.load();
                                editevent ed = new editevent();
                                EditeventController ec = loader.getController();
                               ec.getoldvalues(selectedevent);
                                ScrollPane r = new ScrollPane(root);
                                Stage stage = new Stage(StageStyle.DECORATED);
                                stage.setTitle("Modifier event");
                                stage.setScene(new Scene(r));
                                stage.show();

                            } catch (IOException ex) {
                                Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    delete.setVisible(true);
                    delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            try {
                                deleteevent de = new deleteevent();
                                de.delete(selectedevent.getId());
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/affiche.fxml"));
                                Parent root = loader.load();
                                recherchetext.getScene().setRoot(root);
                            } catch (SQLException | IOException ex) {
                                Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                }
            });
            
            
      
            
            
            imgevent.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                     
                  
            if(event.getClickCount() == 2){
               events selectedevent = ls.ListeEvent().filtered(e -> e.getId() == Integer.parseInt(id.getText())).get(0);
                    Preferences prefs = Preferences.userNodeForPackage(AfficheEventController.class);
                    prefs.putInt("id", selectedevent.getId());
                try { 
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfilEvent.fxml"));
                Parent root = loader.load();
                ProfileventController DDC = loader.getController();
                    DDC.profilevent(selectedevent);

                
                Stage ss=new Stage();
                Scene sc = new Scene(root);
                ss.setScene(sc);
                ss.setWidth(1288);
                ss.setHeight(750);
                
                
                ss.show();
                     
                     
                } catch (IOException ex) {
                    Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                
          
                
                
            }
            }
                
            });
            a.getChildren().add(new VBox(5, imgevent, title, id));

            a.setMaxSize(300, 150);

            c.getChildren().add(a);
        }
        c.setPrefColumns(3);
        c.setPadding(new javafx.geometry.Insets(10));
        c.setHgap(40);
        c.setVgap(40);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);
        edit.setVisible(false);
        delete.setVisible(false);

    }

    @FXML
    private void RechercheDynamique(KeyEvent event) {

        FilteredList<events> filteredData = new FilteredList<>(list, e -> true);
        recherchetext.setOnKeyReleased(e
                -> {
            recherchetext.textProperty().addListener((ObservableValue<? extends String> ObservableValue, String oldValue, String newValue) -> {
                filteredData.setPredicate((Predicate<? super events>) new Predicate<events>() {
                    @Override
                    public boolean test(events e) {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        return e.getName().toLowerCase().contains(lowerCaseFilter.subSequence(0, lowerCaseFilter.length()));
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
                final String imageURI = new File("C:\\wamp64\\www\\PIDEV\\web\\devis\\" + f.getDevis_name()).toURI().toString();
                ImageButton imgdeal = new ImageButton(imageURI);
                a.getChildren().add(new VBox(5, imgdeal, title, id));
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

        });
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

}
