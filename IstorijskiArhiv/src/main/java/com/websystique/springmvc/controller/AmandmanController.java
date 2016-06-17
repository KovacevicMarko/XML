package com.websystique.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dto.SearchDto;
import model.Akt;
import model.Amandman;

@RestController
public class AmandmanController {
	@RequestMapping(value = "/searchAmandman", method = RequestMethod.POST)
    public ResponseEntity<List<Amandman>> searchAmandman(@RequestBody SearchDto search) {
		
		ArrayList<Amandman> amandmans = new ArrayList<Amandman>();
		System.out.println(search.getTag());
		System.out.println(search.getSadrzaj());
		return new ResponseEntity<List<Amandman>>(amandmans,HttpStatus.OK);
    }
}
