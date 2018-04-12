/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import PIDEV.Utils.MyConnexion;

/**
 *
 * @author dahem
 */
public class Deletetemoignages {
     public void delete(int id) throws SQLException {
        Connection cn = MyConnexion.getInstance().getConnection();
        String req = "DELETE FROM events WHERE id=?";
        PreparedStatement ste = cn.prepareStatement(req);
        ste.setInt(1, id);
        ste.executeUpdate();
    }
    
}
