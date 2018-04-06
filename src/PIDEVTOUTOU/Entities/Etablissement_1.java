/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEVTOUTOU.Entities;

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
@Table(name = "etablissement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etablissement_1.findAll", query = "SELECT e FROM Etablissement_1 e")
    , @NamedQuery(name = "Etablissement_1.findById", query = "SELECT e FROM Etablissement_1 e WHERE e.id = :id")
    , @NamedQuery(name = "Etablissement_1.findByName", query = "SELECT e FROM Etablissement_1 e WHERE e.name = :name")
    , @NamedQuery(name = "Etablissement_1.findByAddress", query = "SELECT e FROM Etablissement_1 e WHERE e.address = :address")
    , @NamedQuery(name = "Etablissement_1.findByDescription", query = "SELECT e FROM Etablissement_1 e WHERE e.description = :description")
    , @NamedQuery(name = "Etablissement_1.findByPhone", query = "SELECT e FROM Etablissement_1 e WHERE e.phone = :phone")
    , @NamedQuery(name = "Etablissement_1.findByEmail", query = "SELECT e FROM Etablissement_1 e WHERE e.email = :email")
    , @NamedQuery(name = "Etablissement_1.findByWebsite", query = "SELECT e FROM Etablissement_1 e WHERE e.website = :website")
    , @NamedQuery(name = "Etablissement_1.findByFacebook", query = "SELECT e FROM Etablissement_1 e WHERE e.facebook = :facebook")
    , @NamedQuery(name = "Etablissement_1.findByCategorie", query = "SELECT e FROM Etablissement_1 e WHERE e.categorie = :categorie")
    , @NamedQuery(name = "Etablissement_1.findByLundisamedio", query = "SELECT e FROM Etablissement_1 e WHERE e.lundisamedio = :lundisamedio")
    , @NamedQuery(name = "Etablissement_1.findByLundisamedif", query = "SELECT e FROM Etablissement_1 e WHERE e.lundisamedif = :lundisamedif")
    , @NamedQuery(name = "Etablissement_1.findByDimancheo", query = "SELECT e FROM Etablissement_1 e WHERE e.dimancheo = :dimancheo")
    , @NamedQuery(name = "Etablissement_1.findByDimanchef", query = "SELECT e FROM Etablissement_1 e WHERE e.dimanchef = :dimanchef")
    , @NamedQuery(name = "Etablissement_1.findByParking", query = "SELECT e FROM Etablissement_1 e WHERE e.parking = :parking")
    , @NamedQuery(name = "Etablissement_1.findByCartecredit", query = "SELECT e FROM Etablissement_1 e WHERE e.cartecredit = :cartecredit")
    , @NamedQuery(name = "Etablissement_1.findByChaiseroulante", query = "SELECT e FROM Etablissement_1 e WHERE e.chaiseroulante = :chaiseroulante")
    , @NamedQuery(name = "Etablissement_1.findByFumer", query = "SELECT e FROM Etablissement_1 e WHERE e.fumer = :fumer")
    , @NamedQuery(name = "Etablissement_1.findByTerasse", query = "SELECT e FROM Etablissement_1 e WHERE e.terasse = :terasse")
    , @NamedQuery(name = "Etablissement_1.findByWifi", query = "SELECT e FROM Etablissement_1 e WHERE e.wifi = :wifi")
    , @NamedQuery(name = "Etablissement_1.findByReservations", query = "SELECT e FROM Etablissement_1 e WHERE e.reservations = :reservations")
    , @NamedQuery(name = "Etablissement_1.findByEtoile", query = "SELECT e FROM Etablissement_1 e WHERE e.etoile = :etoile")
    , @NamedQuery(name = "Etablissement_1.findByNbrchambre", query = "SELECT e FROM Etablissement_1 e WHERE e.nbrchambre = :nbrchambre")
    , @NamedQuery(name = "Etablissement_1.findByCheckin", query = "SELECT e FROM Etablissement_1 e WHERE e.checkin = :checkin")
    , @NamedQuery(name = "Etablissement_1.findByCheckout", query = "SELECT e FROM Etablissement_1 e WHERE e.checkout = :checkout")
    , @NamedQuery(name = "Etablissement_1.findByLpd", query = "SELECT e FROM Etablissement_1 e WHERE e.lpd = :lpd")
    , @NamedQuery(name = "Etablissement_1.findByDp", query = "SELECT e FROM Etablissement_1 e WHERE e.dp = :dp")
    , @NamedQuery(name = "Etablissement_1.findByPc", query = "SELECT e FROM Etablissement_1 e WHERE e.pc = :pc")
    , @NamedQuery(name = "Etablissement_1.findByAllinclusive", query = "SELECT e FROM Etablissement_1 e WHERE e.allinclusive = :allinclusive")
    , @NamedQuery(name = "Etablissement_1.findByLivraison", query = "SELECT e FROM Etablissement_1 e WHERE e.livraison = :livraison")
    , @NamedQuery(name = "Etablissement_1.findByClimatisation", query = "SELECT e FROM Etablissement_1 e WHERE e.climatisation = :climatisation")
    , @NamedQuery(name = "Etablissement_1.findByAnimaux", query = "SELECT e FROM Etablissement_1 e WHERE e.animaux = :animaux")
    , @NamedQuery(name = "Etablissement_1.findByAlcool", query = "SELECT e FROM Etablissement_1 e WHERE e.alcool = :alcool")
    , @NamedQuery(name = "Etablissement_1.findByPrixmoy", query = "SELECT e FROM Etablissement_1 e WHERE e.prixmoy = :prixmoy")
    , @NamedQuery(name = "Etablissement_1.findByDevisName", query = "SELECT e FROM Etablissement_1 e WHERE e.devisName = :devisName")
    , @NamedQuery(name = "Etablissement_1.findByImg1", query = "SELECT e FROM Etablissement_1 e WHERE e.img1 = :img1")
    , @NamedQuery(name = "Etablissement_1.findByImg2", query = "SELECT e FROM Etablissement_1 e WHERE e.img2 = :img2")
    , @NamedQuery(name = "Etablissement_1.findByImg3", query = "SELECT e FROM Etablissement_1 e WHERE e.img3 = :img3")
    , @NamedQuery(name = "Etablissement_1.findByLatitude", query = "SELECT e FROM Etablissement_1 e WHERE e.latitude = :latitude")
    , @NamedQuery(name = "Etablissement_1.findByLongitude", query = "SELECT e FROM Etablissement_1 e WHERE e.longitude = :longitude")
    , @NamedQuery(name = "Etablissement_1.findByMoyservice", query = "SELECT e FROM Etablissement_1 e WHERE e.moyservice = :moyservice")
    , @NamedQuery(name = "Etablissement_1.findByMoyqualite", query = "SELECT e FROM Etablissement_1 e WHERE e.moyqualite = :moyqualite")
    , @NamedQuery(name = "Etablissement_1.findByTotalqualite", query = "SELECT e FROM Etablissement_1 e WHERE e.totalqualite = :totalqualite")
    , @NamedQuery(name = "Etablissement_1.findByTotalservice", query = "SELECT e FROM Etablissement_1 e WHERE e.totalservice = :totalservice")
    , @NamedQuery(name = "Etablissement_1.findByPlace", query = "SELECT e FROM Etablissement_1 e WHERE e.place = :place")})
