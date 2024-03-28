import java.util.List;

public class main {

	public static void main(String[] args) {
		
		String filename = "C:/Users/Mohamed/Documents/ahahahaha.txt";
		doc_scanner doc1 = new doc_scanner(filename);
		List<String> processedWords = doc1.getWords();

		// Print the processed words
		for (String word : processedWords) {
			System.out.println(word);
		}	
	}

}
