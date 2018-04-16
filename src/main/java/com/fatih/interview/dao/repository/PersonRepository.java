package com.fatih.interview.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fatih.interview.dao.entity.Candidate;
import com.fatih.interview.dao.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

	List<Candidate> findByName(String title);

}
