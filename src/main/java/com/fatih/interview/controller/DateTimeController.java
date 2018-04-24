package com.fatih.interview.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatih.interview.dao.entity.DateTime;
import com.fatih.interview.dao.entity.Person;
import com.fatih.interview.dao.entity.PersonDateTime;
import com.fatih.interview.dao.entity.PersonDateTimeId;
import com.fatih.interview.dto.DateTimeDTO;
import com.fatih.interview.dto.DateTimeInputWithInterviewersDTO;

@RestController
@RequestMapping("/date-times")
public class DateTimeController extends BaseController {

	@GetMapping("")
	public ResponseEntity<List<DateTime>> getAvailableDateTime() {
		return ResponseEntity.ok(modelMapper.map(dateTimeService.findAll(), dateTimeListType));
	}

	@GetMapping("/{date}/{time}")
	public ResponseEntity<DateTimeDTO> getDateTime(@PathVariable String date, @PathVariable String time) {
		Optional<DateTime> dateTimeFromDB = dateTimeService.findByDateAndTimeSlot(date, time);
		if (dateTimeFromDB.isPresent()) {
			return ResponseEntity.ok(modelMapper.map(dateTimeFromDB.get(), DateTimeDTO.class));
		}
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/save-meeting")
	public ResponseEntity<String> setMeeting(@Valid @RequestBody DateTimeInputWithInterviewersDTO dateTimeInputDTO) {
		List<Person> persons = personService.findAllById(dateTimeInputDTO.getInterviewIds());
		if (persons.size() != dateTimeInputDTO.getInterviewIds().size()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Some users dont exist.");
		}
		List<DateTime> savedDateTimes = dateTimeService.extractAndSaveDateTimes(dateTimeInputDTO);

		List<Person> findArrangedPersonByDateAndTime = personService.findArrangedPersonByDateAndTime(
				dateTimeInputDTO.getInterviewIds(),
				savedDateTimes.stream().map(DateTime::getDateTimeId).collect(Collectors.toList()));
		findArrangedPersonByDateAndTime.forEach(System.out::println);

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

	@GetMapping("/{ids}")
	public ResponseEntity<Object> getAvailableDateTime(@PathVariable ArrayList<Long> ids) {
		List<DateTime> availableDateTimes = dateTimeService.getAvailableDateTime(ids);
		if (availableDateTimes.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No suitable time slot found!");
		}
		return ResponseEntity.ok(modelMapper.map(availableDateTimes, dateTimeListType));

	}

}
