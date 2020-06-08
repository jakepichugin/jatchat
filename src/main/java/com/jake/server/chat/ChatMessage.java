package com.jake.server.chat;

public class ChatMessage {

	private String message;
	private String username;
	
	public ChatMessage() {}
	
	public ChatMessage(String username, String message) {
		this.message = message;
		this.username = username;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
