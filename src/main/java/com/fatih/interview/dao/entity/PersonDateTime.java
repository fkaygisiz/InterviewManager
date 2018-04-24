package com.fatih.interview.dao.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "PERSON_DATE_TIME")
@Entity
public class PersonDateTime implements Serializable {

	private static final long serialVersionUID = -5466159088478962454L;

	@EmbeddedId
	private PersonDateTimeId personDateTimeId;

	private Boolean arranged;

	private String meetingName;

	public PersonDateTime() {
	}

	public PersonDateTime(PersonDateTimeId personDateTimeId, Boolean arranged, String meetingName) {
		this.personDateTimeId = personDateTimeId;
		this.arranged = arranged;
		this.meetingName = meetingName;
	}

	public PersonDateTime(PersonDateTimeId personDateTimeId, Boolean arranged) {
		this.personDateTimeId = personDateTimeId;
		this.arranged = arranged;
	}

	public Boolean getArranged() {
		return arranged;
	}

	public void setArranged(Boolean arranged) {
		this.arranged = arranged;
	}

	public PersonDateTimeId getPersonDateTimeId() {
		return personDateTimeId;
	}

	public void setPersonDateTimeId(PersonDateTimeId personDateTimeId) {
		this.personDateTimeId = personDateTimeId;
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
		if (!(obj instanceof  PersonDateTime))
			return false;
		PersonDateTime other = (PersonDateTime) obj;
		if (personDateTimeId == null) {
			if (other.personDateTimeId != null)
				return false;
		} else if (!personDateTimeId.equals(other.personDateTimeId))
			return false;
		return true;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

}
