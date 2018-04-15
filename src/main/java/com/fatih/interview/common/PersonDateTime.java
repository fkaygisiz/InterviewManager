package com.fatih.interview.common;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fatih.interview.time.DateTime;

@Table(name = "PERSON_DATE_TIME")
@Entity
public class PersonDateTime implements Serializable{

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
