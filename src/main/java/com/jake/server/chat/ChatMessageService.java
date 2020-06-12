package com.jake.server.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {

	@Autowired
	private ChatMessageRepository repository;

	
	public ChatMessage save(ChatMessage chatMessage) {
		return repository.save(chatMessage);
	}

	public List<ChatMessage> getAllMessages(String roomId) {
		return repository.findByRoomId(roomId);
	}

}
