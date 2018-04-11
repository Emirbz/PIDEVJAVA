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
 * @author dahem
 */
public class events {
       @JoinColumn(name = "iduser", referencedColumnName = "id")
    @ManyToOne
    private User iduser;
     private int id;
    
    private String name;
    private String description;
    private String type;
    private Date dateEvenement;
    private String devis_name;
    private Date upadted_at;
    private int nbrplace;
    private int nbrparticipant;
    private int nbrplacerestant;
    private String adresse;
    private String adressemail;
    private int numTel;
    private String informationsociale;
    private String adressefacebook;
    private String adressetwitter;
     private String adresseyoutube;
    private String adresseinstagram;
    private Boolean parking;
    private Boolean fumer;
     private Boolean wifi;
    private Boolean EspaceEenfant;
    private Boolean ascenseur;
    private Boolean Cartebancaire;
     private Boolean Espacefamilial;
     private String Image;
     private String image1;
     private String image2;
     
     
     

    public events() {
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
        
    }

    public int getNbrplacerestant() {
        return nbrplacerestant;
    }

    public void setNbrplacerestant(int nbrplacerestant) {
        this.nbrplacerestant = nbrplacerestant;
    }
    

  
  
 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public String getDevis_name() {
        return devis_name;
    }

    public void setDevis_name(String devis_name) {
        this.devis_name = devis_name;
    }

    public Date getUpadted_at() {
        return upadted_at;
    }

    public void setUpadted_at(Date upadted_at) {
        this.upadted_at = upadted_at;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public int getNbrparticipant() {
        return nbrparticipant;
    }

    public void setNbrparticipant(int nbrparticipant) {
        this.nbrparticipant = nbrparticipant;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdressemail() {
        return adressemail;
    }

    public void setAdressemail(String adressemail) {
        this.adressemail = adressemail;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getInformationsociale() {
        return informationsociale;
    }

    public void setInformationsociale(String informationsociale) {
        this.informationsociale = informationsociale;
    }

    public String getAdressefacebook() {
        return adressefacebook;
    }

    public void setAdressefacebook(String adressefacebook) {
        this.adressefacebook = adressefacebook;
    }

    public String getAdressetwitter() {
        return adressetwitter;
    }

    public void setAdressetwitter(String adressetwitter) {
        this.adressetwitter = adressetwitter;
    }

    public String getAdresseyoutube() {
        return adresseyoutube;
    }

    public void setAdresseyoutube(String adresseyoutube) {
        this.adresseyoutube = adresseyoutube;
    }

    public String getAdresseinstagram() {
        return adresseinstagram;
    }

    public void setAdresseinstagram(String adresseinstagram) {
        this.adresseinstagram = adresseinstagram;
    }

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean getFumer() {
        return fumer;
    }

    public void setFumer(Boolean fumer) {
        this.fumer = fumer;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getEspaceEenfant() {
        return EspaceEenfant;
    }

    public void setEspaceEenfant(Boolean EspaceEenfant) {
        this.EspaceEenfant = EspaceEenfant;
    }

    public Boolean getAscenseur() {
        return ascenseur;
    }

    public void setAscenseur(Boolean ascenseur) {
        this.ascenseur = ascenseur;
    }

    public Boolean getCartebancaire() {
        return Cartebancaire;
    }

    public void setCartebancaire(Boolean Cartebancaire) {
        this.Cartebancaire = Cartebancaire;
    }

    public Boolean getEspacefamilial() {
        return Espacefamilial;
    }

    public void setEspacefamilial(Boolean Espacefamilial) {
        this.Espacefamilial = Espacefamilial;
    }

    public events(String name, String description, Date dateEvenement, int nbrplace,String adresse, int numTel, String adressefacebook, String adressetwitter, Boolean parking, Boolean fumer, Boolean wifi, Boolean EspaceEenfant, Boolean ascenseur, Boolean Cartebancaire, Boolean Espacefamilial) {
       
        this.name = name;
        this.description = description;
        this.dateEvenement = dateEvenement;
        this.nbrplace = nbrplace;
        this.adresse = adresse;        
        this.numTel = numTel;
        this.adressefacebook = adressefacebook;
        this.adressetwitter = adressetwitter;
        this.parking = parking;
        this.fumer = fumer;
        this.wifi = wifi;
        this.EspaceEenfant = EspaceEenfant;
        this.ascenseur = ascenseur;
        this.Cartebancaire = Cartebancaire;
        this.Espacefamilial = Espacefamilial;
    }

    @Override
    public String toString() {
        return "events{" + "id=" + id + ", iduser=" + iduser + ", name=" + name + ", description=" + description + ", type=" + type + ", dateEvenement=" + dateEvenement + ", devis_name=" + devis_name + ", upadted_at=" + upadted_at + ", nbrplace=" + nbrplace + ", nbrparticipant=" + nbrparticipant + ", adresse=" + adresse + ", adressemail=" + adressemail + ", numTel=" + numTel + ", informationsociale=" + informationsociale + ", adressefacebook=" + adressefacebook + ", adressetwitter=" + adressetwitter + ", adresseyoutube=" + adresseyoutube + ", adresseinstagram=" + adresseinstagram + ", parking=" + parking + ", fumer=" + fumer + ", wifi=" + wifi + ", EspaceEenfant=" + EspaceEenfant + ", ascenseur=" + ascenseur + ", Cartebancaire=" + Cartebancaire + ", Espacefamilial=" + Espacefamilial + '}';
    }
    
    
}
