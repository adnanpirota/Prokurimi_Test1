/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dco.prokurimi.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pirota
 */
@Entity
@Table(name="CYBER.FURNITORI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Furnitori.findAll", query = "SELECT f FROM Furnitori f"),
    @NamedQuery(name = "Furnitori.gjejeSipasEmrit", query = "SELECT f FROM Furnitori f WHERE f.emriFurnitorit = :furnitori")})
public class Furnitori implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name="furnitori_s_generator", sequenceName="furnitori_s",
         initialValue = 1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="furnitori_s_generator")
    private BigDecimal id;
    @Column(name="EMRI_FURNITORIT")
    private String emriFurnitorit;
    @Column(name="TEL")
    private String tel;
    @Column(name="EMAILI")
    private String emaili;
    
    public Furnitori(){
        
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
        if (!(object instanceof Furnitori)) {
            return false;
        }
        Furnitori other = (Furnitori) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dco.prokurimi.entity.Furnitori[ id=" + id + " ]";
    }

    public String getEmriFurnitorit() {
        return emriFurnitorit;
    }

    public void setEmriFurnitorit(String emriFurnitorit) {
        this.emriFurnitorit = emriFurnitorit;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmaili() {
        return emaili;
    }

    public void setEmaili(String emaili) {
        this.emaili = emaili;
    }
    
}
