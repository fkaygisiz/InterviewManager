package com.fatih.interview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DateTimeForPerson {

	@JsonProperty(value = "date")
	private String personDateTimeDateTimeIdDate;

	@JsonProperty(value = "timeSlot")
	private String personDateTimeDateTimeIdTimeSlot;

	private Boolean arranged;

	private String meetingName;

	public Boolean getArranged() {
		return arranged;
	}

	public void setArranged(Boolean arranged) {
		this.arranged = arranged;
	}

	public String getPersonDateTimeDateTimeIdDate() {
		return personDateTimeDateTimeIdDate;
	}

	public void setPersonDateTimeDateTimeIdDate(String personDateTimeDateTimeIdDate) {
		this.personDateTimeDateTimeIdDate = personDateTimeDateTimeIdDate;
	}

	public String getPersonDateTimeDateTimeIdTimeSlot() {
		return personDateTimeDateTimeIdTimeSlot;
	}

	public void setPersonDateTimeDateTimeIdTimeSlot(String personDateTimeDateTimeIdTimeSlot) {
		this.personDateTimeDateTimeIdTimeSlot = personDateTimeDateTimeIdTimeSlot;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
}
