package com.fatih.interview.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class DateTimeDTO {

	@NotNull
	@Pattern(regexp = "^([01]\\d|2[0-3]):([0-5][0-5])$")
	private String dateTimeIdDate;

	@Pattern(regexp = "^([01]\\d|2[0-3]):([0-5][0-5])$")
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
