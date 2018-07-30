package com.syn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.syn.dao.*;
import com.syn.entities.TestProcTable;

@RestController
@RequestMapping("/rtest")
public class RestTestController {
	@Autowired
	private static final TestProcTableDao procTableDao = new TestProcTableDaoImpl();

	@RequestMapping(value = "/users/{t_id}", method = RequestMethod.GET)
	@ResponseBody
	public List getUser(@PathVariable int t_id) {
		return procTableDao.getAllProcUsers();
	}

	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public boolean createUser(@RequestBody TestProcTable procUser) {
		return procTableDao.addProcUser(procUser);
	}
	
	
//	@RequestMapping("/users")
//	public List getPersonDetail(@RequestParam(value = "id",required = false,
//	defaultValue = "0") Integer id) {
//		return procTableDao.getAllProcUsersJson();
//	}	

}
