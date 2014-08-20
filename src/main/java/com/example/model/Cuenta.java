package com.example.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * La clase {@code Cuenta} representa las cuentas que puede tener
 * el cliente dentro de la compañia.
 * 
 * <p>Los tipos de cuenta se encuentran definidos en {@link TipoCuenta}</p>
 * 
 * @author Gustavo Bazan
 *
 */
@XmlRootElement
@XmlType (propOrder={"id","cliente","tipo","saldo"})
public final class Cuenta implements Model<Integer>, Serializable {
    /**	 */
    private static final long serialVersionUID = -1768584903284441598L;

    private Integer id;
    private BigDecimal saldo = BigDecimal.ZERO;
    private String tipo;
    private Cliente cliente;

    public Cuenta() {
    }

    public Cuenta(Integer id) {
        this.setId(id);
    }
    
    public Cuenta(Integer id, BigDecimal saldo, String tipo) {
        this.setId(id);
        this.setSaldo(saldo);
        this.setTipo(tipo);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if(!TipoCuenta.isValid(tipo))
            throw new IllegalArgumentException("Tipo de Cuenta Invalido");
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cuenta[ id=" + id + " ]";
    }

}
