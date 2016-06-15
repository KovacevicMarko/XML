package com.websystique.springmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dto.LoginUserDto;
import dto.UserDto;

@RestController
public class UserController {
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<LoginUserDto> signIn(@RequestBody LoginUserDto userDto) {
        return new ResponseEntity<LoginUserDto>(userDto, HttpStatus.OK);
    }
}
