package com.fatih.interview.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class DateTimeInputDTO {

	@NotNull
	private Long personId;

	@NotNull
	@Pattern(regexp = "^\\d{4}\\-([0][0-9]|[1][012])\\-([012][0-9]|3[01])$", message = "Date should be in yyyy-MM-dd format")
	private String date;

	@Pattern(regexp = "^([01]\\d|2[0-3]):([0-5][0-5])$", message = "startTime should be in hh:mm format(eg: 15:30)")
	private String startTime;

	@NotNull
	@Pattern(regexp = "^([01]\\d|2[0-3]):([0-5][0-5])$", message = "startTime should be in hh:mm format(eg: 15:30)")
	private String endTime;

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
