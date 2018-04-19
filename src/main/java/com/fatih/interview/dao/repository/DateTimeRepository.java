package com.fatih.interview.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatih.interview.dao.entity.DateTime;
import com.fatih.interview.dao.entity.DateTimeId;

@Repository
public interface DateTimeRepository extends JpaRepository<DateTime, DateTimeId> {

	// List<DateTime> findByDateTimeId(DateTimeId dateTimeId);

	// List<DateTime> findByDateAndTimeSlot(String date, String time);

}
