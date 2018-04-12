/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Entities.User;
import PIDEV.Utils.MyConnexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.Object;
import java.lang.reflect.InvocationTargetException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ons
 */
public class UserService {

    Connection cn = MyConnexion.getInstance().getConnection();
    MyConnexion cnx = MyConnexion.getInstance();
    
    public void addUser(User user) throws IOException {
        try {
            String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            java.util.Date date_util = new java.util.Date();
            java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
            String requete
                    = "INSERT INTO user (username, username_canonical, email, email_canonical, enabled, password, roles, name, surname, phone, facebook, address, devis_name, date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = cn.prepareStatement(requete);
            st.setString(1, user.getUsername());
            st.setString(2, user.getUsername().toLowerCase());
            st.setString(3, user.getEmail());
            st.setString(4, user.getEmail().toLowerCase());
            st.setBoolean(5, true);
            st.setString(6, pw_hash);
            
            st.setString(7, "a:0:{}");
            st.setString(8, user.getName());
            st.setString(9, user.getSurname());
            st.setString(10, user.getPhone());
            st.setString(11, user.getFacebook());
            st.setString(12, user.getAddress());
            st.setString(13, user.getDevis_name());
            st.setDate(14, date_sql);
            st.executeUpdate();
            System.out.println("User ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
        
    }

    public User searchUserByEmail(String pseudo, String password) throws SQLException {
        User user = null;
      String req="SELECT (password) FROM user where (username_canonical=? OR email_canonical=?)";
      PreparedStatement st1 = cn.prepareStatement(req);
        st1.setString(1, pseudo.toLowerCase());
        st1.setString(2, pseudo.toLowerCase());
        ResultSet rs1 = st1.executeQuery();
        while (rs1.next()){
            if(BCrypt.checkpw(password,"$2a$"+rs1.getString("password").substring(4, rs1.getString("password").length()))){
                String requete = "SELECT * FROM user where (username_canonical=? OR email_canonical=?)";
                PreparedStatement st = cn.prepareStatement(requete);
                st.setString(1, pseudo.toLowerCase());
                st.setString(2, pseudo.toLowerCase());


                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setUsername_canonical(rs.getString("username_canonical"));
                    user.setEmail(rs.getString("email"));
                    user.setEmail_canonical(rs.getString("email_canonical"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setSurname(rs.getString("surname"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setDevis_name(rs.getString("devis_name"));
                    user.setDate(rs.getDate("date"));
                    user.setFacebook(rs.getString("facebook"));
                    user.setRole(rs.getString("role"));
                    user.setLast_login(rs.getDate("last_login"));
                    user.setDate(rs.getDate("date"));
                    System.out.println("User found");

                }
            }else{
                System.out.println("no");
            }
        }
        
        return user;
    }

    public void listpass() throws SQLException {
        String requete = "SELECT * FROM user";
        PreparedStatement st = cn.prepareStatement(requete);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("password"));
        }
    }
    
    public void deleteUser(User user) throws SQLException{
        String req="DELETE FROM User WHERE id=?";
        PreparedStatement ste = cn.prepareStatement(req);
        ste.setInt(1, user.getId());
        ste.executeUpdate();
    }
      public User searchById(int id) throws SQLException
        {
            User user = new User();
            String requete ="Select * from user where id ='"+id+"'";
                    PreparedStatement st = cn.prepareStatement(requete);
              ResultSet rs = st.executeQuery();
             while (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setUsername_canonical(rs.getString("username_canonical"));
                    user.setEmail(rs.getString("email"));
                    user.setEmail_canonical(rs.getString("email_canonical"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setSurname(rs.getString("surname"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setDevis_name(rs.getString("devis_name"));
                    user.setDate(rs.getDate("date"));
                    user.setFacebook(rs.getString("facebook"));
                    user.setRole(rs.getString("role"));
                    user.setLast_login(rs.getDate("last_login"));
            
        }
             return user;}
  
    
    public void editUser(User user) throws SQLException{
        String req = "UPDATE user SET "
                  + "username = ?,"
                    + "username_canonical = ?,"
                    + "email = ?,"
                    + "email_canonical= ?,"
                    + "enabled = ?,"
                    + "last_login = ?,"
                    + "role = ? ,"
                    + "facebook = ?,"
                    + "address = ?,"
                    + "devis_name = ?,"
                    + " date = ?,name=?,surname=?,phone=? where id=?";
        System.out.println(req);
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setString(1, user.getUsername());
        pre.setString(2, user.getUsername().toLowerCase());
        pre.setString(3, user.getEmail());
        pre.setString(4, user.getEmail().toLowerCase());
        pre.setBoolean(5, user.isEnabled());
        
        pre.setDate(6, PIDEV.Views.FirstFrame.user.getLast_login());
        pre.setString(7, user.getRole());
        pre.setString(8, user.getFacebook());
        pre.setString(9, user.getAddress());
        pre.setString(10, user.getDevis_name());
        pre.setDate(11, PIDEV.Views.FirstFrame.user.getDate());
        pre.setString(12, user.getName());
        pre.setString(13, user.getSurname());
        pre.setString(14, user.getPhone());
        pre.setInt(15,PIDEV.Views.FirstFrame.user.getId());
        pre.executeUpdate();
        System.out.println("updated");
        
    }
    public void changePassUser(User user,String password) throws SQLException{
        String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());
        String req = "UPDATE user SET "
                    
                    + " password = ? where id=?";
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setString(1, pw_hash);
        pre.setInt(2, user.getId());
        pre.executeUpdate();
    }
}
