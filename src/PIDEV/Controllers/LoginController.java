/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.User;
import PIDEV.Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import PIDEV.Views.FirstFrame;

/**
 * FXML Controller class
 *
 * @author Skan
 */
public class LoginController implements Initializable {

    @FXML
    private FontAwesomeIconView close;
    @FXML
    private JFXButton login;
    @FXML
    private Hyperlink forgot;
    @FXML
    private JFXSpinner spinner;
    @FXML
    private JFXButton signup;
    @FXML
    private MaterialDesignIconView minimize;
    @FXML
    private ImageView bgimg;
    @FXML
    private ImageView img;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Label error;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String bgimgname = new File("C:/wamp64/www/PIDEV/web/images/bg1.jpg").toURI().toString();
        bgimg.setImage(new Image(bgimgname));
        String imgname = new File("C:/wamp64/www/PIDEV/web/images/404-1.png").toURI().toString();
        img.setImage(new Image(imgname));
        close.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        }
        );
        password.setOnAction((event) -> {
            login.setVisible(false);
                forgot.setVisible(false);
                error.setVisible(false);
                spinner.setVisible(true);
                PauseTransition pauseTransition = new PauseTransition();
                pauseTransition.setDuration(Duration.seconds(2));
                pauseTransition.setOnFinished(ev -> {
                    try {
                        System.out.println("Success");
                        spinner.setVisible(false);
                        UserService us = new UserService();
                        User u = us.searchUserByEmail(username.getText(), password.getText());
                        if (u != null) {
//                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ProfilUser.fxml"));
//                        
//                        Firstframe.user = u;
//                        Parent root = loader.load();
//                        Stage stage = (Stage) login.getScene().getWindow();
//                        stage.close();
//                        Scene sc=new Scene(root);
//                        Stage stage1=new Stage(StageStyle.UNDECORATED);
//                        stage1.setScene(sc);
//                        stage1.show();
                            FirstFrame.user = u;
                            FXMLLoader loader =  new FXMLLoader(getClass().getResource("../Views/HomePage.fxml"));
                            Parent root = loader.load();
                            Stage stage = new Stage(StageStyle.UNDECORATED);
                            stage.setWidth(1300);
                            stage.setHeight(655);

                            stage.setScene(new Scene(root));
                             stage.show();
                            Stage pr=(Stage) login.getScene().getWindow();
                             pr.close();
                        } else {
                            login.setVisible(true);
                            forgot.setVisible(true);
                            error.setVisible(true);
                        }
                    } catch (SQLException | IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                pauseTransition.play();
            
        
        });
        
       
        login.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                login.setVisible(false);
                forgot.setVisible(false);
                error.setVisible(false);
                spinner.setVisible(true);
                PauseTransition pauseTransition = new PauseTransition();
                pauseTransition.setDuration(Duration.seconds(2));
                pauseTransition.setOnFinished(ev -> {
                    try {
                        System.out.println("Success");
                        spinner.setVisible(false);
                        UserService us = new UserService();
                        User u = us.searchUserByEmail(username.getText(), password.getText());
                        if (u != null) {
//                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ProfilUser.fxml"));
//                        
//                        Firstframe.user = u;
//                        Parent root = loader.load();
//                        Stage stage = (Stage) login.getScene().getWindow();
//                        stage.close();
//                        Scene sc=new Scene(root);
//                        Stage stage1=new Stage(StageStyle.UNDECORATED);
//                        stage1.setScene(sc);
//                        stage1.show();
                            FirstFrame.user = u;
                            FXMLLoader loader =  new FXMLLoader(getClass().getResource("../Views/HomePage.fxml"));
                            Parent root = loader.load();
                            Stage stage = new Stage(StageStyle.UNDECORATED);
                            stage.setWidth(1300);
                            stage.setHeight(655);

                            stage.setScene(new Scene(root));
                             stage.show();
                            Stage pr=(Stage) login.getScene().getWindow();
                             pr.close();
                        } else {
                            login.setVisible(true);
                            forgot.setVisible(true);
                            error.setVisible(true);
                        }
                    } catch (SQLException | IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                pauseTransition.play();
            }
        }
        );
        minimize.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) login.getScene().getWindow();
                stage.setIconified(true);
            }
        }
        );
    }
//    @FXML
//    private void login(MouseEvent event) throws SQLException, IOException {
//        String pseudo = username.getText();
//        String pass = password.getText();
//        UserService us = new UserService();
//        User u = us.searchUserByEmail(username.getText(), password.getText());
//        if (u != null) {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfilUser.fxml"));
//
//            Firstframe.user = u;
//            Parent root = loader.load();
//            login.getScene().setRoot(root);
//        } else {
//            error.setVisible(true);
//            error.setText("Username/Email or password incorrect!");
//        }
//        
//    }

    @FXML
    private void registerPage(MouseEvent event) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("../Views/AddUser.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setWidth(1000);
        stage.setHeight(655);
                         
        stage.setScene(new Scene(root));
        stage.show();
        Stage pr=(Stage) login.getScene().getWindow();
        pr.close();
    }
    
    
}
