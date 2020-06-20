package com.jake.server.chat.user;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User findByUsername(String username) {
		
		return repository.findByUsername(username);
	}
	
	public User signup(User user) {
		user.setKey(UUID.randomUUID().toString());
		try {
			user = repository.save(user);
			
		} catch (TransactionSystemException e) {
			throw new UserAlreadyExistsException();
		}
		
		user.setPassword(null);
		return user;
	}
}
