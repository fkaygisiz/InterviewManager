package com.fatih.interview.time;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/date-times")
public class DateTimeController {

	@Autowired
	private DateTimeService dateTimeService;
	
	@GetMapping("")
	public ResponseEntity<List<DateTime>> getAvailableDateTime() {
		return ResponseEntity.ok(dateTimeService.findAll());
	}
	
	@GetMapping("/{date}/{time}")
	public ResponseEntity<List<DateTime>> getAvailableDateTime(@PathVariable String date, @PathVariable String time) {
		return ResponseEntity.ok(dateTimeService.findByDateAndTimeSlot(date, time));
	} 
}
