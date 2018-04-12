/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Article;
import PIDEV.Entities.Comment;
import PIDEV.Entities.Theme;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import static PIDEV.Controllers.AfficheArticleController.id;
import PIDEV.Services.myCroud;

/**
 * FXML Controller class
 *
 * @author Slim sl
 */
public class CommentaireController implements Initializable {

    public static String id;
    public static String id2;
    @FXML
    private TableView<Comment> TableauDeCommentaire;
    @FXML
    private TableColumn<Comment, String> ListeCommentaire;
    @FXML
    private TextField CHampCommantaire;
    @FXML
    private Button AjoutCommentaire;
    @FXML
    private Button AfficheCommentaire;
    private ObservableList<Comment> data;
    private ObservableList<Article> data2;

    @FXML
    private ImageView imagecommentaire;
    @FXML
    private TextField ContenuArticle;
    @FXML
    private TextField NomArticle;
    @FXML
    private Button AfficheTheme;
    @FXML
    private Button Articlepresedent;
    @FXML
    private Button Abonnement;
    @FXML
    private Button Contact;
    @FXML
    private TableColumn<?, ?> utilisateur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aff();

        // TODO
    }

    @FXML
    private void AjouterCommentaire(ActionEvent event) {
        int r = 0;
        Article T = null;
        myCroud croud = new myCroud();
        List<Article> clist = new ArrayList<>();
        clist = croud.listeArticles();
        for (Article a : clist) {
            if (a.getTitle().equals(id)) {
                r = a.getId();
            }

        }
        List<Article> Tlist = new ArrayList<>();
        Tlist = croud.listeArticles();
        for (Article b : Tlist) {
            if (b.getId() == r) {
                T = b;
            }

        }

        Comment a = new Comment(CHampCommantaire.getText(), T);
        croud.ajouterCommentaire(a);
        aff();
    }

    @FXML
    public void AfficherCommentaire(ActionEvent event) {
        myCroud croud = new myCroud();

        int r = 0;
        Article V = null;
        List<Article> clist = new ArrayList<>();
        clist = croud.listeArticles();
        for (Article a : clist) {
            if (a.getTitle().equals(id)) {
                r = a.getId();
            }

        }

        myCroud T = new myCroud();
        data = FXCollections.observableArrayList(T.listCommentparid(r));

        TableauDeCommentaire.getItems().setAll(data);

        ListeCommentaire.setCellValueFactory(new PropertyValueFactory<>("content"));
    }

    private void aff() {
        myCroud croud = new myCroud();

        int r = 0;
        Article V = null;
        List<Article> clist = new ArrayList<>();
        clist = croud.listeArticles();
        for (Article a : clist) {
            if (a.getTitle().equals(id)) {
                r = a.getId();
            }

        }

        myCroud T = new myCroud();
        data = FXCollections.observableArrayList(T.listCommentparid(r));
        TableauDeCommentaire.getItems().setAll(data);
        utilisateur.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        ListeCommentaire.setCellValueFactory(new PropertyValueFactory<>("content"));

        int a = 0;
        Theme F = null;
        myCroud croude = new myCroud();
        List<Theme> dlist = new ArrayList<>();
        dlist = croude.listetheme1();
        for (Theme A : dlist) {
            if (A.getName().equals(id2)) {
                a = A.getId();
            }
            System.out.print(id2);
        }

        myCroud G = new myCroud();
        data2 = FXCollections.observableArrayList(G.listeArticlesparid(a));
        for (Article ar : data2) {

            NomArticle.setText(ar.getTitle());
            ContenuArticle.setText(ar.getTitle());
            final String imageURI = new File("C:/wamp64/www/PIDEV/web/devis/" + ar.getImage()).toURI().toString();
            imagecommentaire.setImage(new Image(imageURI));
        }
    }

    private void AfficherArticle(ActionEvent event) {
        int r = 0;
        Theme V = null;
        myCroud croud = new myCroud();
        List<Theme> clist = new ArrayList<>();
        clist = croud.listetheme1();
        for (Theme a : clist) {
            if (a.getName().equals(id2)) {
                r = a.getId();
            }
            System.out.print(id2);
        }

        myCroud T = new myCroud();
        data2 = FXCollections.observableArrayList(T.listeArticlesparid(r));
        NomArticle.setText(new PropertyValueFactory<>("title").toString());
        ContenuArticle.setText(new PropertyValueFactory<>("content").toString());
//        final String imageURI = new File("C:/wamp64/www/PIDEV/web/devis/" + deal.getDevisName()).toURI().toString();
//        imagecommentaire.setImage(new Image(imageURI));
    }

    @FXML
    private void afficherTheme(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/Theme.fxml"));

        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);

        window.show();
    }

    @FXML
    private void articlepere(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/Article.fxml"));

        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);

        window.show();
    }

    @FXML
    private void ListeAbonnement(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/Abonement.fxml"));

        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);

        window.show();

    }

    @FXML
    private void Contacte(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/Mail.fxml"));

        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);

        window.show();
    }
}
