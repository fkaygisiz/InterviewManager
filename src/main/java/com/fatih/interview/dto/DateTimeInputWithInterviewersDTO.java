package com.fatih.interview.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class DateTimeInputWithInterviewersDTO extends DateTimeInputDTO {

	@NotNull
	List<Long> interviewIds;

	@NotNull
	String meetingName;

	public List<Long> getInterviewIds() {
		return interviewIds;
	}

	public void setInterviewIds(List<Long> interviewIds) {
		this.interviewIds = interviewIds;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

}
