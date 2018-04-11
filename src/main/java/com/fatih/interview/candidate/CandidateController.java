package com.fatih.interview.candidate;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

	@Autowired
	private CandidateService candidateService;

	@GetMapping("")
	public ResponseEntity<List<Candidate>> findAll() {
		return ResponseEntity.ok(candidateService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Candidate> findById(@PathVariable Long id) {
		Optional<Candidate> candidate = candidateService.findById(id);
		if (candidate.isPresent()) {
			return ResponseEntity.ok(candidate.get());
		}
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "")
	public ResponseEntity<Candidate> save(@Valid @RequestBody Candidate candidate) {
		if (candidate.getId() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(candidateService.save(candidate));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Candidate> update(@PathVariable Long id, @Valid @RequestBody Candidate candidate) {
		if (!id.equals(candidate.getId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		if (!candidateService.findById(id).isPresent()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(candidateService.save(candidate));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		candidateService.delete(id);
		return ResponseEntity.ok().build();
	}
}
