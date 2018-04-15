package com.fatih.interview.time;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fatih.interview.common.PersonDateTime;

@Entity
@Table(name = "date_intervals")
public class DateTime implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DateTimeId dateTimeId;

	@OneToMany(mappedBy = "dateTime")
	private List<PersonDateTime> personDateTimes;
	
	
	public DateTimeId getDateTimeId() {
		return dateTimeId;
	}

	public void setDateTimeId(DateTimeId dateTimeId) {
		this.dateTimeId = dateTimeId;
	}

	public List<PersonDateTime> getPersonDateTimes() {
		return personDateTimes;
	}

	public void setPersonDateTime(List<PersonDateTime> personDateTimes) {
		this.personDateTimes = personDateTimes;
	}

}
