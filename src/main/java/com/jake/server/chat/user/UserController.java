package com.jake.server.chat.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public User login(User user) {
		User userFromDatabase = userService.findByUsername(user.getUsername());
		if (userFromDatabase == null) {
			return null;
		}
		
		if (!userFromDatabase.getPassword().equals(user.getPassword())) {
			return null;
		}
		// if your get to here the login is successful
		
//		user.setKey(UUID.randomUUID().toString());
//		user.setId(123L);
		return userFromDatabase;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public User signup(User user) {
		User savedUser = userService.signup(user);
		return savedUser;
	}
	
}
  