

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.HashSet;


public class Puzzle3 extends Puzzle{

    private char endLetter;
    private String lastWord;
    private JTextField inputField;
    private JLabel wordLabel, timerLabel;
    private Timer timer;
    private int wordCount;
    private Random random;

    private HashSet<String> validWords;

    public Puzzle3(String code, int x, int y){
        this.setLocation(x,y);
        this.code = code;
        this.random = new Random();
        this.lastWord = generateRandomWord();
        this.endLetter = generateNewEndLetter();
        this.wordCount = 0;
        setIconImage(img);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 
        setTitle("Word Chain Game");
        setLayout(new FlowLayout());
        setLocationRelativeTo(this);
        setSize(1250,1000);

        wordLabel = new JLabel("The first word is "+lastWord +". Enter a word that ends with '" + endLetter + "':");
        add(wordLabel);

        inputField = new JTextField(20);
        add(inputField);
        inputField.setPreferredSize(new Dimension(200, 30));

        timerLabel = new JLabel("Time remaining: 150");
        add(timerLabel);

        pack();
        setVisible(true);
        startPuzzle();
        loadValidWords();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                timer.stop();
            }
        });
    }

   private void loadValidWords() {
    validWords = new HashSet<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("words_alpha.txt").getAbsolutePath()))) {
        String line;
        while ((line = reader.readLine()) != null) {
            validWords.add(line.trim().toLowerCase());
        }
        System.out.println("Loaded " + validWords.size() + " words.");
    } catch (FileNotFoundException e) {
        System.err.println("The file resources/words_alpha.txt was not found.");
        e.printStackTrace();
    } catch (IOException e) {
        System.err.println("An error occurred while reading the file resources/words_alpha.txt.");
        e.printStackTrace();
    }
}
    
    public void startPuzzle(){
        inputField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String playerWord = inputField.getText().trim();
                if (isValidWord(playerWord)) {
                    lastWord = playerWord;
                    endLetter = generateNewEndLetter(); //
                    wordLabel.setText("Good! Now enter a word starting with '" + lastWord.charAt(lastWord.length() - 1) + "' and ending with '"+endLetter+"':");
                    inputField.setText("");
                    wordCount++;
                    resetTimer();
                    if(wordCount==5){
                        winCondition(true);
                    }
                } else{
                    winCondition(false);
                }
            }
        });
        startTimer();
    }
    private char generateNewEndLetter(){
        char newEndLetter;
        do{
            newEndLetter = (char) (random.nextInt(26)+ 'a');
        } while (newEndLetter==lastWord.charAt(lastWord.length()-1));
        return newEndLetter;
    }
    private String generateRandomWord(){
        String[] words = {"apple", "sandwich", "hamburger", "banana", "lemon", "dog", "elephant", "hat"};
        return words[random.nextInt(words.length)];
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener(){
            int timeLeft = 150;

            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    timerLabel.setText("Time remaining: " + --timeLeft);
                } else {
                    ((Timer) e.getSource()).stop();
                    winCondition(false);
                }
            }
        });
        timer.start();
    }

    private void resetTimer() {
        timer.stop();
        timer.start();
    }

    private void winCondition(boolean hasWon) {
        timer.stop();
        String message = hasWon ? "Congratulations! You've won!" : "Unfortunately, You lost.";
        JOptionPane.showMessageDialog(this, message);
        if(hasWon)
        puzzleFinish();
        else
        this.dispose();
    }

    protected void puzzleFinish(){
        JOptionPane.showMessageDialog(this, "3rd Character of the code is " + code.charAt(2), "code", JOptionPane.INFORMATION_MESSAGE);
        timer.stop();
        this.dispose();  
    }

    private boolean isValidWord(String word) {
        word = word.toLowerCase();
        return word.startsWith(String.valueOf(lastWord.charAt(lastWord.length() - 1))) 
        && word.endsWith(String.valueOf(endLetter))
        && validWords.contains(word);
    }
}