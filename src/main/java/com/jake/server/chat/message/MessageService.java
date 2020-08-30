package com.jake.server.chat.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

	@Autowired
	private MessageRepository repository;

	
	public Message save(Message chatMessage) {
		return repository.save(chatMessage);
	}

	public List<Message> getAllMessages(String roomId) {
		return repository.findByRoomId(roomId);
	}

}
