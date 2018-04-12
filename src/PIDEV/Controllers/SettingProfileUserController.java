/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import PIDEV.Views.FirstFrame;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import org.mindrot.jbcrypt.BCrypt;


/**
 * FXML Controller class
 *
 * @author ons
 */
public class SettingProfileUserController implements Initializable {
    private AnchorPane profile;
    @FXML
    private AnchorPane settings;
    @FXML
    private JFXButton deleteAccount;
    @FXML
    private JFXPasswordField currentPass;
    @FXML
    private JFXPasswordField newPass;
    @FXML
    private JFXPasswordField confPass;
    @FXML
    private JFXButton changePass;
    @FXML
    private Label errCurrentPass;
    @FXML
    private Label errDiffPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errCurrentPass.setVisible(false);
            errDiffPass.setVisible(false);
    }   
    
    

    @FXML
    private void deleteUser(ActionEvent event) throws SQLException, IOException {
       
//         FXMLLoader loader = new FXMLLoader(ReservationProfilUserController.class.getResource("../Views/DeleteProfile.fxml"));
//         AnchorPane page = (AnchorPane) loader.load();
//        Stage dialogStage = new Stage();
//            dialogStage.initOwner(primaryStage);
//            dialogStage.initModality(Modality.WINDOW_MODAL);
//          dialogStage.initStyle(StageStyle.UNDECORATED);
//        dialogStage.initOwner(primaryStage);
//            Scene scene = new Scene(page);
//         dialogStage.setScene(scene);
//         dialogStage.show();
        ButtonType bt1=new ButtonType("yes");
        ButtonType bt2=new ButtonType("no");
        
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION, null, bt1,bt2);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure?");
        alert.setHeaderText(null);
        
        Optional<ButtonType> bt=alert.showAndWait();
        
        if((bt.isPresent())&&(bt.get()==bt1) ){
        
        
        
        UserService userService = new UserService();   
        userService.deleteUser(FirstFrame.user);
          FirstFrame.user=null;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/HomePage.fxml"));
        AnchorPane accueil =FXMLLoader.load(getClass().getResource("../Views/Accueil.fxml"));
        Parent root = loader.load();
        HomePageController hp=loader.getController();
        hp.setNode(accueil);
        deleteAccount.getScene().setRoot(root);
        }else{
            alert.close();
        }
        
    }

    @FXML
    private void changePassword(ActionEvent event) throws SQLException, IOException {
        if(BCrypt.checkpw(currentPass.getText(),"$2a$"+FirstFrame.user.getPassword().substring(4, FirstFrame.user.getPassword().length()))){
            errCurrentPass.setVisible(false);
            if(newPass.getText().equals(confPass.getText())){
                errDiffPass.setVisible(false);
            UserService userService = new UserService();
            userService.changePassUser(FirstFrame.user,newPass.getText());
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../Views/HomePage.fxml"));
            profile=FXMLLoader.load(getClass().getResource("../Views/ProfilUser.fxml"));
            Parent root= loader.load();
            HomePageController hpc= loader.getController();
            hpc.setNode(profile);
            deleteAccount.getScene().setRoot(root);
            }else{
                errDiffPass.setVisible(true);
            }
               
        }
        else{
            errCurrentPass.setVisible(true);
        }
        
    }
            
    
}
