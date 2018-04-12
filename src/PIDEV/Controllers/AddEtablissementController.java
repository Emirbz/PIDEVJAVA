/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Categorie;
import PIDEV.Services.CategorieService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class AddEtablissementController implements Initializable {

    @FXML
    private AnchorPane firstPane;
    @FXML
    private AnchorPane secondPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          VBox vbCat=new VBox();
        vbCat.setPrefWidth(1038);
        vbCat.setSpacing(5);
        vbCat.setAlignment(Pos.CENTER);
        vbCat.setLayoutY(15);
        Label descLabel = new Label();
        descLabel.setText("Explorer les différentes catégories");
        descLabel.setFont(Font.font("SansSerif", FontWeight.SEMI_BOLD, 16));
        descLabel.setTextFill(Color.web("#707070"));
        
        
        Label descLabel2 = new Label();
        descLabel2.setText("Ajouter Votre Etablissement");
        descLabel2.setFont(Font.font("SansSerif", FontWeight.SEMI_BOLD, 18));
        descLabel2.setTextFill(Color.web("#222222"));
        
        
        HBox hbCat=new HBox();
        
        
        
        
        hbCat.setAlignment(Pos.CENTER);
        hbCat.setSpacing(8);
        CategorieService cs=new CategorieService();
        ObservableList<Categorie> listCat=cs.listCategorie();
        for(Categorie lc : listCat){
            AnchorPane ap=new AnchorPane();
            ap.setPrefHeight(150);
            ap.setPrefWidth(270);
            ap.setStyle("-fx-background-radius : 10; -fx-background-radius : 10; ");
            Rectangle rc=new Rectangle();
            rc.setHeight(200);
            rc.setWidth(270);
            Label lb=new Label();
            lb.setTextFill(Color.WHITE);
             lb.setFont(Font.font("SansSerif", FontWeight.EXTRA_BOLD, 12));
             lb.setLayoutX(13);
             lb.setLayoutY(170);
             FontAwesomeIconView ic=new FontAwesomeIconView(FontAwesomeIcon.CIRCLE);
             ic.setSize(String.valueOf(50));
             ic.setLayoutX(13);
             ic.setLayoutY(50);
             FontAwesomeIconView ic1;
            
             
            if(lc.getNom().equals("Beauté et bien être")){
                Image image = new Image("file:C:/wamp64/www/PIDEV/web/images/beaute.jpg");
                rc.setFill(new ImagePattern(image));
                rc.setSmooth(true);
                rc.setStrokeLineCap(StrokeLineCap.ROUND);
                rc.setStrokeMiterLimit(10);
                rc.setArcHeight(7);
                rc.setArcWidth(7);
                rc.setStrokeLineJoin(StrokeLineJoin.MITER);
                rc.setStrokeType(StrokeType.OUTSIDE);
                lb.setText("Beauté et bien être");
                ic.setFill(Color.web("#007bff"));
                ic1=new FontAwesomeIconView(FontAwesomeIcon.FEMALE);
                ic1.setSize(String.valueOf(20));
             ic1.setLayoutX(27);
             ic1.setLayoutY(40);
             ic1.setFill(Color.web("#fff4f7"));
              rc.setOnMouseClicked((event) -> {
                    try {
                        AnchorPane hotel = FXMLLoader.load(getClass().getResource("../Views/AjoutBeaute.fxml"));
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/HomePage.fxml"));
                        Parent root = loader.load();
                        HomePageController PR = loader.getController();
                        PR.setNode(hotel);
                        secondPane.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(AddEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
             });
            
               
            }else if(lc.getNom().equals("Espace culturel")){
                Image image = new Image("file:C:/wamp64/www/PIDEV/web/images/theatre.jpg");
                rc.setFill(new ImagePattern(image));
                rc.setSmooth(true);
                rc.setStrokeLineCap(StrokeLineCap.ROUND);
                rc.setStrokeMiterLimit(10);
                rc.setArcHeight(7);
                rc.setArcWidth(7);
                rc.setStrokeLineJoin(StrokeLineJoin.MITER);
                rc.setStrokeType(StrokeType.OUTSIDE);
                lb.setText("Espace culturel");
                ic.setFill(Color.web("#ff214f"));
                ic1=new FontAwesomeIconView(FontAwesomeIcon.TELEVISION);
                ic1.setSize(String.valueOf(20));
                 ic1.setLayoutX(23);
                 ic1.setLayoutY(40);
             ic1.setFill(Color.web("#fff4f7"));
            rc.setOnMouseClicked((event) -> {
                    try {
                        AnchorPane hotel = FXMLLoader.load(getClass().getResource("../Views/AjoutCulture.fxml"));
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/HomePage.fxml"));
                        Parent root = loader.load();
                        HomePageController PR = loader.getController();
                        PR.setNode(hotel);
                        secondPane.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(AddEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
             });
            }else if(lc.getNom().equals("Hôtel")){
                Image image = new Image("file:C:/wamp64/www/PIDEV/web/images/hotel1.jpg");
                rc.setFill(new ImagePattern(image));
                rc.setSmooth(true);
                rc.setStrokeLineCap(StrokeLineCap.ROUND);
                rc.setStrokeMiterLimit(10);
                rc.setArcHeight(7);
                rc.setArcWidth(7);
                rc.setStrokeLineJoin(StrokeLineJoin.MITER);
                rc.setStrokeType(StrokeType.OUTSIDE);
                lb.setText("Hôtel");
                ic.setFill(Color.web("#f89406"));
                ic1=new FontAwesomeIconView(FontAwesomeIcon.HOTEL);
                ic1.setSize(String.valueOf(20));
                ic1.setLayoutX(24);
                ic1.setLayoutY(40);
                ic1.setFill(Color.web("#fff4f7"));
                 rc.setOnMouseClicked((event) -> {
                    try {
                        AnchorPane hotel = FXMLLoader.load(getClass().getResource("../Views/AjoutHotel.fxml"));
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/HomePage.fxml"));
                        Parent root = loader.load();
                        HomePageController PR = loader.getController();
                        PR.setNode(hotel);
                        secondPane.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(AddEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
             });
            }else{
                Image image = new Image("file:C:/wamp64/www/PIDEV/web/images/slide1.jpg");
                rc.setFill(new ImagePattern(image));
                rc.setSmooth(true);
                rc.setStrokeLineCap(StrokeLineCap.ROUND);
                rc.setStrokeMiterLimit(10);
                rc.setArcHeight(7);
                rc.setArcWidth(7);
                rc.setStrokeLineJoin(StrokeLineJoin.MITER);
                rc.setStrokeType(StrokeType.OUTSIDE);
                lb.setText("Restaurant");
                ic.setFill(Color.web("#f9690e"));
                ic1=new FontAwesomeIconView(FontAwesomeIcon.CUTLERY);
                ic1.setSize(String.valueOf(20));
                ic1.setLayoutX(25);
                ic1.setLayoutY(40);
                ic1.setFill(Color.web("#fff4f7"));
                rc.setOnMouseClicked((event) -> {
                    try {
                        AnchorPane hotel = FXMLLoader.load(getClass().getResource("../Views/AjoutResto.fxml"));
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/HomePage.fxml"));
                        Parent root = loader.load();
                        HomePageController PR = loader.getController();
                        PR.setNode(hotel);
                        secondPane.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(AddEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
             });
               
            }
            ap.getChildren().addAll(rc,lb,ic,ic1);
            hbCat.getChildren().addAll(ap);
            
            

            
            
        } 
        vbCat.getChildren().addAll(descLabel,descLabel2,hbCat);
         secondPane.getChildren().addAll(vbCat);
        // TODO
    }    
    
}
