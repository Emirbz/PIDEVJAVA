/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Article;
import PIDEV.Entities.Theme;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;
import PIDEV.Services.myCroud;

/**
 * FXML Controller class
 *
 * @author Slim sl
 */
public class AfficheArticleController implements Initializable {

    ObservableList<Article> list = FXCollections.observableArrayList();
    public static String id;
    @FXML
    private TableView<Article> TableArticle;
    @FXML
    private TableColumn<Article, String> TitreArticleCol;
    @FXML
    private TableColumn<Article, String> ContenuArticleCol;
    @FXML
    private Button AfficheArticleBut;
    private ObservableList<Article> data;
    @FXML
    private Button ajouter_article;
    @FXML
    private Button AjouterTheme;
    @FXML
    private Button showtheme;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Afficher();
        // TODO
    }

    @FXML
    private void AfficherArticle(ActionEvent event) {
        int r = 0;
        Theme V = null;
        myCroud croud = new myCroud();
        List<Theme> clist = new ArrayList<>();
        clist = croud.listetheme1();
        for (Theme a : clist) {
            if (a.getName().equals(id)) {
                r = a.getId();
            }

        }

        myCroud T = new myCroud();
        data = FXCollections.observableArrayList(T.listeArticlesparid(r));

        TableArticle.setItems(data);

        TitreArticleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        ContenuArticleCol.setCellValueFactory(new PropertyValueFactory<>("content"));
        TableArticle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {

                    try {
                        @SuppressWarnings("rawtypes")
                        TablePosition pos = TableArticle.getSelectionModel().getSelectedCells().get(0);
                        int row = pos.getRow();
                        int col = pos.getColumn();
                        @SuppressWarnings("rawtypes")
                        TableColumn column = pos.getTableColumn();
                        String val = column.getCellData(row).toString();
                        System.out.println("Selected Value, " + val + ", Column: " + col + ", Row: " + row);
                        CommentaireController.id = val;
                        CommentaireController.id2=id;
                        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/Commentaire.fxml"));

                        Scene tableViewScene = new Scene(tableViewParent);

                        //This line gets the Stage information
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        window.setScene(tableViewScene);

                        window.show();
                    } catch (IOException ex) {
                        Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        });
    }

    private void Afficher() {
        int r = 0;
        Theme V = null;
        myCroud croud = new myCroud();
        List<Theme> clist = new ArrayList<>();
        clist = croud.listetheme1();
        for (Theme a : clist) {
            if (a.getName().equals(id)) {
                r = a.getId();
            }

        }

        myCroud T = new myCroud();
        data = FXCollections.observableArrayList(T.listeArticlesparid(r));

        TableArticle.setItems(data);

        TitreArticleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        ContenuArticleCol.setCellValueFactory(new PropertyValueFactory<>("content"));
        TableArticle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {

                    try {
                        @SuppressWarnings("rawtypes")
                        TablePosition pos = TableArticle.getSelectionModel().getSelectedCells().get(0);
                        int row = pos.getRow();
                        int col = pos.getColumn();
                        @SuppressWarnings("rawtypes")
                        TableColumn column = pos.getTableColumn();
                        String val = column.getCellData(row).toString();
                        System.out.println("Selected Value, " + val + ", Column: " + col + ", Row: " + row);
                        CommentaireController.id = val;
                        CommentaireController.id2=id;
                        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/Commentaire.fxml"));

                        Scene tableViewScene = new Scene(tableViewParent);

                        //This line gets the Stage information
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        window.setScene(tableViewScene);

                        window.show();
                    } catch (IOException ex) {
                        Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        });
    }

    @FXML
    private void SupprimerArticle(ActionEvent event) {
        myCroud croud = new myCroud();
        Article SelectedForDeletion = TableArticle.getSelectionModel().getSelectedItem();
        int r = 0;
        Theme V = null;
        List<Article> clist = new ArrayList<>();
        clist = croud.listeArticles();
        for (Article a : clist) {
            if (a.getTitle().equals(SelectedForDeletion.getTitle())) {
                r = a.getId();
            }
            }

      
            croud.deleteArticle(r);
        
        Afficher();

    }

    @FXML
    private void showtheme(ActionEvent event) {
          try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/AffichageTheme.fxml"));

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
