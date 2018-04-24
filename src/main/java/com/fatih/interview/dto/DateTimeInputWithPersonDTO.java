package com.fatih.interview.dto;

import javax.validation.constraints.NotNull;

public class DateTimeInputWithPersonDTO extends DateTimeInputDTO {

	@NotNull
	private Long personId;

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}
}
