package com.fatih.interview.dao.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
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

	@OneToMany(mappedBy = "personDateTimeId.dateTime", cascade= CascadeType.DETACH)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateTimeId == null) ? 0 : dateTimeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DateTime other = (DateTime) obj;
		if (dateTimeId == null) {
			if (other.dateTimeId != null)
				return false;
		} else if (!dateTimeId.equals(other.dateTimeId))
			return false;
		return true;
	}

}
