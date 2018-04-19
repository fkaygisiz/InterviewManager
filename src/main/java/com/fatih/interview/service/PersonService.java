package com.fatih.interview.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatih.interview.dao.entity.DateTimeId;
import com.fatih.interview.dao.entity.Person;
import com.fatih.interview.dao.entity.PersonDateTimeId;
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

	public List<Person> findAllById(@NotNull List<Long> interviewIds) {
		return personRepository.findAllById(interviewIds);
	}
	
	public List<Person> findArrangedPersonByDateAndTime(List<Long> personIds, List<DateTimeId> dateTimeId){
		return personRepository.findByIdInAndPersonDateTimes_ArrangedAndPersonDateTimes_PersonDateTimeId_DateTime_DateTimeId_In(personIds, true, dateTimeId);
	}
	
}
