/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Skan
 */
@Entity
@Table(name = "events")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Events.findAll", query = "SELECT e FROM Events e")
    , @NamedQuery(name = "Events.findById", query = "SELECT e FROM Events e WHERE e.id = :id")
    , @NamedQuery(name = "Events.findByName", query = "SELECT e FROM Events e WHERE e.name = :name")
    , @NamedQuery(name = "Events.findByDescription", query = "SELECT e FROM Events e WHERE e.description = :description")
    , @NamedQuery(name = "Events.findByType", query = "SELECT e FROM Events e WHERE e.type = :type")
    , @NamedQuery(name = "Events.findByDateEvenement", query = "SELECT e FROM Events e WHERE e.dateEvenement = :dateEvenement")
    , @NamedQuery(name = "Events.findByDevisName", query = "SELECT e FROM Events e WHERE e.devisName = :devisName")
    , @NamedQuery(name = "Events.findByUpdatedAt", query = "SELECT e FROM Events e WHERE e.updatedAt = :updatedAt")
    , @NamedQuery(name = "Events.findByAdresse", query = "SELECT e FROM Events e WHERE e.adresse = :adresse")
    , @NamedQuery(name = "Events.findByParking", query = "SELECT e FROM Events e WHERE e.parking = :parking")
    , @NamedQuery(name = "Events.findByFumer", query = "SELECT e FROM Events e WHERE e.fumer = :fumer")
    , @NamedQuery(name = "Events.findByInformationsociale", query = "SELECT e FROM Events e WHERE e.informationsociale = :informationsociale")
    , @NamedQuery(name = "Events.findByAdressemail", query = "SELECT e FROM Events e WHERE e.adressemail = :adressemail")
    , @NamedQuery(name = "Events.findByAdressefacebook", query = "SELECT e FROM Events e WHERE e.adressefacebook = :adressefacebook")
    , @NamedQuery(name = "Events.findByAdressetwitter", query = "SELECT e FROM Events e WHERE e.adressetwitter = :adressetwitter")
    , @NamedQuery(name = "Events.findByAdresseyoutube", query = "SELECT e FROM Events e WHERE e.adresseyoutube = :adresseyoutube")
    , @NamedQuery(name = "Events.findByWifi", query = "SELECT e FROM Events e WHERE e.wifi = :wifi")
    , @NamedQuery(name = "Events.findByEspaceeenfant", query = "SELECT e FROM Events e WHERE e.espaceeenfant = :espaceeenfant")
    , @NamedQuery(name = "Events.findByCartebancaire", query = "SELECT e FROM Events e WHERE e.cartebancaire = :cartebancaire")
    , @NamedQuery(name = "Events.findByAscenseur", query = "SELECT e FROM Events e WHERE e.ascenseur = :ascenseur")
    , @NamedQuery(name = "Events.findByEspacefamilial", query = "SELECT e FROM Events e WHERE e.espacefamilial = :espacefamilial")
    , @NamedQuery(name = "Events.findByAdresseinstagram", query = "SELECT e FROM Events e WHERE e.adresseinstagram = :adresseinstagram")
    , @NamedQuery(name = "Events.findByNumTel", query = "SELECT e FROM Events e WHERE e.numTel = :numTel")
    , @NamedQuery(name = "Events.findByNbrplace", query = "SELECT e FROM Events e WHERE e.nbrplace = :nbrplace")
    , @NamedQuery(name = "Events.findByNbrparticipant", query = "SELECT e FROM Events e WHERE e.nbrparticipant = :nbrparticipant")
    , @NamedQuery(name = "Events.findByImage", query = "SELECT e FROM Events e WHERE e.image = :image")
    , @NamedQuery(name = "Events.findByImage1", query = "SELECT e FROM Events e WHERE e.image1 = :image1")
    , @NamedQuery(name = "Events.findByImage2", query = "SELECT e FROM Events e WHERE e.image2 = :image2")
    , @NamedQuery(name = "Events.findByImage3", query = "SELECT e FROM Events e WHERE e.image3 = :image3")})
