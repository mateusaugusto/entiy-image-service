package com.avenuecode.test.avenuecodetest.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PersonController {

	@RequestMapping(method = RequestMethod.GET)
	public String getPeople() {
		return "oi";
	}


}
