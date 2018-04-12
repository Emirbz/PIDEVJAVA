/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;


import PIDEV.Entities.Etablissement;
import PIDEV.Services.DeleteEtablissement;
import PIDEV.Services.ListEtablissement;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class MyListingController implements Initializable {

    @FXML
    private ScrollPane pane;
     private ObservableList<Etablissement> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListEtablissement le = new ListEtablissement();
        data = le.ListRestaurantUser();
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();

        for (Etablissement etab : data) {

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
            FontAwesomeIconView editicon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
            editicon.setVisible(false);
            FontAwesomeIconView deleteicon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            deleteicon.setVisible(false);
            imgetab.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ListEtablissement le = new ListEtablissement();
                    Etablissement selectedetab = le.ListRestaurant().filtered(e -> e.getId() == Integer.parseInt(id.getText())).get(0);
                    editicon.setVisible(true);

                    deleteicon.setVisible(true);

                    if (event.getClickCount() == 2) {
                        doubleclick(event, selectedetab);
                    }

                }
            });

            editicon.setStyle("-glyph-size:20;-fx-fill:#3366cc");

            editicon.setOnMouseClicked((event) -> {
                editetab(etab, url, rb);
            });

            deleteicon.setStyle("-glyph-size:20;-fx-fill:ff3333");
            deleteicon.setOnMouseClicked((event) -> {
                deleteetab(etab);
            });
            a.getChildren().add(new VBox(5, new HBox(270, editicon, deleteicon), imgetab, id, title, new HBox(2, qualite, p), new HBox(2, service, pp)));
               a.setPadding(new javafx.geometry.Insets(60, 5, 10, 35));
            a.setMaxSize(300, 150);
              a.setPrefColumns(2);
            c.getChildren().add(a);
        }
        c.setPrefColumns(3);
        c.setPadding(new javafx.geometry.Insets(10));
        c.setHgap(60);
        c.setVgap(40);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);
        
        
        // TODO
    }    
     public void deleteetab(Etablissement selectedetab) {
        try {
            DeleteEtablissement de = new DeleteEtablissement();
            de.DeleteEtablissement(selectedetab.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfilUser.fxml"));
            Parent root = loader.load();
            ProfilUserController pu = loader.getController();
            AnchorPane list = FXMLLoader.load(getClass().getResource("../Views/MyListing.fxml"));
            pu.setNode(list);
            pane.getScene().setRoot(root);
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
    public void doubleclick(MouseEvent event, Etablissement selectedetab) {
        if (event.getClickCount() == 2) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfilResto.fxml"));
                Parent root = loader.load();
                ProfilrestoController DDC = loader.getController();
                DDC.Reviewslist(selectedetab);
                DDC.RestoProfil(selectedetab.getId());

                pane.getScene().setRoot(root);
            } catch (IOException | SQLException ex) {
                Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
