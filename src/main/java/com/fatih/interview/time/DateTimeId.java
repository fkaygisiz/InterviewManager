package com.fatih.interview.time;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fatih.interview.common.Person;

@Embeddable
public class DateTimeId implements Serializable {

	private static final long serialVersionUID = -8700874053714505375L;
	
	private String date;
	
	private String timeSlot;
	
	@ManyToOne
	@JsonManagedReference
	private Person person;
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}