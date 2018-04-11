/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "article")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a")
    , @NamedQuery(name = "Article.findById", query = "SELECT a FROM Article a WHERE a.id = :id")
    , @NamedQuery(name = "Article.findByTitle", query = "SELECT a FROM Article a WHERE a.title = :title")
    , @NamedQuery(name = "Article.findByDate", query = "SELECT a FROM Article a WHERE a.date = :date")
    , @NamedQuery(name = "Article.findByImage", query = "SELECT a FROM Article a WHERE a.image = :image")
    , @NamedQuery(name = "Article.findByNbreLike", query = "SELECT a FROM Article a WHERE a.nbreLike = :nbreLike")
    , @NamedQuery(name = "Article.findByNbreVue", query = "SELECT a FROM Article a WHERE a.nbreVue = :nbreVue")})
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Lob
    @Column(name = "content")
    private String content;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "image")
    private String image;
    @Column(name = "nbreLike")
    private Integer nbreLike;
    @Column(name = "nbreVue")
    private BigInteger nbreVue;
    @JoinColumn(name = "idTheme", referencedColumnName = "id")
    @ManyToOne
    private Theme idTheme;
    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne
    private User user;

    public Article() {
    }

    public Article(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getNbreLike() {
        return nbreLike;
    }

    public void setNbreLike(Integer nbreLike) {
        this.nbreLike = nbreLike;
    }

    public BigInteger getNbreVue() {
        return nbreVue;
    }

    public void setNbreVue(BigInteger nbreVue) {
        this.nbreVue = nbreVue;
    }

    public Theme getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(Theme idTheme) {
        this.idTheme = idTheme;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return title;
    }
    
}
