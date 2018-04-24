package com.fatih.interview.dto;

import static com.fatih.interview.dto.DTOConstants.DATE_FORMAT_REGEXP;
import static com.fatih.interview.dto.DTOConstants.DATE_FORMAT_REGEXP_MESSAGE;
import static com.fatih.interview.dto.DTOConstants.TIME_SLOT_REGEXP;
import static com.fatih.interview.dto.DTOConstants.TIME_SLOT_REGEXP_MESSAGE;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class DateTimeInputDTO {

	@NotNull
	@Pattern(regexp = DATE_FORMAT_REGEXP, message = DATE_FORMAT_REGEXP_MESSAGE)
	@ApiModelProperty(required = true, value = DATE_FORMAT_REGEXP_MESSAGE)
	private String date;

	@NotNull
	@Pattern(regexp = TIME_SLOT_REGEXP, message = TIME_SLOT_REGEXP_MESSAGE)
	@ApiModelProperty(required = true, value = TIME_SLOT_REGEXP_MESSAGE)
	private String startTime;

	@NotNull
	@Pattern(regexp = TIME_SLOT_REGEXP, message = TIME_SLOT_REGEXP_MESSAGE)
	@ApiModelProperty(required = true, value = TIME_SLOT_REGEXP_MESSAGE)
	private String endTime;

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
