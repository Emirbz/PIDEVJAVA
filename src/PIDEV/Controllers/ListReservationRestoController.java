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
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ons
 */
public class ListReservationRestoController implements Initializable {

    @FXML
    private TableView<Reservation> reservations;

    @FXML
    private TableColumn<Reservation, String> aunomde;
    @FXML
    private TableColumn<Reservation, String> description;
    @FXML
    private TableColumn<Reservation, Integer> nombre;
    @FXML
    private TableColumn<Reservation, Timestamp> date;

    private ObservableList<Reservation> data;
    @FXML
    private Label idEtab;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void listReservation(Etablissement etab) throws IOException {
        ReservationService lr = new ReservationService();
        data = FXCollections.observableArrayList(lr.listReservation(etab));
        reservations.setItems(data);
        aunomde.setCellValueFactory(new PropertyValueFactory<>("aunomde"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
}