public class Etablissement_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "description")
    private String description;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "website")
    private String website;
    @Column(name = "facebook")
    private String facebook;
    @Column(name = "categorie")
    private String categorie;
    @Column(name = "lundisamedio")
    private String lundisamedio;
    @Column(name = "lundisamedif")
    private String lundisamedif;
    @Column(name = "dimancheo")
    private String dimancheo;
    @Column(name = "dimanchef")
    private String dimanchef;
    @Column(name = "parking")
    private Boolean parking;
    @Column(name = "cartecredit")
    private Boolean cartecredit;
    @Column(name = "chaiseroulante")
    private Boolean chaiseroulante;
    @Column(name = "fumer")
    private Boolean fumer;
    @Column(name = "terasse")
    private Boolean terasse;
    @Column(name = "wifi")
    private Boolean wifi;
    @Column(name = "reservations")
    private Boolean reservations;
    @Column(name = "etoile")
    private Integer etoile;
    @Column(name = "nbrchambre")
    private Integer nbrchambre;
    @Column(name = "checkin")
    private String checkin;
    @Column(name = "checkout")
    private String checkout;
    @Column(name = "lpd")
    private Boolean lpd;
    @Column(name = "dp")
    private Boolean dp;
    @Column(name = "pc")
    private Boolean pc;
    @Column(name = "allinclusive")
    private Boolean allinclusive;
    @Column(name = "livraison")
    private Boolean livraison;
    @Column(name = "climatisation")
    private Boolean climatisation;
    @Column(name = "animaux")
    private Boolean animaux;
    @Column(name = "alcool")
    private Boolean alcool;
    @Column(name = "prixmoy")
    private Integer prixmoy;
    @Column(name = "devis_name")
    private String devisName;
    @Column(name = "img1")
    private String img1;
    @Column(name = "img2")
    private String img2;
    @Column(name = "img3")
    private String img3;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "moyservice")
    private Double moyservice;
    @Column(name = "moyqualite")
    private Double moyqualite;
    @Column(name = "totalqualite")
    private Double totalqualite;
    @Column(name = "totalservice")
    private Double totalservice;
    @Column(name = "place")
    private Integer place;
    @JoinColumn(name = "iduser", referencedColumnName = "id")
    @ManyToOne
    private User iduser;
    @JoinColumn(name = "souscat", referencedColumnName = "id")
    @ManyToOne
    private SousCategorie souscat;

    public Etablissement_1() {
    }

    public Etablissement_1(Integer id) {
        this.id = id;
    }

    public Etablissement_1(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getLundisamedio() {
        return lundisamedio;
    }

    public void setLundisamedio(String lundisamedio) {
        this.lundisamedio = lundisamedio;
    }

    public String getLundisamedif() {
        return lundisamedif;
    }

    public void setLundisamedif(String lundisamedif) {
        this.lundisamedif = lundisamedif;
    }

    public String getDimancheo() {
        return dimancheo;
    }

    public void setDimancheo(String dimancheo) {
        this.dimancheo = dimancheo;
    }

    public String getDimanchef() {
        return dimanchef;
    }

    public void setDimanchef(String dimanchef) {
        this.dimanchef = dimanchef;
    }

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean getCartecredit() {
        return cartecredit;
    }

    public void setCartecredit(Boolean cartecredit) {
        this.cartecredit = cartecredit;
    }

    public Boolean getChaiseroulante() {
        return chaiseroulante;
    }

    public void setChaiseroulante(Boolean chaiseroulante) {
        this.chaiseroulante = chaiseroulante;
    }

    public Boolean getFumer() {
        return fumer;
    }

    public void setFumer(Boolean fumer) {
        this.fumer = fumer;
    }

    public Boolean getTerasse() {
        return terasse;
    }

    public void setTerasse(Boolean terasse) {
        this.terasse = terasse;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getReservations() {
        return reservations;
    }

    public void setReservations(Boolean reservations) {
        this.reservations = reservations;
    }

    public Integer getEtoile() {
        return etoile;
    }

    public void setEtoile(Integer etoile) {
        this.etoile = etoile;
    }

    public Integer getNbrchambre() {
        return nbrchambre;
    }

    public void setNbrchambre(Integer nbrchambre) {
        this.nbrchambre = nbrchambre;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public Boolean getLpd() {
        return lpd;
    }

    public void setLpd(Boolean lpd) {
        this.lpd = lpd;
    }

    public Boolean getDp() {
        return dp;
    }

    public void setDp(Boolean dp) {
        this.dp = dp;
    }

    public Boolean getPc() {
        return pc;
    }

    public void setPc(Boolean pc) {
        this.pc = pc;
    }

    public Boolean getAllinclusive() {
        return allinclusive;
    }

    public void setAllinclusive(Boolean allinclusive) {
        this.allinclusive = allinclusive;
    }

    public Boolean getLivraison() {
        return livraison;
    }

    public void setLivraison(Boolean livraison) {
        this.livraison = livraison;
    }

    public Boolean getClimatisation() {
        return climatisation;
    }

    public void setClimatisation(Boolean climatisation) {
        this.climatisation = climatisation;
    }

    public Boolean getAnimaux() {
        return animaux;
    }

    public void setAnimaux(Boolean animaux) {
        this.animaux = animaux;
    }

    public Boolean getAlcool() {
        return alcool;
    }

    public void setAlcool(Boolean alcool) {
        this.alcool = alcool;
    }

    public Integer getPrixmoy() {
        return prixmoy;
    }

    public void setPrixmoy(Integer prixmoy) {
        this.prixmoy = prixmoy;
    }

    public String getDevisName() {
        return devisName;
    }

    public void setDevisName(String devisName) {
        this.devisName = devisName;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getMoyservice() {
        return moyservice;
    }

    public void setMoyservice(Double moyservice) {
        this.moyservice = moyservice;
    }

    public Double getMoyqualite() {
        return moyqualite;
    }

    public void setMoyqualite(Double moyqualite) {
        this.moyqualite = moyqualite;
    }

    public Double getTotalqualite() {
        return totalqualite;
    }

    public void setTotalqualite(Double totalqualite) {
        this.totalqualite = totalqualite;
    }

    public Double getTotalservice() {
        return totalservice;
    }

    public void setTotalservice(Double totalservice) {
        this.totalservice = totalservice;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    public SousCategorie getSouscat() {
        return souscat;
    }

    public void setSouscat(SousCategorie souscat) {
        this.souscat = souscat;
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
        if (!(object instanceof Etablissement_1)) {
            return false;
        }
        Etablissement_1 other = (Etablissement_1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pidevtou.entities.Etablissement_1[ id=" + id + " ]";
    }
    
}
