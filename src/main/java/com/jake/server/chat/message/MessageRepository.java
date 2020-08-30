package com.jake.server.chat.message;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {

	List<Message> findByRoomId(String roomId);

	// select * from chatmessage WHERE username = 'asdf'
//	List<ChatMessage> findByUsername(String username);

}
