package com.fatih.interview;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fatih.interview.candidate.Candidate;
import com.fatih.interview.common.DateTimeDTO;
import com.fatih.interview.common.PersonDTO;
import com.fatih.interview.common.PersonDateTime;
import com.fatih.interview.time.DateTime;
import com.fatih.interview.time.DateTimeId;

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

		List<PersonDateTime> personDateTime = new ArrayList<>();

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
