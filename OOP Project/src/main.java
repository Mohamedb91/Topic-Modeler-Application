import java.util.List;

public class main {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
            	TopicModelerGUI gui = new TopicModelerGUI();
            	gui.setVisible(true);
       	        });
		
		String filename = "README.txt";
		doc_scanner file1 = new doc_scanner(filename);
		List<String> processedWords = file1.getWords();

		// Print the processed words
		for (String word : processedWords) {
			System.out.println(word);
		}
		
	}

}


        
    
