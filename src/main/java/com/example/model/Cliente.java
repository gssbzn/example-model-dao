package com.example.model;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * La clase {@code Cliente} representa un cliente de la compañia.
 * @author Gustavo Bazan
 *
 */
@XmlRootElement
@XmlType (propOrder={"id","cedula","nombre"})
public final class Cliente implements Model<Integer>, Serializable {	
    /**	 */
    private static final long serialVersionUID = -3956424980931876024L;
    
    private Integer id;
    private String nombre;
    private String cedula;
    private Collection<Cuenta> cuentasCollection;
    
    public Cliente() {
    }

    public Cliente(Integer id, String nombre, String cedula) {
        this.setId(id);
        this.setNombre(nombre);
        this.setCedula(cedula);        
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @XmlTransient
    public Collection<Cuenta> getCuentasCollection() {
        return cuentasCollection;
    }

    public void setCuentasCollection(Collection<Cuenta> cuentasCollection) {
        this.cuentasCollection = cuentasCollection;
    }

    @Override
    public int hashCode() {
    	final int prime = 31;
        int hash = 1;
        hash += prime * hash + (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Cliente[ id=" + id + " ]";
    }
	
}
