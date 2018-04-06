/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEVTOUTOU.Entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Emir
 */
@Entity
@Table(name = "sous__categorie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SousCategorie.findAll", query = "SELECT s FROM SousCategorie s")
    , @NamedQuery(name = "SousCategorie.findById", query = "SELECT s FROM SousCategorie s WHERE s.id = :id")
    , @NamedQuery(name = "SousCategorie.findByNom", query = "SELECT s FROM SousCategorie s WHERE s.nom = :nom")})
public class SousCategorie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @JoinColumn(name = "idcategorie", referencedColumnName = "id")
    @ManyToOne
    private Categorie idcategorie;
    @OneToMany(mappedBy = "souscat")
    private Collection<Etablissement_1> etablissementCollection;

    public SousCategorie() {
    }

    public SousCategorie(Integer id) {
        this.id = id;
    }

    public SousCategorie(Integer id, String nom,Categorie idcategorie) {
        this.id = id;
        this.nom = nom;
        this.idcategorie=idcategorie;
       
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

    public Categorie getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Categorie idcategorie) {
        this.idcategorie = idcategorie;
    }

    @XmlTransient
    public Collection<Etablissement_1> getEtablissementCollection() {
        return etablissementCollection;
    }

    public void setEtablissementCollection(Collection<Etablissement_1> etablissementCollection) {
        this.etablissementCollection = etablissementCollection;
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
        if (!(object instanceof SousCategorie)) {
            return false;
        }
        SousCategorie other = (SousCategorie) object;
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
