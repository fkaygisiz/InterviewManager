package com.fatih.interview.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatih.interview.dao.entity.DateTime;
import com.fatih.interview.dao.entity.DateTimeId;
import com.fatih.interview.dao.repository.DateTimeRepository;

@Service
public class DateTimeService {

	@Autowired
	private DateTimeRepository dateTimeRepository;

	public List<DateTime> findAll() {
		return StreamSupport.stream(dateTimeRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public Optional<DateTime> findByDateAndTimeSlot(String date, String time) {
		return dateTimeRepository.findById(new DateTimeId(date, time));
	}
	
	public List<DateTime> saveAll(Set<DateTime> dateTimes) {
		return dateTimeRepository.saveAll(dateTimes);
	}

	public List<DateTime> findAll(Set<DateTimeId> dateTimeIds){
		return dateTimeRepository.findAllById(dateTimeIds);
	}
}
