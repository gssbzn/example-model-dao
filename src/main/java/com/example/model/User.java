package com.example.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType (propOrder={"id","idEPA","name"})
public class User implements Model<Long> {

	/**	 */
	private static final long serialVersionUID = 2393968040348880878L;
	
	protected Long id;
	protected int idEPA;
	protected String name;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public int getIdEPA() {
		return idEPA;
	}

	public void setIdEPA(int idEPA) {
		this.idEPA = idEPA;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }

}