package com.fatih.interview.interviewer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewerRepository extends CrudRepository<Interviewer, Long> {

	List<Interviewer> findByName(String title);
}
