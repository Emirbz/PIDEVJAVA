/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Views;

import PIDEV.Entities.Etablissement;
import PIDEV.Services.EditEtablissement;
import PIDEV.Services.ListEtablissement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Emir
 */
public class ValiderRestoController implements Initializable {

    @FXML
    private JFXTextField recherchetext;
    @FXML
    private TableView<Etablissement> table;
    @FXML
    private TableColumn<Etablissement,String> nom;
    @FXML
    private TableColumn<Etablissement,String> souscat;
    @FXML
    private TableColumn<Etablissement,String> address;
    @FXML
    private TableColumn<Etablissement,String> phone;
    @FXML
    private TableColumn<Etablissement,String> email;
    @FXML
    private JFXButton Valider;
    @FXML
    private TableColumn<Etablissement,String> categorie;
    @FXML
    private TableColumn<Etablissement,String> etat;
    private ObservableList<Etablissement> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ActionEvent event = null;
     
            listEtab(event);
        
        
            
       
        
        
        // TODO
    }    
    public void listEtab(ActionEvent event)
    {
        ListEtablissement le = new ListEtablissement();
        data = FXCollections.observableArrayList(le.ListEtabsNonValide());
        table.setItems(data);
        nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
     souscat.setCellValueFactory(new PropertyValueFactory<>("souscat"));
    }

    @FXML
    private void RechercheDynamique(KeyEvent event) {
    }

    @FXML
    private void Valider(ActionEvent event) throws SQLException {
         EditEtablissement d = new EditEtablissement();
   
        Etablissement e = table.getSelectionModel().getSelectedItem();
      
       d.ModifierEtat(e.getId());
        ActionEvent event1=null;
        listEtab(event);
    }
    
}
