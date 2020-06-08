package com.jake.server.projects;
//
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Scanner;

public class ReadingLinesInDocuments {

	public String find(String searchWord, String text) {
//		
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Enter the word to find (press enter when done): ");
//		String wordToFind = scanner.nextLine();
//		
//		Scanner textScanner = new Scanner(System.in);
//		System.out.println("Enter the text file path (press enter when done): ");
//		String  textToFindIn= scanner.nextLine();
//		
//		
//
//		try (FileInputStream myFile = new FileInputStream(textToFindIn);
//				BufferedInputStream buff = new BufferedInputStream(myFile);
//				FileOutputStream myOutFile = new FileOutputStream("C:\\Users\\Jake\\Desktop\\result.html");) {
//				
//			byte[] byteValue = buff.readAllBytes();
//			String fileContent = new String(byteValue);
//			System.out.println(fileContent);
//			String findingWord = wordToFind;
//			
			String output = "There are " + countHowManyWords(text) + " words in the text." +"<i> <h1> we found '" + searchWord + "' " + countSubStrings(text, searchWord) 
					+ " times in:</h1>" + "<h3>" + text + "</h3>" + "</i>";
			return output;
//			myOutFile.write(output.getBytes());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public static int countSubStrings(String sourceString, String substring) {
		sourceString = sourceString.toLowerCase();
		substring = substring.toLowerCase();
		int loopCounter = 0;
		int i = sourceString.indexOf(substring);
		while (i != -1) {
			i = sourceString.indexOf(substring, i + 1);
			loopCounter++;
		}
		return loopCounter;

	}
	
	public int countHowManyWords(String text) {
		int numOfWords = text.split("[ \\t\\n\\-]").length; //text.split("[\\s\\-]").length; //stores number of words
		return numOfWords;
	}
}