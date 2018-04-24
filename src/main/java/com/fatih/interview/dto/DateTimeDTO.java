package com.fatih.interview.dto;

import static com.fatih.interview.dto.DTOConstants.DATE_FORMAT_REGEXP;
import static com.fatih.interview.dto.DTOConstants.TIME_SLOT_REGEXP;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;;

public class DateTimeDTO {

	@NotNull
	@Pattern(regexp = DATE_FORMAT_REGEXP)
	@JsonProperty(value = "date")
	private String dateTimeIdDate;

	@NotNull
	@Pattern(regexp = TIME_SLOT_REGEXP)
	@JsonProperty(value = "timeSlot")
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
