/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	private int NUM_OF_GUESSES = 8;

    public void run() {
    	
    	canvas.reset(); // every time you start to appear only scaffold
    	
		println("Welcome to Hangman!");
		
		MysteryWord = new HangmanLexicon(); 
    	        int randomWord = rgen.nextInt(0, (MysteryWord.getWordCount() - 1)); 
    	        word = MysteryWord.getWord(randomWord);
    	
		EnigmaWord = NumberOfLetters(); 
		
		canvas.displayWord(EnigmaWord); 
		
		println("The word now looks like this: " + EnigmaWord);
		println("You have " + NUM_OF_GUESSES + " guesses left");
		
		while (NUM_OF_GUESSES != 0) { // until the player loses or wins
			
			String letter = readLine("Your guess: ");
			
			while (true) {
				if(letter.length() > 1) {
					letter = readLine("The Guess is illegal. You can only guess one letter at a time. Try again: ");
				}
				if(letter.length() == 1) break;
			}
			ch = letter.charAt(0);
			
			if(Character.isLowerCase(ch)) {
				ch = Character.toUpperCase(ch);
			}
			
			letterCheck(); 
			
			if (EnigmaWord.equals(word)) {  
				println("You guessed the word: " + word);
				println("You win");
				break;
			}
			if (NUM_OF_GUESSES == 1) {
				println("The word now looks like this: " + EnigmaWord);
				println("You have only one guess left");
			}
				
			if (NUM_OF_GUESSES > 1) {
				println("The word now looks like this: " + EnigmaWord);
				println("You have " + NUM_OF_GUESSES + " guesses left.");
			}
				
			if (NUM_OF_GUESSES == 0) {
					println("You're completely hung.");
					println("The word was:" + word);
					println("You lose.");
			}
		}


			
	}
    
    
	private void letterCheck() {
		//checks to see if the guessed letter is in the word
		if(word.indexOf(ch) == -1) { 
			println("There are no " + ch + "'s in the word");
			NUM_OF_GUESSES--;
			
			canvas.noteIncorrectGuess(ch); // method from HangmanCanvas for drawing a body parts for every wrong guess
			}
		
		if(word.indexOf(ch) != -1) {
			println("The guess is correct.");
		}
		
		//goes through each of the letters in the word and checks if it matches the guessed letter, 
		//if it's a match, updates the hidden word to reveal the position of the guessed letter
		for(int i = 0; i < word.length(); i++) {
			if (ch == word.charAt(i)) { 
				if (i > 0) {
					EnigmaWord = EnigmaWord.substring(0, i) + ch + EnigmaWord.substring(i + 1);  
				} 
				if(i == 0) {
					EnigmaWord = ch + EnigmaWord.substring(1);
				}
				canvas.displayWord(EnigmaWord);
			}
		}
	}

	        // checks the length of the random selected word "word" and returns hyphens instead of its letters
		private String NumberOfLetters() {
			String result = "";
			for(int i = 0; i< word.length(); i++) {
				result = result + "-";
			}
			return result;
		}
		
		// adds another window for scaffold
		public void init() {
			canvas = new HangmanCanvas();
			add(canvas);
			}
		
		
		// inst var
		private String word;   
		private RandomGenerator rgen = RandomGenerator.getInstance();
		private String EnigmaWord; 
		private char ch; //the latest character entered by the user
		
		
		private HangmanCanvas canvas; 
		private HangmanLexicon MysteryWord; 
		

}
