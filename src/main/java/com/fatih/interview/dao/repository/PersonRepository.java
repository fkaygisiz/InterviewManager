package com.fatih.interview.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatih.interview.dao.entity.DateTimeId;
import com.fatih.interview.dao.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	List<Person> findByName(String title);

	List<Person> findByIdInAndPersonDateTimes_ArrangedAndPersonDateTimes_PersonDateTimeId_DateTime_DateTimeId_In(
			List<Long> personIds, boolean b, List<DateTimeId> personDateTimeId);
}
