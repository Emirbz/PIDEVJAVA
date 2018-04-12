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
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
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

    @FXML
    private void signInFb(ActionEvent event) {
         String domain = "http://localhost";
        String appId = "1020906611394409";
        String appSecret = "c8163148dd16e6e2b4e29fa9648a71c1";

        String authUrl = "https://www.facebook.com/dialog/oauth?\n"
                + "                    client_id=1020906611394409\n"
                + "                    &redirect_uri=https://www.facebook.com/connect/login_success.html \n"
                + "                    &client_secret=9f4784603794d68b12fc2999e77745b1&response_type=token&scope=email,user_hometown,public_profile";

        System.setProperty("webdriver.chrome.driver", "D:/O  N  S/Documents/NetBeansProjects/PIDEVTOUTOU/chromedriver/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);

        System.out.println(driver.getCurrentUrl());
        String accessToken;

        boolean b = true;
        while (b) {
//            if (!driver.getCurrentUrl().contains("facebook.com")) {
//
//                String url = driver.getCurrentUrl();
//                accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
//                System.out.println("test");
//                driver.quit();
//                b = false;
//                FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);
//                String fields = "name,first_name,last_name,email,address,picture";
//                User user = fbClient.fetchObject("me", User.class, Parameter.with("fields", fields));
//                System.out.println(user.toString());
//                System.out.println(user.getName());
//                System.out.println(user.toString());
//
//                UserService us = new UserService();
//                if (us.searchUserByEmailOnly(user.getEmail()) != null) {
//                    User u = us.searchUserByEmailOnly(user.getEmail().toLowerCase());
//                    
//                    
//                }
//
//            }
//
        }

    }
    
    
}
