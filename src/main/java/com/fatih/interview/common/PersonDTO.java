package com.fatih.interview.common;

import java.util.List;

public class PersonDTO {

	private Long id;

	private String name;

	private String lastName;

	private List<DateTimeForPerson> dates;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<DateTimeForPerson> getDates() {
		return dates;
	}

	public void setDates(List<DateTimeForPerson> dates) {
		this.dates = dates;
	}
}
