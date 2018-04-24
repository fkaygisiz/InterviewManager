package com.fatih.interview.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import com.fatih.interview.dto.DateTimeDTO;
import com.fatih.interview.dto.PersonDTO;
import com.fatih.interview.service.DateTimeService;
import com.fatih.interview.service.PersonService;

public class BaseController {

	@Autowired
	protected PersonService personService;

	@Autowired
	protected DateTimeService dateTimeService;

	final java.lang.reflect.Type personListType = new TypeToken<List<PersonDTO>>() {
	}.getType();

	final java.lang.reflect.Type dateTimeListType = new TypeToken<List<DateTimeDTO>>() {
	}.getType();

	final ModelMapper modelMapper = new ModelMapper();
}
