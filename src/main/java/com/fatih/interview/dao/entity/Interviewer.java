package com.fatih.interview.dao.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Interviewer")
public class Interviewer extends Person {

	private static final long serialVersionUID = -4380598837310592777L;

}
