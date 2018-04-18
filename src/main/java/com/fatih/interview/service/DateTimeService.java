package com.fatih.interview.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatih.interview.dao.entity.DateTime;
import com.fatih.interview.dao.repository.DateTimeRepository;

@Service
public class DateTimeService {

	@Autowired
	private DateTimeRepository dateTimeRepository;

	public List<DateTime> findAll() {
		return StreamSupport.stream(dateTimeRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public List<DateTime> findByDateAndTimeSlot(String date, String time) {
		// return dateTimeRepository.findByDateAndTimeSlot(date, time);
		return null;
	}

	public Iterable<DateTime> saveAll(Set<DateTime> dateTimes) {
		return dateTimeRepository.saveAll(dateTimes);
		
	}

}
