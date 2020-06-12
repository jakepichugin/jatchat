package com.jake.server.chat;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {

	List<ChatMessage> findByRoomId(String roomId);

	// select * from chatmessage WHERE username = 'asdf'
//	List<ChatMessage> findByUsername(String username);

}
