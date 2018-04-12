/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Entities.Article;
import PIDEV.Entities.Categorie;
import PIDEV.Entities.Comment;
import PIDEV.Entities.SousCategorie;
import PIDEV.Entities.Theme;
import PIDEV.Entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import PIDEV.Utils.MyConnexion;
import PIDEV.Views.FirstFrame;

/**
 *
 * @author Slim sl
 */
public class myCroud {
    public static int id;
    public static Article modifier;
    public static int id_user;

    public void ajouterCategorie(Categorie p) {
        try {
            String e = p.getNom();
            String r = p.getDescription();
            String requete = "INSERT INTO categorie (nom,description) VALUES ('" + e + "','" + r + "')";

            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(requete);
//            st.setString(0, p.a);
//            st.setString(1, p.b);
            st.executeUpdate();
            System.out.println("Categorie ajoutée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Categorie> listerCategories() {
        List<Categorie> myList = new ArrayList<Categorie>();
        try {

            String requete2 = "Select * From categorie";
            Statement st2 = MyConnexion.getInstance().getConnection().createStatement();
            ResultSet rs = st2.executeQuery(requete2);

            while (rs.next()) {
                Categorie p = new Categorie();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setDescription(rs.getString(3));
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public void ajouterSous_Categorie(SousCategorie p) {
        try {
            String requete = "INSERT INTO sous_categorie (nom,description) VALUES (?,?)";
            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(requete);
            st.setString(0, p.getNom());
            st.setString(1, p.getIdcategorie().getNom());

            st.executeUpdate();
            System.out.println("Sous_Categorie ajoutée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<SousCategorie> listerSous_Categories() {
        List<SousCategorie> myList = new ArrayList<SousCategorie>();
        try {

            String requete2 = "Select * From sous_categorie";
            Statement st2 = MyConnexion.getInstance().getConnection().createStatement();
            ResultSet rs = st2.executeQuery(requete2);

            while (rs.next()) {
                SousCategorie p = new SousCategorie();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(3));

                myList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public void modifierCategorie(Categorie p) {

        try {
            String req = "UPDATE `categorie` SET `nom` = ?, `description`= ? WHERE `id` = ?";
            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(req);
            st.setString(1, p.getNom());
            st.setString(2, p.getDescription());
            st.setInt(3, p.getId());

            st.executeUpdate();
            st.executeUpdate();
            System.out.println("Categorie modifiée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierSous_Categorie(SousCategorie p) {

        try {
            String req = "UPDATE `sous_categorie` SET `nom` = ?, WHERE `id` = ?";
            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(req);
            st.setString(1, p.getNom());

            st.setInt(2, p.getId());

            st.executeUpdate();
            st.executeUpdate();
            System.out.println("Sous_Categorie modifiée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteCategorie(int id) {
        try {
            String req = "DELETE FROM `categorie` WHERE `id` = ? ";
            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteSous_Categorie(int id) {
        try {
            String req = "DELETE FROM `sous_categorie` WHERE `id` = ? ";
            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void ajoutertheme(Theme i,int id) {
        try {
            String e = i.getName();
            String r = i.getDescription();
            String f = i.getImage();
            
            String requete = "INSERT INTO theme (Name,description,image,user) VALUES ('" + e + "','" + r + "','" + f + "','" + id+ "')";

            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(requete);
//            st.setString(0, p.a);
//            st.setString(1, p.b);
            st.executeUpdate();
            System.out.println("theme ajoutée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Theme> listetheme1() {
        //List<Theme> myList = new ArrayList<Theme>();
        ObservableList<Theme> myList = FXCollections.observableArrayList();

        try {

            String requete2 = "Select * From theme";
            Statement st2 = MyConnexion.getInstance().getConnection().createStatement();
            ResultSet rs = st2.executeQuery(requete2);

            while (rs.next()) {
                Theme p = new Theme();
                p.setName(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setId(rs.getInt(1));
                p.setImage(rs.getString("image"));
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public boolean deleteTheme(Theme theme) {
        try {
            String req = "DELETE FROM `theme` WHERE `id` = ? ";
            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(req);
            st.setInt(1, theme.getId());
            int res = st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void modifierTheme(Theme p) {

        try {
            String req = "UPDATE `theme` SET `Name`=?,`Description` = ? WHERE `id` = ?";
            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(req);
            st.setString(1, p.getName());
            st.setString(2, p.getDescription());
            st.setInt(3, p.getId());

            st.executeUpdate();
            st.executeUpdate();
            System.out.println("Article modifiée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouterArticle(Article i) {
        try {
//            String e = i.getTitle();
//            String r = i.getContent();
//            int T = i.getIdTheme().getId();
            String requete = "INSERT INTO Article (title,content,idTheme,image,NbreLike,user) VALUES (?,?,?,?,?,?)";
            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(requete);
            st.setString(1, i.getTitle());
            st.setString(2, i.getContent());
            st.setInt(3, i.getIdTheme().getId());
            st.setString(4, i.getImage());
            st.setInt(5,0);
            st.setInt(6,id);
            st.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Article> listeArticles() {
        ObservableList<Article> myList = FXCollections.observableArrayList();
        try {

            String requete2 = "Select * From article ";
            Statement st2 = MyConnexion.getInstance().getConnection().createStatement();
            ResultSet rs = st2.executeQuery(requete2);

            while (rs.next()) {
                Article p = new Article();
                p.setTitle(rs.getString(3));
                p.setContent(rs.getString(4));
                p.setId(rs.getInt(1));

                myList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public List<Article> listeArticlesparid(int id) {
        // List<Article> myList = new ArrayList<Article>();
        ObservableList<Article> myList = FXCollections.observableArrayList();

        try {

            String requete2 = "Select title,content,image,id,nbreLike From `Article` WHERE `idtheme` = '" + id + "'";
            Statement st2 = MyConnexion.getInstance().getConnection().createStatement();
            ResultSet rs = st2.executeQuery(requete2);

            while (rs.next()) {
                Article p = new Article();
                p.setTitle(rs.getString(1));
                p.setContent(rs.getString(2));
                p.setImage(rs.getString(3));
                p.setId(rs.getInt(4));
                p.setNbreLike(rs.getInt(5));
//                p.setNbreLike(rs.getInt(6));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public void deleteArticle(int a) {
        try {
            String req = "DELETE FROM article WHERE id = ? ";
            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(req);
            st.setInt(1, a);
            int res = st.executeUpdate();
            System.out.println(res);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifierArticle(Article p) {

        try {
            String req = "UPDATE `Article` SET `Title` = ?,`Content` = ? WHERE `id` = "+modifier.getId();
            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(req);
            st.setString(1, p.getTitle());
            st.setString(2, p.getContent());

            st.executeUpdate();
            st.executeUpdate();
            System.out.println("Article modifiée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouterCommentaire(Comment i) {

        try {

            String requete = "INSERT INTO Comment (content,idArticle,idUser) VALUES (?,?,?)";
            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(requete);

            st.setString(1, i.getContent());
            st.setInt(2, i.getIdArticle().getId());
            st.setInt (3,FirstFrame.user.getId());
            st.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Comment> listeComment(int id) {
        ObservableList<Comment> myList = FXCollections.observableArrayList();
        try {

            String req = "Select * From `comment` where `idArticle` = (?)";
            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(req);
            Statement st2 = MyConnexion.getInstance().getConnection().createStatement();

            st.setInt(1, id);
            ResultSet rs = st2.executeQuery(req);
            while (rs.next()) {
                Comment p = new Comment();
                p.setContent(rs.getString(1));
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public List<Comment> listCommentparid(int id) {
        // List<Article> myList = new ArrayList<Article>();
        ObservableList<Comment> myList = FXCollections.observableArrayList();

        try {

            String requete2 = "Select * From Comment WHERE `idArticle` = '" + id + "'";
            Statement st2 = MyConnexion.getInstance().getConnection().createStatement();
            ResultSet rs = st2.executeQuery(requete2);
            while (rs.next()) {
                Comment p = new Comment();
                p.setContent(rs.getString(2));
                UserService us =new UserService();
                User u=us.searchById(rs.getInt("idUser"));
                p.setIdUser(u);
//                p.setNbreLike(rs.getInt(6));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public void deleteComment(int id) {
        try {
            String req = "DELETE FROM `Comment` WHERE `id` = ? ";
            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifierComment(Comment p) {

        try {
            String req = "UPDATE `Comment` SET `Content` = ?, WHERE `id` = ?";
            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(req);

            st.setString(1, p.getContent());
            st.executeUpdate();
            System.out.println("Commentaire modifiée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void aime (int id) {

        try {
            String req = "UPDATE `article` SET `nbreLike` = `nbreLike`+1 WHERE `article`.`id`  =?" ;
            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("Commentaire modifiée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void ajout_abonnement(int a,int b ) {

        try {

            String requete = "INSERT INTO aime (article,user) VALUES (?,?)";
            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(requete);

            st.setInt(1, a);
            
            st.setInt(2, b);
            st.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public List<Article> Abonnement(int id) {
        // List<Article> myList = new ArrayList<Article>();
        ObservableList<Article> myList = FXCollections.observableArrayList();

        try {

            String requete2 = "SELECT DISTINCT title,content,image,nbreLike,A.id From Article A,Aime Ai,User U WHERE Ai.article=A.id AND Ai.user = '" +id+ "'";
            Statement st2 = MyConnexion.getInstance().getConnection().createStatement();
            ResultSet rs = st2.executeQuery(requete2);

            while (rs.next()) {
                Article p = new Article();
                p.setTitle(rs.getString(1));
                p.setContent(rs.getString(2));
                p.setImage(rs.getString(3));
                p.setNbreLike(rs.getInt(4));
                p.setId(rs.getInt(5));
//                p.setNbreLike(rs.getInt(6));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
      public void deleteAbonnement(int a) {
        try {
            String req = "DELETE FROM aime WHERE id_user = "+id_user+" And id_article = "+a;
            PreparedStatement st = MyConnexion.getInstance().getConnection().prepareStatement(req);
            int res = st.executeUpdate();
            System.out.println(res);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
}
