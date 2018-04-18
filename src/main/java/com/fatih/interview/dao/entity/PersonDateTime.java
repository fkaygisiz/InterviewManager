package com.fatih.interview.dao.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "PERSON_DATE_TIME")
@Entity
public class PersonDateTime implements Serializable {

	private static final long serialVersionUID = -5466159088478962454L;

	@EmbeddedId
	private PersonDateTimeId personDateTimeId;
	
	private Boolean arranged;
	
	public PersonDateTime() {}
	
	public PersonDateTime(PersonDateTimeId personDateTimeId, Boolean arranged) {
		this.personDateTimeId = personDateTimeId;
		this.arranged = arranged;
	}



	public Boolean getArranged() {
		return arranged;
	}

	public void setArranged(Boolean arranged) {
		this.arranged = arranged;
	}

	public PersonDateTimeId getPersonDateTimeId() {
		return personDateTimeId;
	}

	public void setPersonDateTimeId(PersonDateTimeId personDateTimeId) {
		this.personDateTimeId = personDateTimeId;
	}

}
