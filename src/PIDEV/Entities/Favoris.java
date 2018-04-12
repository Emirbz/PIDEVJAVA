/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Emir
 */
@Entity
@Table(name = "favoris")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Favoris.findAll", query = "SELECT f FROM Favoris f")
    , @NamedQuery(name = "Favoris.findById", query = "SELECT f FROM Favoris f WHERE f.id = :id")})
public class Favoris implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_Etablissement", referencedColumnName = "id")
    @ManyToOne
    private Etablissement idEtablissement;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne
    private User idUser;

    public Favoris() {
    }

    public Favoris(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Etablissement getIdEtablissement() {
        return idEtablissement;
    }

    public void setIdEtablissement(Etablissement idEtablissement) {
        this.idEtablissement = idEtablissement;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof Favoris)) {
            return false;
        }
        Favoris other = (Favoris) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PIDEV.Entities.Favoris[ id=" + id + " ]";
    }
    
}
