/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Entities.Reclamation;
import PIDEV.Utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Skan
 */
public class AddReclamation {

    Connection cn = MyConnexion.getInstance().getConnection();

    public void AddReclamation(Reclamation R) throws SQLException {
        String requete
                = "INSERT INTO reclamation (contenu,typeobj,idobj,iduser,etat) VALUES (?,?,?,?,?)";
        PreparedStatement st = cn.prepareStatement(requete);
        st.setString(1, R.getContenu());
        st.setString(2, R.getTypeobj());
        st.setInt(3, R.getIdobj());
        st.setInt(4, 3);
        st.setString(5, R.getEtat());
        st.executeUpdate();
    }
}
