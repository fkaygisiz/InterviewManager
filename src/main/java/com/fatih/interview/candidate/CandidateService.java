package com.fatih.interview.candidate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	public Candidate doSth(Candidate candidate) {
		return candidateRepository.save(candidate);
	}

	public ResponseEntity<List<Candidate>> findAll() {
		return ResponseEntity.ok(
				StreamSupport.stream(candidateRepository.findAll().spliterator(), false).collect(Collectors.toList()));
	}

	public Optional<Candidate> findById(Long id) {
		return candidateRepository.findById(id);
	}

	public Candidate save(Candidate candidate) {
		return candidateRepository.save(candidate);
	}
}
