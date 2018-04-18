package com.fatih.interview.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
	public ResponseEntity<String> getAvailableDateTime(@Valid @RequestBody DateTimeInputDTO dateTimeInputDTO) {

		Optional<Person> personFromDb = personService.findById(dateTimeInputDTO.getPersonId());
		if (!personFromDb.isPresent()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Person id is not found.");
		}
		Person person = personFromDb.get();
		List<String> timeSlots = getTimeSlotIntervals(dateTimeInputDTO.getStartTime(), dateTimeInputDTO.getEndTime());
		Set<DateTime> dateTimes = timeSlots.stream()
				.map(e -> new DateTime(dateTimeInputDTO.getDate(), e))
				.collect(Collectors.toSet());
		Iterable<DateTime> savedDateTimes = dateTimeService.saveAll(dateTimes);
		
		Set<PersonDateTime> personDateTimes = new HashSet<>();
		savedDateTimes.forEach(e -> personDateTimes.add(new PersonDateTime(new PersonDateTimeId(e, person), false)));
//		dateTimes.forEach(e -> personDateTimes.add(new PersonDateTime(new PersonDateTimeId(e, person), false)));
		
		timeSlots.stream()
				.map(e -> new PersonDateTime(new PersonDateTimeId(new DateTime(dateTimeInputDTO.getDate(), e), person), false))
				.collect(Collectors.toSet());

		personDateTimes.forEach(e-> person.getPersonDateTimes().add(e));
		personService.save(person);
		System.out.println(dateTimeInputDTO);
		return ResponseEntity.ok().build();
	}

	private List<String> getTimeSlotIntervals(String startTime, String endTime) {
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
