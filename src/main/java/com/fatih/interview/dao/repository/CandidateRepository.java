package com.fatih.interview.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatih.interview.dao.entity.Candidate;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {

	List<Candidate> findByName(String title);
}