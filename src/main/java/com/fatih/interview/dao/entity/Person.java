package com.fatih.interview.dao.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Person implements Serializable {

	private static final long serialVersionUID = 9037195857651873348L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "name")
	private String name;

	@NotBlank
	@Column(name = "last_name")
	private String lastName;

	@OneToMany(mappedBy = "person")
	private Set<PersonDateTime> personDateTimes;

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

	public Set<PersonDateTime> getPersonDateTimes() {
		return personDateTimes;
	}

	public void setPersonDateTimes(Set<PersonDateTime> personDateTimes) {
		this.personDateTimes = personDateTimes;
	}


}
