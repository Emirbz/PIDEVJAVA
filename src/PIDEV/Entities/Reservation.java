/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;


import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author ons
 */
public class Reservation extends RecursiveTreeObject<Reservation> {
    private int id;
    @JoinColumn(name = "id_Etablissement", referencedColumnName = "id")
    @ManyToOne
    private Etablissement etablissement;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne
    private User user;
    private String aunomde;
    private int nombre;
    private String description;
    private Timestamp date;

    public Reservation(Etablissement etablissement,String aunomde, int nombre, String description, Timestamp date) {
       this.etablissement = etablissement;
        this.aunomde = aunomde;
        this.nombre = nombre;
        this.description = description;
        this.date = date;
    }
    
    public Reservation(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAunomde() {
        return aunomde;
    }

    public void setAunomde(String aunomde) {
        this.aunomde = aunomde;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    
    
    
}
