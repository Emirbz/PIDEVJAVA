/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Slim sl
 */
public class AfficheCategorieController implements Initializable {

    @FXML
    private Label NomCat;
    @FXML
    private Label descricat;
    @FXML
    private Button affiche;

    
    
    public Label getNomCat() {
        return NomCat;
        // TODO
    }    

    public void setNomCat(Label NomCat) {
        this.NomCat = NomCat;
    }

    public Label getDescricat() {
        return descricat;
    }

    public void setDescricat(Label descricat) {
        this.descricat = descricat;
    }

    public Button getAffiche() {
        return affiche;
    }

    /**
     * Initializes the controller class.
     */
    public void setAffiche(Button affiche) {
        this.affiche = affiche;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherCat(ActionEvent event) {
    }
    
}
