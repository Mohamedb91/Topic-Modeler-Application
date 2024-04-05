import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class TopicModelerGUI extends JFrame {
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
        //compareButton.addActionListener(new CompareButtonListener());
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

    /*// Action listener for "Compare" button
    private class CompareButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (selectedFile1 == null || selectedFile2 == null) {
                JOptionPane.showMessageDialog(null, "Please select both files.");
                return;
            }

            // Read contents of selected files
            String text1 = readFile(selectedFile1);
            String text2 = readFile(selectedFile2);

            // Calculate similarity
            double similarity = calculateSimilarity(text1, text2);

            // Display similarity
            JOptionPane.showMessageDialog(null, "Similarity: " + similarity);
        }
    } */

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

}
