package com.fatih.interview.dao.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Candidate")
public class Candidate extends Person {

	private static final long serialVersionUID = -6830184574897010121L;

}
