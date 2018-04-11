/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;


import PIDEV.Entities.Categorie;
import PIDEV.Utils.MyConnexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ons
 */
public class CategorieService {
    Connection cn = MyConnexion.getInstance().getConnection();
    MyConnexion cnx = MyConnexion.getInstance();
    public void addCategorie(Categorie categorie) throws IOException {
        try{
            String requete
                    = "INSERT INTO Categorie (nom, description) VALUES (?,?)";
            PreparedStatement st = cn.prepareStatement(requete);
            st.setString(1, categorie.getNom());
            st.setString(2, categorie.getDescription());
           
            st.executeUpdate();
            System.out.println("Catégorie ajoutée");
  
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
    }
    public void deleteCategorie(int id) throws SQLException{
            
        String req = "DELETE FROM etablissement WHERE id=?";
        PreparedStatement ste = cn.prepareStatement(req);
        ste.setInt(1, id);
        ste.executeUpdate();
    }
    
    public ObservableList<Categorie> listCategorie() {
        ObservableList<Categorie> myList = FXCollections.observableArrayList();
        
        try {
            String requete = "SELECT * from categorie";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Categorie cat = new Categorie();
                cat.setNom(rs.getString("nom"));
                
                myList.add(cat);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
}
