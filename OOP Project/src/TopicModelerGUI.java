import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;


public class TopicModelerGUI extends JFrame {
	
	private File Stopwords;
	private List<String> words;
	private List<String> stopwords;
	int score = 0;
	
    private JPanel homeScreen, mainScreen, settingsScreen;
    private JButton beginButton, fileButton1, fileButton2, compareButton, resetButton, homeButton, settingsButton, backButton;
    private JLabel descriptionLabel, label1, label2, settingsLabel;
    private File selectedFile1, selectedFile2;
    private JSlider slider;

    public TopicModelerGUI() {
        setTitle("Topic Modeler");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Create home screen 
        homeScreen = new JPanel();
        homeScreen.setLayout(new BorderLayout());
        descriptionLabel = new JLabel("Welcome to the Topic Modeler program. This program compares the similarity between two text files.");
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        beginButton = new JButton("Begin");
        beginButton.addActionListener(new BeginButtonListener());
        homeScreen.add(descriptionLabel, BorderLayout.CENTER);
        homeScreen.add(beginButton, BorderLayout.SOUTH);

        //Create main screen
        mainScreen = new JPanel();
        mainScreen.setLayout(new BorderLayout());
   

        // Initialize GUI components for main screen
        fileButton1 = new JButton("Choose File 1");
        fileButton1.addActionListener(new FileButtonListener1());
        fileButton2 = new JButton("Choose File 2");
        fileButton2.addActionListener(new FileButtonListener2());
        compareButton = new JButton("Compare");
        compareButton.addActionListener(new CompareButtonListener());
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetButtonListener());
        homeButton = new JButton("Home");
        homeButton.addActionListener(new HomeButtonListener());
        settingsButton = new JButton("Settings");
        settingsButton.addActionListener(new SettingsButtonListener());
        label1 = new JLabel("File 1: ");
        label2 = new JLabel("File 2: ");
        //Edit text size
        Font currentFont = label1.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() + 4f);
        label1.setFont(newFont);
        label2.setFont(newFont);
        
        //Create settings screen 
        settingsScreen = new JPanel();
        settingsScreen.setLayout(new BorderLayout());
        settingsLabel = new JLabel("Welcome to Settings");
        settingsLabel.setHorizontalAlignment(JLabel.CENTER);
        //Create slider for settings
        slider = new JSlider(0,25,10);
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(1);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(5);
        slider.setPaintLabels(true);
        backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonListener());
        settingsScreen.add(settingsLabel, BorderLayout.NORTH);
        settingsScreen.add(slider, BorderLayout.CENTER);
        settingsScreen.add(backButton, BorderLayout.SOUTH);
        
        

        JPanel filePanel = new JPanel();
        filePanel.setLayout(new GridLayout(4, 2));
        filePanel.add(label1);
        filePanel.add(fileButton1);
        filePanel.add(label2);
        filePanel.add(fileButton2);
        filePanel.add(compareButton);
        filePanel.add(resetButton);
        filePanel.add(homeButton); 
        filePanel.add(settingsButton);

        mainScreen.add(filePanel, BorderLayout.CENTER);

        // Initially display home screen
        getContentPane().add(homeScreen, BorderLayout.CENTER);
    }

    // Action listener for "Begin" button to switch to main panel
    private class BeginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            getContentPane().removeAll(); // Remove current content
            getContentPane().add(mainScreen, BorderLayout.CENTER); // Add main panel
            revalidate(); // Refresh frame
            repaint();
        }
    }
    

    // Action listener for "Choose File 1" button
    private class FileButtonListener1 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile1 = fileChooser.getSelectedFile();
                label1.setText("File 1: " + selectedFile1.getName());
                
            }
        }
    }

    // Action listener for "Choose File 2" button
    private class FileButtonListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile2 = fileChooser.getSelectedFile();
                label2.setText("File 2: " + selectedFile2.getName());
            }
        }
    }

    // Action listener for "Compare" button
    private class CompareButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (selectedFile1 == null || selectedFile2 == null) {
                JOptionPane.showMessageDialog(null, "Please select both files.");
                return;
            }

            // Call algorithm to calculate the score
            int score = algorithm(selectedFile1.getAbsolutePath(), selectedFile2.getAbsolutePath());
            System.out.println("Score: " + score);
            
            JOptionPane.showMessageDialog(null, "Similarity Score: " + score);
            score = 0;
        }
    } 

    // Action listener for "Reset" button
    private class ResetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            selectedFile1 = null;
            selectedFile2 = null;
            label1.setText("File 1: ");
            label2.setText("File 2: ");
        }
    }

    // Action listener for "Home" button
    private class HomeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            getContentPane().removeAll(); // Remove current content
            getContentPane().add(homeScreen, BorderLayout.CENTER); // Add home panel
            revalidate(); // Refresh frame
            repaint();
        }
    }
    
    // Action listener for "Settings" button
    private class SettingsButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            getContentPane().removeAll(); // Remove current content
            getContentPane().add(settingsScreen, BorderLayout.CENTER); // Add settings screen
            revalidate(); // Refresh frame
            repaint();
        }
    }
    
     // Action listener for "Back" button
    private class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            getContentPane().removeAll(); // Remove current content
            getContentPane().add(mainScreen, BorderLayout.CENTER); // Add main panel
            revalidate(); // Refresh frame
            repaint();
        }
    }
    
    public int algorithm(String file1, String file2) {
        List<String> processedFile1 = file_processor(file1);
        List<String> processedFile2 = file_processor(file2);
        
        // Compare the processed files
        for (String word1 : processedFile1) {
            for (String word2 : processedFile2) {
                if (word2.equals(word1)) {
                    score += 1;   // add better score calculator..............................................
                }
            }
        }
        
        return score;
    }
    
    public List<String> file_processor(String filename) {
		 File file = new File(filename);
		this.Stopwords = new File("StopWords.txt");
		this.words = new ArrayList<>();
		this.stopwords = new ArrayList<>();
		try {
			Scanner scan = new Scanner(file);
			Scanner scanStopwords = new Scanner(Stopwords);
			while (scanStopwords.hasNextLine()) {
				String stopword = scanStopwords.nextLine().trim().toLowerCase();
				stopwords.add(stopword);
			}
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] lineToWords = line.split("\\s+");
				for(String word : lineToWords) {
					word = word.toLowerCase();
					word = word.replaceAll("[^a-zA-Z0-9]+", "");
					boolean isStopword = false;
					for(String stopword : stopwords) {
						if(word.equals(stopword)) {
							isStopword = true;
							break;
						}
					}
					if(!isStopword) {
						words.add(word);				
					}
				}
			}
			scan.close();
			scanStopwords.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return words;
	} 
    
    public List<String> getWords(){
		return words;
	}
	public File getStopwords() {
		return Stopwords;
	}

}
