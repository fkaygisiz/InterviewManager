package com.fatih.interview.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatih.interview.dao.entity.Person;
import com.fatih.interview.dao.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Optional<Person> findById(Long personId) {
		return personRepository.findById(personId);
	}

	public Person save(Person person) {
		return personRepository.save(person);
		
	}
	
}
