package com.example.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * La clase {@code Movimiento} representa los movimientos en las cuentas de los clientes
 * 
 * <p>Los tipos de cuenta se encuentran definidos en {@link TipoMovimiento}</p>
 * 
 * @author Gustavo Bazan
 *
 */
@XmlRootElement
@XmlType (propOrder={"id","cuenta","tipo","monto"})
public final class Movimiento implements Model<Integer>, Serializable {
    /**	 */
    private static final long serialVersionUID = 6778400152607853715L;

    private Integer id;	
    private Cuenta cuenta;
    private String tipo;
    private BigDecimal monto;

    public Movimiento() {		
    }

	
    public Movimiento(Integer id, String tipo, BigDecimal monto) {        
        this.id = id;
        this.tipo = tipo;
        this.monto = monto;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }	

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if(!TipoMovimiento.isValid(tipo))
            throw new IllegalArgumentException("Tipo de Movimiento Invalido");
        this.tipo = tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
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
        if (!(object instanceof Movimiento)) {
            return false;
        }
        Movimiento other = (Movimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Movimiento[ id=" + id + " ]";
    }

}
