/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Article;
import PIDEV.Entities.Theme;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static PIDEV.Controllers.ArticleController.id;
import PIDEV.Services.myCroud;

/**
 * FXML Controller class
 *
 * @author Slim sl
 */
public class AbonementController implements Initializable {

    @FXML
    private ScrollPane panne;
    @FXML
    private Button AjouterArticle;
    @FXML
    private Button AfficheArticleBut;
    @FXML
    private Button Abonnement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            panne.setContent(Abonnement());
        } catch (SQLException ex) {
            Logger.getLogger(ThemeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    
public Node Abonnement() throws SQLException {
System.out.print("hédhi abonement");
        VBox root0 = new VBox(10);
        HBox H0 = new HBox(6);
        VBox V2 = new VBox(10);
        myCroud mc = new myCroud();
        ObservableList<Article> OB = FXCollections.observableArrayList(mc.Abonnement(PIDEV.Views.FirstFrame.user.getId()));
      //  OB = (ObservableList<Article>) mc.listeArticlesparid(r);

        for (int i = 0; i < OB.size(); i++) {

            HBox root = new HBox(10);
            root.setAlignment(Pos.CENTER_LEFT);
            root.setStyle("-fx-Border-color:  #2471A3");
 root.setPadding(new javafx.geometry.Insets(105,5, 5,5));            
                Article u = OB.get(i);

                //affiche image
                String nomimg=new File("C:/wamp64/www/PIDEV/web/devis/" + u.getImage()).toURI().toString();
                Image image = new Image(nomimg);
                ImageView IMG = new ImageView(image);
                IMG.fitHeightProperty().set(200);
                IMG.fitWidthProperty().set(200);
                IMG.preserveRatioProperty().set(true);
                Separator sep = new Separator(Orientation.VERTICAL);
                VBox root2 = new VBox(6);
                root2.prefWidthProperty().set(100);
                root2.prefHeightProperty().set(200);
                root2.setPadding(new javafx.geometry.Insets(4, 4, 4, 4));

                //les information de evene
                //*****titre
                Label Titre = new Label(u.getTitle());
                Titre.setFont(new javafx.scene.text.Font("Arial", 30));
                Titre.setTextFill(Color.web("#17202A"));
                 Label like = new Label(u.getNbreLike().toString());
                Titre.setFont(new javafx.scene.text.Font("Arial", 30));
                Titre.setTextFill(Color.web("#17202A"));
                //*******description
                Label Description = new Label(u.getContent());
                Description.backgroundProperty().set(Background.EMPTY);
                Description.setTextFill(Color.web("#17202A"));
                VBox root3 = new VBox(3);
                Separator sep2 = new Separator(Orientation.HORIZONTAL);
                //*****date

                Button btn = new Button();
                Button btn2 = new Button();
                btn.setText("Afficher");
                btn2.setText("Supprimer");
                
                Article b = u;
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Article a = u;
                        System.out.println(b);
                        CommentaireController.id=u.getTitle();
                        CommentaireController.id2=id;
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Commentaire.fxml"));
                        Parent root;
                        try {
                            root = loader.load();
                            HomePageController hp=new HomePageController();
                            hp.setNode(root);
                            btn.getScene().setRoot(root);
                        } catch (IOException ex) {
                            Logger.getLogger(ThemeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                btn2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    
                    public void handle(ActionEvent event) {
                                                myCroud cr = new myCroud();
                                                cr.deleteAbonnement(b.getId());
                        
                          
                    }
                });

                root3.getChildren().addAll(btn, btn2);
                root2.getChildren().addAll(Titre, root3,like,Description);
                root.getChildren().addAll(IMG, sep, root2, Description);

            

            root0.getChildren().add(root);
            root0.setPrefSize(720, 200);
            root0.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));

        }
        return root0;

}

    @FXML
    private void AjouterArticle(ActionEvent event) {
    }

    @FXML
    private void AfficheTheme(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/Theme.fxml"));

                        Scene tableViewScene = new Scene(tableViewParent);

                        //This line gets the Stage information
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        window.setScene(tableViewScene);

                        window.show();
    }
}