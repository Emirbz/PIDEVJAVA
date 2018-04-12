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
 * @author Skan
 */
@Entity
@Table(name = "reclamation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reclamation.findAll", query = "SELECT r FROM Reclamation r")
    , @NamedQuery(name = "Reclamation.findById", query = "SELECT r FROM Reclamation r WHERE r.id = :id")
    , @NamedQuery(name = "Reclamation.findByContenu", query = "SELECT r FROM Reclamation r WHERE r.contenu = :contenu")
    , @NamedQuery(name = "Reclamation.findByTypeobj", query = "SELECT r FROM Reclamation r WHERE r.typeobj = :typeobj")
    , @NamedQuery(name = "Reclamation.findByIdobj", query = "SELECT r FROM Reclamation r WHERE r.idobj = :idobj")
    , @NamedQuery(name = "Reclamation.findByEtat", query = "SELECT r FROM Reclamation r WHERE r.etat = :etat")})
public class Reclamation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "contenu")
    private String contenu;
    @Basic(optional = false)
    @Column(name = "typeobj")
    private String typeobj;
    @Basic(optional = false)
    @Column(name = "idobj")
    private int idobj;
    @Basic(optional = false)
    @Column(name = "etat")
    private String etat;
    @JoinColumn(name = "iduser", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User iduser;

    public Reclamation() {
    }

    public Reclamation(Integer id) {
        this.id = id;
    }

    public Reclamation(Integer id, String contenu, String typeobj, int idobj, String etat) {
        this.id = id;
        this.contenu = contenu;
        this.typeobj = typeobj;
        this.idobj = idobj;
        this.etat = etat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getTypeobj() {
        return typeobj;
    }

    public void setTypeobj(String typeobj) {
        this.typeobj = typeobj;
    }

    public int getIdobj() {
        return idobj;
    }

    public void setIdobj(int idobj) {
        this.idobj = idobj;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
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
        if (!(object instanceof Reclamation)) {
            return false;
        }
        Reclamation other = (Reclamation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Reclamation[ id=" + id + " ]";
    }
    
}
