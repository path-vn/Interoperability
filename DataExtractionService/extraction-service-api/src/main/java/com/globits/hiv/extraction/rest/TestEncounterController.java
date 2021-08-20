package com.globits.hiv.extraction.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/public/test")
public class TestEncounterController {
	@RequestMapping(path ="/encounters",method = RequestMethod.POST)
	public ResponseEntity <String> getQuestionnaireResponse(@RequestBody String body) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("encounters:"+body);
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json;charset=UTF-8");
		return new ResponseEntity<String>("Success",headers,HttpStatus.OK);
	}	
	@RequestMapping(path ="/encounters",method = RequestMethod.GET)
	public ResponseEntity<String> getQuestionnaireResponse() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("encounters");
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json;charset=UTF-8");
		return new ResponseEntity<String>("Success",headers,HttpStatus.OK);
	}	
}
