/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Controllers;

import PIDEV.Services.DealDetailsService;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Skan
 */
public class RateMeController implements Initializable {

    @FXML
    private HBox holder;
    @FXML
    private FontAwesomeIconView s1;
    @FXML
    private FontAwesomeIconView s2;
    @FXML
    private FontAwesomeIconView s3;
    @FXML
    private FontAwesomeIconView s4;
    @FXML
    private FontAwesomeIconView s5;

    int i = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        s1.pressedProperty().addListener((observable) -> {
            s1.setGlyphName("STAR");
            s2.setGlyphName("STAR_ALT");
            s3.setGlyphName("STAR_ALT");
            s4.setGlyphName("STAR_ALT");
            s5.setGlyphName("STAR_ALT");
            i = 1;
        });
        s2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                s1.setGlyphName("STAR");
                s2.setGlyphName("STAR");
                s3.setGlyphName("STAR_ALT");
                s4.setGlyphName("STAR_ALT");
                s5.setGlyphName("STAR_ALT");
                i = 2;
            }
        }
        );
        s3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                s1.setGlyphName("STAR");
                s2.setGlyphName("STAR");
                s3.setGlyphName("STAR");
                s4.setGlyphName("STAR_ALT");
                s5.setGlyphName("STAR_ALT");
                i = 3;
            }
        }
        );
        s4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                s1.setGlyphName("STAR");
                s2.setGlyphName("STAR");
                s3.setGlyphName("STAR");
                s4.setGlyphName("STAR");
                s5.setGlyphName("STAR_ALT");
                i = 4;
            }
        }
        );
        s5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                s1.setGlyphName("STAR");
                s2.setGlyphName("STAR");
                s3.setGlyphName("STAR");
                s4.setGlyphName("STAR");
                s5.setGlyphName("STAR");
                i = 5;
            }
        }
        );
    }

    public int geti() {
        return i;
    }
}
