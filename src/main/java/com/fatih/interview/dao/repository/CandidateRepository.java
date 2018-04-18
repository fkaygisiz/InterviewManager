package com.fatih.interview.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatih.interview.dao.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

	List<Candidate> findByName(String title);
}
