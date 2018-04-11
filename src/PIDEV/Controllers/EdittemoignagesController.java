/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import PIDEV.Services.Edittemoignages;
import PIDEV.Entities.temoignages;

/**
 * FXML Controller class
 *
 * @author dahem
 */
public class EdittemoignagesController implements Initializable {

    @FXML
    private Button modifier;
    @FXML
    private TextField description;
    @FXML
    private TextField titre;
    @FXML
    Label id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void getoldvalues(temoignages tem) {
        description.setText(tem.getDescription());
        titre.setText(tem.getTitre());
        id.setText(String.valueOf(tem.getId()));

    }
    // TODO

    @FXML
    private void Edittemoignages(ActionEvent event) throws SQLException, IOException {
        temoignages tt = new temoignages();
        tt.setDescription(description.getText());
        tt.setTitre(titre.getText());
        tt.setId(Integer.parseInt(id.getText()));
        Edittemoignages et = new Edittemoignages();
        et.modifiertemoignages(tt,tt.getId());
    }
}
