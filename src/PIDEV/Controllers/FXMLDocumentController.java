/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;


import PIDEV.Entities.Categorie;
import PIDEV.Entities.SousCategorie;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import PIDEV.Services.myCroud;

/**
 * FXML Controller class
 *
 * @author Slim sl
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextArea categorie;
    @FXML
    private TextArea desciption;
   
   myCroud cr= new myCroud() ;
    @FXML
    private ComboBox<String> combo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        myCroud croud = new myCroud();
        List<SousCategorie> clist = new ArrayList<>();
        clist = croud.listerSous_Categories();
        List<String> Slist = new ArrayList<String>();
         
        for(SousCategorie a : clist){
            System.out.println(a.getNom());
           Slist.add(a.getNom());
           
        }
        ObservableList<String> observableList = FXCollections.observableList(Slist);
    combo.setItems(observableList);  
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        Categorie a = new Categorie(categorie.getText(), desciption.getText());
        cr.ajouterCategorie(a);
    }
    
}
