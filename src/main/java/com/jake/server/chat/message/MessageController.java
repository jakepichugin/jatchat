package com.jake.server.chat.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {
	
	@Autowired
	private MessageService chatMessageService;

	
	@RequestMapping(value = "/rooms/{roomId}/messages", method = RequestMethod.GET)
	@ResponseBody
	public List<Message> getMessages(
			@PathVariable("roomId") String rid) {

		List<Message> messages = chatMessageService.getAllMessages(rid);
		
		return messages;
	}
	
	
	@MessageMapping("/rooms/{roomId}/messages") // /hello
	@SendTo("/topic/rooms/{roomId}/messages") // /topic/greetings
	public Message addMessage(@DestinationVariable("roomId") String roomId, Message message) throws Exception {
		message.setRoomId(roomId);
		chatMessageService.save(message);
//		try (FileOutputStream myFile = new FileOutputStream(CHATROOM_DIR + rid + ".txt", true)) {
//
//			myFile.write(("<p><span style='font-weight:bold; width: 100px'>" + message.getUsername() + "</span>: " + message.getMessage() + "</p>\n").getBytes());
//		} catch (IOException ioe) {
//			System.out.println("Could not write into the file: " + ioe.getMessage());
//		}
		return message;
	}

}
