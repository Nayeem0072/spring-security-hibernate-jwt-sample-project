package com.syn.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.syn.entities.AuthUser;

@RestController
@RequestMapping("/dip")
public class BillEntryPointController {
	
	@RequestMapping(value = "/fetch", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody
	public boolean fetchBill(@RequestBody AuthUser user) {
		System.out.println(user.toString());
		return true;
	}
}
