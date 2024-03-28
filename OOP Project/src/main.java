import java.util.List;

public class main {

	public static void main(String[] args) {
		
		String filename = "README.txt"; //add the path to the file you want to pick
		doc_scanner doc1 = new doc_scanner(filename);
		List<String> processedWords = doc1.getWords();

		// Print the processed words
		for (String word : processedWords) {
			System.out.println(word);
		}	
		
	}

}
