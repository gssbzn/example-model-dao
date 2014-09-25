package com.example.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.example.enums.SaleUnit;

/**
 * 
 *
 *
 */
@XmlRootElement
@XmlType (propOrder={"id","code","name","items","itemCost","ItemTaxAmount"})
public final class Article implements Model<Long> {

	/**	 */
	private static final long serialVersionUID = 5845081352321961718L;

	private Long id;
	private String code;
	private String name;
	private BigDecimal items = BigDecimal.ZERO;
	private BigDecimal itemCost = BigDecimal.ZERO;
	private BigDecimal itemTaxAmount = BigDecimal.ZERO;
	/** Precio total de los articulos */
	private BigDecimal totalCost = BigDecimal.ZERO;
	private SaleUnit saleUnit;

	
	public Article() {
	}
	
	public Article(String code, String name, BigDecimal itemCost) {
		this.code = code;
		this.name = name;
		this.itemCost = itemCost;
	}


	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getItems() {
		return items;
	}

	public void setItems(BigDecimal items) {
		this.items = items;
	}

	public BigDecimal getItemCost() {
		return itemCost;
	}

	public void setItemCost(BigDecimal itemCost) {
		this.itemCost = itemCost;
	}
	
	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public SaleUnit getSaleUnit() {
		return saleUnit;
	}

	public void setSaleUnit(SaleUnit saleUnit) {
		this.saleUnit = saleUnit;
	}

	public BigDecimal getItemTaxAmount() {
		return itemTaxAmount;
	}

	public void setItemTaxAmount(BigDecimal itemTaxAmount) {
		this.itemTaxAmount = itemTaxAmount;
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
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }
	
}
