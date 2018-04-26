package com.fatih.interview.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatih.interview.dao.entity.DateTime;
import com.fatih.interview.dto.DateTimeDTO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/date-times")
public class DateTimeController extends BaseController {

	@ApiOperation(value = "Gets all date")
	@GetMapping("")
	public ResponseEntity<List<DateTime>> getAvailableDateTime() {
		return ResponseEntity.ok(modelMapper.map(dateTimeService.findAll(), dateTimeListType));
	}

	@ApiOperation(value = "Finds all date of a meeting name. eg: .../meetings/by-name/meeting1")
	@GetMapping("/meetings/by-name/{meetingName}")
	public ResponseEntity<List<DateTimeDTO>> getByMeetingName(@PathVariable String meetingName) {
		Set<DateTime> availableDateTimes = dateTimeService.findByMeetingName(meetingName);
		if (availableDateTimes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(modelMapper.map(availableDateTimes, dateTimeListType));
	}

	@ApiOperation(value = "Finds specific date. Sample request: .../2018-04-26./11:30")
	@GetMapping("/{date}/{time}")
	public ResponseEntity<DateTimeDTO> getDateTime(@PathVariable String date, @PathVariable String time) {
		Optional<DateTime> dateTimeFromDB = dateTimeService.findByDateAndTimeSlot(date, time);
		if (dateTimeFromDB.isPresent()) {
			return ResponseEntity.ok(modelMapper.map(dateTimeFromDB.get(), DateTimeDTO.class));
		}
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Finds available time slots for the given person id list. eg: .../1,2,3")
	@GetMapping("/{ids}")
	public ResponseEntity<Object> getAvailableDateTime(@PathVariable ArrayList<Long> ids) {
		List<DateTime> availableDateTimes = dateTimeService.getAvailableDateTimeByPersonIdList(ids);
		if (availableDateTimes.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No suitable time slot found!");
		}
		return ResponseEntity.ok(modelMapper.map(availableDateTimes, dateTimeListType));

	}

}
