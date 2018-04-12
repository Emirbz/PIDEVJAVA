/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Theme;
import PIDEV.Entities.User;
import java.awt.Insets;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import PIDEV.Services.myCroud;

/**
 * FXML Controller class
 *
 * @author Slim sl
 */
public class ThemeController implements Initializable {

    private Pane paane;
    @FXML
    private ScrollPane pane;
    @FXML
    private Button AfficheArticleBut;
    @FXML
    private Button Mail;
    @FXML
    private Button Abonnement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            pane.setContent(ListTheme());
            AjouterArticleController.id=PIDEV.Views.FirstFrame.user.getId();
        } catch (SQLException ex) {
            Logger.getLogger(ThemeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public Node ListTheme() throws SQLException {

        VBox root0 = new VBox(10);
        HBox H0 = new HBox(10);
        VBox V2 = new VBox(10);
        myCroud mc = new myCroud();
        ObservableList<Theme> OB = FXCollections.observableArrayList();
        OB = (ObservableList<Theme>) mc.listetheme1();

        for (int i = 0; i < OB.size(); i++) {

            HBox root = new HBox(10);
            root.setAlignment(Pos.CENTER_LEFT);
            root.setStyle("-fx-Border-color:  #2471A3");
            root.setPadding(new javafx.geometry.Insets(5,5, 5, 5));
            
                Theme u = OB.get(i);

                //affiche image
                String nomimg=new File("C:/wamp64/www/PIDEV/web/devis/" + u.getImage()).toURI().toString();
                Image image = new Image(nomimg);
                ImageView IMG = new ImageView(image);
                IMG.fitHeightProperty().set(100);
                IMG.fitWidthProperty().set(100);
                IMG.preserveRatioProperty().set(true);
                Separator sep = new Separator(Orientation.VERTICAL);
                VBox root2 = new VBox(6);
                root2.prefWidthProperty().set(200);
                root2.prefHeightProperty().set(200);
                root2.setPadding(new javafx.geometry.Insets(4, 4, 4, 4));

                //les information de evene
                //*****titre
                Label Name = new Label(u.getName());
                Name.setFont(new javafx.scene.text.Font("Arial", 30));
                Name.setTextFill(Color.web("#17202A"));
                Label Description = new Label(u.getDescription());
                Name.setFont(new javafx.scene.text.Font("Arial", 30));
                Name.setTextFill(Color.web("#17202A"));
                
                
                VBox root3 = new VBox(3);
                Separator sep2 = new Separator(Orientation.HORIZONTAL);
                
                

                Button btn = new Button();
                btn.setText("Affiche l'article");
                
                Theme theme = u;
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Theme T = u;
                        System.out.println(T);
                        ArticleController.id = T.getName();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Article.fxml"));
                        Parent root;
                        try {
                            root = loader.load();
                            btn.getScene().setRoot(root);
                        } catch (IOException ex) {
                            Logger.getLogger(ThemeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
               
               

                root3.getChildren().addAll(btn);
                root2.getChildren().addAll(Name, root3);
                root.getChildren().addAll(IMG, sep, root2, Description);

            

            root0.getChildren().add(root);
            root0.setPrefSize(720, 200);
            root0.setPadding(new javafx.geometry.Insets(0, 0, 0, 0));

        }
        return root0;

    }

    @FXML
    private void AfficherArticle(ActionEvent event) {
    }

    @FXML
    private void Mail(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../Views/Mail.fxml"));

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
    }


