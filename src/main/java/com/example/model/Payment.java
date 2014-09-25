package com.example.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.example.enums.PaymentMethod;

@XmlRootElement
@XmlType (propOrder={"id","payMethod","ammount"})
public class Payment implements Model<String> {

	/** */
	private static final long serialVersionUID = 2623328335018916229L;
	
	private String id;
	private PaymentMethod payMethod;
	private BigDecimal ammount;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public PaymentMethod getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(PaymentMethod payMethod) {
		this.payMethod = payMethod;
	}

	public BigDecimal getAmmount() {
		return ammount;
	}

	public void setAmmount(BigDecimal ammount) {
		this.ammount = ammount;
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
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }

}
