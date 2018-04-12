package com.fatih.interview.time;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateTimeRepository extends CrudRepository<DateTime, DateTimeId>  {

	List<DateTime> findByDateTimeId(DateTimeId dateTimeId);

	List<DateTime> findByDateTimeIdDateAndDateTimeIdTimeSlot(String date, String time);

}
