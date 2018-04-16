package com.fatih.interview.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatih.interview.dao.entity.Candidate;
import com.fatih.interview.dao.repository.CandidateRepository;

@Service
public class CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	public Candidate doSth(Candidate candidate) {
		return candidateRepository.save(candidate);
	}

	public List<Candidate> findAll() {
		return StreamSupport.stream(candidateRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public Optional<Candidate> findById(Long id) {
		return candidateRepository.findById(id);
	}

	public Candidate save(Candidate candidate) {
		return candidateRepository.save(candidate);
	}

	public void delete(Long id) {
		candidateRepository.deleteById(id);
	}
}
