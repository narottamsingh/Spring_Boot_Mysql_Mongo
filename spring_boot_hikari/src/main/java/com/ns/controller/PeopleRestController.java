package com.ns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ns.entity.People;
import com.ns.repository.PeopleRepository;

@RestController
public class PeopleRestController {

	@Autowired
	private PeopleRepository peopleRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<People> fetch() {
		return peopleRepository.findAll();
	}
}
