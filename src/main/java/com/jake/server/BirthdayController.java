package com.jake.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BirthdayController {
	
	@RequestMapping(value = "/birthday", method = RequestMethod.GET)
	@ResponseBody
	
	public String combinPhotoName() {
		return "<h1>Word Finder</h1>" + "<div style='border-top: 1px solid #eee; padding-top: 20px;'>"
				+ "<form action='/birthday' method='POST'>"
				+ "Enter name: <input name='name'/><br/>"
				+  "Enter Image link: <input name='link'/><br/>"
				+  "<button type='submit'>combin</button>"
				+ "</form>" + "</div>";
	}
	
	@RequestMapping(value = "/birthday", method = RequestMethod.POST)
	@ResponseBody
	public String getImageWithName(@RequestParam("name") String name,@RequestParam("link") String link ) {
		return "<h1>Happy brithday</h1>" 
				+ "<div style='border-top: 1px solid #eee; padding-top: 20px;'>"
				+ "	<img src='" + link  + "' style='max-width: 50%;'" + " /> "
				+ " <br/>Happy Birthday " + name
				+ "</div>";
	}
	
	
}
