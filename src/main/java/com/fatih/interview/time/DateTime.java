package com.fatih.interview.time;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fatih.interview.common.Person;

@Entity
@Table(name = "date_intervals")
public class DateTime {

	@EmbeddedId
	private DateTimeId dateTimeId;

	@ManyToMany(targetEntity = Person.class, mappedBy = "dates", fetch = FetchType.EAGER)
	private List<Person> people;

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}

	public DateTimeId getDateTimeId() {
		return dateTimeId;
	}

	public void setDateTimeId(DateTimeId dateTimeId) {
		this.dateTimeId = dateTimeId;
	}

}
