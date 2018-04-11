package com.fatih.interview.interviewer;

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
@RequestMapping("/interviewers")
public class InterviewerController {

	@Autowired
	private InterviewerService interviewerService;

	@GetMapping("")
	public ResponseEntity<List<Interviewer>> findAll() {
		return ResponseEntity.ok(interviewerService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Interviewer> findById(@PathVariable Long id) {
		Optional<Interviewer> interviewer = interviewerService.findById(id);
		if (interviewer.isPresent()) {
			return ResponseEntity.ok(interviewer.get());
		}
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "")
	public ResponseEntity<Interviewer> save(@Valid @RequestBody Interviewer interviewer) {
		if (interviewer.getId() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(interviewerService.save(interviewer));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Interviewer> update(@PathVariable Long id, @Valid @RequestBody Interviewer interviewer) {
		if (!id.equals(interviewer.getId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		if (!interviewerService.findById(id).isPresent()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(interviewerService.save(interviewer));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		interviewerService.delete(id);
		return ResponseEntity.ok().build();
	}
}
