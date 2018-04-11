package com.fatih.interview.time;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fatih.interview.common.Person;

@Entity
@Table(name = "date_intervals")
public class DateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private List<String> timeSlots;

	//@OneToMany(targetEntity=Student.class, mappedBy="college", fetch=FetchType.EAGER)
	private Person person;
	
	private boolean arranged;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<String> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(List<String> timeSlots) {
		this.timeSlots = timeSlots;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean isArranged() {
		return arranged;
	}

	public void setArranged(boolean arranged) {
		this.arranged = arranged;
	}
	
	
}
