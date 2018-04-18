package com.fatih.interview.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatih.interview.dao.entity.Interviewer;

@Repository
public interface InterviewerRepository extends JpaRepository<Interviewer, Long> {

	List<Interviewer> findByName(String title);
}
