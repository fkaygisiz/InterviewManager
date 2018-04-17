package com.fatih.interview.dao.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "date_intervals")
public class DateTime implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DateTimeId dateTimeId;

	@OneToMany(mappedBy = "dateTime")
	private Set<PersonDateTime> personDateTimes;

	public DateTime() {
	}

	public DateTime(DateTimeId dateTimeId) {
		this.dateTimeId = dateTimeId;
	}

	public DateTime(String date, String timeSlot) {
		this.dateTimeId = new DateTimeId(date, timeSlot);
	}

	public DateTimeId getDateTimeId() {
		return dateTimeId;
	}

	public void setDateTimeId(DateTimeId dateTimeId) {
		this.dateTimeId = dateTimeId;
	}

	public Set<PersonDateTime> getPersonDateTimes() {
		return personDateTimes;
	}

	public void setPersonDateTime(Set<PersonDateTime> personDateTimes) {
		this.personDateTimes = personDateTimes;
	}

}
