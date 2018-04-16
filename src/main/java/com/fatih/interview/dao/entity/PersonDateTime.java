package com.fatih.interview.dao.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "PERSON_DATE_TIME")
@Entity
public class PersonDateTime implements Serializable {

	private static final long serialVersionUID = -5466159088478962454L;

	@Id
	@ManyToOne
	private DateTime dateTime;

	@Id
	@ManyToOne
	private Person person;
	private Boolean arranged;

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Boolean getArranged() {
		return arranged;
	}

	public void setArranged(Boolean arranged) {
		this.arranged = arranged;
	}

}
