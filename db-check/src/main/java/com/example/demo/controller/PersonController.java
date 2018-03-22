package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.logging.ApplicationLogger;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;
	@Autowired
	private ApplicationLogger appLogger;
	
	@GetMapping(value="/persons")
	public List<Person> getAllPerson(){
		appLogger.logInfo(this.getClass()+ "called for /persons api endpoint");
		return personService.findAllPerson();
	}
	
}
