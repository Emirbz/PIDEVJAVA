/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Article;
import PIDEV.Entities.Deal;
import PIDEV.Entities.Etablissement;
import PIDEV.Entities.Events;
import PIDEV.Utils.MyConnexion;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Skan
 */
public class AddClaimController implements Initializable {

    @FXML
    private JFXComboBox<String> type;
    @FXML
    private JFXComboBox<Deal> tdeal;
    @FXML
    private JFXComboBox<Etablissement> tetab;
    @FXML
    private JFXComboBox<Events> tevent;
    @FXML
    private JFXComboBox<Article> tarticle;
    @FXML
    private JFXTextField tautre;
    @FXML
    private Pane Pane;
    @FXML
    private JFXTextArea contenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Pane.setPrefSize(150, 50);
        Pane.setPadding(new Insets(10, 5, 10, 5));
        Connection cn = MyConnexion.getInstance().getConnection();
        type.getItems().addAll("Catégorie :", "Etablissement", "Deal", "Article", "Evenement", "Autre");
        type.getSelectionModel().selectFirst();
        tdeal.setVisible(false);
        tetab.setVisible(false);
        tevent.setVisible(false);
        tarticle.setVisible(false);
        tautre.setVisible(false);
        type.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    switch (type.getValue()) {
                        case "Etablissement":
                            String req = "Select * from Etablissement";
                            Statement st = cn.createStatement();
                            ResultSet rs = st.executeQuery(req);
                            while (rs.next()) {
                                Etablissement et = new Etablissement();
                                et.setName(rs.getString("name"));
                                et.setId(rs.getInt("id"));
                                tetab.getItems().add(et);
                                tetab.setVisible(true);
                                tdeal.setVisible(false);
                                tevent.setVisible(false);
                                tarticle.setVisible(false);
                                tautre.setVisible(false);
                            }
                            break;
                        case "Deal":
                            String reqq = "Select * from Deal";
                            Statement stt = cn.createStatement();
                            ResultSet rsr = stt.executeQuery(reqq);
                            while (rsr.next()) {
                                Deal d = new Deal();
                                d.setNom(rsr.getString("nom"));
                                d.setId(rsr.getInt("id"));
                                tdeal.getItems().add(d);
                            }
                            tdeal.setVisible(true);
                            tetab.setVisible(false);
                            tevent.setVisible(false);
                            tarticle.setVisible(false);
                            tautre.setVisible(false);
                            break;
                        case "Article":
                            String reeq = "Select * from Article";
                            Statement sst = cn.createStatement();
                            ResultSet rrs = sst.executeQuery(reeq);
                            while (rrs.next()) {
                                Article a = new Article();
                                a.setTitle(rrs.getString("title"));
                                a.setId(rrs.getInt("id"));
                                tarticle.getItems().add(a);
                            }
                            tarticle.setVisible(true);
                            tdeal.setVisible(false);
                            tetab.setVisible(false);
                            tevent.setVisible(false);
                            tautre.setVisible(false);
                            break;
                        case "Evenement":
                            String rreq = "Select * from Article";
                            Statement rst = cn.createStatement();
                            ResultSet srs = rst.executeQuery(rreq);
                            JFXComboBox<Events> nome = new JFXComboBox<>();
                            while (srs.next()) {
                                Events e = new Events();
                                e.setName(srs.getString("name"));
                                e.setId(srs.getInt("id"));
                                tevent.getItems().add(e);
                            }
                            tevent.setVisible(true);
                            tdeal.setVisible(false);
                            tetab.setVisible(false);
                            tarticle.setVisible(false);
                            tautre.setVisible(false);
                            break;
                        case "Autre":

                            tautre.setPromptText("Précizer l'objet reclamé");
                            tautre.setVisible(true);
                            tdeal.setVisible(false);
                            tetab.setVisible(false);
                            tevent.setVisible(false);
                            tarticle.setVisible(false);
                            break;
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    public String getType() {
        return type.getValue();
    }

    public Deal getTdeal() {
        return tdeal.getValue();
    }
    public Etablissement getTetab() {
        return tetab.getValue();
    }
    public Events getTevent() {
        return tevent.getValue();
    }
    public Article getTarticle() {
        return tarticle.getValue();
    }

    public String getTautre() {
        return tautre.getText();
    }

    public String getContenu() {
        return contenu.getText();
    }

  
}
