/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEVTOUTOU.Services;

import PIDEVTOUTOU.Controllers.ProfilrestoController;
import PIDEVTOUTOU.Entities.Review;
import PIDEVTOUTOU.Entities.etablissement;
import PIDEVTOUTOU.Utils.Myconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Emir
 */
public class GestionReviews {
    Connection cn = Myconnexion.getInstance().getConnection();
    Myconnexion cnx = Myconnexion.getInstance();
    public void AddReview (Review R) throws SQLException
    {
        String requete
                    = "INSERT INTO review ("
                    + "commentaire,"
                    + "service,"
                    + "qualite,"
                    + "titre,"
               + "idetab,date)"
                    
                    + "VALUES (?,?,?,?,?,?)";
        PreparedStatement st = cn.prepareStatement(requete);
            st.setString(1, R.getCommentaire());
            st.setDouble(2, R.getService());
            st.setDouble(3, R.getQualite());
            st.setString(4, R.getTitre());
        st.setInt(5, R.getIdetab().getId());
        st.setDate(6,R.getDate());
         st.executeUpdate();
        }
    public ObservableList<Review> ListReviews(etablissement e) {
        ObservableList<Review> myList = FXCollections.observableArrayList();
       myList.clear();
        try {
            String requete = "SELECT * from review where idetab='"+e.getId()+"'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Review rev = new Review();
                rev.setId(rs.getInt("id"));
                rev.setCommentaire(rs.getString("commentaire"));
                rev.setService(rs.getDouble("service"));
                rev.setQualite(rs.getDouble("qualite"));
                rev.setTitre(rs.getString("titre"));
                rev.setDate(rs.getDate("date"));
               rev.setIdetab(e);
                myList.add(rev);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    public void DeleteReveiw(int id) throws SQLException{
            

        String req = "DELETE FROM review WHERE id=?";
        PreparedStatement ste = cn.prepareStatement(req);
        ste.setInt(1, id);
        ste.executeUpdate();
    }
public int NbrReview(etablissement e)
{ int s=0;
     try {
            Connection cn = Myconnexion.getInstance().getConnection();
            String requete = "SELECT COUNT(*) AS total from review where idetab='" + e.getId() + "'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                s = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfilrestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
return s;
}
    
}
