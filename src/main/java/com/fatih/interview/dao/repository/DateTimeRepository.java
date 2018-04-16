package com.fatih.interview.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatih.interview.dao.entity.DateTime;

@Repository
public interface DateTimeRepository extends CrudRepository<DateTime, Long> {

	// List<DateTime> findByDateTimeId(DateTimeId dateTimeId);

	// List<DateTime> findByDateAndTimeSlot(String date, String time);

}
