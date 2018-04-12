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
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class EditReservationController implements Initializable {

    /**
     * Initializes the controller class.
     */
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
    private JFXButton edit;
    @FXML
    private Label idres;

    public JFXButton getEdit() {
        return edit;
    }

    public void setEdit(JFXButton edit) {
        this.edit = edit;
    }

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

    @FXML
    public void edit(ActionEvent event) throws SQLException {
        LocalDate nDate = date.getValue();
        LocalTime nTime = heure.getValue();
        LocalDateTime dateTime = nDate.atTime(nTime);

        Instant instant = nTime.atDate(LocalDate.of(nDate.getYear(), nDate.getMonth(), nDate.getDayOfMonth())).
                atZone(ZoneId.systemDefault()).toInstant();

        java.util.Date date_util = java.util.Date.from(dateTime.atZone(ZoneId.systemDefault())
                .toInstant());
        java.sql.Timestamp dateF = new java.sql.Timestamp(date_util.getTime());
        ReservationService rs = new ReservationService();
        ListEtablissement le = new ListEtablissement();
        Etablissement etab;
        int i = Integer.parseInt(idEtab.getText());
        System.out.println(i);
        etab = le.searchRestaurant(i);
        
        
        Reservation res = new Reservation(
                PIDEV.Views.FirstFrame.user,
                etab,
                aunomde.getText(),
                Integer.parseInt(nombre.getText()),
                description.getValue(),
                dateF
        );
    res.setId(Integer.parseInt(idres.getText()));
        ReservationService reservationService = new ReservationService();
        reservationService.editReservation(res);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        description.getItems().addAll("Aucune Occasion", "Occasion", "Evènement", "Anniversaire");
    }

//    public void setIdEtab(int id){
//        idEtab.setText(String.valueOf(id));
//        ListEtablissement le=new ListEtablissement();
//        Etablissement etabli = le.searchRestaurant(id);
//        nameEtab.setText(etabli.getName());
//        addressEtab.setText(etabli.getAddress());
//        emailEtab.setText(etabli.getEmail());
//        PhoneEtab.setText(etabli.getPhone());
//        File file = new File("C:/wamp64/www/PIDEV/web/devis/"+etabli.getDevis_name());
//        try {
//            
//            BufferedImage bufferedImage = ImageIO.read(file);
//            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
//            photoEtab.setFill(new ImagePattern(image));
//            
//        } catch (IOException ex) {
//            Logger.getLogger(ProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void showOldValues(Reservation reservation) {
        ListEtablissement le = new ListEtablissement();
        Etablissement etab = le.searchRestaurant(reservation.getEtablissement().getId());
        nameEtab.setText(etab.getName());
        addressEtab.setText(etab.getAddress());
        emailEtab.setText(etab.getEmail());
        PhoneEtab.setText(etab.getPhone());
        File file = new File("C:/wamp64/www/PIDEV/web/devis/" + etab.getDevis_name());
        try {

            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            photoEtab.setFill(new ImagePattern(image));

        } catch (IOException ex) {
            Logger.getLogger(ProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idEtab.setText(String.valueOf(reservation.getEtablissement().getId()));
        nombre.setText(String.valueOf(reservation.getNombre()));
        description.setValue(reservation.getDescription());
        aunomde.setText(reservation.getAunomde());
        date.setValue(reservation.getDate().toLocalDateTime().toLocalDate());
        heure.setValue(reservation.getDate().toLocalDateTime().toLocalTime());
        idres.setText(String.valueOf(reservation.getId()));

    }

//    public void setEtab(Etablissement etab){
//        this.idEtab.setText(String.valueOf(etab.getId()));
//       
//        nameEtab.setText(etab.getName());
//        addressEtab.setText(etab.getAddress());
//        emailEtab.setText(etab.getEmail());
//        PhoneEtab.setText(etab.getPhone());
//        File file = new File("C:/wamp64/www/PIDEV/web/devis/"+etab.getDevis_name());
//        try {
//            
//            BufferedImage bufferedImage = ImageIO.read(file);
//            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
//            photoEtab.setFill(new ImagePattern(image));
//            
//        } catch (IOException ex) {
//            Logger.getLogger(ProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
