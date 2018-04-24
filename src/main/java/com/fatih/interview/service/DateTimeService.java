package com.fatih.interview.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatih.interview.dao.entity.DateTime;
import com.fatih.interview.dao.entity.DateTimeId;
import com.fatih.interview.dao.repository.DateTimeRepository;
import com.fatih.interview.dto.DateTimeInputDTO;

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

	public List<DateTime> findAll(Set<DateTimeId> dateTimeIds) {
		return dateTimeRepository.findAllById(dateTimeIds);
	}

	public List<DateTime> extractAndSaveDateTimes(DateTimeInputDTO dateTimeInputDTO) {
		List<String> timeSlots = extractTimeSlotIntervals(dateTimeInputDTO.getStartTime(),
				dateTimeInputDTO.getEndTime());
		Set<DateTime> dateTimes = timeSlots.stream().map(e -> new DateTime(dateTimeInputDTO.getDate(), e))
				.collect(Collectors.toSet());

		return this.saveAll(dateTimes);
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

	/*
	 * select i.date, i.time_slot from date_intervals i inner join person_date_time
	 * t on t.date_time_date = i.date and t.date_time_time_slot=i.time_slot where
	 * t.arranged='FALSE' group by i.date, i.time_slot having count(t.PERSON_ID) =3
	 */
	public List<DateTime> getAvailableDateTime(List<Long> ids) {
		// List<DateTime>
		// byPersonDateTimes_ArrangedAndPersonDateTimes_PersonDateTimeId_Person_IdIn =
		// dateTimeRepository.getByPersonDateTimes_ArrangedAndPersonDateTimes_PersonDateTimeId_Person_IdIn(false,ids);

		// List<Person> persons = ids.stream().map(e-> {Person p = new
		// Person();p.setId(e);return p;}).collect(Collectors.toList());
		return dateTimeRepository.getDateTimeContainsAll(ids, Long.valueOf(ids.size()));
		// return
		// byPersonDateTimes_ArrangedAndPersonDateTimes_PersonDateTimeId_Person_IdIn;
	}
}
