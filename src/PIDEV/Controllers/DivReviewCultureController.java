/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Etablissement;
import PIDEV.Entities.Likereview;
import PIDEV.Entities.Review;
import PIDEV.Services.EditEtablissement;
import PIDEV.Services.GestionReviews;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class DivReviewCultureController implements Initializable {

    @FXML
    private Label rname;
    @FXML
    private Pane sq;
    @FXML
    private JFXButton rjaime;
    @FXML
    private JFXButton rliekd;
    @FXML
    private Label nbrlikes;
    @FXML
    private Circle rcircle;
    @FXML
    private Label rtitre;
    @FXML
    private Label rcom;
    @FXML
    private Label id;
    @FXML
    private Label rdate;
    @FXML
    private FontAwesomeIconView trash;
    @FXML
    private Label daterev;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void LoadValues(Review r,Etablissement e) throws IOException, SQLException {
        
        String charname = r.getIduser().getName().substring(0, 1).toUpperCase();
        String charsurname = r.getIduser().getSurname().substring(0, 1).toUpperCase();
        rname.setText(charname + r.getIduser().getName().substring(1) + " " + charsurname + r.getIduser().getSurname().substring(1));
            ProfilrestoController pr= new ProfilrestoController();
       rtitre.setText(r.getTitre());
       rcom.setText(r.getCommentaire());
       Calendar cal = Calendar.getInstance();
        cal.setTime(r.getIduser().getDate());
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
        daterev.setText(String.valueOf(r.getDate()));

       rdate.setText("Membre depuis " + monthx + "," + year);

        id.setText(String.valueOf(r.getId()));
        HBox qu = new HBox();
        Label lq = new Label("Qualite :");
        lq.setStyle("-fx-text-fill:#73879c;-fx-font-weight: bold;-fx-font-size:16px");
        qu.getChildren().add(lq);
        for (int i = 1; i <= Integer.parseInt(String.valueOf(r.getQualite()).substring(0, 1)); i++) {
            FontAwesomeIconView starq = new FontAwesomeIconView(FontAwesomeIcon.STAR);
            starq.setStyle("-glyph-size:20;-fx-fill:#FFD700");
            qu.getChildren().add(starq);

        }

        HBox se = new HBox();
        Label ls = new Label("Service :");
        ls.setStyle("-fx-text-fill:#73879c;-fx-font-weight: bold;-fx-font-size:16px");
        se.getChildren().add(ls);
        for (int i = 1; i <= Integer.parseInt(String.valueOf(r.getService()).substring(0, 1)); i++) {
            FontAwesomeIconView stars = new FontAwesomeIconView(FontAwesomeIcon.STAR);
            stars.setStyle("-glyph-size:20;-fx-fill:#FFD700");
            se.getChildren().add(stars);
        }
        sq.getChildren().add(new HBox(20,qu,se));


        Image imageURI = new Image("file:C:/wamp64/www/PIDEV/web/devis/" + r.getIduser().getDevis_name());
      rcircle.setFill(new ImagePattern(imageURI));
      GestionReviews gr = new GestionReviews();
      nbrlikes.setText(String.valueOf(gr.CountReview(r)));
      if (PIDEV.Views.FirstFrame.user==null)
      {
          rjaime.setVisible(false);
      }
         
      if (PIDEV.Views.FirstFrame.user!=null)
             
      {if ((gr.checkreview(PIDEV.Views.FirstFrame.user,r))>0)
      { 
          rjaime.setVisible(false);
          rliekd.setVisible(true);}}

       trash.setOnMouseClicked((MouseEvent event) -> {
                 
                try {

                    int newtotal = gr.NbrReview(e);
                    if (newtotal==1)
                    { e.setTotalqualite(0.0);
                    e.setTotalservice(0.0);
                    e.setMoyqualite(0.0);
                    e.setMoyservice(0.0);
                        
                    }
                    else 
                    {
                    e.setTotalqualite(e.getTotalqualite() - r.getQualite());
                    e.setTotalservice(e.getTotalservice() - r.getService());
                    e.setMoyqualite(e.getTotalqualite() / (newtotal - 1));
                    e.setMoyservice(e.getTotalservice() / (newtotal - 1));}
                    EditEtablissement EE = new EditEtablissement();
                    EE.ModifierReview(e, e.getId());
                    gr.DeleteReveiw(r.getId());
                    System.out.println("deleted");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfilCulture.fxml"));
                    Parent root = loader.load();
                    ProfilCultureController PR = loader.getController();

                    PR.RestoProfil(e.getId());
                    PR.Reviewslist(e);
                    rcom.getScene().setRoot(root);
                } catch (SQLException | IOException ex) {
                    Logger.getLogger(ProfilrestoController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });


       rjaime.setOnMouseClicked((event) -> {
           
          rjaime.setVisible(false);
          rliekd.setVisible(true);
           
           Likereview lr=new Likereview();
       lr.setIdreview(r);
       lr.setIduser(PIDEV.Views.FirstFrame.user);
            try {
                gr.AddLike(lr);
            } catch (SQLException ex) {
                Logger.getLogger(DivReviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
             try {
                nbrlikes.setText(String.valueOf(gr.CountReview(r)));
            } catch (SQLException ex) {
                Logger.getLogger(DivReviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
       });
      rliekd.setOnMouseClicked((event) -> {
           
            try {
                gr.DeleteLike(PIDEV.Views.FirstFrame.user.getId(),r.getId());
                rjaime.setVisible(true);
                rliekd.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(DivReviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
             try {
                nbrlikes.setText(String.valueOf(gr.CountReview(r)));
            } catch (SQLException ex) {
                Logger.getLogger(DivReviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
      });

}
     
      public void LoadMyValues(Review r) throws IOException, SQLException {
        
        String charname = r.getIduser().getName().substring(0, 1).toUpperCase();
        String charsurname = r.getIduser().getSurname().substring(0, 1).toUpperCase();
        rname.setText(charname + r.getIduser().getName().substring(1) + " " + charsurname + r.getIduser().getSurname().substring(1));
            ProfilrestoController pr= new ProfilrestoController();
       rtitre.setText(r.getTitre());
       rcom.setText(r.getCommentaire());
       Calendar cal = Calendar.getInstance();
        cal.setTime(r.getIduser().getDate());
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
        daterev.setText(String.valueOf(r.getDate()));

       rdate.setText("Membre depuis " + monthx + "," + year);

        id.setText(String.valueOf(r.getId()));
        HBox qu = new HBox();
        Label lq = new Label("Qualite :");
        lq.setStyle("-fx-text-fill:#73879c;-fx-font-weight: bold;-fx-font-size:16px");
        qu.getChildren().add(lq);
        for (int i = 1; i <= Integer.parseInt(String.valueOf(r.getQualite()).substring(0, 1)); i++) {
            FontAwesomeIconView starq = new FontAwesomeIconView(FontAwesomeIcon.STAR);
            starq.setStyle("-glyph-size:20;-fx-fill:#FFD700");
            qu.getChildren().add(starq);

        }

        HBox se = new HBox();
        Label ls = new Label("Service :");
        ls.setStyle("-fx-text-fill:#73879c;-fx-font-weight: bold;-fx-font-size:16px");
        se.getChildren().add(ls);
        for (int i = 1; i <= Integer.parseInt(String.valueOf(r.getService()).substring(0, 1)); i++) {
            FontAwesomeIconView stars = new FontAwesomeIconView(FontAwesomeIcon.STAR);
            stars.setStyle("-glyph-size:20;-fx-fill:#FFD700");
            se.getChildren().add(stars);
        }
        sq.getChildren().add(new HBox(20,qu,se));
rjaime.setVisible(false);
            rliekd.setVisible(false);

        Image imageURI = new Image("file:C:/wamp64/www/PIDEV/web/devis/" + r.getIduser().getDevis_name());
      rcircle.setFill(new ImagePattern(imageURI));
      GestionReviews gr = new GestionReviews();
      nbrlikes.setText(String.valueOf(gr.CountReview(r)));
      trash.setOnMouseClicked((event) -> {
            try {
                GestionReviews de = new GestionReviews();
            de.DeleteReveiw(r.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfilUser.fxml"));
            Parent root = loader.load();
            ProfilUserController pu = loader.getController();
            AnchorPane Rev = FXMLLoader.load(getClass().getResource("../Views/MyReviews.fxml"));
            pu.setNode(Rev);
            rname.getScene().setRoot(root);
            
            } catch (IOException | SQLException ex) {
                Logger.getLogger(DivReviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
      
          
          
          
          
      });
      }
        
    
}
