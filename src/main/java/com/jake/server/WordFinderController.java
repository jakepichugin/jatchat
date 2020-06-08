package com.jake.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jake.server.projects.ReadingLinesInDocuments;

@Controller
public class WordFinderController {

	@RequestMapping(value = "/word-finder", method = RequestMethod.GET)
	@ResponseBody
	public String getHelloWorldPage() {
		return "<h1>Word Finder</h1>" + "<div style='border-top: 1px solid #eee; padding-top: 20px;'>"
				+ "<form action='/word-finder/search' method='POST'>"
				+ "Search for: <input type='text' name='searchWord'/><br/>"
				+ "Search in text: <textarea name='text'></textarea>" + "<button type='submit'>Search</button>"
				+ "</form>" + "</div>";
	}

	@RequestMapping(value = "/word-finder/search", method = RequestMethod.POST)
	@ResponseBody
	public String getBlah(@RequestParam("searchWord") String searchWord, @RequestParam("text") String text) {

		ReadingLinesInDocuments finder = new ReadingLinesInDocuments();
		
		if (searchWord != null && !searchWord.isEmpty()
				&& text != null && !text.isEmpty()) {
		
			return getHelloWorldPage() + finder.find(searchWord, text);

		} else {
			return getHelloWorldPage() + "please fill in all of the fields" ;
		}
	}
}
