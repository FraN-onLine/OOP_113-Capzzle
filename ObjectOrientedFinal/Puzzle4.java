

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Puzzle4 extends Puzzle {

    private int currentRiddleIndex = 0;
    private int totalAnswered = 0;
    boolean[] answered = new boolean[10]; //checks if riddle has been answered
    Random random = new Random();

    private final String[] riddles = { //list of riddles
        "What has to be broken before you can use it?",
        "I'm tall when I'm young, and I'm short when I'm old. What am I?",
        "What has a head, a tail, is brown, and has no legs?",
        "What can run but never walk?, has a head but never weeps, has a bed but never sleeps? ", 
        "What can travel around the world while staying in a corner?",
        "What has many keys but can't open a single lock?",
        "What is full of holes but still holds water?",
        "The more of this there is, the less you see. What is it?",
        "I'm light as a feather, yet the strongest person can't hold me for more than 5 minutes. What am I?",
        "What begins with T, ends with T, and has T in it?"
    };
    private final String[] answers = {//answers on corresponding index
        "egg",
        "candle",
        "penny",
        "river",
        "stamp",
        "piano",
        "sponge",
        "darkness",
        "breath",
        "teapot"
    };

    private JLabel riddleLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JLabel messageLabel;
    private JProgressBar progressBar;
    private JButton hintButton;

    public Puzzle4(String code, int x, int y) {
        this.setLocation(x,y);
        setIconImage(img);
        new JFrame("Riddle Game");
        this.setTitle("Riddle Game");
        this.code = code;
        currentRiddleIndex = random.nextInt(10);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(400, 170
        );
        startPuzzle();
        setVisible(true);
    }

    protected void startPuzzle() {
        riddleLabel = new JLabel(riddles[currentRiddleIndex]);
        add(riddleLabel);

        answerField = new JTextField(10);
        add(answerField);

        submitButton = new JButton("Submit Answer");
        submitButton.setFocusPainted(false);
        add(submitButton);
        hintButton = new JButton("Get a Hint");
        hintButton.setFocusPainted(false);
        add(hintButton);

        messageLabel = new JLabel("Enter your answer and press Submit.");
        add(messageLabel);

        progressBar = new JProgressBar(0, 3);
        progressBar.setForeground(new Color(0xFFDB58));
        progressBar.setValue(0);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(progressBar);
        

        submitButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });

        hintButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                new Puzzle6(answers[currentRiddleIndex], getX(), getY());
            }
        });
    }

    private void checkAnswer() {
        String userAnswer = answerField.getText().trim().toLowerCase();
        if (userAnswer.equalsIgnoreCase(answers[currentRiddleIndex])) {
            messageLabel.setText("Correct! Here's the next one.");
            if (totalAnswered < 2) {
                answered[currentRiddleIndex] = true;
                totalAnswered++;
                progressBar.setValue(totalAnswered);
                while(answered[currentRiddleIndex]){
                currentRiddleIndex = random.nextInt(10);
                }
                riddleLabel.setText(riddles[currentRiddleIndex]);
                answerField.setText("");
            } else {
                progressBar.setValue(3);
                messageLabel.setText("Congratulations!");
                submitButton.setEnabled(false);
                puzzleFinish();
            }
        } else {
            messageLabel.setText("Wrong answer. Try again!");
        }
    }

    protected void puzzleFinish(){
        JOptionPane.showMessageDialog(this, "4th Character of the code is " + code.charAt(3), "code", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();  
    }
}