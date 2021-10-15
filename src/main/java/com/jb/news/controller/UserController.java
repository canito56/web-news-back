package com.jb.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jb.news.model.Message;
import com.jb.news.model.User;
import com.jb.news.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService serviceUser;
  
	@PostMapping("/signin")
	public ResponseEntity<User> getUser(@RequestBody User u) {
		User us = serviceUser.getUser(u.getSuser());
		if (us == null)
			return new ResponseEntity(new Message("User does not exist"), HttpStatus.BAD_REQUEST);
		if (!us.getPassword().equals(serviceUser.getSecurePassword(u.getPassword())))
			return new ResponseEntity(new Message("Invalid password"), HttpStatus.BAD_REQUEST); 
		return new ResponseEntity<User>(us, HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> addUser(@RequestBody User u) {
		User us = serviceUser.getUser(u.getSuser());
		if (us != null)
			return new ResponseEntity<>(new Message("User already exists"), HttpStatus.BAD_REQUEST);
		u.setPassword(serviceUser.getSecurePassword(u.getPassword()));
		serviceUser.save(u);
		return new ResponseEntity<>(new Message("User registered, enter with new user"), HttpStatus.OK);
	}

	@PutMapping("/chgpwd/{user}/{psold}/{psnew1}/{psnew2}")
	public ResponseEntity<?> chgPassword(@PathVariable(value="user") String user, 
										 @PathVariable(value="psold") String psold, 
										 @PathVariable(value="psnew1") String psnew1, 
										 @PathVariable(value="psnew2") String psnew2) {	
		User u = serviceUser.getUser(user);
		String pwdold = serviceUser.getSecurePassword(psold);
		String pwdnew1 = serviceUser.getSecurePassword(psnew1);
		String pwdnew2 = serviceUser.getSecurePassword(psnew2);
		if (u != null) {	
			if (u.getPassword().equals(pwdold)) {
				if (!pwdold.equals(pwdnew1)) {
					if (pwdnew1.equals(pwdnew2)) {
						u.setPassword(pwdnew1);
						serviceUser.save(u);
						return new ResponseEntity<>(new Message("Signin with your new password"), HttpStatus.OK);						
					} else {
						return new ResponseEntity<>(new Message("New passwords are different"), HttpStatus.BAD_REQUEST);
					}					
				} else {
					return new ResponseEntity<>(new Message("the new password must be different from the current one"), HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<>(new Message("Invalid password"), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(new Message("User not found"), HttpStatus.BAD_REQUEST);		
		} 
	}

}