public class Events implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "dateEvenement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEvenement;
    @Basic(optional = false)
    @Column(name = "devis_name")
    private String devisName;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "Parking")
    private Boolean parking;
    @Column(name = "fumer")
    private Boolean fumer;
    @Column(name = "informationsociale")
    private String informationsociale;
    @Basic(optional = false)
    @Column(name = "adressemail")
    private String adressemail;
    @Column(name = "adressefacebook")
    private String adressefacebook;
    @Column(name = "adressetwitter")
    private String adressetwitter;
    @Column(name = "adresseyoutube")
    private String adresseyoutube;
    @Column(name = "wifi")
    private Boolean wifi;
    @Column(name = "Espaceeenfant")
    private Boolean espaceeenfant;
    @Column(name = "Cartebancaire")
    private Boolean cartebancaire;
    @Column(name = "ascenseur")
    private Boolean ascenseur;
    @Column(name = "Espacefamilial")
    private Boolean espacefamilial;
    @Column(name = "adresseinstagram")
    private String adresseinstagram;
    @Basic(optional = false)
    @Column(name = "numTel")
    private String numTel;
    @Basic(optional = false)
    @Column(name = "nbrplace")
    private int nbrplace;
    @Column(name = "nbrparticipant")
    private Integer nbrparticipant;
    @Basic(optional = false)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @Column(name = "image1")
    private String image1;
    @Basic(optional = false)
    @Column(name = "image2")
    private String image2;
    @Basic(optional = false)
    @Column(name = "image3")
    private String image3;
    @JoinColumn(name = "iduser", referencedColumnName = "id")
    @ManyToOne
    private User iduser;

    public Events() {
    }

    public Events(Integer id) {
        this.id = id;
    }

    public Events(Integer id, String name, String description, String type, Date dateEvenement, String devisName, Date updatedAt, String adresse, String adressemail, String numTel, int nbrplace, String image, String image1, String image2, String image3) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.dateEvenement = dateEvenement;
        this.devisName = devisName;
        this.updatedAt = updatedAt;
        this.adresse = adresse;
        this.adressemail = adressemail;
        this.numTel = numTel;
        this.nbrplace = nbrplace;
        this.image = image;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getDevisName() {
        return devisName;
    }

    public void setDevisName(String devisName) {
        this.devisName = devisName;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

    public String getInformationsociale() {
        return informationsociale;
    }

    public void setInformationsociale(String informationsociale) {
        this.informationsociale = informationsociale;
    }

    public String getAdressemail() {
        return adressemail;
    }

    public void setAdressemail(String adressemail) {
        this.adressemail = adressemail;
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

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getEspaceeenfant() {
        return espaceeenfant;
    }

    public void setEspaceeenfant(Boolean espaceeenfant) {
        this.espaceeenfant = espaceeenfant;
    }

    public Boolean getCartebancaire() {
        return cartebancaire;
    }

    public void setCartebancaire(Boolean cartebancaire) {
        this.cartebancaire = cartebancaire;
    }

    public Boolean getAscenseur() {
        return ascenseur;
    }

    public void setAscenseur(Boolean ascenseur) {
        this.ascenseur = ascenseur;
    }

    public Boolean getEspacefamilial() {
        return espacefamilial;
    }

    public void setEspacefamilial(Boolean espacefamilial) {
        this.espacefamilial = espacefamilial;
    }

    public String getAdresseinstagram() {
        return adresseinstagram;
    }

    public void setAdresseinstagram(String adresseinstagram) {
        this.adresseinstagram = adresseinstagram;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public Integer getNbrparticipant() {
        return nbrparticipant;
    }

    public void setNbrparticipant(Integer nbrparticipant) {
        this.nbrparticipant = nbrparticipant;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Events)) {
            return false;
        }
        Events other = (Events) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
