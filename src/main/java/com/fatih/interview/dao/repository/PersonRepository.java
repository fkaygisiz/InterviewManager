package com.fatih.interview.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.fatih.interview.dao.entity.Candidate;
import com.fatih.interview.dao.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	List<Candidate> findByName(String title);

}
