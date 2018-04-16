package com.fatih.interview.dao.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DateTimeId implements Serializable {

	private static final long serialVersionUID = -8700874053714505375L;

	private String date;

	private String timeSlot;

	public DateTimeId() {
	}

	public DateTimeId(String date, String timeSlot) {
		super();
		this.date = date;
		this.timeSlot = timeSlot;
	}

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

}