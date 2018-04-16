package com.fatih.interview.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

import com.fatih.interview.dao.entity.Candidate;
import com.fatih.interview.dto.PersonDTO;
import com.fatih.interview.service.CandidateService;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

	@Autowired
	private CandidateService candidateService;

	final java.lang.reflect.Type targetListType = new TypeToken<List<PersonDTO>>() {
	}.getType();
	final ModelMapper modelMapper = new ModelMapper();

	@GetMapping("")
	public ResponseEntity<List<PersonDTO>> findAll() {
		List<Candidate> allCandidates = candidateService.findAll();

		return ResponseEntity.ok(modelMapper.map(allCandidates, targetListType));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
		Optional<Candidate> candidate = candidateService.findById(id);
		if (candidate.isPresent()) {
			return ResponseEntity.ok(modelMapper.map(candidate.get(), PersonDTO.class));
		}
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "")
	public ResponseEntity<PersonDTO> save(@Valid @RequestBody Candidate candidate) {
		if (candidate.getId() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(modelMapper.map(candidateService.save(candidate), PersonDTO.class));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PersonDTO> update(@PathVariable Long id, @Valid @RequestBody Candidate candidate) {
		if (!id.equals(candidate.getId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		if (!candidateService.findById(id).isPresent()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(modelMapper.map(candidateService.save(candidate), PersonDTO.class));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		candidateService.delete(id);
		return ResponseEntity.ok().build();
	}

	public static void main(String[] args) {

	}
}
