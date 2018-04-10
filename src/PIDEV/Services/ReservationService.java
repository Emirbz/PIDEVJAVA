/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Entities.Etablissement;
import PIDEV.Entities.Reservation;
import PIDEV.Utils.MyConnexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import PIDEV.Services.ListEtablissement;
import java.lang.reflect.Method;
/**
 *
 * @author ons
 */
public class ReservationService {
    Connection cn = MyConnexion.getInstance().getConnection();
    MyConnexion cnx = MyConnexion.getInstance();
    public void addReservation(Reservation reservation) throws IOException {
        try{
            String requete
                    = "INSERT INTO Reservation (id_Etablissement,aunomde, nombre, description, date) VALUES (?,?,?,?,?)";
            PreparedStatement st = cn.prepareStatement(requete);
            st.setInt(1,reservation.getEtablissement().getId());
            st.setString(2, reservation.getAunomde());
            st.setInt(3,reservation.getNombre());
            st.setString(4, reservation.getDescription());
            st.setTimestamp(5, reservation.getDate());
            st.executeUpdate();
            System.out.println("Reservation ajoutée");
  
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
    }
    public ObservableList<Reservation> listReservation(Etablissement etab) {
        ObservableList<Reservation> myList = FXCollections.observableArrayList();
        Connection cn = MyConnexion.getInstance().getConnection();
        try {
            String requete = "SELECT * from reservation where id_Etablissement='"+etab.getId()+"'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reservation res = new Reservation();
                res.setEtablissement(etab);
                res.setId(rs.getInt("id"));
                res.setAunomde(rs.getString("aunomde"));
                res.setNombre(rs.getInt("nombre"));
                res.setDescription(rs.getString("description"));
                res.setDate(rs.getTimestamp("date"));
                
                
                myList.add(res);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    public ObservableList<Reservation> listReservation(){
        ObservableList<Reservation> myList = FXCollections.observableArrayList();
        Connection cn = MyConnexion.getInstance().getConnection();
        try {
            String requete = "SELECT * from reservation";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reservation res = new Reservation();
                ObservableList<Etablissement> etabList = FXCollections.observableArrayList();
                ListEtablissement le =new ListEtablissement();
                etabList = le.ListRestaurant();
                int idetab= rs.getInt("id_Etablissement");
                Etablissement etabli=etabList.filtered(e -> e.getId() == idetab).get(0);
                res.setEtablissement(etabli);
                res.setId(rs.getInt("id"));
                res.setAunomde(rs.getString("aunomde"));
                res.setNombre(rs.getInt("nombre"));
                res.setDescription(rs.getString("description"));
                res.setDate(rs.getTimestamp("date"));
                
                
                myList.add(res);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    
}