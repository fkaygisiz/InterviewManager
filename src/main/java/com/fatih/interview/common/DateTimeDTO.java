package com.fatih.interview.common;

import java.util.List;

public class DateTimeDTO {

	private String dateTimeIdDate;
	
	private String dateTimeIdTimeSlot;
	
	private List<PersonForDateTime> personDateTimes;

	public String getDateTimeIdDate() {
		return dateTimeIdDate;
	}

	public void setDateTimeIdDate(String dateTimeIdDate) {
		this.dateTimeIdDate = dateTimeIdDate;
	}

	public String getDateTimeIdTimeSlot() {
		return dateTimeIdTimeSlot;
	}

	public void setDateTimeIdTimeSlot(String dateTimeIdTimeSlot) {
		this.dateTimeIdTimeSlot = dateTimeIdTimeSlot;
	}

	public List<PersonForDateTime> getPersonDateTimes() {
		return personDateTimes;
	}

	public void setPersonDateTimes(List<PersonForDateTime> personDateTimes) {
		this.personDateTimes = personDateTimes;
	}
}
