package org.maia.hroauth.controller;

import org.maia.hroauth.domain.User;
import org.maia.hroauth.servives.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserServices services;

	
	@GetMapping("/search")
	public ResponseEntity<User>findByEmail(@RequestParam String email){		
		try {
			User user = services.findByEmail(email);
			return ResponseEntity.ok().body(user);		
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}		
	}
	
	
}
