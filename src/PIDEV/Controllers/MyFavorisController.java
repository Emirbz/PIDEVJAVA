/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Etablissement;
import PIDEV.Services.FavorisService;
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

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class MyFavorisController implements Initializable {

    @FXML
    private ScrollPane pane;
         private ObservableList<Etablissement> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FavorisService fs = new FavorisService();
        data = fs.ListFavorisUser();
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();

        for (Etablissement etab : data) {
            

            TilePane a = new TilePane();
            Label title = new Label(etab.getName());
            title.setStyle("-fx-font-size:22px;-fx-font-weight: bold");
            Label cat = new Label(etab.getCategorie());
            title.setStyle("-fx-font-size:18px;-fx-font-weight: bold");
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
          ImageButton imgetab =  new ImageButton(imageURI);
            FontAwesomeIconView editicon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
            editicon.setVisible(false);
            FontAwesomeIconView deleteicon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            deleteicon.setVisible(false);
          
FontAwesomeIconView fav = new FontAwesomeIconView(FontAwesomeIcon.STAR);
fav.setStyle("-glyph-size:35;-fx-fill:#FFD700");
          
            a.getChildren().add(new VBox(5, new HBox(270, editicon, deleteicon), imgetab, id, title,cat, new HBox(2, qualite, p), new HBox(2, service, pp),fav));
               a.setPadding(new javafx.geometry.Insets(60, 5, 10, 35));
            a.setMaxSize(300, 150);
              a.setPrefColumns(2);
            c.getChildren().add(a);
            fav.setOnMouseClicked((event) -> {
                try {
                    FavorisService favserv = new FavorisService();
                    favserv.DeleteFavoris(etab.getId());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfilUser.fxml"));
                    Parent root = loader.load();
                    ProfilUserController pu = loader.getController();
                    AnchorPane list = FXMLLoader.load(getClass().getResource("../Views/MyFavoris.fxml"));
                    pu.setNode(list);
                    pane.getScene().setRoot(root);
                } catch (SQLException ex) {
                    Logger.getLogger(MyFavorisController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MyFavorisController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
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
        // TODO
    }    
    

