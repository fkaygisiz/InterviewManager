package com.fatih.interview.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

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
import com.fatih.interview.dao.entity.DateTime;
import com.fatih.interview.dao.entity.Interviewer;
import com.fatih.interview.dao.entity.Person;
import com.fatih.interview.dao.entity.PersonDateTime;
import com.fatih.interview.dao.entity.PersonDateTimeId;
import com.fatih.interview.dto.DateTimeInputWithPersonDTO;
import com.fatih.interview.dto.PersonDTO;

@RestController
@RequestMapping("/persons")
public class PersonController extends BaseController {

	@GetMapping("")
	public ResponseEntity<List<PersonDTO>> findAll() {
		List<Person> allCandidates = personService.findAll();

		return ResponseEntity.ok(modelMapper.map(allCandidates, personListType));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
		Optional<Person> person = personService.findById(id);
		if (person.isPresent()) {
			return ResponseEntity.ok(modelMapper.map(person.get(), PersonDTO.class));
		}
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/canditates")
	public ResponseEntity<PersonDTO> saveCandidate(@Valid @RequestBody Candidate candidate) {
		return savePerson(candidate);
	}

	@PostMapping(value = "/interviewers")
	public ResponseEntity<PersonDTO> saveInterviewer(@Valid @RequestBody Interviewer interviewer) {
		return savePerson(interviewer);
	}

	private ResponseEntity<PersonDTO> savePerson(Person person) {
		if (person.getId() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(modelMapper.map(personService.save(person), PersonDTO.class));
	}

	@PutMapping(value = "/canditates/{id}")
	public ResponseEntity<PersonDTO> updateCandidate(@PathVariable Long id, @Valid @RequestBody Candidate candidate) {
		return updatePerson(id, candidate);
	}

	@PutMapping(value = "/interviewers/{id}")
	public ResponseEntity<PersonDTO> updateInterviewers(@PathVariable Long id,
			@Valid @RequestBody Candidate candidate) {
		return updatePerson(id, candidate);
	}

	private ResponseEntity<PersonDTO> updatePerson(Long id, Person person) {
		if (!id.equals(person.getId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		if (!personService.findById(id).isPresent()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(personService.save(person), PersonDTO.class));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		personService.delete(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping("")
	public ResponseEntity<String> setAvailableDateTimeForPerson(
			@Valid @RequestBody DateTimeInputWithPersonDTO dateTimeInputDTO) {

		Optional<Person> personFromDb = personService.findById(dateTimeInputDTO.getPersonId());
		if (!personFromDb.isPresent()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Person id is not found.");
		}
		Person person = personFromDb.get();
		List<DateTime> savedDateTimes = dateTimeService.extractAndSaveDateTimes(dateTimeInputDTO);

		Set<PersonDateTime> personDateTimes = new HashSet<>();
		savedDateTimes.forEach(e -> personDateTimes.add(new PersonDateTime(new PersonDateTimeId(e, person), false)));

		personDateTimes.stream().forEach(e -> {
			System.out.println(e.getPersonDateTimeId().getDateTime().getDateTimeId().getTimeSlot());
			person.getPersonDateTimes().remove(e);
		});
		personService.save(person);

		personDateTimes.stream().forEach(e -> {
			System.out.println(e.getPersonDateTimeId().getDateTime().getDateTimeId().getTimeSlot());
			person.getPersonDateTimes().add(e);
		});
		personService.save(person);
		System.out.println(dateTimeInputDTO);
		return ResponseEntity.ok().build();
	}

}
