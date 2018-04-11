/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Controllers.ProfilrestoController;
import PIDEV.Entities.Likereview;
import PIDEV.Entities.Review;
import PIDEV.Entities.User;
import PIDEV.Entities.Etablissement;
import PIDEV.Utils.MyConnexion;
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
               + "idetab,date,iduser)"
                    
                    + "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement st = cn.prepareStatement(requete);
            st.setString(1, R.getCommentaire());
            st.setDouble(2, R.getService());
            st.setDouble(3, R.getQualite());
            st.setString(4, R.getTitre());
        st.setInt(5, R.getIdetab().getId());
        st.setDate(6,R.getDate());
        st.setInt(7,R.getIduser().getId());
         st.executeUpdate();
        }
    public ObservableList<Review> ListReviews(Etablissement e) {
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
                UserService us = new UserService();
                int idutilisateur = rs.getInt("iduser");
                
               User utilisateur = us.searchById(idutilisateur);
                rev.setIduser(utilisateur);
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
public int NbrReview(Etablissement e)
{ int s=0;
     try {
            Connection cn = MyConnexion.getInstance().getConnection();
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
public void AddLike (Likereview R) throws SQLException
    {
        String requete
                    = "INSERT INTO likereview ( iduser, idreview) VALUES (?,?)";
        PreparedStatement st = cn.prepareStatement(requete);
            st.setInt(1, R.getIduser().getId());
            st.setInt(2, R.getIdreview().getId());
            
         st.executeUpdate();
        }
public int checkreview(User u,Review R) throws SQLException
{int s=0;
     String requete = "SELECT COUNT(*) as total from likereview where iduser='"+u.getId()+"' and idreview='"+R.getId()+"'";
      Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                s = rs.getInt("total");
            }
            return s;
    
}
 public void DeleteLike(int id1,int id2) throws SQLException{
            

        String req = "delete from likereview where  iduser=? and idreview=?";
        PreparedStatement ste = cn.prepareStatement(req);
        ste.setInt(1, id1);
        ste.setInt(2, id2);
        ste.executeUpdate();
    }
 public int CountReview(Review R) throws SQLException
{int s=0;
     String requete = "SELECT COUNT(*) as total from likereview where idreview='"+R.getId()+"'";
      Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                s = rs.getInt("total");
            }
            return s;
    
}
 public int DejaNote(User U , Etablissement E) throws SQLException
{int s=0;
     String requete = "SELECT COUNT(*) as total from review where idetab='"+E.getId()+"' and iduser='"+U.getId()+"'";
      Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                s = rs.getInt("total");
            }
            return s;
    

    
}
 
 
  public ObservableList<Review> MyReviews(User u) {
        ObservableList<Review> myList = FXCollections.observableArrayList();
       myList.clear();
        try {
            String requete = "SELECT * from review where iduser='"+u.getId()+"'";
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
//               rev.setIdetab(e);
                UserService us = new UserService();
                int idutilisateur = rs.getInt("iduser");
                
               User utilisateur = us.searchById(idutilisateur);
                rev.setIduser(utilisateur);
                myList.add(rev);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
 
 
 
}
