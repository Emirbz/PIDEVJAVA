/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Entities.Deal;
import PIDEV.Services.ListDealsService;
import PIDEV.Views.FirstFrame;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author Skan
 */
public class DivDealController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private ScrollPane sc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListDealsService LDS = new ListDealsService();
        ObservableList<Deal> mylist = LDS.ListerDeals().filtered(e -> e.getIduser().getId() == FirstFrame.user.getId());
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();
        for (Deal d : mylist) {
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
        c.setPrefColumns(4);
        c.setPadding(new javafx.geometry.Insets(50));
        c.setHgap(40);
        c.setVgap(40);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        sc.setContent(b);

    }
}
