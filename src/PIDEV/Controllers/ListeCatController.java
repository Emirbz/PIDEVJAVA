/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Categorie;
import PIDEV.Services.CategorieService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ons
 */
public class ListeCatController implements Initializable {

    @FXML
    private TableColumn<Categorie, ImageView> images;

    @FXML
    private TableColumn<Categorie, String> noms;
    private ObservableList<Categorie> data;
    @FXML
    private TableView<?> table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void listCat(ActionEvent event) throws IOException {
        CategorieService catService=new CategorieService();
        data=FXCollections.observableArrayList(catService.listCategorie());
       
    }
    
}
