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
@Table(name="CYBER.DEPARTAMENTI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamenti.findAll", query = "SELECT d FROM Departamenti d")})
public class Departamenti implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional=false)
    @NotNull
    @SequenceGenerator(name="departamenti_s_generator", sequenceName="departamenti_s",
         initialValue = 1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="nafta_s_generator")
    @Column(name="ID")
    private BigDecimal id;
    @Column(name="DEPARTAMENTI")
    private String departamenti;
    
    public Departamenti(){
        
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
        if (!(object instanceof Departamenti)) {
            return false;
        }
        Departamenti other = (Departamenti) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dco.prokurimi.entity.Departamenti[ id=" + id + " ]";
    }

    public String getDepartamenti() {
        return departamenti;
    }

    public void setDepartamenti(String departamenti) {
        this.departamenti = departamenti;
    }
    
}
