/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;

import java.util.Date;

/**
 *
 * @author dahem
 */
public class temoignages {

    private int id;
    private String name;
    private String description;
    private String titre;
    private Date Datetemoignage;
    private User iduser;

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    public temoignages() {
        this.Datetemoignage = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDatetemoignage() {
        return Datetemoignage;
    }

    public void setDatetemoignage(Date Datetemoignage) {
        this.Datetemoignage = Datetemoignage;
    }

    public temoignages(String name, String description, Date Datetemoignage, String titre) {

        this.name = name;
        this.description = description;
        this.titre = titre;
        this.Datetemoignage = Datetemoignage;

    }

    @Override
    public String toString() {
        return "temoignages{" + "id=" + id + ", name=" + name + ",titre=" + titre + ", description=" + description + ",Datetemoignage=" + Datetemoignage + "}";
    }

}
