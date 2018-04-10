/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;

import java.sql.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Emir
 */
public class Review {
     public Etablissement getIdetab() {
        return idetab;
    }

    public void setIdetab(Etablissement idetab) {
        this.idetab = idetab;
    }

     @JoinColumn(name = "iduser", referencedColumnName = "id")
    @ManyToOne
    private User iduser;

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }
    private Integer id;
   
    private String commentaire;
    
    private double service;
  
    private double qualite;
   private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
   
    
    
    private String titre;
     @JoinColumn(name = "idetab", referencedColumnName = "id")
    @ManyToOne
    private Etablissement idetab;

    public Review() {
    }

    public Review(Integer id) {
        this.id = id;
    }

    public Review( String commentaire, double service, double qualite, String titre,Etablissement idetab,Date date,User idUser) {
       
        this.commentaire = commentaire;
        this.service = service;
        this.qualite = qualite;
        this.idetab=idetab;
        this.titre = titre;
        this.date= date;
        this.iduser=idUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public double getService() {
        return service;
    }

    public void setService(double service) {
        this.service = service;
    }

    public double getQualite() {
        return qualite;
    }

    public void setQualite(double qualite) {
        this.qualite = qualite;
    }

    

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
}
