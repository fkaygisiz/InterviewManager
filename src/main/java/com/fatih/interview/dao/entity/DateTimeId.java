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
	@Override
    public int hashCode() {
        return 31;
    }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DateTimeId))
			return false;
		DateTimeId other = (DateTimeId) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (timeSlot == null) {
			if (other.timeSlot != null)
				return false;
		} else if (!timeSlot.equals(other.timeSlot))
			return false;
		return true;
	}

}