package com.jake.server.chat.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@RequestMapping(value = "/rooms", method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Room> getMyRooms() {
		
		return roomService.getAllRooms();
	}
	
	@RequestMapping(value = "/rooms", method = RequestMethod.POST)
	@ResponseBody
	public Room addRoom(@RequestBody Room room) {
		
		return roomService.save(room);
	}
}
