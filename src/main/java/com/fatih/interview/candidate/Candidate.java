package com.fatih.interview.candidate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fatih.interview.common.Person;

@Entity
@DiscriminatorValue("Candidate")
public class Candidate extends Person {

}
