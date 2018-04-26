package com.fatih.interview.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.fatih.interview.dto.DateTimeInputWithInterviewersDTO;
import com.fatih.interview.dto.DateTimeInputWithPersonDTO;
import com.fatih.interview.dto.PersonDTO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/persons")
public class PersonController extends BaseController {

	@ApiOperation(value = "Gets all people")
	@GetMapping("")
	public ResponseEntity<List<PersonDTO>> findAll() {
		List<Person> allCandidates = personService.findAll();

		return ResponseEntity.ok(modelMapper.map(allCandidates, personListType));
	}

	@ApiOperation(value = "Finds specific person. eg: .../1")
	@GetMapping("/{id}")
	public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
		Optional<Person> person = personService.findById(id);
		if (person.isPresent()) {
			return ResponseEntity.ok(modelMapper.map(person.get(), PersonDTO.class));
		}
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Saves a candidate")
	@PostMapping(value = "/canditates")
	public ResponseEntity<PersonDTO> saveCandidate(@Valid @RequestBody Candidate candidate) {
		return savePerson(candidate);
	}

	@ApiOperation(value = "Saves an interviewer")
	@PostMapping(value = "/interviewers")
	public ResponseEntity<PersonDTO> saveInterviewer(@Valid @RequestBody Interviewer interviewer) {
		return savePerson(interviewer);
	}

	private ResponseEntity<PersonDTO> savePerson(Person person) {
		if (person.getId() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Person savedPerson = personService.save(person);
		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedPerson, PersonDTO.class));
	}

	@ApiOperation(value = "Updates a candidate")
	@PutMapping(value = "/canditates/{id}")
	public ResponseEntity<PersonDTO> updateCandidate(@PathVariable Long id, @Valid @RequestBody Candidate candidate) {
		return updatePerson(id, candidate);
	}

	@ApiOperation(value = "Updates an interviewer")
	@PutMapping(value = "/interviewers/{id}")
	public ResponseEntity<PersonDTO> updateInterviewers(@PathVariable Long id,
			@Valid @RequestBody Interviewer interviewer) {
		return updatePerson(id, interviewer);
	}

	private ResponseEntity<PersonDTO> updatePerson(Long id, Person person) {
		if (!id.equals(person.getId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		Optional<Person> personFromDB = personService.findById(id);
		if (!personFromDB.isPresent()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		personFromDB.get().getPersonDateTimes().forEach(pdt -> person.getPersonDateTimes().add(pdt));
		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(personService.save(person), PersonDTO.class));
	}

	@ApiOperation(value = "Deletes an interviewer or a candidate")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		personService.delete(id);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Sets available time of an interviewer or a candidate")
	@PostMapping("/set-available-date-time")
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

		personDateTimes.stream().forEach(e -> person.getPersonDateTimes().remove(e));
		personService.save(person);

		personDateTimes.stream().forEach(e -> person.getPersonDateTimes().add(e));
		personService.save(person);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Saves a meeting with interviewers and/or candidates")
	@PostMapping("/save-meeting")
	public ResponseEntity<String> setMeeting(@Valid @RequestBody DateTimeInputWithInterviewersDTO dateTimeInputDTO) {
		List<Person> persons = personService.findAllById(dateTimeInputDTO.getInterviewIds());
		if (persons.size() != dateTimeInputDTO.getInterviewIds().size()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Some users dont exist.");
		}

		List<Person> meetingPersons = personService.findPersonsByMeetingName(dateTimeInputDTO.getMeetingName());
		if (!meetingPersons.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("There is another meeting with the same name.");
		}

		List<DateTime> savedDateTimes = dateTimeService.extractAndSaveDateTimes(dateTimeInputDTO);

		List<Person> findArrangedPersonByDateAndTime = personService.findArrangedPersonByDateAndTime(
				dateTimeInputDTO.getInterviewIds(),
				savedDateTimes.stream().map(DateTime::getDateTimeId).collect(Collectors.toList()));

		if (!findArrangedPersonByDateAndTime.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Some users have conflicting meetings. " + findArrangedPersonByDateAndTime.stream()
							.map(e -> String.valueOf(e.getId())).collect(Collectors.joining(",")));
		}
		persons.forEach(p -> {
			savedDateTimes
					.forEach(d -> p.getPersonDateTimes().remove(new PersonDateTime(new PersonDateTimeId(d, p), false)));
			personService.save(p);

			savedDateTimes.forEach(d -> p.getPersonDateTimes()
					.add(new PersonDateTime(new PersonDateTimeId(d, p), true, dateTimeInputDTO.getMeetingName())));
			personService.save(p);
		});

		return ResponseEntity.ok().build();

	}
}
