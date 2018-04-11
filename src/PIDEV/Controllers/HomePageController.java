/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Views.FirstFrame;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.collections.ObservableArray;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author ons
 */
public class HomePageController implements Initializable {

    @FXML
    private AnchorPane home;
    @FXML
    private JFXDrawer menu;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane mainAP;
    private AnchorPane accueil, profilUser,settingPage;
    private Button test;
    @FXML
    private AnchorPane menuBar;
    @FXML
    private AnchorPane loginRegisterProfie;
    @FXML
    private HBox profileHB;
    @FXML
    private Circle photoProfilMenu;
    @FXML
    private HBox menuHB;
    @FXML
    private VBox profileVB;
    @FXML
    private JFXButton profilMenu;
    @FXML
    private Label nameUserMenu;
    @FXML
    private Label labelLastLoginMenu;
    @FXML
    private Label LastLoginMenu;
    @FXML
    private JFXButton logoutMenu;
    @FXML
    private AnchorPane barreRecherche;
    @FXML
    private JFXTextField searchLabel;
    @FXML
    private JFXComboBox<?> typeSearch;
    @FXML
    private JFXButton searchButton;
    @FXML
    private VBox menuVB;
    @FXML
    private VBox accueilVB;
    @FXML
    private JFXButton accueilButton;
    @FXML
    private VBox catVB;
    @FXML
    private JFXButton catButton;
    @FXML
    private VBox sousMenuCat;
    @FXML
    private JFXButton ajoutCatB;
    @FXML
    private JFXButton restaurantB;
    @FXML
    private JFXButton hotelB;
    @FXML
    private JFXButton centreBeauteB;
    @FXML
    private JFXButton culturelB;
    @FXML
    private VBox eventVB;
    @FXML
    private JFXButton eventButton;
    @FXML
    private VBox sousMenuEvent;
    @FXML
    private JFXButton ajoutEventB;
    @FXML
    private JFXButton listeEventB;
    @FXML
    private VBox blogVB;
    @FXML
    private JFXButton blogButton;
    @FXML
    private VBox sousMenuBlog;
    @FXML
    private JFXButton ajoutArticleB;
    @FXML
    private JFXButton themeB;
    @FXML
    private JFXButton listArticleB;
    @FXML
    private VBox dealVB;
    @FXML
    private JFXButton dealButton;
    @FXML
    private VBox sousMenuDeal;
    @FXML
    private JFXButton ajoutDealB;
    @FXML
    private JFXButton listDealB;
     Map<HBox, HBox> mapProfile = new HashMap<HBox, HBox>();
     Map<VBox, VBox> map = new HashMap<VBox, VBox>();
    @FXML
    private ScrollPane scrollHomePage;
    @FXML
    private JFXButton settingsProfile;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        scrollHomePage.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollHomePage.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        
        //homepage Hamburger setting 
        
//            AnchorPane mb = FXMLLoader.load(getClass().getResource("../Views/MenuBar.fxml"));
              menu.setSidePane(menuBar);
//            for(Node node : mb.getChildren()){
//                    if (node.getTypeSelector()=="JFXButton"){
//                         if(node.getAccessibleText()=="Profile"){
//                            
//                            
//                    }
//                       
//                
//            }
//          }
            HamburgerBackArrowBasicTransition burger = new HamburgerBackArrowBasicTransition(hamburger);
            burger.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                burger.setRate(burger.getRate() * -1);
                burger.play();
                
