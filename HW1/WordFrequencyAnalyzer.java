package hw1;

import dsUtils.WordReader;

public class WordFrequencyAnalyzer {
	/**********************************************************************************/
	/* You are not allowed to add any fields to this class beyond the one given below */
	/* You may only read in from the file once.  This means you may only use a single */
	/* word reader object.                                                            */
	/**********************************************************************************/
	
	// TODO
	// Your task is to make this class more efficient by using a symbol table to 
	// keep track of all the word counts.
	
	// You will need to a add another field to the class (maybe call it counters)
	// that is a SequentialSearchST.  You will then read through the file
	// once to count up all the words storing the information in the SequentialSearchST.
	// Then when someone asks for the count of word, you only need to look
	// it up in the symbol table instead of reading through the file.
	
	// You have permission to add one more field that is a SequentialSearchST below.
	
	private String filename;
	private SequentialSearchST<String,Integer>counters;
	
	
	/**
	 * Currently, this constructor just stores the file name so that it can open the
	 * file later when someone asks for a word count.  Change this constructor
	 * to instead count ALL the words in the file and store the counts in the 
	 * new SequentialSearchST you are adding to the class.
	 * 
	 * @param filename the name of the file on which to count all word occurrences.
	 */
	public WordFrequencyAnalyzer(String filename) {
		this.filename = filename;
		this.counters = new SequentialSearchST<String,Integer>();
		
		WordReader r = new WordReader(filename);
		
		for (char[] w : r){ // for each word
			String word = String.valueOf(w); //set word string variable
			if (!counters.contains(word)) {//if the word isn't in the symbol table, add it in with value 1
				counters.put(word, 1);
			}
			else {counters.put(word, counters.get(word)+1);} //if the word is in the table, add to the count
			
		}
	}
	
	/**
	 * Returns the number of times a given word appears in the file from which this
	 * object was created.
	 * (Currently, it reads through the entire file to count the number of times
	 * the given word appears.  Modify this to instead use the information stored
	 * in the SequentialSearchST you created and populated in the constructor.
	 * 
	 * @param word the word to count
	 * @return the number of times <code>word</code> appears.
	 */
	public int getCount(char[] w) {
		String word = String.valueOf(w); //set string variable
		if(!counters.contains(word)) {
			return 0;
		}
		int count = counters.get(word); //set count variable by finding the word in symbol table
		return count; //return count
	}
	
	/**
	 * Returns the maximum frequency over all words in the file from which this
	 * object was created.
	 * (Currently, this method is not implemented.  You must implement it.
	 * Make sure to use the SequentialSearchST that already has the word
	 * counts in it.)
	 * 
	 * @return the maximum frequency of any word in the the file.
	 */
	public int maxCount() {
		String max = "";
		counters.put(max, 0);
		int maxCount = 0;
		for (String word : counters.keys()) {
			if(counters.get(word)>counters.get(max)) {
				max = word;
				maxCount = counters.get(max);
			}
		}
		return maxCount;
		
		//throw new RuntimeException("Not implemented.");
	}
}
