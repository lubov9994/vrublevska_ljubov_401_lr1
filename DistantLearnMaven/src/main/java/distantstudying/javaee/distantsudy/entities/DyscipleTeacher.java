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
@Table(name = "dysciple_teacher", catalog = "javaeechgu", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DyscipleTeacher.findAll", query = "SELECT d FROM DyscipleTeacher d"),
    @NamedQuery(name = "DyscipleTeacher.findById", query = "SELECT d FROM DyscipleTeacher d WHERE d.id = :id"),
    @NamedQuery(name = "DyscipleTeacher.findByIdDysciple", query = "SELECT d FROM DyscipleTeacher d WHERE d.idDysciple = :idDysciple"),
    @NamedQuery(name = "DyscipleTeacher.findByIdTeacher", query = "SELECT d FROM DyscipleTeacher d WHERE d.idTeacher = :idTeacher")})
public class DyscipleTeacher implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_dysciple")
    private int idDysciple;
    @Basic(optional = false)
    @Column(name = "id_teacher")
    private int idTeacher;

    public DyscipleTeacher() {
    }

    public DyscipleTeacher(Integer id) {
        this.id = id;
    }

    public DyscipleTeacher(Integer id, int idDysciple, int idTeacher) {
        this.id = id;
        this.idDysciple = idDysciple;
        this.idTeacher = idTeacher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdDysciple() {
        return idDysciple;
    }

    public void setIdDysciple(int idDysciple) {
        this.idDysciple = idDysciple;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
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
        if (!(object instanceof DyscipleTeacher)) {
            return false;
        }
        DyscipleTeacher other = (DyscipleTeacher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "distantstudying.javaee.distantsudy.entities.DyscipleTeacher[ id=" + id + " ]";
    }
    
}
