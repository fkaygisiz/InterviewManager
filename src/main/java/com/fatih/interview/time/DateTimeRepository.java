package com.fatih.interview.time;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateTimeRepository extends CrudRepository<DateTime, Long>  {

	//List<DateTime> findByDateTimeId(DateTimeId dateTimeId);

	//List<DateTime> findByDateAndTimeSlot(String date, String time);

}
