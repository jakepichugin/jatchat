package com.jake.server.chat.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="This username is already taken") //409
public class UserAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1326055880977830412L;

}
