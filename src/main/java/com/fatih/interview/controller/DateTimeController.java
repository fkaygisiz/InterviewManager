package com.fatih.interview.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.fatih.interview.dto.DateTimeInputDTO;
import com.fatih.interview.dto.DateTimeInputWithInterviewersDTO;
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

	@PostMapping("/save-meeting")
	public ResponseEntity<String> setMeeting(@Valid @RequestBody DateTimeInputWithInterviewersDTO dateTimeInputDTO) {
		dateTimeInputDTO.getInterviewIds().add(dateTimeInputDTO.getPersonId());
		List<Person> persons = personService.findAllById(dateTimeInputDTO.getInterviewIds());
		if (persons.size() != dateTimeInputDTO.getInterviewIds().size()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Some users dont exist.");
		}
		List<String> timeSlots = extractTimeSlotIntervals(dateTimeInputDTO.getStartTime(),
				dateTimeInputDTO.getEndTime());
		Set<DateTime> dateTimes = timeSlots.stream().map(e -> new DateTime(dateTimeInputDTO.getDate(), e))
				.collect(Collectors.toSet());
		List<DateTime> savedDateTimes = dateTimeService.saveAll(dateTimes);

		List<Person> findArrangedPersonByDateAndTime = personService.findArrangedPersonByDateAndTime(
				dateTimeInputDTO.getInterviewIds(),
				savedDateTimes.stream().map(e -> e.getDateTimeId()).collect(Collectors.toList()));
		findArrangedPersonByDateAndTime.forEach(System.out::println);
		
		if(findArrangedPersonByDateAndTime.size() > 0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Some users have conflicting meetings. " + findArrangedPersonByDateAndTime.stream().map(e->String.valueOf(e.getId())).collect(Collectors.joining(",")));
		}
		
		
		return null;

	}

	@PostMapping("")
	public ResponseEntity<String> setAvailableDateTimeForPerson(@Valid @RequestBody DateTimeInputDTO dateTimeInputDTO) {

		Optional<Person> personFromDb = personService.findById(dateTimeInputDTO.getPersonId());
		if (!personFromDb.isPresent()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Person id is not found.");
		}
		Person person = personFromDb.get();
		List<String> timeSlots = extractTimeSlotIntervals(dateTimeInputDTO.getStartTime(),
				dateTimeInputDTO.getEndTime());
		Set<DateTime> dateTimes = timeSlots.stream().map(e -> new DateTime(dateTimeInputDTO.getDate(), e))
				.collect(Collectors.toSet());
		// dateTimeService.findAll(dateTimes)

		Iterable<DateTime> savedDateTimes = dateTimeService.saveAll(dateTimes);

		Set<PersonDateTime> personDateTimes = new HashSet<>();
		savedDateTimes.forEach(e -> personDateTimes.add(new PersonDateTime(new PersonDateTimeId(e, person), false)));
		// dateTimes.forEach(e -> personDateTimes.add(new PersonDateTime(new
		// PersonDateTimeId(e, person), false)));

		/*
		 * timeSlots.stream() .map(e -> new PersonDateTime(new PersonDateTimeId(new
		 * DateTime(dateTimeInputDTO.getDate(), e), person), false))
		 * .collect(Collectors.toSet());
		 */

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

	private List<String> extractTimeSlotIntervals(String startTime, String endTime) {
		List<String> timeSlots = new ArrayList<>();
		long startTimeInMins = convertToMinutes(startTime);
		long endTimeInMins = convertToMinutes(endTime);

		for (long i = startTimeInMins; i < endTimeInMins; i += 30) {
			timeSlots.add(formatMinutes(i));
		}

		return timeSlots;
	}

	private String formatMinutes(long timeInMins) {
		long hours = TimeUnit.MINUTES.toHours(timeInMins);
		long remainMinute = timeInMins - TimeUnit.HOURS.toMinutes(hours);
		return String.format("%02d", hours) + ":" + String.format("%02d", remainMinute);
	}

	private long convertToMinutes(String startTime) {
		String[] startTimeSplitted = startTime.split(":");
		return Long.valueOf(startTimeSplitted[0]) * 60 + Long.valueOf(startTimeSplitted[1]);
	}
}
