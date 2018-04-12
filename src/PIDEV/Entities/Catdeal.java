/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "catdeal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catdeal.findAll", query = "SELECT c FROM Catdeal c")
    , @NamedQuery(name = "Catdeal.findById", query = "SELECT c FROM Catdeal c WHERE c.id = :id")
    , @NamedQuery(name = "Catdeal.findByNom", query = "SELECT c FROM Catdeal c WHERE c.nom = :nom")
    , @NamedQuery(name = "Catdeal.findByDevisName", query = "SELECT c FROM Catdeal c WHERE c.devisName = :devisName")
    , @NamedQuery(name = "Catdeal.findByUpdatedAt", query = "SELECT c FROM Catdeal c WHERE c.updatedAt = :updatedAt")})
public class Catdeal implements Serializable {

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
    @Column(name = "devis_name")
    private String devisName;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(mappedBy = "cat")
    private Collection<Deal> dealCollection;

    public Catdeal() {
    }

    public Catdeal(Integer id) {
        this.id = id;
    }

    public Catdeal(Integer id, String nom, String devisName, Date updatedAt) {
        this.id = id;
        this.nom = nom;
        this.devisName = devisName;
        this.updatedAt = updatedAt;
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

    @XmlTransient
    public Collection<Deal> getDealCollection() {
        return dealCollection;
    }

    public void setDealCollection(Collection<Deal> dealCollection) {
        this.dealCollection = dealCollection;
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
        if (!(object instanceof Catdeal)) {
            return false;
        }
        Catdeal other = (Catdeal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom;
    }

}
