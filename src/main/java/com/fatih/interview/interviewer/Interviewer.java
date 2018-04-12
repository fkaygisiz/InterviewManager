package com.fatih.interview.interviewer;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fatih.interview.common.Person;

@Entity
@DiscriminatorValue("Interviewer")
public class Interviewer extends Person {

}
