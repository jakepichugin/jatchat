package com.jake.server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jake.server.chat.ChatMessage;

@Controller
public class ChatersController {
	
	private static String CHATROOM_DIR = "chatrooms/";
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	@ResponseBody
	public String getJoinPage() {
		return "<h1>Chatter</h1>" + "<div style='border-top: 1px solid #eee; padding-top: 20px;'>"
				+ "<h2>Join a chat room</h2>"
				+ "<form action='/rooms' method='POST'>"
				+ "Enter name: <input name='name'/><br/>"
				+ "Enter roomID: <input name='roomID'/><br/>"
				+ "<input type='hidden' name='createRoom' value='false'/><br/>"
				+ "<button type='submit'>Join</button>"
				+ "</form>" 
				+ "<hr/>"
				+ "<h2>Create a chat room</h2>"
				+ "<form action='/rooms' method='POST'>"
				+ "Enter name: <input name='name'/><br/>"
				+ "Enter roomID: <input name='roomID'/><br/>"
				+ "<input type='hidden' name='createRoom' value='true'/><br/>"
				+ "<button type='submit'>Create</button>"
				+ "</form>"
				+ "</div>";
	}

	@RequestMapping(value = "/rooms", method = RequestMethod.POST)
	@ResponseBody
	public String enterRoom(
			@RequestParam("name") String name,
			@RequestParam("roomID") String rid, 
			@RequestParam("createRoom") boolean newRoom) {
		
		File chatRoomDir = new File(CHATROOM_DIR);
		if (!chatRoomDir.exists()) {
			chatRoomDir.mkdirs();
		}
		
		File roomFile = new File(CHATROOM_DIR + rid + ".txt");
		if (!roomFile.exists() && !newRoom) {
			return "<h1 style='color:red;'>The room '" + rid + "' not found</h1>" + getJoinPage();
		}
		if (roomFile.exists() && newRoom) {
			return "<h1 style='color:red;'>The room '" + rid + "' already exists</h1>" + getJoinPage();
		}
		try (FileOutputStream myFile = new FileOutputStream(CHATROOM_DIR + rid + ".txt", true)) {

//			myFile.write(theDate.getBytes());
		} catch (IOException ioe) {
			System.out.println("Could not write into the file: " + ioe.getMessage());
		}
		String html = buildRoomHtml(name, rid);
		html = html.replaceAll("REPLACEME", getMessages(rid)); ///<p>no messages</p>
		return html; 
	}
	
	private String buildRoomHtml(String name, String rid) {
		return "<html>\n" + 
				"<head>\n" +
				"    <link href=\"/webjars/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n" + 
				"    <link href=\"/main.css\" rel=\"stylesheet\">\r\n" + 
				"    <script src=\"/webjars/jquery/jquery.min.js\"></script>\r\n" + 
				"    <script src=\"/webjars/sockjs-client/sockjs.min.js\"></script>\r\n" + 
				"    <script src=\"/webjars/stomp-websocket/stomp.min.js\"></script>" +
				"</head>\n" + 
				"<body>\n" +
				"<h1>" + name + ", welcome to Chat room " + rid + "</h1>" +
				"	<div style='max-height: 300px;overflow-y: scroll;' id='msgContainer'>REPLACEME</div>\n" +
				"	<input type='text' id='message'/> <button onclick='sendMessage()'>Send</button>\n" + 
				"   <input type='hidden' id='roomId' value=" + rid+ "/>" + 
				"   <input type='hidden' id='name' value=" + name+ "/>" + 
				"	<script type='text/javascript' src='chat-room.js'></script>\n" + 
				"</body>\n" + 
				"</html>\n";
	}
	
/*
	@RequestMapping(value = "/rooms/{roomId}/messages", method = RequestMethod.POST)
	@ResponseBody
	public String addMessage(
			@PathVariable("roomId") String rid,
			@RequestParam("message") String message,
			@RequestParam("name") String name) {
		
		try (FileOutputStream myFile = new FileOutputStream(CHATROOM_DIR + rid + ".txt", true)) {

			myFile.write(("<p><span style='font-weight:bold; width: 100px'>" + name + "</span>: " + message + "</p>\n").getBytes());
		} catch (IOException ioe) {
			System.out.println("Could not write into the file: " + ioe.getMessage());
		}
		return getMessages(rid);
	}
*/	
	
	@RequestMapping(value = "/rooms/{roomId}/messages", method = RequestMethod.GET)
	@ResponseBody
	public String getMessages(
			@PathVariable("roomId") String rid) {
		
		try (BufferedInputStream buff = new BufferedInputStream( new FileInputStream(CHATROOM_DIR + rid + ".txt"))) {

			return new String(buff.readAllBytes());
		} catch (IOException ioe) {
			System.out.println("Could not write into the file: " + ioe.getMessage());
		}
		return "Sorry did not find the room";
	}
	
	
	@MessageMapping("/rooms/{roomId}/messages") // /hello
	@SendTo("/topic/rooms/{roomId}/messages") // /topic/greetings
	public ChatMessage greeting(@DestinationVariable("roomId") String rid, ChatMessage message) throws Exception {
		
		try (FileOutputStream myFile = new FileOutputStream(CHATROOM_DIR + rid + ".txt", true)) {

			myFile.write(("<p><span style='font-weight:bold; width: 100px'>" + message.getUsername() + "</span>: " + message.getMessage() + "</p>\n").getBytes());
		} catch (IOException ioe) {
			System.out.println("Could not write into the file: " + ioe.getMessage());
		}
		return message;
	}
	
}
