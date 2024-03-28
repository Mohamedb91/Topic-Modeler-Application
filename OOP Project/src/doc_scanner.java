import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class doc_scanner {
	
	private File file;
	private List<String> words;
	
	public doc_scanner(String filename) {
		this.file = new File(filename);
		this.words = new ArrayList<>();
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] lineToWords = line.split("\\s+");
				for(String word : lineToWords) {
					word = word.toLowerCase();
					word = word.replace("^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", "");
					words.add(word);				
				}
			}
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<String> getWords(){
		return words;
	}
	
}
