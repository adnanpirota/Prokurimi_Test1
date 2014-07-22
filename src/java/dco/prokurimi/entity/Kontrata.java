/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dco.prokurimi.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author pirota
 */
@Entity
@Table(name="CYBER.KONTRATA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kontrata.findAll", query = "SELECT k FROM Kontrata k"),
    @NamedQuery(name = "Kontrata.krejtDesc", query = "SELECT k FROM Kontrata k ORDER By k.id DESC")})
public class Kontrata implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional=false)
    @NotNull
    @SequenceGenerator(name="kontrata_s_generator", sequenceName="kontrata_s",
         initialValue = 1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="kontrata_s_generator")
    @Column(name="ID")
    private BigDecimal id;
    @ManyToOne
    @JoinColumn(name="DEPARTAMENTI", referencedColumnName="ID")
    private Departamenti departamenti;
    @ManyToOne
    @JoinColumn(name = "FURNITORI", referencedColumnName="ID")
    private Furnitori furnitori;
    @NotNull
    @Column(name="NUMRI_KONTRATES")
    private String numriKontrates;
    @ManyToOne
    @JoinColumn(name = "ARTIKULLI", referencedColumnName="ID")
    private Artikulli artikulli;
    @Column(name="SASIA")
    private int sasia;
    @Column(name="AVANSI")
    private int avansi;
    @Temporal(TemporalType.DATE)
    @Column(name="KOHA_ARRITJES_PORT")
    private Date kohaArritjesPort;
    @Temporal(TemporalType.DATE)
    @Column(name="KOHA_REALE_ARRITJES_PORT")
    private Date kohaRealeArritjesPort;
    @Column(name="CMIMI_KONTRATES")
    private int cmimiKontrates;
    @Column(name="PJESA_MBETUR_PER_PAGESE")
    private int pjesaMbeturPerPagese;
    @Column(name="KOMPLETUAR")
    private Boolean kompletuar;
    
    public Kontrata(){
        
    }
    

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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
        if (!(object instanceof Kontrata)) {
            return false;
        }
        Kontrata other = (Kontrata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dco.prokurimi.entity.Kontrata[ id=" + id + " ]";
    }

    public Departamenti getDepartamenti() {
        return departamenti;
    }

    public void setDepartamenti(Departamenti departamenti) {
        this.departamenti = departamenti;
    }

    public Furnitori getFurnitori() {
        return furnitori;
    }

    public void setFurnitori(Furnitori furnitori) {
        this.furnitori = furnitori;
    }

    public String getNumriKontrates() {
        return numriKontrates;
    }

    public void setNumriKontrates(String numriKontrates) {
        System.out.println("Po edhe nese vjen qetu te setNumriKontrates une sdi qka me bo me to?????" + numriKontrates);
        this.numriKontrates = numriKontrates;
    }

    public Artikulli getArtikulli() {
        return artikulli;
    }

    public void setArtikulli(Artikulli artikulli) {
        this.artikulli = artikulli;
    }

    public int getSasia() {
        return sasia;
    }

    public void setSasia(int sasia) {
        this.sasia = sasia;
    }

    public int getAvansi() {
        return avansi;
    }

    public void setAvansi(int avansi) {
        this.avansi = avansi;
    }

    public Date getKohaArritjesPort() {
        return kohaArritjesPort;
    }

    public void setKohaArritjesPort(Date kohaArritjesPort) {
        this.kohaArritjesPort = kohaArritjesPort;
    }

    public Date getKohaRealeArritjesPort() {
        return kohaRealeArritjesPort;
    }

    public void setKohaRealeArritjesPort(Date kohaRealeArritjesPort) {
        this.kohaRealeArritjesPort = kohaRealeArritjesPort;
    }

    public int getCmimiKontrates() {
        return cmimiKontrates;
    }

    public void setCmimiKontrates(int cmimiKontrates) {
        this.cmimiKontrates = cmimiKontrates;
    }

    public int getPjesaMbeturPerPagese() {
        return pjesaMbeturPerPagese;
    }

    public void setPjesaMbeturPerPagese(int pjesaMbeturPerPagese) {
        this.pjesaMbeturPerPagese = pjesaMbeturPerPagese;
    }

    public Boolean isKompletuar() {
        return kompletuar;
    }

    public void setKompletuar(Boolean kompletuar) {
        this.kompletuar = kompletuar;
    }
    
    
    
}
