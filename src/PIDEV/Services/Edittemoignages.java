/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import PIDEV.Utils.MyConnexion;
import PIDEV.Entities.temoignages;

/**
 *
 * @author dahem
 */
public class Edittemoignages {

    public void modifiertemoignages(temoignages tem, int item) throws SQLException, IOException {
        Connection cn = MyConnexion.getInstance().getConnection();
        String req = "UPDATE `temoignages` SET `titre` = ?, `description` = ?  where `id`=?";

        PreparedStatement st = cn.prepareStatement(req);
        st.setString(1, tem.getTitre());
        st.setString(2, tem.getDescription());
        st.setInt(3, item);
        st.executeUpdate();
        System.out.println("témoignages modifié");

    }

}
