/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Skan
 */
@Entity
@Table(name = "deal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deal.findAll", query = "SELECT d FROM Deal d")
    , @NamedQuery(name = "Deal.findById", query = "SELECT d FROM Deal d WHERE d.id = :id")
    , @NamedQuery(name = "Deal.findByNom", query = "SELECT d FROM Deal d WHERE d.nom = :nom")
    , @NamedQuery(name = "Deal.findByOldprix", query = "SELECT d FROM Deal d WHERE d.oldprix = :oldprix")
    , @NamedQuery(name = "Deal.findByPromotion", query = "SELECT d FROM Deal d WHERE d.promotion = :promotion")
    , @NamedQuery(name = "Deal.findByDescription", query = "SELECT d FROM Deal d WHERE d.description = :description")
    , @NamedQuery(name = "Deal.findByDatecreation", query = "SELECT d FROM Deal d WHERE d.datecreation = :datecreation")
    , @NamedQuery(name = "Deal.findByDatefin", query = "SELECT d FROM Deal d WHERE d.datefin = :datefin")
    , @NamedQuery(name = "Deal.findByNewprix", query = "SELECT d FROM Deal d WHERE d.newprix = :newprix")
    , @NamedQuery(name = "Deal.findByDevisName", query = "SELECT d FROM Deal d WHERE d.devisName = :devisName")
    , @NamedQuery(name = "Deal.findByUpdatedAt", query = "SELECT d FROM Deal d WHERE d.updatedAt = :updatedAt")
    , @NamedQuery(name = "Deal.findByRating", query = "SELECT d FROM Deal d WHERE d.rating = :rating")
    , @NamedQuery(name = "Deal.findByRegion", query = "SELECT d FROM Deal d WHERE d.region = :region")
    , @NamedQuery(name = "Deal.findByAdresse", query = "SELECT d FROM Deal d WHERE d.adresse = :adresse")
    , @NamedQuery(name = "Deal.findByPlacesdispo", query = "SELECT d FROM Deal d WHERE d.placesdispo = :placesdispo")})
public class Deal implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "newprix")
    private Double newprix;
    @Column(name = "rating")
    private Integer rating;
    @OneToMany(mappedBy = "iddeal")
    private Collection<Achat> achatCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "oldprix")
    private double oldprix;
    @Basic(optional = false)
    @Column(name = "promotion")
    private double promotion;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;
    @Basic(optional = false)
    @Column(name = "datefin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datefin;
    @Basic(optional = true)
    @Column(name = "devis_name")
    private String devisName;
    @Basic(optional = true)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = true)
    @Column(name = "region")
    private String region;
    @Basic(optional = false)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @Column(name = "placesdispo")
    private int placesdispo;
    @JoinColumn(name = "iduser", referencedColumnName = "id")
    @ManyToOne
    private User iduser;
    @JoinColumn(name = "cat", referencedColumnName = "id")
    @ManyToOne
    private Catdeal cat;

    public Deal() {
    }

    public Deal(Integer id) {
        this.id = id;
    }

    public Deal(String nom, double oldprix, double promotion, String description, Date datefin, String region, String adresse, int placesdispo) {
        this.nom = nom;
        this.oldprix = oldprix;
        this.promotion = promotion;
        this.description = description;
        Calendar cal = Calendar.getInstance();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
        this.datecreation = timestamp;
        this.datefin = datefin;
        this.updatedAt = new Date();
        this.newprix = oldprix - (oldprix * promotion / 100);
        this.region = region;
        this.adresse = adresse;
        this.placesdispo = placesdispo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getOldprix() {
        return oldprix;
    }

    public void setOldprix(double oldprix) {
        this.oldprix = oldprix;
    }

    public double getPromotion() {
        return promotion;
    }

    public void setPromotion(double promotion) {
        this.promotion = promotion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getPlacesdispo() {
        return placesdispo;
    }

    public void setPlacesdispo(int placesdispo) {
        this.placesdispo = placesdispo;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    public Catdeal getCat() {
        return cat;
    }

    public void setCat(Catdeal cat) {
        this.cat = cat;
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
        if (!(object instanceof Deal)) {
            return false;
        }
        Deal other = (Deal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom;
    }

    public Double getNewprix() {
        return newprix;
    }

    public void setNewprix(Double newprix) {
        this.newprix = newprix;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @XmlTransient
    public Collection<Achat> getAchatCollection() {
        return achatCollection;
    }

    public void setAchatCollection(Collection<Achat> achatCollection) {
        this.achatCollection = achatCollection;
    }

}
