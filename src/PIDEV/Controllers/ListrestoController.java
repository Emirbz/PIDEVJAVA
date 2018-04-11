/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import PIDEV.Entities.SousCategorie;
import PIDEV.Entities.Etablissement;
import PIDEV.Services.DeleteEtablissement;
import PIDEV.Services.ListEtablissement;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class ListrestoController implements Initializable {

    @FXML
    private TableView<Etablissement> table;
    @FXML
    private TableColumn<Etablissement, String> nom;
    @FXML
    private TableColumn<Etablissement, String> address;
    @FXML
    private TableColumn<Etablissement, String> email;
    @FXML
    private TableColumn<Etablissement, String> phone;
    @FXML
    private TableColumn<Etablissement, String> lundisamedio;
    @FXML
    private TableColumn<Etablissement, String> lundisamedif;
    @FXML
    private TableColumn<Etablissement, String> dimancheo;
    @FXML
    private TableColumn<Etablissement, String> dimanchef;
    private ObservableList<Etablissement> data;
    @FXML
    private JFXTextField recherchetext;
    @FXML
    private JFXButton delete;
    @FXML
    private TableColumn<?, ?> souscat;
    @FXML
    private JFXButton edit;
    @FXML
    private FontAwesomeIconView home;
    @FXML
    private JFXButton profil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ActionEvent event = null;
        try {
            Listrestaurant(event);
        } catch (IOException ex) {
            Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Listrestaurant(ActionEvent event) throws IOException {
        ListEtablissement le = new ListEtablissement();
        data = FXCollections.observableArrayList(le.ListRestaurant());
        table.setItems(data);
        nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        lundisamedio.setCellValueFactory(new PropertyValueFactory<>("lundisamedio"));
        lundisamedif.setCellValueFactory(new PropertyValueFactory<>("lundisamedif"));
        dimancheo.setCellValueFactory(new PropertyValueFactory<>("dimancheo"));
        dimanchef.setCellValueFactory(new PropertyValueFactory<>("dimanchef"));
        
     souscat.setCellValueFactory(new PropertyValueFactory<>("souscat"));
        

    }

    @FXML
    private void RechercheDynamique(KeyEvent event) {

        FilteredList<Etablissement> filteredData = new FilteredList<>(data, e -> true);
        recherchetext.setOnKeyReleased(e
                -> {
            recherchetext.textProperty().addListener((ObservableValue<? extends String> ObservableValue, String oldValue, String newValue) -> {
                filteredData.setPredicate((Predicate<? super Etablissement>) new Predicate<Etablissement>() {
                    @Override
                    public boolean test(Etablissement etablissement) {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (etablissement.getName().toLowerCase().contains(lowerCaseFilter.subSequence(0, lowerCaseFilter.length()))) {
                            return true;
                        } else if (etablissement.getName().toLowerCase().contains(lowerCaseFilter.subSequence(0, lowerCaseFilter.length()))) {
                            return true;
                        }
                        return false;
                    }

                });

            });
            SortedList<Etablissement> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
        });
    }

    @FXML
    private void ModifierRestaurant(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/EditResto.fxml"));
        Parent root = loader.load();
        EditrestoController editrest = loader.getController();
        Etablissement etab = table.getSelectionModel().getSelectedItem();
        editrest.showresto(etab);
        table.getScene().setRoot(root);
    }


    @FXML
    private void DeleteRestaurant(MouseEvent event) throws SQLException, IOException {
        
        DeleteEtablissement d = new DeleteEtablissement();
   
        Etablissement e = table.getSelectionModel().getSelectedItem();
      
        d.DeleteEtablissement(e.getId());
        ActionEvent event1=null;
        Listrestaurant(event1);
    }

     @FXML
    private void home(MouseEvent event) throws IOException {
   
    
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Home.fxml"));
            Parent root = loader.load();
            home.getScene().setRoot(root);
     
        
    }

    @FXML
    private void Profil(ActionEvent event) throws IOException {
      
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfilResto.fxml"));
        Parent root = loader.load();
        ProfilrestoController PR = loader.getController();
        Etablissement etab = table.getSelectionModel().getSelectedItem();
        PR.RestoProfil(etab.getId());
        PR.setIdEtab(etab.getId());
        table.getScene().setRoot(root);
    
    }


}
