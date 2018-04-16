package com.fatih.interview.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatih.interview.dao.entity.DateTime;
import com.fatih.interview.dao.entity.Person;
import com.fatih.interview.dao.repository.PersonRepository;
import com.fatih.interview.dto.DateTimeInputDTO;
import com.fatih.interview.service.DateTimeService;
import com.fatih.interview.service.PersonService;

@RestController
@RequestMapping("/date-times")
public class DateTimeController {

	@Autowired
	private DateTimeService dateTimeService;
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("")
	public ResponseEntity<List<DateTime>> getAvailableDateTime() {
		return ResponseEntity.ok(dateTimeService.findAll());
	}

	@GetMapping("/{date}/{time}")
	public ResponseEntity<List<DateTime>> getDateTime(@PathVariable String date, @PathVariable String time) {
		return ResponseEntity.ok(dateTimeService.findByDateAndTimeSlot(date, time));
	}

	@PostMapping("")
	public void getAvailableDateTime(@Valid @RequestBody DateTimeInputDTO dateTimeInputDTO) {
		
		Optional<Person> findById = personService.findById(dateTimeInputDTO.getPersonId());
		Person p = findById.isPresent() ? findById.get() : null;
		System.out.println(dateTimeInputDTO);
	}
}
