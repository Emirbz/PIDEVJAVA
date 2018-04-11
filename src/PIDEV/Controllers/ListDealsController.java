/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Deal;
import PIDEV.Entities.Reclamation;
import PIDEV.Services.AddReclamation;
import PIDEV.Services.ListDealsService;
import PIDEV.Services.TestUserReclamation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.sun.glass.ui.Robot;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Skan
 */
public class ListDealsController implements Initializable {

    @FXML
    private JFXTextField recherchedeal;
    @FXML
    private ScrollPane pane;
    @FXML
    private AnchorPane ap;
    @FXML
    private JFXComboBox<String> tri;
    private ObservableList<Deal> list;
    @FXML
    private StackPane reclamation;
    @FXML
    private JFXButton reclamerbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reclamation.setVisible(false);
        ListDealsService LDS = new ListDealsService();
        list = LDS.ListerDeals();
        List<String> names = LDS.getDealsNames();
        TextFields.bindAutoCompletion(recherchedeal, names);
        tri.getItems().addAll("Tous les Deals", "Les nouveaux Deals", "Les Deals passés", "Les meilleurs notés", "Les moins notés");
        tri.getSelectionModel().selectFirst();
        KeyEvent k = null;
        tri.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                switch (newValue) {
                    case "Tous les Deals":
                        list = LDS.ListerDeals();
                        Search(k);
                        break;
                    case "Les nouveaux Deals":
                        list = LDS.ListerDeals().sorted((d1, d2) -> d2.getDatecreation().compareTo(d1.getDatecreation()));
                        Search(k);
                        break;
                    case "Les Deals passés":
                        Date now = new Date();
                        ObservableList<Deal> filter = FXCollections.observableArrayList();
                        list = LDS.ListerDeals();
                        for (Deal d : list) {
                            if (d.getDatefin().before(now)) {
                                filter.add(d);
                            }
                        }
                        list = filter;
                        Search(k);
                        break;
                    case "Les moins notés":
                        list = LDS.ListerDeals().sorted((d1, d2) -> d1.getRating().compareTo(d2.getRating()));
                        Search(k);
                        break;
                    case "Les meilleurs notés":
                        list = LDS.ListerDeals().sorted((d1, d2) -> d2.getRating().compareTo(d1.getRating()));
                        Search(k);
                        break;
                }
            }
        });
        Search(k);

    }

    @FXML
    private void Search(KeyEvent event) {
        FilteredList<Deal> filteredData = new FilteredList<>(list, e -> true);
        recherchedeal.setOnKeyReleased(e
                -> {
            recherchedeal.textProperty().addListener((ObservableValue<? extends String> ObservableValue, String oldValue, String newValue) -> {
                filteredData.setPredicate((Predicate<? super Deal>) new Predicate<Deal>() {
                    @Override
                    public boolean test(Deal d) {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        return d.getNom().toLowerCase().contains(lowerCaseFilter.subSequence(0, lowerCaseFilter.length()));
                    }
                });
            });
            pane.setContent(show(filteredData));
        });
    }

    public void rec() {
        try {
            JFXDialogLayout dialogLayout = new JFXDialogLayout();
            Text title = new Text("Nouvelle Réclamation");
            dialogLayout.setHeading(title);
            FXMLLoader loade = new FXMLLoader(getClass().getResource("../Views/AddClaim.fxml"));
            Parent pe = (StackPane) loade.load();
            dialogLayout.setBody(pe);
            JFXDialog dialog = new JFXDialog(reclamation, dialogLayout, JFXDialog.DialogTransition.CENTER);
            JFXButton addBtn = new JFXButton("Réclamer");
            addBtn.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(77,102,204);-fx-font-size: 14px;-fx-text-fill: WHITE;");
            JFXButton closeButton = new JFXButton("Annuler");
            closeButton.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(77,102,204);-fx-font-size: 14px;-fx-text-fill: WHITE;");
            closeButton.setOnAction((ActionEvent event1) -> {
                dialog.close();
            });
            dialogLayout.autosize();
            Separator sep = new Separator(Orientation.HORIZONTAL);
            sep.setVisible(false);
            sep.setPadding(new Insets(10));
            HBox box = new HBox();
            box.setSpacing(20);
            box.setAlignment(Pos.CENTER);
            box.getChildren().addAll(addBtn, closeButton);
            VBox s = (VBox) dialogLayout.getChildren().get(0);
            s.getChildren().addAll(box, sep);
            dialog.show();
            dialog.autosize();
            reclamation.setVisible(true);
            reclamation.toFront();
            addBtn.setOnAction((ActionEvent event1) -> {
                AddClaimController ACC = loade.getController();
                Reclamation r = new Reclamation();
                String type = "";
                if (ACC.getTautre() != null) {
                    r.setTypeobj(ACC.getTautre());
                    r.setIdobj(0);
                }
                if (ACC.getTarticle() != null) {
                    type = ACC.getTarticle().getClass().getName().substring(9);
                    r.setTypeobj(type);
                    r.setIdobj(ACC.getTarticle().getId());
                }
                if (ACC.getTdeal() != null) {
                    type = ACC.getTdeal().getClass().getName().substring(9);
                    r.setTypeobj(type);
                    r.setIdobj(ACC.getTdeal().getId());
                }
                if (ACC.getTetab() != null) {
                    type = ACC.getTetab().getClass().getName().substring(9);
                    r.setTypeobj(type);
                    r.setIdobj(ACC.getTetab().getId());
                }
                if (ACC.getTevent() != null) {
                    type = ACC.getTevent().getClass().getName().substring(9);
                    r.setTypeobj(type);
                    r.setIdobj(ACC.getTevent().getId());
                }
                r.setContenu(ACC.getContenu());
                r.setEtat("En cours de traitement");
                r.setIduser(PIDEV.Views.FirstFrame.user);
                AddReclamation AR = new AddReclamation();
                try {
                    TestUserReclamation TUR = new TestUserReclamation();
                    if (TUR.test(r.getIdobj(), 3)) {
                        AR.AddReclamation(r);
                        Label success = new Label("Votre réclamation sera traité par l'admin");
                        success.setPadding(new Insets(30, 0, 0, 45));
                        dialogLayout.setBody(success);
                        box.setVisible(false);
                    } else {
                        Label fail = new Label("Vous avez déja réclamé cet objet");
                        dialogLayout.setBody(fail);
                        fail.setPadding(new Insets(30, 0, 0, 70));
                        box.setVisible(false);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ListDealsController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
            ap.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    dialog.close();
                    reclamation.toBack();
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(ListDealsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void key(MouseEvent event) {
        Robot robot = com.sun.glass.ui.Application.GetApplication().createRobot();
        recherchedeal.requestFocus();
        robot.keyPress(java.awt.event.KeyEvent.VK_UP);
        robot.keyRelease(java.awt.event.KeyEvent.VK_UP);
    }

    public Node show(ObservableList<Deal> li) {
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();
        for (Deal d : li) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/DivHolder.fxml"));
                Parent root = (Pane) loader.load();
                DivHolderController DHC = loader.getController();
                DHC.LoadValues(d);
                c.setHgap(15);
                //   c.setVgap(40);
                c.getChildren().removeAll();
                PopOver pop = new PopOver();
                Label desc = new Label("Description : \n" + d.getDescription());
                desc.setPadding(new Insets(10));
                desc.setPrefWidth(200);
                desc.setWrapText(true);
                pop.setContentNode(desc);
                root.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        pop.show(root);
                    }
                });
                root.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        pop.hide();
                    }
                });
                c.setPadding(new javafx.geometry.Insets(10, 50, 0, 50));
                c.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ListDealsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        reclamerbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rec();
            }
        });
        c.setPrefColumns(4);
        c.setPadding(new javafx.geometry.Insets(50));
        c.setHgap(40);
        c.setVgap(40);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        return b;
    }
}
