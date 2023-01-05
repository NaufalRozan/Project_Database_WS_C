/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.ws.c.mantan;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rozan
 */
@Entity
@Table(name = "c2020ws")
@NamedQueries({
    @NamedQuery(name = "C2020ws.findAll", query = "SELECT c FROM C2020ws c"),
    @NamedQuery(name = "C2020ws.findById", query = "SELECT c FROM C2020ws c WHERE c.id = :id"),
    @NamedQuery(name = "C2020ws.findByName", query = "SELECT c FROM C2020ws c WHERE c.name = :name"),
    @NamedQuery(name = "C2020ws.findByBirthdate", query = "SELECT c FROM C2020ws c WHERE c.birthdate = :birthdate")})
public class C2020ws implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Lob
    @Column(name = "picture")
    private byte[] picture;

    public C2020ws() {
    }

    public C2020ws(Integer id) {
        this.id = id;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
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
        if (!(object instanceof C2020ws)) {
            return false;
        }
        C2020ws other = (C2020ws) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.ws.c.mantan.C2020ws[ id=" + id + " ]";
    }
    
}
