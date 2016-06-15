package com.websystique.springmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.LoginUserDto;
import dto.SearchDto;
import model.Akt;
import model.Amandman;



@RestController
public class AktController {
	
	@RequestMapping(value = "/searchAkt", method = RequestMethod.POST)
    public ResponseEntity<List<Akt>> searchAkt(@RequestBody SearchDto search) {
		
		ArrayList<Akt> akts = new ArrayList<Akt>();
		System.out.println(search.getTag());
		System.out.println(search.getSadrzaj());
		return new ResponseEntity<List<Akt>>(akts,HttpStatus.OK);
    }
}
