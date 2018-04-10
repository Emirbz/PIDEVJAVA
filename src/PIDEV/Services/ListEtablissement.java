/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import PIDEV.Entities.Categorie;
import PIDEV.Entities.SousCategorie;
import PIDEV.Entities.User;
import PIDEV.Entities.Etablissement;
import PIDEV.Utils.MyConnexion;

/**
 *
 * @author Emir
 */
public class ListEtablissement {
     public ObservableList<Etablissement> ListEtab() {
        ObservableList<Etablissement> myList = FXCollections.observableArrayList();
        Connection cn = MyConnexion.getInstance().getConnection();
        try {
            String requete = "SELECT * from etablissement" ;
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Etablissement etab = new Etablissement();
                etab.setName(rs.getString("name"));
               
                etab.setLatitude(rs.getDouble("latitude"));
                etab.setLongitude(rs.getDouble("longitude"));

                etab.setCategorie(rs.getString("categorie"));
               
                myList.add(etab);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public ObservableList<Etablissement> ListRestaurant() {
        ObservableList<Etablissement> myList = FXCollections.observableArrayList();
        Connection cn = MyConnexion.getInstance().getConnection();
        try {
            String requete = "SELECT * from etablissement where categorie='Restaurant'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Etablissement etab = new Etablissement();
                etab.setName(rs.getString("name"));
                etab.setAddress(rs.getString("address"));
                etab.setEmail(rs.getString("email"));
                etab.setPhone(rs.getString("phone"));
                etab.setLundisamedio(rs.getString("lundisamedio"));
                etab.setLundisamedif(rs.getString("lundisamedif"));
                etab.setDimancheo(rs.getString("dimancheo"));
                etab.setDimanchef(rs.getString("dimanchef"));
                etab.setId(rs.getInt("id"));
                etab.setAlcool(rs.getBoolean("alcool"));
                etab.setDescription(rs.getString("description"));
                etab.setWebsite(rs.getString("website"));
                etab.setFacebook(rs.getString("facebook"));
                etab.setImg1(rs.getString("img1"));
                etab.setImg2(rs.getString("img2"));
                etab.setImg3(rs.getString("img3"));
                etab.setDevis_name(rs.getString("devis_name"));
                etab.setTotalqualite(rs.getDouble("totalqualite"));
                etab.setTotalservice(rs.getDouble("totalservice"));
                etab.setMoyqualite(rs.getDouble("moyqualite"));
                etab.setMoyservice(rs.getDouble("moyservice"));
                etab.setLatitude(rs.getDouble("latitude"));
                etab.setLongitude(rs.getDouble("longitude"));

                etab.setCategorie(rs.getString("categorie"));
                etab.setParking(rs.getBoolean("parking"));

                UserService us = new UserService();
                int idutilisateur = rs.getInt("iduser");

                User utilisateur = us.searchById(idutilisateur);
                etab.setIduser(utilisateur);

                etab.setCartecredit(rs.getBoolean("cartecredit"));
                etab.setChaiseroulante(rs.getBoolean("chaiseroulante"));
                etab.setFumer(rs.getBoolean("fumer"));
                etab.setTerasse(rs.getBoolean("terasse"));
                etab.setWifi(rs.getBoolean("wifi"));
                etab.setReservations(rs.getBoolean("reservations"));
                etab.setPlace(rs.getInt("place"));
                etab.setLivraison(rs.getBoolean("livraison"));
                etab.setClimatisation(rs.getBoolean("climatisation"));
                etab.setAnimaux(rs.getBoolean("animaux"));
                Categorie c = new Categorie(4, "Restaurant", "Restaurant");
                String requete2 = "SELECT * from sous__categorie where id='" + rs.getInt("souscat") + "'";
                PreparedStatement st2 = cn.prepareStatement(requete2);

                ResultSet rs2 = st2.executeQuery(requete2);

                SousCategorie sc = new SousCategorie();
                while (rs2.next()) {
                    sc.setId(rs2.getInt(1));

                    sc.setIdcategorie(c);
                    sc.setNom(rs2.getString(3)); // oubien 3 

                }

                etab.setSouscat(sc);
                myList.add(etab);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public ObservableList<Etablissement> ListRestaurantUser() {
        ObservableList<Etablissement> myList = FXCollections.observableArrayList();
        Connection cn = MyConnexion.getInstance().getConnection();
        try {
            String requete = "SELECT * from etablissement where  iduser='" + PIDEV.Views.FirstFrame.user.getId() + "'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Etablissement etab = new Etablissement();
                etab.setName(rs.getString("name"));
                etab.setAddress(rs.getString("address"));
                etab.setEmail(rs.getString("email"));
                etab.setPhone(rs.getString("phone"));
                etab.setLundisamedio(rs.getString("lundisamedio"));
                etab.setLundisamedif(rs.getString("lundisamedif"));
                etab.setDimancheo(rs.getString("dimancheo"));
                etab.setDimanchef(rs.getString("dimanchef"));
                etab.setId(rs.getInt("id"));
                etab.setAlcool(rs.getBoolean("alcool"));
                etab.setDescription(rs.getString("description"));
                etab.setWebsite(rs.getString("website"));
                etab.setFacebook(rs.getString("facebook"));
                etab.setImg1(rs.getString("img1"));
                etab.setImg2(rs.getString("img2"));
                etab.setImg3(rs.getString("img3"));
                etab.setDevis_name(rs.getString("devis_name"));
                etab.setTotalqualite(rs.getDouble("totalqualite"));
                etab.setTotalservice(rs.getDouble("totalservice"));
                etab.setMoyqualite(rs.getDouble("moyqualite"));
                etab.setMoyservice(rs.getDouble("moyservice"));
                etab.setLatitude(rs.getDouble("latitude"));
                etab.setLongitude(rs.getDouble("longitude"));

                etab.setCategorie(rs.getString("categorie"));
                etab.setParking(rs.getBoolean("parking"));

                UserService us = new UserService();
                int idutilisateur = rs.getInt("iduser");

                User utilisateur = us.searchById(idutilisateur);
                etab.setIduser(utilisateur);

                etab.setCartecredit(rs.getBoolean("cartecredit"));
                etab.setChaiseroulante(rs.getBoolean("chaiseroulante"));
                etab.setFumer(rs.getBoolean("fumer"));
                etab.setTerasse(rs.getBoolean("terasse"));
                etab.setWifi(rs.getBoolean("wifi"));
                etab.setReservations(rs.getBoolean("reservations"));
                etab.setPlace(rs.getInt("place"));
                etab.setLivraison(rs.getBoolean("livraison"));
                etab.setClimatisation(rs.getBoolean("climatisation"));
                etab.setAnimaux(rs.getBoolean("animaux"));
                Categorie c = new Categorie(4, "Restaurant", "Restaurant");
                String requete2 = "SELECT * from sous__categorie where id='" + rs.getInt("souscat") + "'";
                PreparedStatement st2 = cn.prepareStatement(requete2);

                ResultSet rs2 = st2.executeQuery(requete2);

                SousCategorie sc = new SousCategorie();
                while (rs2.next()) {
                    sc.setId(rs2.getInt(1));

                    sc.setIdcategorie(c);
                    sc.setNom(rs2.getString(3)); // oubien 3 

                }

                etab.setSouscat(sc);
                myList.add(etab);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public ObservableList<Etablissement> ListRestauranttrie(String scvalue, String trisq) throws SQLException {
        ObservableList<Etablissement> myList = FXCollections.observableArrayList();
        Connection cn = MyConnexion.getInstance().getConnection();
        if (trisq == "Meilleur Qualité") {
            trisq = "moyqualite";
        }
        if (trisq == "Meilleur Service") {
            trisq = "moyservice";
        }

        String requete2 = "select id from sous__categorie where nom='" + scvalue + "'";

        Statement st2 = cn.createStatement();
        ResultSet rs2 = st2.executeQuery(requete2);
        int i = 0;
        while (rs2.next()) {
            i = rs2.getInt(1);

        }
        try {
            String requete = "SELECT * from etablissement where categorie='Restaurant' and souscat='" + i + "' ORDER BY " + trisq + " DESC ";
            System.out.println(requete);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Etablissement etab = new Etablissement();
                etab.setName(rs.getString("name"));
                etab.setAddress(rs.getString("address"));
                etab.setEmail(rs.getString("email"));
                etab.setPhone(rs.getString("phone"));
                etab.setLundisamedio(rs.getString("lundisamedio"));
                etab.setLundisamedif(rs.getString("lundisamedif"));
                etab.setDimancheo(rs.getString("dimancheo"));
                etab.setDimanchef(rs.getString("dimanchef"));
                etab.setId(rs.getInt("id"));
                etab.setAlcool(rs.getBoolean("alcool"));
                etab.setDescription(rs.getString("description"));
                etab.setWebsite(rs.getString("website"));
                etab.setFacebook(rs.getString("facebook"));
                etab.setImg1(rs.getString("img1"));
                etab.setImg2(rs.getString("img2"));
                etab.setImg3(rs.getString("img3"));
                etab.setDevis_name(rs.getString("devis_name"));
                etab.setTotalqualite(rs.getDouble("totalqualite"));
                etab.setTotalservice(rs.getDouble("totalservice"));
                etab.setMoyqualite(rs.getDouble("moyqualite"));
                etab.setMoyservice(rs.getDouble("moyservice"));
                etab.setLatitude(rs.getDouble("latitude"));
                etab.setLongitude(rs.getDouble("longitude"));

                etab.setCategorie(rs.getString("categorie"));
                etab.setParking(rs.getBoolean("parking"));

                etab.setCartecredit(rs.getBoolean("cartecredit"));
                etab.setChaiseroulante(rs.getBoolean("chaiseroulante"));
                etab.setFumer(rs.getBoolean("fumer"));
                etab.setTerasse(rs.getBoolean("terasse"));
                etab.setWifi(rs.getBoolean("wifi"));
                etab.setReservations(rs.getBoolean("reservations"));
                etab.setPlace(rs.getInt("place"));
                etab.setLivraison(rs.getBoolean("livraison"));
                etab.setClimatisation(rs.getBoolean("climatisation"));
                etab.setAnimaux(rs.getBoolean("animaux"));
                Categorie c = new Categorie(4, "Restaurant", "Restaurant");
                String requete3 = "SELECT * from sous__categorie where id='" + rs.getInt("souscat") + "'";
                PreparedStatement st3 = cn.prepareStatement(requete3);

                ResultSet rs3 = st3.executeQuery(requete3);

                SousCategorie sc = new SousCategorie();
                while (rs3.next()) {
                    sc.setId(rs3.getInt(1));

                    sc.setIdcategorie(c);
                    sc.setNom(rs3.getString(3)); // oubien 3 

                }

                etab.setSouscat(sc);
                myList.add(etab);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public ObservableList<Etablissement> Triservice(String trisq) {
        ObservableList<Etablissement> myList = FXCollections.observableArrayList();
        Connection cn = MyConnexion.getInstance().getConnection();
        if (trisq == "Meilleur Qualité") {
            trisq = "moyqualite";
        }
        if (trisq == "Meilleur Service") {
            trisq = "moyservice";
        }

        try {
            String requete = "Select * from etablissement where categorie='Restaurant' ORDER BY " + trisq + " DESC";
            System.out.println(requete);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Etablissement etab = new Etablissement();
                etab.setName(rs.getString("name"));
                etab.setAddress(rs.getString("address"));
                etab.setEmail(rs.getString("email"));
                etab.setPhone(rs.getString("phone"));
                etab.setLundisamedio(rs.getString("lundisamedio"));
                etab.setLundisamedif(rs.getString("lundisamedif"));
                etab.setDimancheo(rs.getString("dimancheo"));
                etab.setDimanchef(rs.getString("dimanchef"));
                etab.setId(rs.getInt("id"));
                etab.setAlcool(rs.getBoolean("alcool"));
                etab.setDescription(rs.getString("description"));
                etab.setWebsite(rs.getString("website"));
                etab.setFacebook(rs.getString("facebook"));
                etab.setImg1(rs.getString("img1"));
                etab.setImg2(rs.getString("img2"));
                etab.setImg3(rs.getString("img3"));
                etab.setDevis_name(rs.getString("devis_name"));
                etab.setTotalqualite(rs.getDouble("totalqualite"));
                etab.setTotalservice(rs.getDouble("totalservice"));
                etab.setMoyqualite(rs.getDouble("moyqualite"));
                etab.setMoyservice(rs.getDouble("moyservice"));
                etab.setLatitude(rs.getDouble("latitude"));
                etab.setLongitude(rs.getDouble("longitude"));

                etab.setCategorie(rs.getString("categorie"));
                etab.setParking(rs.getBoolean("parking"));

                etab.setCartecredit(rs.getBoolean("cartecredit"));
                etab.setChaiseroulante(rs.getBoolean("chaiseroulante"));
                etab.setFumer(rs.getBoolean("fumer"));
                etab.setTerasse(rs.getBoolean("terasse"));
                etab.setWifi(rs.getBoolean("wifi"));
                etab.setReservations(rs.getBoolean("reservations"));
                etab.setPlace(rs.getInt("place"));
                etab.setLivraison(rs.getBoolean("livraison"));
                etab.setClimatisation(rs.getBoolean("climatisation"));
                etab.setAnimaux(rs.getBoolean("animaux"));
                Categorie c = new Categorie(4, "Restaurant", "Restaurant");
                String requete2 = "SELECT * from sous__categorie where id='" + rs.getInt("souscat") + "'";
                PreparedStatement st2 = cn.prepareStatement(requete2);

                ResultSet rs2 = st2.executeQuery(requete2);

                SousCategorie sc = new SousCategorie();
                while (rs2.next()) {
                    sc.setId(rs2.getInt(1));

                    sc.setIdcategorie(c);
                    sc.setNom(rs2.getString(3)); // oubien 3 

                }

                etab.setSouscat(sc);
                myList.add(etab);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public Etablissement searchRestaurant(int id) {
        Connection cn = MyConnexion.getInstance().getConnection();
        try {
            String requete = "SELECT * from etablissement where id='" + id + "'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Etablissement etab = new Etablissement();
                etab.setName(rs.getString("name"));
                etab.setAddress(rs.getString("address"));
                etab.setEmail(rs.getString("email"));
                etab.setPhone(rs.getString("phone"));
                etab.setLundisamedio(rs.getString("lundisamedio"));
                etab.setLundisamedif(rs.getString("lundisamedif"));
                etab.setDimancheo(rs.getString("dimancheo"));
                etab.setDimanchef(rs.getString("dimanchef"));
                etab.setId(rs.getInt("id"));
                etab.setAlcool(rs.getBoolean("alcool"));
                etab.setDescription(rs.getString("description"));
                etab.setWebsite(rs.getString("website"));
                etab.setFacebook(rs.getString("facebook"));
                etab.setImg1(rs.getString("img1"));
                etab.setImg2(rs.getString("img2"));
                etab.setImg3(rs.getString("img3"));
                etab.setDevis_name(rs.getString("devis_name"));

                etab.setCategorie(rs.getString("categorie"));
                etab.setParking(rs.getBoolean("parking"));

                etab.setCartecredit(rs.getBoolean("cartecredit"));
                etab.setChaiseroulante(rs.getBoolean("chaiseroulante"));
                etab.setFumer(rs.getBoolean("fumer"));
                etab.setTerasse(rs.getBoolean("terasse"));
                etab.setWifi(rs.getBoolean("wifi"));
                etab.setReservations(rs.getBoolean("reservations"));
                etab.setPlace(rs.getInt("place"));
                etab.setLivraison(rs.getBoolean("livraison"));
                etab.setClimatisation(rs.getBoolean("climatisation"));
                etab.setAnimaux(rs.getBoolean("animaux"));
                Categorie c = new Categorie(4, "Restaurant", "Restaurant");
                String requete2 = "SELECT * from sous__categorie where id='" + rs.getInt("souscat") + "'";
                PreparedStatement st2 = cn.prepareStatement(requete2);

                ResultSet rs2 = st2.executeQuery(requete2);

                SousCategorie sc = new SousCategorie();
                while (rs2.next()) {
                    sc.setId(rs2.getInt(1));

                    sc.setIdcategorie(c);
                    sc.setNom(rs2.getString(3)); // oubien 3 

                }

                etab.setSouscat(sc);
                return etab;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public ObservableList<Etablissement> ListHotel() {
        ObservableList<Etablissement> myList = FXCollections.observableArrayList();
        Connection cn = MyConnexion.getInstance().getConnection();
        try {
            String requete = "SELECT * from etablissement where categorie='Hotel'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Etablissement etab = new Etablissement();
                etab.setName(rs.getString("name"));
                etab.setAddress(rs.getString("address"));
                etab.setEmail(rs.getString("email"));
                etab.setPhone(rs.getString("phone"));
                etab.setCheckin(rs.getString("checkin"));
                etab.setCheckout(rs.getString("checkout"));
                etab.setEtoile(rs.getInt("etoile"));
                etab.setNbrchambre(rs.getInt("nbrchambre"));
                etab.setLpd(rs.getBoolean("lpd"));
                etab.setDp(rs.getBoolean("dp"));
                etab.setPc(rs.getBoolean("pc"));
                etab.setAllinclusive(rs.getBoolean("allinclusive"));
                etab.setPrixmoy(rs.getInt("prixmoy"));
                etab.setId(rs.getInt("id"));
                etab.setAlcool(rs.getBoolean("alcool"));
                etab.setDescription(rs.getString("description"));
                etab.setWebsite(rs.getString("website"));
                etab.setFacebook(rs.getString("facebook"));
                etab.setImg1(rs.getString("img1"));
                etab.setImg2(rs.getString("img2"));
                etab.setImg3(rs.getString("img3"));
                etab.setDevis_name(rs.getString("devis_name"));
                etab.setTotalqualite(rs.getDouble("totalqualite"));
                etab.setTotalservice(rs.getDouble("totalservice"));
                etab.setMoyqualite(rs.getDouble("moyqualite"));
                etab.setMoyservice(rs.getDouble("moyservice"));
                etab.setLatitude(rs.getDouble("latitude"));
                etab.setLongitude(rs.getDouble("longitude"));

                etab.setCategorie(rs.getString("categorie"));
                etab.setParking(rs.getBoolean("parking"));

                UserService us = new UserService();
                int idutilisateur = rs.getInt("iduser");

                User utilisateur = us.searchById(idutilisateur);
                etab.setIduser(utilisateur);

                etab.setCartecredit(rs.getBoolean("cartecredit"));
                etab.setChaiseroulante(rs.getBoolean("chaiseroulante"));
                etab.setFumer(rs.getBoolean("fumer"));
                etab.setTerasse(rs.getBoolean("terasse"));
                etab.setWifi(rs.getBoolean("wifi"));
                etab.setReservations(rs.getBoolean("reservations"));

                etab.setLivraison(rs.getBoolean("livraison"));
                etab.setClimatisation(rs.getBoolean("climatisation"));
                etab.setAnimaux(rs.getBoolean("animaux"));
                Categorie c = new Categorie(2, "Hotel", "Hotel");
                String requete2 = "SELECT * from sous__categorie where id='" + rs.getInt("souscat") + "'";
                PreparedStatement st2 = cn.prepareStatement(requete2);

                ResultSet rs2 = st2.executeQuery(requete2);

                SousCategorie sc = new SousCategorie();
                while (rs2.next()) {
                    sc.setId(rs2.getInt(1));

                    sc.setIdcategorie(c);
                    sc.setNom(rs2.getString(3)); // oubien 3 

                }

                etab.setSouscat(sc);
                myList.add(etab);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public ObservableList<Etablissement> ListHotelTrie(String scvalue, String trisq) throws SQLException {
        ObservableList<Etablissement> myList = FXCollections.observableArrayList();
        Connection cn = MyConnexion.getInstance().getConnection();
        if (trisq == "Meilleur Qualité") {
            trisq = "moyqualite";
        }
        if (trisq == "Meilleur Service") {
            trisq = "moyservice";
        }

        String requete2 = "select id from sous__categorie where nom='" + scvalue + "'";

        Statement st2 = cn.createStatement();
        ResultSet rs2 = st2.executeQuery(requete2);
        int i = 0;
        while (rs2.next()) {
            i = rs2.getInt(1);

        }
        try {
            String requete = "SELECT * from etablissement where categorie='Hotel' and souscat='" + i + "' ORDER BY " + trisq + " DESC ";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Etablissement etab = new Etablissement();
                etab.setName(rs.getString("name"));
                etab.setAddress(rs.getString("address"));
                etab.setEmail(rs.getString("email"));
                etab.setPhone(rs.getString("phone"));
                etab.setCheckin(rs.getString("checkin"));
                etab.setCheckout(rs.getString("checkout"));
                etab.setEtoile(rs.getInt("etoile"));
                etab.setNbrchambre(rs.getInt("nbrchambre"));
                etab.setLpd(rs.getBoolean("lpd"));
                etab.setDp(rs.getBoolean("dp"));
                etab.setPc(rs.getBoolean("pc"));
                etab.setAllinclusive(rs.getBoolean("allinclusive"));
                etab.setPrixmoy(rs.getInt("prixmoy"));
                etab.setId(rs.getInt("id"));
                etab.setAlcool(rs.getBoolean("alcool"));
                etab.setDescription(rs.getString("description"));
                etab.setWebsite(rs.getString("website"));
                etab.setFacebook(rs.getString("facebook"));
                etab.setImg1(rs.getString("img1"));
                etab.setImg2(rs.getString("img2"));
                etab.setImg3(rs.getString("img3"));
                etab.setDevis_name(rs.getString("devis_name"));
                etab.setTotalqualite(rs.getDouble("totalqualite"));
                etab.setTotalservice(rs.getDouble("totalservice"));
                etab.setMoyqualite(rs.getDouble("moyqualite"));
                etab.setMoyservice(rs.getDouble("moyservice"));
                etab.setLatitude(rs.getDouble("latitude"));
                etab.setLongitude(rs.getDouble("longitude"));

                etab.setCategorie(rs.getString("categorie"));
                etab.setParking(rs.getBoolean("parking"));

                UserService us = new UserService();
                int idutilisateur = rs.getInt("iduser");

                User utilisateur = us.searchById(idutilisateur);
                etab.setIduser(utilisateur);

                etab.setCartecredit(rs.getBoolean("cartecredit"));
                etab.setChaiseroulante(rs.getBoolean("chaiseroulante"));
                etab.setFumer(rs.getBoolean("fumer"));
                etab.setTerasse(rs.getBoolean("terasse"));
                etab.setWifi(rs.getBoolean("wifi"));
                etab.setReservations(rs.getBoolean("reservations"));

                etab.setLivraison(rs.getBoolean("livraison"));
                etab.setClimatisation(rs.getBoolean("climatisation"));
                etab.setAnimaux(rs.getBoolean("animaux"));
                Categorie c = new Categorie(2, "Hotel", "Hotel");
                String requete3 = "SELECT * from sous__categorie where id='" + rs.getInt("souscat") + "'";
                PreparedStatement st3 = cn.prepareStatement(requete3);

                ResultSet rs3 = st3.executeQuery(requete3);

                SousCategorie sc = new SousCategorie();
                while (rs3.next()) {
                    sc.setId(rs3.getInt(1));

                    sc.setIdcategorie(c);
                    sc.setNom(rs3.getString(3)); // oubien 3 

                }

                etab.setSouscat(sc);
                myList.add(etab);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
     public ObservableList<Etablissement> TriserviceHotel(String trisq) {
        ObservableList<Etablissement> myList = FXCollections.observableArrayList();
        Connection cn = MyConnexion.getInstance().getConnection();
       if (trisq == "Meilleur Qualité") {
            trisq = "moyqualite";
        }
        if (trisq == "Meilleur Service") {
            trisq = "moyservice";
        }

        try {
            String requete = "Select * from etablissement where categorie='Hotel' ORDER BY " + trisq + " DESC";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Etablissement etab = new Etablissement();
                etab.setName(rs.getString("name"));
                etab.setAddress(rs.getString("address"));
                etab.setEmail(rs.getString("email"));
                etab.setPhone(rs.getString("phone"));
                etab.setCheckin(rs.getString("checkin"));
                etab.setCheckout(rs.getString("checkout"));
                etab.setEtoile(rs.getInt("etoile"));
                etab.setNbrchambre(rs.getInt("nbrchambre"));
                etab.setLpd(rs.getBoolean("lpd"));
                etab.setDp(rs.getBoolean("dp"));
                etab.setPc(rs.getBoolean("pc"));
                etab.setAllinclusive(rs.getBoolean("allinclusive"));
                etab.setPrixmoy(rs.getInt("prixmoy"));
                etab.setId(rs.getInt("id"));
                etab.setAlcool(rs.getBoolean("alcool"));
                etab.setDescription(rs.getString("description"));
                etab.setWebsite(rs.getString("website"));
                etab.setFacebook(rs.getString("facebook"));
                etab.setImg1(rs.getString("img1"));
                etab.setImg2(rs.getString("img2"));
                etab.setImg3(rs.getString("img3"));
                etab.setDevis_name(rs.getString("devis_name"));
                etab.setTotalqualite(rs.getDouble("totalqualite"));
                etab.setTotalservice(rs.getDouble("totalservice"));
                etab.setMoyqualite(rs.getDouble("moyqualite"));
                etab.setMoyservice(rs.getDouble("moyservice"));
                etab.setLatitude(rs.getDouble("latitude"));
                etab.setLongitude(rs.getDouble("longitude"));

                etab.setCategorie(rs.getString("categorie"));
                etab.setParking(rs.getBoolean("parking"));

                UserService us = new UserService();
                int idutilisateur = rs.getInt("iduser");

                User utilisateur = us.searchById(idutilisateur);
                etab.setIduser(utilisateur);

                etab.setCartecredit(rs.getBoolean("cartecredit"));
                etab.setChaiseroulante(rs.getBoolean("chaiseroulante"));
                etab.setFumer(rs.getBoolean("fumer"));
                etab.setTerasse(rs.getBoolean("terasse"));
                etab.setWifi(rs.getBoolean("wifi"));
                etab.setReservations(rs.getBoolean("reservations"));

                etab.setLivraison(rs.getBoolean("livraison"));
                etab.setClimatisation(rs.getBoolean("climatisation"));
                etab.setAnimaux(rs.getBoolean("animaux"));
                Categorie c = new Categorie(2, "Hotel", "Hotel");
                String requete2 = "SELECT * from sous__categorie where id='" + rs.getInt("souscat") + "'";
                PreparedStatement st2 = cn.prepareStatement(requete2);

                ResultSet rs2 = st2.executeQuery(requete2);

                SousCategorie sc = new SousCategorie();
                while (rs2.next()) {
                    sc.setId(rs2.getInt(1));

                    sc.setIdcategorie(c);
                    sc.setNom(rs2.getString(3)); // oubien 3 

                }

                etab.setSouscat(sc);
                myList.add(etab);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
      public ObservableList<Etablissement> ListCulture() {
        ObservableList<Etablissement> myList = FXCollections.observableArrayList();
        Connection cn = MyConnexion.getInstance().getConnection();
        try {
            String requete = "SELECT * from etablissement where categorie='Espace culturel'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Etablissement etab = new Etablissement();
                etab.setName(rs.getString("name"));
                etab.setAddress(rs.getString("address"));
                etab.setEmail(rs.getString("email"));
                etab.setPhone(rs.getString("phone"));
                etab.setLundisamedio(rs.getString("lundisamedio"));
                etab.setLundisamedif(rs.getString("lundisamedif"));
                etab.setDimancheo(rs.getString("dimancheo"));
                etab.setDimanchef(rs.getString("dimanchef"));
                etab.setId(rs.getInt("id"));
                etab.setAlcool(rs.getBoolean("alcool"));
                etab.setDescription(rs.getString("description"));
                etab.setWebsite(rs.getString("website"));
                etab.setFacebook(rs.getString("facebook"));
                etab.setImg1(rs.getString("img1"));
                etab.setImg2(rs.getString("img2"));
                etab.setImg3(rs.getString("img3"));
                etab.setDevis_name(rs.getString("devis_name"));
                etab.setTotalqualite(rs.getDouble("totalqualite"));
                etab.setTotalservice(rs.getDouble("totalservice"));
                etab.setMoyqualite(rs.getDouble("moyqualite"));
                etab.setMoyservice(rs.getDouble("moyservice"));
                etab.setLatitude(rs.getDouble("latitude"));
                etab.setLongitude(rs.getDouble("longitude"));

                etab.setCategorie(rs.getString("categorie"));
                etab.setParking(rs.getBoolean("parking"));

                UserService us = new UserService();
                int idutilisateur = rs.getInt("iduser");

                User utilisateur = us.searchById(idutilisateur);
                etab.setIduser(utilisateur);

                etab.setCartecredit(rs.getBoolean("cartecredit"));
                etab.setChaiseroulante(rs.getBoolean("chaiseroulante"));
                etab.setFumer(rs.getBoolean("fumer"));
                etab.setTerasse(rs.getBoolean("terasse"));
                etab.setWifi(rs.getBoolean("wifi"));
                etab.setReservations(rs.getBoolean("reservations"));
                etab.setPlace(rs.getInt("place"));
                etab.setLivraison(rs.getBoolean("livraison"));
                etab.setClimatisation(rs.getBoolean("climatisation"));
                etab.setAnimaux(rs.getBoolean("animaux"));
                Categorie c = new Categorie(4, "Restaurant", "Restaurant");
                String requete2 = "SELECT * from sous__categorie where id='" + rs.getInt("souscat") + "'";
                PreparedStatement st2 = cn.prepareStatement(requete2);

                ResultSet rs2 = st2.executeQuery(requete2);

                SousCategorie sc = new SousCategorie();
                while (rs2.next()) {
                    sc.setId(rs2.getInt(1));

                    sc.setIdcategorie(c);
                    sc.setNom(rs2.getString(3)); // oubien 3 

                }

                etab.setSouscat(sc);
                myList.add(etab);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

   

    public ObservableList<Etablissement> ListCultureTrie(String scvalue, String trisq) throws SQLException {
        ObservableList<Etablissement> myList = FXCollections.observableArrayList();
        Connection cn = MyConnexion.getInstance().getConnection();
        if (trisq == "Meilleur Qualité") {
            trisq = "moyqualite";
        }
        if (trisq == "Meilleur Service") {
            trisq = "moyservice";
        }

        String requete2 = "select id from sous__categorie where nom='" + scvalue + "'";

        Statement st2 = cn.createStatement();
        ResultSet rs2 = st2.executeQuery(requete2);
        int i = 0;
        while (rs2.next()) {
            i = rs2.getInt(1);

        }
        try {
            String requete = "SELECT * from etablissement where categorie='Espace culturel' and souscat='" + i + "' ORDER BY " + trisq + " DESC ";
            System.out.println(requete);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Etablissement etab = new Etablissement();
                etab.setName(rs.getString("name"));
                etab.setAddress(rs.getString("address"));
                etab.setEmail(rs.getString("email"));
                etab.setPhone(rs.getString("phone"));
                etab.setLundisamedio(rs.getString("lundisamedio"));
                etab.setLundisamedif(rs.getString("lundisamedif"));
                etab.setDimancheo(rs.getString("dimancheo"));
                etab.setDimanchef(rs.getString("dimanchef"));
                etab.setId(rs.getInt("id"));
                etab.setAlcool(rs.getBoolean("alcool"));
                etab.setDescription(rs.getString("description"));
                etab.setWebsite(rs.getString("website"));
                etab.setFacebook(rs.getString("facebook"));
                etab.setImg1(rs.getString("img1"));
                etab.setImg2(rs.getString("img2"));
                etab.setImg3(rs.getString("img3"));
                etab.setDevis_name(rs.getString("devis_name"));
                etab.setTotalqualite(rs.getDouble("totalqualite"));
                etab.setTotalservice(rs.getDouble("totalservice"));
                etab.setMoyqualite(rs.getDouble("moyqualite"));
                etab.setMoyservice(rs.getDouble("moyservice"));
                etab.setLatitude(rs.getDouble("latitude"));
                etab.setLongitude(rs.getDouble("longitude"));

                etab.setCategorie(rs.getString("categorie"));
                etab.setParking(rs.getBoolean("parking"));

                etab.setCartecredit(rs.getBoolean("cartecredit"));
                etab.setChaiseroulante(rs.getBoolean("chaiseroulante"));
                etab.setFumer(rs.getBoolean("fumer"));
                etab.setTerasse(rs.getBoolean("terasse"));
                etab.setWifi(rs.getBoolean("wifi"));
                etab.setReservations(rs.getBoolean("reservations"));
                etab.setPlace(rs.getInt("place"));
                etab.setLivraison(rs.getBoolean("livraison"));
                etab.setClimatisation(rs.getBoolean("climatisation"));
                etab.setAnimaux(rs.getBoolean("animaux"));
                Categorie c = new Categorie(4, "Restaurant", "Restaurant");
                String requete3 = "SELECT * from sous__categorie where id='" + rs.getInt("souscat") + "'";
                PreparedStatement st3 = cn.prepareStatement(requete3);

                ResultSet rs3 = st3.executeQuery(requete3);

                SousCategorie sc = new SousCategorie();
                while (rs3.next()) {
                    sc.setId(rs3.getInt(1));

                    sc.setIdcategorie(c);
                    sc.setNom(rs3.getString(3)); // oubien 3 

                }

                etab.setSouscat(sc);
                myList.add(etab);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public ObservableList<Etablissement> TriserviceCulture(String trisq) {
        ObservableList<Etablissement> myList = FXCollections.observableArrayList();
        Connection cn = MyConnexion.getInstance().getConnection();
        if (trisq == "Meilleur Qualité") {
            trisq = "moyqualite";
        }
        if (trisq == "Meilleur Service") {
            trisq = "moyservice";
        }

        try {
            String requete = "Select * from etablissement where categorie='Espace culturel' ORDER BY " + trisq + " DESC";
            System.out.println(requete);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Etablissement etab = new Etablissement();
                etab.setName(rs.getString("name"));
                etab.setAddress(rs.getString("address"));
                etab.setEmail(rs.getString("email"));
                etab.setPhone(rs.getString("phone"));
                etab.setLundisamedio(rs.getString("lundisamedio"));
                etab.setLundisamedif(rs.getString("lundisamedif"));
                etab.setDimancheo(rs.getString("dimancheo"));
                etab.setDimanchef(rs.getString("dimanchef"));
                etab.setId(rs.getInt("id"));
                etab.setAlcool(rs.getBoolean("alcool"));
                etab.setDescription(rs.getString("description"));
                etab.setWebsite(rs.getString("website"));
                etab.setFacebook(rs.getString("facebook"));
                etab.setImg1(rs.getString("img1"));
                etab.setImg2(rs.getString("img2"));
                etab.setImg3(rs.getString("img3"));
                etab.setDevis_name(rs.getString("devis_name"));
                etab.setTotalqualite(rs.getDouble("totalqualite"));
                etab.setTotalservice(rs.getDouble("totalservice"));
                etab.setMoyqualite(rs.getDouble("moyqualite"));
                etab.setMoyservice(rs.getDouble("moyservice"));
                etab.setLatitude(rs.getDouble("latitude"));
                etab.setLongitude(rs.getDouble("longitude"));

                etab.setCategorie(rs.getString("categorie"));
                etab.setParking(rs.getBoolean("parking"));

                etab.setCartecredit(rs.getBoolean("cartecredit"));
                etab.setChaiseroulante(rs.getBoolean("chaiseroulante"));
                etab.setFumer(rs.getBoolean("fumer"));
                etab.setTerasse(rs.getBoolean("terasse"));
                etab.setWifi(rs.getBoolean("wifi"));
                etab.setReservations(rs.getBoolean("reservations"));
                etab.setPlace(rs.getInt("place"));
                etab.setLivraison(rs.getBoolean("livraison"));
                etab.setClimatisation(rs.getBoolean("climatisation"));
                etab.setAnimaux(rs.getBoolean("animaux"));
                Categorie c = new Categorie(4, "Restaurant", "Restaurant");
                String requete2 = "SELECT * from sous__categorie where id='" + rs.getInt("souscat") + "'";
                PreparedStatement st2 = cn.prepareStatement(requete2);

                ResultSet rs2 = st2.executeQuery(requete2);

                SousCategorie sc = new SousCategorie();
                while (rs2.next()) {
                    sc.setId(rs2.getInt(1));

                    sc.setIdcategorie(c);
                    sc.setNom(rs2.getString(3)); // oubien 3 

                }

                etab.setSouscat(sc);
                myList.add(etab);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

}
