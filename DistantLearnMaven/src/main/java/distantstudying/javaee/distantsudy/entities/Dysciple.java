/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantstudying.javaee.distantsudy.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Администратор
 */
@Entity
@Table(name = "dysciple", catalog = "javaeechgu", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dysciple.findAll", query = "SELECT d FROM Dysciple d"),
    @NamedQuery(name = "Dysciple.findById", query = "SELECT d FROM Dysciple d WHERE d.id = :id"),
    @NamedQuery(name = "Dysciple.findByName", query = "SELECT d FROM Dysciple d WHERE d.name = :name"),
    @NamedQuery(name = "Dysciple.findByDirection", query = "SELECT d FROM Dysciple d WHERE d.direction = :direction")})
public class Dysciple implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "direction")
    private String direction;

    public Dysciple() {
    }

    public Dysciple(Integer id) {
        this.id = id;
    }

    public Dysciple(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
 
    public Dysciple(Integer id, String name, String disc) {
        this.id = id;
        this.name = name;
        this.direction = disc;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
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
        if (!(object instanceof Dysciple)) {
            return false;
        }
        Dysciple other = (Dysciple) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Dysciple[ id=" + this.id + ", name=" + this.name + ","
                + "description=" + this.direction + " ]";
    }
    
}
