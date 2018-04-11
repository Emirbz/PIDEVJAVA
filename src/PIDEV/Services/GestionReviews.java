/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Entities.Review;
import PIDEV.Entities.Etablissement;
import PIDEV.Utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Emir
 */
public class GestionReviews {
    Connection cn = MyConnexion.getInstance().getConnection();
    MyConnexion cnx = MyConnexion.getInstance();
    public void AddReview (Review R) throws SQLException
    {
        String requete
                    = "INSERT INTO review ("
                    + "commentaire,"
                    + "service,"
                    + "qualite,"
                    + "titre,"
               + "idetab)"
                    
                    + "VALUES (?,?,?,?,?)";
        PreparedStatement st = cn.prepareStatement(requete);
            st.setString(1, R.getCommentaire());
            st.setDouble(2, R.getService());
            st.setDouble(3, R.getQualite());
            st.setString(4, R.getTitre());
        st.setInt(5, R.getIdetab().getId());
         st.executeUpdate();
        
    }
    
}
