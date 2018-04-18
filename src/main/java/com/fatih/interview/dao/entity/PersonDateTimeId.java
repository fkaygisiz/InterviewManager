package com.fatih.interview.dao.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Embeddable
public class PersonDateTimeId implements Serializable {

	@ManyToOne
	private DateTime dateTime;

	@ManyToOne
	private Person person;

	public PersonDateTimeId() {}
	public PersonDateTimeId(DateTime e, Person person2) {
		this.dateTime = e;
		this.person = person2;
	}

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
}
