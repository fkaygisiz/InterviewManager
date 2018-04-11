package com.fatih.interview.interviewer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterviewerService {

	@Autowired
	private InterviewerRepository candidateRepository;

	public Interviewer doSth(Interviewer candidate) {
		return candidateRepository.save(candidate);
	}

	public List<Interviewer> findAll() {
		return StreamSupport.stream(candidateRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public Optional<Interviewer> findById(Long id) {
		return candidateRepository.findById(id);
	}

	public Interviewer save(Interviewer candidate) {
		return candidateRepository.save(candidate);
	}

	public void delete(Long id) {
		candidateRepository.deleteById(id);
	}
}
