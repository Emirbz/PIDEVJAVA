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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author Slim sl
 */
public class AjouterArticleADminController implements Initializable {

    @FXML
    private Button AjouterArticleButton;
    @FXML
    private Button AnnulerArticleBUtton;
    @FXML
    private ComboBox<String> ListThemeArticle;
    @FXML
    private TextField TitreArticle;
    @FXML
    private TextField ContenuArticle;
    @FXML
    private ImageView img;
    @FXML
    private Button ajoutimg;
    @FXML
    private Button showarticle;
    @FXML
    private Button showtheme;
    @FXML
    private Button addtheme;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      myCroud croud = new myCroud();
        List<Theme> clist = new ArrayList<>();
        clist = croud.listetheme1();
        List<String> Slist = new ArrayList<String>();
        for(Theme a : clist){
           Slist.add(a.getName());
        }
        ObservableList<String> observableList = FXCollections.observableList(Slist);
    ListThemeArticle.setItems(observableList);  
     int r=0;
        Theme T = null;
               myCroud Croud = new myCroud();
        List<Theme> Clist = new ArrayList<>();
        Clist = croud.listetheme1();
        for (Theme a : clist){
            if (a.getName().equals(ListThemeArticle.getValue()))
            {
                r = a.getId();
            }   
        }
        List<Theme> Tlist = new ArrayList<>();
        Tlist = croud.listetheme1();
        for (Theme b : Tlist){
            if (b.getId()==r){
                T=b;
            }
        }
    
        // TODO
    }   

    @FXML
    private void AjouterArticle(ActionEvent event) {
        if (TitreArticle.getText()==""||ContenuArticle.getText()==""){
            System.out.println("vide");
        }else
        {
        int r=0;
        Theme T = null;
               myCroud croud = new myCroud();
        List<Theme> clist = new ArrayList<>();
        clist = croud.listetheme1();
        for (Theme a : clist){
            if (a.getName().equals(ListThemeArticle.getValue())){
                r = a.getId();
            }
                
        }
        List<Theme> Tlist = new ArrayList<>();
        Tlist = croud.listetheme1();
        for (Theme b : Tlist){
            if (b.getId()==r){
                T=b;
            }
                
        }
        Article a = new Article(TitreArticle.getText(), ContenuArticle.getText(),T);
        Image imge=img.getImage();
        String nomimage=saveToFileImageNormal(imge);
        a.setImage(nomimage);
        System.out.print("qqqaze"+PIDEV.Views.FirstFrame.user.getId());
        croud.ajouterArticle(a);
    }}

    @FXML
    private void AnnulerArticle(ActionEvent event) {
    }
    @FXML
    private void addImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            img.setImage(image);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String saveToFileImageNormal(Image image) {

        String ext = "jpg";
        File dir = new File("C:/wamp64/www/PIDEV/web/devis");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    }

    @FXML
    private void showarticle(ActionEvent event) {
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

    @FXML
    private void addtheme(ActionEvent event) {
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
    
}
