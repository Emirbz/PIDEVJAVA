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
import com.jfoenix.controls.JFXDecorator;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.ScopeBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
    @FXML
    private JFXButton fcbSignIn;
    
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
        
       
        login.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
//                        FirstFrame.user = u;
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
//            FirstFrame.user = u;
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

    @FXML
    private void signInFb(ActionEvent event) throws SQLException, IOException {
        String domain = "www://www.google.tn";
        String appId = "569016770142335";
        String appSecret = "ca1174e40d63bb64cffe6853dddf8b5d";

        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain + "&scope=public_profile,email";

        System.setProperty("webdriver.chrome.driver", "D:/O  N  S/Documents/NetBeansProjects/PIDEVTOUTOU/chromedriver/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);

        System.out.println(driver.getCurrentUrl());
        String accessToken;

        boolean b = true;
        while (b) {
            if (!driver.getCurrentUrl().contains("facebook.com")) {

                String url = driver.getCurrentUrl();
                accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
                System.out.println("test");
                
                driver.quit();
                b = false;
                FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);
                String fields = "name,first_name,last_name,email,address";
                User user = fbClient.fetchObject("me", User.class, Parameter.with("fields", fields));
                System.out.println(user.toString());
                System.out.println(user.getName());
                System.out.println(user.toString());
    }
        
        } }  
    
    
    
}
