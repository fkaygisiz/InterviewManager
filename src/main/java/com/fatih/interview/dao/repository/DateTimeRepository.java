package com.fatih.interview.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fatih.interview.dao.entity.DateTime;
import com.fatih.interview.dao.entity.DateTimeId;

@Repository
public interface DateTimeRepository extends JpaRepository<DateTime, DateTimeId> {

	/*
	 * List<DateTime>
	 * getByPersonDateTimes_ArrangedAndPersonDateTimes_PersonDateTimeId_Person_IdIn(
	 * boolean b, List<Long> ids); String str =
	 * "select i.date, i.time_slot, count(t.PERSON_ID)\r\n" +
	 * "from date_intervals i inner join person_date_time t\r\n" +
	 * "on t.date_time_date = i.date and t.date_time_time_slot=i.time_slot\r\n" +
	 * "where t.arranged='FALSE'\r\n" +
	 * "group by i.date, i.time_slot having count(t.PERSON_ID) =3";
	 */

	@Query("select i from DateTime i join i.personDateTimes t\r\n"
			+ "where t.arranged='FALSE' AND t.personDateTimeId.person.id in (:persons) group by i.dateTimeId.date, i.dateTimeId.timeSlot "
			+ "having count(t.personDateTimeId.person.id) = :personCount ")
	List<DateTime> getDateTimeContainsAll(@Param(value = "persons") List<Long> persons,
			@Param(value = "personCount") Long personCount);

	// List<DateTime> findByDateTimeId(DateTimeId dateTimeId);

	// List<DateTime> findByDateAndTimeSlot(String date, String time);

}
