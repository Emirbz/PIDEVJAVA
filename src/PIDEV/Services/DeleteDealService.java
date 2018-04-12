/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.util.Optional;
//import javafx.scene.control.Alert;
//import javafx.scene.control.ButtonType;

/**
 *
 * @author Skan
 */
public class DeleteDealService {

    Connection cn = MyConnexion.getInstance().getConnection();

    public void DeleteDeal(int id) throws SQLException {
        String req = "DELETE FROM Deal WHERE id=?";
        PreparedStatement ste = cn.prepareStatement(req);
        ste.setInt(1, id);
//        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
//        alert1.setTitle("Confirmation Dialog");
//        alert1.setContentText("Etes vous sur de supprimer ce deal ");
//        alert1.setHeaderText(null);
//        Optional<ButtonType> action = alert1.showAndWait();
//        if (action.get() == ButtonType.OK) {
//            ste.executeUpdate();
//        }
    }
}
