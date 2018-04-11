package com.fatih.interview.interviewer;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fatih.interview.common.Person;

@Entity
@Table(name = "interviewer")
public class Interviewer extends Person {

}
