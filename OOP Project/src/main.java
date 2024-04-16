import javax.swing.SwingUtilities;

public class main {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
            	bo gui = new bo();
            	gui.setVisible(true);
       	        });
		
//		String filename = "README.txt";
//		doc_scanner file1 = new doc_scanner(filename);
//		List<String> processedWords = file1.getWords();
//
//		// Print the processed words
//		for (String word : processedWords) {
//			System.out.println(word);
//		}
		
	}

}


        
    
