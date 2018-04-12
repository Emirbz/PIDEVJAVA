/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Controllers.AffichageThemeController;
import PIDEV.Entities.Article;
import PIDEV.Entities.Theme;
import PIDEV.Services.myCroud;
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

/**
 * FXML Controller class
 *
 * @author Slim sl
 */
public class AffichageArticleAdminController implements Initializable {

    @FXML
    private Button displaythemebutton;
    @FXML
    private Button ajout_article;
    @FXML
    private Button affichagetheme;
    private ObservableList<Article> data;
    @FXML
    private TableView<Article> TableViewArticle;
    @FXML
    private TableColumn<Article, String> ArticleTitle;
    @FXML
    private TableColumn<Article, String> Contenu;
    @FXML
    private Button ajout_article1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        myCroud T = new myCroud();
        data = FXCollections.observableArrayList(T.listeArticles());

        TableViewArticle.setItems(data);

        ArticleTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        Contenu.setCellValueFactory(new PropertyValueFactory<>("content"));
        TableViewArticle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {

                    try {
                        @SuppressWarnings("rawtypes")
                        TablePosition pos = TableViewArticle.getSelectionModel().getSelectedCells().get(0);
                        int row = pos.getRow();
                        int col = pos.getColumn();
                        @SuppressWarnings("rawtypes")
                        TableColumn column = pos.getTableColumn();
                        String val = column.getCellData(row).toString();
                        System.out.println("Selected Value, " + val + ", Column: " + col + ", Row: " + row);
                      
                        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Commentaire.fxml"));

                        Scene tableViewScene = new Scene(tableViewParent);

                        //This line gets the Stage information
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        window.setScene(tableViewScene);

                        window.show();
                    } catch (IOException ex) {
                    }

                }

            }

        });
        // TODO
    }    

    @FXML
    private void SupprimerArticle(ActionEvent event) {
         myCroud croud = new myCroud();
        Article SelectedForDeletion = TableViewArticle.getSelectionModel().getSelectedItem();
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
            
        
    }

    @FXML
    private void ModifierArticle(ActionEvent event) {
        myCroud croud = new myCroud();
        Article SelectedForEdit = TableViewArticle.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/UpdateArticle.fxml"));
            Parent parent = loader.load();
            croud.modifier=SelectedForEdit;
        Stage stage = new Stage();
            stage.setTitle("modifier");
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(AffichageArticleAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void affichetheme(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AffichageTheme.fxml"));

                        Scene tableViewScene = new Scene(tableViewParent);

                        //This line gets the Stage information
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        window.setScene(tableViewScene);

                        window.show();
                    

    }

    @FXML
    private void redirect_to_add_article(ActionEvent event) {
          try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/AjouterArticle.fxml"));

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
    private void rediract_to_show_themes(ActionEvent event) {
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

    @FXML
    private void rediract_to_show_article(ActionEvent event) {
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
