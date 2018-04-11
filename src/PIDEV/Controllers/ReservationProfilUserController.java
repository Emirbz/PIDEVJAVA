/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Reservation;
import PIDEV.Services.ReservationService;
import PIDEV.Views.FirstFrame;
import com.sun.prism.paint.Paint;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author ons
 */
public class ReservationProfilUserController implements Initializable {
    
    @FXML
    private AnchorPane main;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReservationService rs = new ReservationService();
        ObservableList<Reservation> list = rs.listReservation();
        int pages = list.size() / itemsPerPage() + 1;
        Pagination pagination = new Pagination(pages, 0);
        pagination.setMaxPageIndicatorCount(3);        
        pagination.setPrefHeight(405);
        pagination.setPrefWidth(1300);
        pagination.setLayoutX(0);
        pagination.setLayoutY(0);
        pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex));
        main.getChildren().add(pagination);        
    }    
    
    public int itemsPerPage() {
        return 6;
    }

    public AnchorPane createPage(int pageIndex) {
        AnchorPane ap = new AnchorPane();
        ap.setPrefHeight(300);
        ap.setMaxHeight(300);
        ap.setMaxWidth(720);
        ap.setPrefWidth(720);
        ap.setLayoutX(290);
        ap.setLayoutY(52);
        ap.setStyle("-fx-border-color : #dadada; -fx-border-radius: 10; -fx-background-color: white; -fx-background-radius: 10;");
        
        TilePane element = new TilePane();
        
        element.setPadding(new javafx.geometry.Insets(30));
        element.setPrefColumns(3);
        element.setPrefRows(2);
        
        ReservationService rs = new ReservationService();
        ObservableList<Reservation> list = rs.listReservation();
        int page = pageIndex * itemsPerPage();
        for (int i = page; i < page + itemsPerPage(); i++) {
            if (i <= list.size() - 1) {
                TilePane a = new TilePane();
                a.setMaxSize(100, 100);
                VBox infos;
                HBox allRes;
                String[] auNomDe=list.get(i).getAunomde().split(" ", 0);
                String auN="";
                for(int j=0; j< auNomDe.length;j++){
                    String s=auNomDe[j].toUpperCase().substring(0,1)+""+auNomDe[j].substring(1,auNomDe[j].length());
                    auN=auN+s;
                    if(i!= auNomDe.length){
                        auN=auN;
                    }
                }
                Label aunomde = new Label();
                Label nombre = new Label();
                Label dateR=new Label();
                Label timeR=new Label();
                Label occasionR= new Label();
                aunomde.setText(auN);
                aunomde.setStyle("-fx-font-size : 16; ");
                nombre.setText("Nombre : "+String.valueOf(list.get(i).getNombre()));
                nombre.setStyle("-fx-text-fill : #707070;");
                dateR.setText("Date : "+String.valueOf(list.get(i).getDate().toLocalDateTime().getDayOfMonth())+"/"+String.valueOf(list.get(i).getDate().toLocalDateTime().getMonthValue())+"/"+String.valueOf(list.get(i).getDate().toLocalDateTime().getYear()));
                dateR.setStyle("-fx-text-fill : #707070;");
                timeR.setText("Heure : "+String.valueOf(list.get(i).getDate().toLocalDateTime().getHour())+":"+String.valueOf(list.get(i).getDate().toLocalDateTime().getMinute()));
                timeR.setStyle("-fx-text-fill: #707070;");
                occasionR.setText(list.get(i).getDescription());
                occasionR.setStyle("-fx-text-fill : #ff214f;");
                Rectangle etabPhoto = new Rectangle(100, 100);
              
                etabPhoto.setStroke(Color.WHITE);
                etabPhoto.setStrokeWidth(3);
                etabPhoto.setSmooth(true);
                etabPhoto.setStrokeLineCap(StrokeLineCap.ROUND);
                etabPhoto.setStrokeMiterLimit(10);
                etabPhoto.setArcHeight(5);
                etabPhoto.setArcWidth(5);
                etabPhoto.setStrokeLineJoin(StrokeLineJoin.MITER);
                etabPhoto.setStrokeType(StrokeType.OUTSIDE);
                
                               
                File file = new File("C:/wamp64/www/PIDEV/web/devis/" + list.get(i).getEtablissement().getDevis_name());
                try {
                    
                    BufferedImage bufferedImage = ImageIO.read(file);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    etabPhoto.setFill(new ImagePattern(image));
                    
                } catch (IOException ex) {
                    Logger.getLogger(ProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 etabPhoto.setOnMousePressed(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent mouseEvent){
                        
                        
                        try {
                            
                            FXMLLoader loader = new FXMLLoader(ReservationProfilUserController.class.getResource("../Views/EditDeleteReservationWindow.fxml"));
                            AnchorPane page = (AnchorPane) loader.load();
                            Stage dialogStage = new Stage();
                          
                            dialogStage.initModality(Modality.WINDOW_MODAL);
                              dialogStage.initStyle(StageStyle.UNDECORATED);
                            //dialogStage.initOwner(primaryStage);
                            Scene scene = new Scene(page);
                             dialogStage.setScene(scene);
                            dialogStage.show();
//                            Stage stage = new Stage();
//                            stage.setTitle("My New Stage Title");
//                            stage.setScene(new Scene(root, 200 , 100));
//                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ReservationProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
                        }        
                    
                    } 
        
                                });

                
                infos = new VBox(2, aunomde, nombre,dateR,timeR,occasionR);
                
                
                allRes = new HBox(etabPhoto, infos);
                allRes.setPrefWidth(200);
                a.getChildren().addAll(allRes);
                a.setStyle(" -fx-padding:5; ");

//                 a.setVgap(10);
                a.setPrefHeight(60);
                a.setPrefWidth(100);
                element.getChildren().add(a);
                element.setHgap(65);
                element.setVgap(10);
                //element.setPrefWidth(480);
            }
        }
        ap.getChildren().addAll(element);
        
        
        return ap;
        
    }
    
}
