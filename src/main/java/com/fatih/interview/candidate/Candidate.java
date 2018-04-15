package com.fatih.interview.candidate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fatih.interview.common.Person;

@Entity
@DiscriminatorValue("Candidate")
public class Candidate extends Person {

	private static final long serialVersionUID = -6830184574897010121L;

}
