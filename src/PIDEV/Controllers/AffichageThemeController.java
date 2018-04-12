/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import com.mysql.jdbc.PreparedStatement;
import PIDEV.Entities.Theme;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import PIDEV.Services.myCroud;
import PIDEV.Utils.MyConnexion;

/**
 * FXML Controller class
 *
 * @author Slim sl
 */
public class AffichageThemeController implements Initializable {

    ObservableList<Theme> list = FXCollections.observableArrayList();

    @FXML
    private AnchorPane AnchorPaneTHeme;
    @FXML
    private TableView<Theme> TableViewTheme;
    @FXML
    private TableColumn<Theme, String> ThemeNameColumn;
    @FXML
    private TableColumn<Theme, String> ThemeDescriptionColumn;
    private ObservableList<Theme> data;
    @FXML
    private Button displaythemebutton;
    @FXML
    private Button AjoutTheme;
    @FXML
    private Button affiche_article;
    @FXML
    private Button AjoutArticle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affi();
        // TODO
    }

    @FXML
    private void affichetheme(ActionEvent event) {
//        myCroud T = new myCroud();
//         int r = 0;
//        Theme V = null;
//        myCroud croud = new myCroud();
//        List<Theme> clist = new ArrayList<>();
//        clist = croud.listetheme1();
//      
//        data = FXCollections.observableArrayList(T.listetheme1());
//          for (Theme a : clist) {
//            if (a.getName().equals(T)) {
//               
//            }
//
//        }
//        TableViewTheme.setItems(data);
//        ThemeNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        ThemeDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
//        TableViewTheme.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if (event.getClickCount() == 2) {
//
//                    try {
//                        @SuppressWarnings("rawtypes")
//                        TablePosition pos = TableViewTheme.getSelectionModel().getSelectedCells().get(0);
//                        int row = pos.getRow();
//                        int col = pos.getColumn();
//                        @SuppressWarnings("rawtypes")
//                        TableColumn column = pos.getTableColumn();
//                        String val = column.getCellData(row).toString();
//                        System.out.println("Selected Value, " + val + ", Column: " + col + ", Row: " + row);
//                        AfficheArticleController.id = val;
//                        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AfficheArticle.fxml"));
//
//                        Scene tableViewScene = new Scene(tableViewParent);
//
//                        //This line gets the Stage information
//                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//                        window.setScene(tableViewScene);
//
//                        window.show();
//                    } catch (IOException ex) {
//                        Logger.getLogger(AffichageThemeController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                }
//
//            }
//
//        }
//        );
    }

    private void affi() {
        myCroud T = new myCroud();
        data = FXCollections.observableArrayList(T.listetheme1());
        TableViewTheme.setItems(data);
        ThemeNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ThemeDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableViewTheme.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {

                    try {
                        @SuppressWarnings("rawtypes")
                        TablePosition pos = TableViewTheme.getSelectionModel().getSelectedCells().get(0);
                        int row = pos.getRow();
                        int col = pos.getColumn();
                        @SuppressWarnings("rawtypes")
                        TableColumn column = pos.getTableColumn();
                        String val = column.getCellData(row).toString();
                        System.out.println("Selected Value, " + val + ", Column: " + col + ", Row: " + row);
                        AfficheArticleController.id = val;
                        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/AfficheArticle.fxml"));

                        Scene tableViewScene = new Scene(tableViewParent);

                        //This line gets the Stage information
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        window.setScene(tableViewScene);

                        window.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AffichageThemeController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        }
        );

    }

    @FXML
    private void SupprimerTheme(ActionEvent event) {
        myCroud croud = new myCroud();
        Theme SelectedForDeletion = TableViewTheme.getSelectionModel().getSelectedItem();

        if (croud.deleteTheme(SelectedForDeletion) == true) {
            croud.deleteTheme(SelectedForDeletion);
        }
        affi();

    }

    @FXML
    private void ModifierTheme(ActionEvent event) {
        myCroud croud = new myCroud();
        Theme SelectedForEdit = TableViewTheme.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/AjoutTheme.fxml"));
            Parent parent = loader.load();
            AjoutThemeController controller = (AjoutThemeController) loader.getController();
            controller.modifier(SelectedForEdit);
            Stage stage = new Stage();
            stage.setTitle("modifier");
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(AffichageThemeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void AjoutTheme(ActionEvent event) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/AjoutTheme.fxml"));

            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);

            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageThemeController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @FXML
    private void Ajoutarticle(ActionEvent event) {
          try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/AjouterArticleADmin.fxml"));

            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);

            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageThemeController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @FXML
    private void afficheArticle(ActionEvent event) {
      try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/AffichageArticleAdmin.fxml"));

            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);

            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageThemeController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
    }

