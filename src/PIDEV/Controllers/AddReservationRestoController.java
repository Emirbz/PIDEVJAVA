/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Etablissement;
import PIDEV.Entities.Reservation;
import PIDEV.Services.ListEtablissement;
import PIDEV.Services.ReservationService;
import PIDEV.Utils.MyConnexion;
import PIDEV.Views.FirstFrame;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.*;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author ons
 */
public class AddReservationRestoController implements Initializable {
    
    @FXML
    private JFXTextField nombre;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXComboBox<String> description;
    @FXML
    private JFXTimePicker heure;
    @FXML
    private JFXTextField aunomde;
    @FXML
    private Label idEtab;
    @FXML
    private Label addressEtab;
    @FXML
    private Label PhoneEtab;
    @FXML
    private Label emailEtab;
    @FXML
    private JFXButton fcbEtab;
    @FXML
    private Circle photoEtab;
    @FXML
    private Label nameEtab;
Etablissement etab;
    @FXML
    private JFXButton book;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        description.getItems().addAll("Aucune Occasion","Occasion","Evènement","Anniversaire");
        
    }    
    
    public void setIdEtab(int id){
        idEtab.setText(String.valueOf(id));
        ListEtablissement le=new ListEtablissement();
        Etablissement etabli = le.searchRestaurant(id);
        nameEtab.setText(etabli.getName());
        addressEtab.setText(etabli.getAddress());
        emailEtab.setText(etabli.getEmail());
        PhoneEtab.setText(etabli.getPhone());
        File file = new File("C:/wamp64/www/PIDEV/web/devis/"+etabli.getDevis_name());
        try {
            
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            photoEtab.setFill(new ImagePattern(image));
            
        } catch (IOException ex) {
            Logger.getLogger(ProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reserver(ActionEvent event)throws IOException{
       
      
            LocalDate nDate= date.getValue();
            LocalTime nTime= heure.getValue();
            LocalDateTime dateTime = nDate.atTime(nTime);
           
            Instant instant = nTime.atDate(LocalDate.of(nDate.getYear(), nDate.getMonth(), nDate.getDayOfMonth())).
            atZone(ZoneId.systemDefault()).toInstant();
           
             java.util.Date date_util =java.util.Date.from(dateTime.atZone(ZoneId.systemDefault())
      .toInstant());
             java.sql.Timestamp dateF = new java.sql.Timestamp(date_util.getTime());
             System.out.println(date_util);
           // Date dateF =Date.from(nDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
           
           ListEtablissement lr =new ListEtablissement();
           etab = lr.searchRestaurant(Integer.parseInt(idEtab.getText()));
            Reservation reservation=new Reservation(
                    FirstFrame.user,
                    etab,
                    aunomde.getText(),
                    Integer.parseInt(nombre.getText()),
                    description.getValue(),
                    dateF
                    
            );
            
            ReservationService reservationService = new ReservationService();
            reservationService.addReservation(reservation);
                    
    }

    private void edit(ActionEvent event) {
        
        Connection cn = MyConnexion.getInstance().getConnection();
      
            LocalDate nDate= date.getValue();
            LocalTime nTime= heure.getValue();
            LocalDateTime dateTime = nDate.atTime(nTime);
           
            Instant instant = nTime.atDate(LocalDate.of(nDate.getYear(), nDate.getMonth(), nDate.getDayOfMonth())).
            atZone(ZoneId.systemDefault()).toInstant();
           
             java.util.Date date_util =java.util.Date.from(dateTime.atZone(ZoneId.systemDefault())
      .toInstant());
             java.sql.Timestamp dateF = new java.sql.Timestamp(date_util.getTime());
             System.out.println(date_util);
           // Date dateF =Date.from(nDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
           
           ListEtablissement lr =new ListEtablissement();
           etab = lr.searchRestaurant(Integer.parseInt(idEtab.getText()));
            Reservation reservation=new Reservation(
                    FirstFrame.user,
                    etab,
                    aunomde.getText(),
                    Integer.parseInt(nombre.getText()),
                    description.getValue(),
                    dateF
                    
            );
            
            ReservationService reservationService = new ReservationService();
            
    }


   
    
}
