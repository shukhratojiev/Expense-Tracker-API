package com.shukhrat.expensetrackerapi.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shukhrat.expensetrackerapi.entity.User;
import com.shukhrat.expensetrackerapi.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/profile")
	public ResponseEntity<User> get(){
		return new ResponseEntity<User>(userService.read(), HttpStatus.OK);
	}
	
	@PutMapping("/profile")
	public ResponseEntity<User> update(@RequestBody User user){
		User uuser = userService.update(user);
		return new ResponseEntity<User>(uuser, HttpStatus.OK);
	}
	
	@DeleteMapping("/deactivate")
	public ResponseEntity<HttpStatus> delete() {
		userService.delete();
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	
}