                if (menu.isShown()) {
                    menuBar.toBack();
                  //   menuBar.setVisible(false);
                    menu.close();
                    hamburger.setLayoutX(20);
                } else {
                    menuBar.toFront();
                    menu.open();
                    hamburger.setLayoutX(320);
                   // menuBar.setVisible(true);
                }
            }
            );
        
        //end hamburger
        try {
            accueil = FXMLLoader.load(getClass().getResource("../Views/Accueil.fxml"));

            setNode(accueil);

        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (FirstFrame.user == null) {
            FontAwesomeIconView iconCon = new FontAwesomeIconView(FontAwesomeIcon.SIGN_IN);
            iconCon.setFill(Color.web("#c9c9c9"));
            iconCon.setSize(String.valueOf(14));

            JFXButton inscriButton = new JFXButton("Inscription|Connexion");
            inscriButton.setGraphic(iconCon);
            inscriButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    //       System.out.println("lol");
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Login.fxml"));
                        Parent root = loader.load();
                        LoginController lc = loader.getController();
                        Stage stage = new Stage(StageStyle.UNDECORATED);
                        stage.setWidth(700);
                        stage.setHeight(400);

                        stage.setScene(new Scene(root));
//                            iconCon.getScene().setRoot(root);
                        stage.show();
                        Stage pr = (Stage) searchButton.getScene().getWindow();
                        pr.close();
                    } catch (IOException ex) {
                        Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            inscriButton.setTextFill(Color.web("#c9c9c9"));
            inscriButton.setLayoutX(70);
            inscriButton.setLayoutY(30);

            loginRegisterProfie.getChildren().add(inscriButton);
        } else {
            File file = new File("C:/wamp64/www/PIDEV/web/devis/" + FirstFrame.user.getDevis_name());
            BufferedImage bufferedImage;
            try {
                bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                photoProfilMenu.setFill(new ImagePattern(image));
                String[] userName = FirstFrame.user.getName().split(" ", 0);
                String userNom = "";
                for (int i = 0; i < userName.length; i++) {
                    String s = userName[i].toUpperCase().substring(0, 1) + "" + userName[i].substring(1, userName[i].length());
                    userNom = userNom + s;
                    if (i != userName.length) {
                        userNom = userNom;
                    }
                }
                String[] userSurname = FirstFrame.user.getSurname().split(" ", 0);
                String userPrenom = "";
                for (int i = 0; i < userSurname.length; i++) {
                    String s = userSurname[i].toUpperCase().substring(0, 1) + "" + userSurname[i].substring(1, userSurname[i].length());
                    userPrenom = userPrenom + s;
                    if (i != userSurname.length) {
                        userPrenom = userPrenom + " ";
                    }
                }
                nameUserMenu.setText(userNom + " " + userPrenom);
                LastLoginMenu.setText(FirstFrame.user.getDate().toLocalDate().getDayOfMonth() + " " + FirstFrame.user.getDate().toLocalDate().getMonth() + " " + FirstFrame.user.getDate().toLocalDate().getYear());

            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }

            profileHB.setVisible(true);
            photoProfilMenu.setVisible(true);
            nameUserMenu.setVisible(true);
            labelLastLoginMenu.setVisible(true);
            LastLoginMenu.setVisible(true);
            logoutMenu.setVisible(true);
            menuHB.setVisible(true);
            addMenusToMapProfile();
            photoProfilMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {

                public void handle(MouseEvent event) {
                    toolsSliderProfile(profileHB, menuHB);
                    // removeOtherMenusProfile(menuHB);
                }

            });

        }

        setComponentsSize();
        addMenusToMap();

        catButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                toolsSlider(catVB, sousMenuCat);
                removeOtherMenus(catVB);
            }
        });

        eventButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                toolsSlider(eventVB, sousMenuEvent);
                removeOtherMenus(eventVB);
            }
        });

        blogButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                toolsSlider(blogVB, sousMenuBlog);
                removeOtherMenus(blogVB);
            }
        });

        dealButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                toolsSlider(dealVB, sousMenuDeal);
                removeOtherMenus(dealVB);
            }
        });

    }

    public void setNode(Node node) {
        mainAP.getChildren().clear();
        mainAP.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    public AnchorPane getMainAP() {
        return mainAP;
    }

    public void setMainAP(AnchorPane mainAP) {
        this.mainAP = mainAP;
    }

    public void setProfileUser() throws IOException {
        profilUser = FXMLLoader.load(getClass().getResource("../Views/ProfilUser.fxml"));
        setNode(profilUser);
    }

    public Button getTest() {
        return test;
    }

    public void setTest(Button test) {
        this.test = test;
    }
    
    
    
    /**
     * Set stage size as per screen resolution
     */
    private void setComponentsSize() {
        menuVB.setPrefHeight(150);
    }

    /**
     * Add Menus to map
     */
    public void addMenusToMap() {
        addMenusToMapImpl();
    }

    private void addMenusToMapImpl() {

        //map.put(accueilVB,null);
        map.put(catVB, sousMenuCat);
        map.put(eventVB, sousMenuEvent);
        map.put(blogVB, sousMenuBlog);
        map.put(dealVB, sousMenuDeal);

        /**
         * Remove the components from VBox on load of stage
         */
        for (Map.Entry<VBox, VBox> entry : map.entrySet()) {
            entry.getKey().getChildren().remove(entry.getValue());
        }
    }

    /**
     * Menu slider
     *
     * @param menu
     * @param subMenu
     */
    public void toolsSlider(VBox menu, VBox subMenu) {
        toolsSliderImpl(menu, subMenu);
    }

    private void toolsSliderImpl(VBox menu, VBox subMenu) {
        if (menu.getChildren().contains(subMenu)) {
            final FadeTransition transition = new FadeTransition(Duration.millis(500), menu);
            transition.setFromValue(0.5);
            transition.setToValue(1);
            transition.setInterpolator(Interpolator.EASE_IN);
            menu.getChildren().remove(subMenu);
            transition.play();
        } else {
            final FadeTransition transition = new FadeTransition(Duration.millis(500), menu);
            transition.setFromValue(0.5);
            transition.setToValue(1);
            transition.setInterpolator(Interpolator.EASE_IN);
            menu.getChildren().add(subMenu);
            transition.play();
        }
    }

    /**
     * Remove other menus
     *
     * @param menu
     */
    public void removeOtherMenus(VBox menu) {
        removeOtherMenusImpl(menu);
    }

    private void removeOtherMenusImpl(VBox menu) {
        for (Map.Entry<VBox, VBox> entry : map.entrySet()) {
            if (!entry.getKey().equals(menu)) {
                entry.getKey().getChildren().remove(entry.getValue());
            }
        }
    }

    /**
     * Add Menus to ProfileMenu*
     */
    public void addMenusToMapProfile() {
        addMenusToMapProfileImpl();
    }

    private void addMenusToMapProfileImpl() {

        mapProfile.put(profileHB, menuHB);

        /**
         * Remove the components from VBox on load of stage
         */
        for (Map.Entry<HBox, HBox> entry : mapProfile.entrySet()) {
            entry.getKey().getChildren().remove(entry.getValue());
        }
    }

    /**
     * end*
     */
    /**
     * Menu slider
     *
     * @param menu
     * @param subMenu
     */
    public void toolsSliderProfile(HBox menu, HBox subMenu) {
        toolsSliderProfileImpl(menu, subMenu);
    }

    private void toolsSliderProfileImpl(HBox menu, HBox subMenu) {
        if (menu.getChildren().contains(subMenu)) {
            final FadeTransition transition = new FadeTransition(Duration.millis(500), menu);
            transition.setFromValue(0.5);
            transition.setToValue(1);
            transition.setInterpolator(Interpolator.EASE_IN);
            menu.getChildren().remove(subMenu);
            transition.play();
        } else {
            final FadeTransition transition = new FadeTransition(Duration.millis(500), menu);
            transition.setFromValue(0.5);
            transition.setToValue(1);
            transition.setInterpolator(Interpolator.EASE_IN);
            menu.getChildren().add(subMenu);
            transition.play();
        }
    }

    /**
     * Remove other menus
     *
     * @param menu
     */
    public void removeOtherMenusProfile(HBox menu) {
        removeOtherMenusProfileImpl(menu);
    }

    private void removeOtherMenusProfileImpl(HBox menu) {
        for (Map.Entry<HBox, HBox> entry : mapProfile.entrySet()) {
            if (!entry.getKey().equals(menu)) {
                entry.getKey().getChildren().remove(entry.getValue());
            }
        }
    }
    @FXML
    private void profilePage(ActionEvent event) throws IOException {
        profilUser = FXMLLoader.load(getClass().getResource("../Views/ProfilUser.fxml"));
        setNode(profilUser);
    }

    @FXML
    private void settingPage(ActionEvent event) throws IOException {
       settingPage= FXMLLoader.load(getClass().getResource("../Views/SettingProfileUser.fxml"));
        setNode(settingPage);
    }

    public void closeHomePage() throws IOException{
        FXMLLoader loader =new FXMLLoader(getClass().getResource("../Views/HomePage.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage(StageStyle.UNDECORATED);               
        stage.setScene(new Scene(root));
        stage.show();
        Stage pr=(Stage) accueil.getScene().getWindow();
        pr.close();
    }


}
