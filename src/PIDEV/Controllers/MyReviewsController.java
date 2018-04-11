/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Etablissement;
import PIDEV.Entities.Review;
import PIDEV.Services.DeleteEtablissement;
import PIDEV.Services.GestionReviews;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class MyReviewsController implements Initializable {

    @FXML
    private AnchorPane scroll;
        private ObservableList<Review> listreview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TilePane b = new TilePane();

        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();
       
                GestionReviews gr = new GestionReviews();
      listreview = gr.MyReviews(PIDEV.Views.FirstFrame.user);

       
        for ( Review d : listreview) {
            
            try {
               
           
       
        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/DivReview.fxml"));
                Parent root = (Pane) loader.load();
                DivReviewController DHC = loader.getController();
                DHC.LoadMyValues(d);
               
                //   c.setVgap(40);
                c.getChildren().removeAll();
               
                
                c.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ProfilrestoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MyReviewsController.class.getName()).log(Level.SEVERE, null, ex);
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
     public void deleteReview(Review R) {
        try {
           GestionReviews de = new GestionReviews();
            de.DeleteReveiw(R.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfilUser.fxml"));
            Parent root = loader.load();
            ProfilUserController pu = loader.getController();
            AnchorPane Rev = FXMLLoader.load(getClass().getResource("../Views/MyReviews.fxml"));
            pu.setNode(Rev);
            scroll.getScene().setRoot(root);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
    
}
