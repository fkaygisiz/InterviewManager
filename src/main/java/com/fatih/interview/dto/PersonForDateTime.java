package com.fatih.interview.dto;

public class PersonForDateTime {

	private Long personDateTimePersonId;

	private String personDateTimePersonName;

	private String personDateTimePersonLastName;

	private Boolean arranged;

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
}
