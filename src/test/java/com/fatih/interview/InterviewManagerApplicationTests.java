package com.fatih.interview;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fatih.interview.dao.entity.Candidate;
import com.fatih.interview.dao.entity.DateTime;
import com.fatih.interview.dao.entity.DateTimeId;
import com.fatih.interview.dao.entity.PersonDateTime;
import com.fatih.interview.dto.DateTimeDTO;
import com.fatih.interview.dto.PersonDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InterviewManagerApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testMapper() {
		Candidate c = new Candidate();
		c.setId(1L);
		c.setLastName("kaygisiz");
		c.setName("fatih");

		DateTime dateTime = new DateTime();
		dateTime.setDateTimeId(new DateTimeId("2018-04-12", "11:30"));

		Set<PersonDateTime> personDateTime = new HashSet<>();

		PersonDateTime e = new PersonDateTime();
		e.setArranged(true);
		e.setPerson(c);
		e.setDateTime(dateTime);
		personDateTime.add(e);

		c.setPersonDateTimes(personDateTime);   
		dateTime.setPersonDateTime(personDateTime);

		ModelMapper modelMapper = new ModelMapper();
		PersonDTO cDTO = modelMapper.map(c, PersonDTO.class);
		System.out.println(cDTO);
		
		DateTimeDTO map = modelMapper.map(dateTime, DateTimeDTO.class);
		System.out.println(map);
	}

}
