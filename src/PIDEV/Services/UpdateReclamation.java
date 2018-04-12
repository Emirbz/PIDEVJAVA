/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Entities.Deal;
import PIDEV.Utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Skan
 */
public class UpdateReclamation {

    Connection cn = MyConnexion.getInstance().getConnection();

    public void UpdateReclamation(Deal deal, int item) throws SQLException {
        String req = "UPDATE reclamation SET contenu=?,typeobj=? where id=?";
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setString(1, deal.getNom());
        pre.setString(2, deal.getDescription());
        pre.setInt(3, item);
        pre.executeUpdate();
    }
}
