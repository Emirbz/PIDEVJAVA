/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Views;

import PIDEV.Entities.Article;
import PIDEV.Services.myCroud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Slim sl
 */
public class ModifierThemeController implements Initializable {

    @FXML
    private TextField Nomthemebutton;
    @FXML
    private TextField DescriptionNameButton;
    @FXML
    private Button AjoutThemeButton;
    @FXML
    private Button AnnuleThemeButton;
    @FXML
    private Button addarticle;
    @FXML
    private Button ListeTheme;
    @FXML
    private Button showarticle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjoutTheme(ActionEvent event) {
        myCroud cr = new myCroud();
        Article a = new Article(Nomthemebutton.getText(), DescriptionNameButton.getText());
        cr.modifierArticle(a);
    }

    @FXML
    private void Annuler(ActionEvent event) {
    }

    @FXML
    private void adarticle(ActionEvent event) {
    }

    @FXML
    private void AfficherTheme(ActionEvent event) {
    }

    @FXML
    private void showarticle(ActionEvent event) {
    }
    
}
