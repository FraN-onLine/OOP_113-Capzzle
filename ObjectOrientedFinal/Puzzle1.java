

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.*;

public class Puzzle1 extends Puzzle implements ActionListener{

    private JPanel panel = new JPanel();
    private Random random = new Random();
    private int number;
    private int firstnum;
    private int storednum;
    private int correct;

    JButton button1 = new JButton("1");
    JButton button2 = new JButton("2");
    JButton button3 = new JButton("3");
    JButton button4 = new JButton("4");
    

    
  public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        int buttonNumber = Integer.parseInt(button.getText());

        if (buttonNumber == number) { //if button number matches random number that lit up
            correct++;
            if (correct == 11) { //if clicked button is correct 11 times in a row
                puzzleFinish();
                setVisible(false);
            } else {
                color();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect", "Whoops", JOptionPane.ERROR_MESSAGE);
            setVisible(false);
        }
    }
    
    Puzzle1(String code,int x, int y){
        this.setLocation(x,y);
        this.code = code;
        startPuzzle();
    }

    protected void startPuzzle(){
    new JFrame();
    setIconImage(img);
    setVisible(true);
    setResizable(false);
    setSize(500,500);
    setTitle("The game of SIMON SAYS");

    panel = new JPanel(new GridLayout(2,2));
    add(panel);
    panel.add(button1);
    panel.add(button2);
    panel.add(button3);
    panel.add(button4);
    
    number = random.nextInt(4) + 1;

     button1.addActionListener(this);
     button2.addActionListener(this);
     button3.addActionListener(this);
     button4.addActionListener(this);
     button1.setBackground(Color.WHITE);
     button2.setBackground(Color.WHITE);
     button3.setBackground(Color.WHITE);
     button4.setBackground(Color.WHITE);

    color();
}

    protected void color(){//function that lights up a random button

        //these conditions prevent a light up and prompts a question instead
        if(correct == 3){
            if(number % 2 == 0){
            number = firstnum;
            JOptionPane.showMessageDialog(this, "Tap the first button shaded" , "nice :)", JOptionPane.INFORMATION_MESSAGE);
            return;
            } else {
            number = storednum;
            JOptionPane.showMessageDialog(this, "Tap the second button shaded" , "nice :)", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
        }
        if(correct == 4){
            number = random.nextInt(4) + 1;
            JOptionPane.showMessageDialog(this, "Tap the number when multiplied by itself then substracted by twice it's square the result would be: " + ((number * number) - (( number * number) * 2)) , "nice :)", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if(correct == 6){
            if(number % 2 == 0){
                number = firstnum;
                JOptionPane.showMessageDialog(this, "Tap the first button shaded" , "nice :)", JOptionPane.INFORMATION_MESSAGE);
                return;
                } else {
                number = storednum;
                JOptionPane.showMessageDialog(this, "Tap the second button shaded" , "nice :)", JOptionPane.INFORMATION_MESSAGE);
                return;
                }
        }

        if(correct == 9){
            number = random.nextInt(4) + 1;
            switch(number){
            case 1:
            JOptionPane.showMessageDialog(null, "Tap the number where x^2 - x = 0" , "nice :)", JOptionPane.INFORMATION_MESSAGE);
            break;
            case 2:
            JOptionPane.showMessageDialog(null, "Tap the number where 4 times it's amount would result to itself cubed but not itself squared" , "nice :)", JOptionPane.INFORMATION_MESSAGE);
            break;
            case 3:
            JOptionPane.showMessageDialog(null, "Tap the amount of times a color flashed before the first prompt" , "nice :)", JOptionPane.INFORMATION_MESSAGE);
            break;
            case 4:
            JOptionPane.showMessageDialog(null, "Tap one more the amount of times a color flashed before the first prompt" , "nice :)", JOptionPane.INFORMATION_MESSAGE);
            break;
            } //pampagulo hehe
            return;
        }

        if(correct == 10){
            number = storednum;
            JOptionPane.showMessageDialog(null, "Tap the last button shaded" , "nice :)", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        number = random.nextInt(4) + 1;
        if(correct == 0){
            firstnum = number;
        }
        if(correct == 1){
            storednum = number;
        }
        if(correct == 8){
            storednum = number;
        }

     Timer timer = new Timer(200, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            switch (number) {
                case 1:
                    button1.setBackground(Color.WHITE);
                    break;
                case 2:
                    button2.setBackground(Color.WHITE);
                    break;
                case 3:
                    button3.setBackground(Color.WHITE);
                    break;
                case 4:
                    button4.setBackground(Color.WHITE);
                    break;
            }
        }
    });
    timer.setRepeats(false);

    switch (number) {
        case 1:
            button1.setBackground(Color.RED);
            break;
        case 2:
            button2.setBackground(Color.RED);
            break;
        case 3:
            button3.setBackground(Color.RED);
            break;
        case 4:
            button4.setBackground(Color.RED);
            break;
    }

    // Start the timer
    timer.start();
    }

    protected void puzzleFinish(){
        int random2 = random.nextInt(5);
        //show message
        switch(random2){
            case 0:
            JOptionPane.showMessageDialog(this, "1st Character of the code is " + code.charAt(0), "code", JOptionPane.INFORMATION_MESSAGE);  
            break;
        
            case 1:
            JOptionPane.showMessageDialog(this, "Ceasar Cipher Shift 2 >> 1st Character of the code is " + Character.toString(code.charAt(0) + 2), "code", JOptionPane.INFORMATION_MESSAGE);  
            break;

            case 2:
            JOptionPane.showMessageDialog(this, "Ceasar Cipher Shift 3 >> 1st Character of the code is " + Character.toString(code.charAt(0) + 3), "code", JOptionPane.INFORMATION_MESSAGE);  
            break;

            case 3:
            JOptionPane.showMessageDialog(this, "1st Character of the code is either " + code.charAt(0) + " or " + code.charAt(random.nextInt(3) + 1) + ", The other is also a character present within the code", "code", JOptionPane.INFORMATION_MESSAGE);  
            break;

            case 4:
            JOptionPane.showMessageDialog(this, "1st Character of the code is either " + code.charAt(random.nextInt(3) + 1) + " or " + code.charAt(0) + ", The other is also present within the code", "code", JOptionPane.INFORMATION_MESSAGE);  
            break;
        
        }
    }
}

