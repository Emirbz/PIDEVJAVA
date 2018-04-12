/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import PIDEV.Services.Ajouttemoignages;
import PIDEV.Utils.MyConnexion;
import PIDEV.Entities.temoignages;

/**
 * FXML Controller class
 *
 * @author dahem
 */
public class AjouttemoignagesController implements Initializable {

    @FXML
    private TextField description;
    @FXML
    private TextField titre;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void AjoutTemoignage(ActionEvent event) throws IOException, SQLException {
        Connection cn = MyConnexion.getInstance().getConnection();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        temoignages T = new temoignages();

        T.setTitre(titre.getText());
        T.setDescription(description.getText());
        T.setIduser(PIDEV.Views.FirstFrame.user);
        T.setName("aaaa");
        Ajouttemoignages at = new Ajouttemoignages();
        at.addtemoignage(T);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Affichetemoignages.fxml"));
        Parent root = loader.load();
        AffichetemoignagesController ae = loader.getController();
        Stage s = (Stage) description.getScene().getWindow();
        s.close();
        Stage se=new Stage();
        Scene sc=new Scene(root);
        se.setScene(sc);
        se.show();

    }

}
