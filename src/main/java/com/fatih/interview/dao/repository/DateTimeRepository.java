package com.fatih.interview.dao.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fatih.interview.dao.entity.DateTime;
import com.fatih.interview.dao.entity.DateTimeId;

@Repository
public interface DateTimeRepository extends JpaRepository<DateTime, DateTimeId> {

	@Query("select i from DateTime i join i.personDateTimes t\r\n"
			+ "where t.arranged='FALSE' AND t.personDateTimeId.person.id in (:persons) group by i.dateTimeId.date, i.dateTimeId.timeSlot "
			+ "having count(t.personDateTimeId.person.id) = :personCount ")
	List<DateTime> getDateTimeContainsAllPerson(@Param(value = "persons") List<Long> persons,
			@Param(value = "personCount") int personCount);

	Set<DateTime> findByPersonDateTimes_MeetingName(String meetingName);

}
