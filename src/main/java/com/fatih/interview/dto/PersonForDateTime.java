package com.fatih.interview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonForDateTime {

	@JsonProperty(value = "personId")
	private Long personDateTimePersonId;

	@JsonProperty(value = "personName")
	private String personDateTimePersonName;

	@JsonProperty(value = "personLastName")
	private String personDateTimePersonLastName;

	private Boolean arranged;

	private String meetingName;

	public Long getPersonDateTimePersonId() {
		return personDateTimePersonId;
	}

	public void setPersonDateTimePersonId(Long personDateTimePersonId) {
		this.personDateTimePersonId = personDateTimePersonId;
	}

	public String getPersonDateTimePersonName() {
		return personDateTimePersonName;
	}

	public void setPersonDateTimePersonName(String personDateTimePersonName) {
		this.personDateTimePersonName = personDateTimePersonName;
	}

	public String getPersonDateTimePersonLastName() {
		return personDateTimePersonLastName;
	}

	public void setPersonDateTimePersonLastName(String personDateTimePersonLastName) {
		this.personDateTimePersonLastName = personDateTimePersonLastName;
	}

	public Boolean getArranged() {
		return arranged;
	}

	public void setArranged(Boolean arranged) {
		this.arranged = arranged;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
}
