package com.jake.server.chat.user;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public User login(User user) {
//			@RequestParam("username") String name,
//			@RequestParam("password") String rid) {
		user.setKey(UUID.randomUUID().toString());
		user.setId(123L);
		return user;
	}

}
