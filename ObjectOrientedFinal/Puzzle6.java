

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Puzzle6 extends JFrame {

    private String answer;
    private JTextField guessField;
    private JLabel feedbackLabel;
    private JPanel guessPanel;
    private int guessCount;
    private JTextField[][] guessCells; //2d array for the field
    private Set<String> validWords;

    public Puzzle6(String answer, int x, int y) {
        loadValidWords();
        Image img = new ImageIcon(new File("Cappzle.jpg").getAbsolutePath()).getImage();
        this.setLocation(x,y);
        setIconImage(img);
        this.answer = answer.toLowerCase();
        this.guessCount = 0;
        setTitle("Wordle Game");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        startPuzzle();
        setVisible(true);
    }

    private void loadValidWords() {
        validWords = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("words_alpha.txt").getAbsolutePath()));
            String line;
            while ((line = br.readLine()) != null) {
                validWords.add(line.trim().toLowerCase());
            }
            br.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading word list: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startPuzzle() {
        JLabel instructionLabel = new JLabel("Guess the word:");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(instructionLabel);

        guessField = new JTextField(10);
        guessField.setMaximumSize(new Dimension(Integer.MAX_VALUE, guessField.getPreferredSize().height));
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(guessField);

        JButton submitButton = new JButton("Submit Guess");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(submitButton);

        guessPanel = new JPanel(new GridLayout(6, answer.length(), 5, 5));
        guessPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(guessPanel);

        feedbackLabel = new JLabel(" ");
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        feedbackLabel.setFont(new Font("Serif", Font.BOLD, 14));
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(feedbackLabel);

        guessCells = new JTextField[6][answer.length()];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < answer.length(); j++) {
                guessCells[i][j] = new JTextField();
                guessCells[i][j].setEditable(false);
                guessCells[i][j].setHorizontalAlignment(JTextField.CENTER);
                guessCells[i][j].setBackground(Color.LIGHT_GRAY);
                guessPanel.add(guessCells[i][j]);
            }
        }

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private void checkGuess() {
        String guess = guessField.getText().trim().toLowerCase();
        if (guess.length() != answer.length()) {
            feedbackLabel.setText("Guess must be " + answer.length() + " letters long.");
            feedbackLabel.setForeground(Color.RED);
            return;
        }

        if (!validWords.contains(guess)) {
            feedbackLabel.setText("Invalid word. Try again.");
            feedbackLabel.setForeground(Color.RED);
            return;
        }

        if (guessCount < 6) {
            displayGuess(guess);
            guessCount++;
            if (guess.equals(answer)) {
                feedbackLabel.setText("Correct! The answer is " + answer);
                feedbackLabel.setForeground(Color.GREEN);
            } else if (guessCount == 6) {
                feedbackLabel.setText("Game over! Try again?");
                feedbackLabel.setForeground(Color.RED);
            } else {
                feedbackLabel.setText("Incorrect. Try again!");
                feedbackLabel.setForeground(Color.RED);
            }
        }
    }

    private void displayGuess(String guess) {
        boolean[] correct = new boolean[answer.length()]; //marks all correct positions
        int[] amtPresent = new int[26];

        // marks every instance of the letter in an array 0-25, if the letter is present, amtPresent increments
        //telling that there is one or more instances of this letter in the riddle answer
        for (int i = 0; i < answer.length(); i++) {
            amtPresent[answer.charAt(i) - 'a']++;
        }

        // turns all correct positions to green - 'a' refers to ascii values
        for (int i = 0; i < answer.length(); i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                correct[i] = true;
                guessCells[guessCount][i].setBackground(Color.GREEN);
                amtPresent[answer.charAt(i) - 'a']--;
            } else {
                guessCells[guessCount][i].setBackground(Color.GRAY);
            }
            guessCells[guessCount][i].setText(String.valueOf(guess.charAt(i)).toUpperCase()); //displays the string in field
        }

        //turns all characters that still has a value above 0 in amt to yellow
        for (int i = 0; i < answer.length(); i++) {
            if (!correct[i] && amtPresent[guess.charAt(i) - 'a'] > 0) {
                guessCells[guessCount][i].setBackground(Color.YELLOW);
                amtPresent[guess.charAt(i) - 'a']--;
            }
        }

        guessField.setText("");
    }
}

