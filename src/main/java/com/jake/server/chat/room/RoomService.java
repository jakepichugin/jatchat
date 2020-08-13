package com.jake.server.chat.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
	@Autowired
	private RoomRepository repository;

	
	public Room save(Room room) {
		return repository.save(room);
	}

	public Iterable<Room> getAllRooms() {
		return repository.findAll();
	}

}


