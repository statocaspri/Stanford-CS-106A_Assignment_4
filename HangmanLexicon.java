/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.*;

public class HangmanLexicon {


		public HangmanLexicon() {
			
		        //adds the words from the .txt file to the array list
			try {
				BufferedReader WordsForGame = new BufferedReader(new FileReader("HangmanLexicon.txt"));
				
				while(true) {
					String line = WordsForGame.readLine();
					if(line == null) break;
					ListOfWords.add(line);
				}
				WordsForGame.close();
			} catch (IOException ex) {
				throw new ErrorException(ex);
			}
		}



	        /** Returns the word at the specified index. */
		public String getWord(int index) {
			return ListOfWords.get(index);
		}

		/** Returns the number of words in the lexicon. */
		public int getWordCount() {
			return ListOfWords.size();
		}	
		
		
		private ArrayList <String> ListOfWords = new ArrayList <String> ();	
	}
