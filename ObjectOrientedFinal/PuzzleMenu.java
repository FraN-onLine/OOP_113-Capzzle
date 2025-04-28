

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;;

public class PuzzleMenu extends JFrame {

    private JFrame puzzleFrame = new JFrame();

    PuzzleMenu(String message, String code, int x, int y){

    Image img = new ImageIcon(new File("Cappzle.jpg").getAbsolutePath()).getImage();
    puzzleFrame.setIconImage(img);

    puzzleFrame.setSize(new Dimension(500, 500));
    puzzleFrame.setResizable(false);
    puzzleFrame.setTitle("Capzzle");
    puzzleFrame.setVisible(true);
    puzzleFrame.setLayout(new BorderLayout());
    puzzleFrame.add(new JPanel(), BorderLayout.SOUTH);
    puzzleFrame.add(new JPanel(), BorderLayout.NORTH);
    puzzleFrame.add(new JPanel(), BorderLayout.EAST);
    puzzleFrame.add(new JPanel(), BorderLayout.WEST);
    puzzleFrame.setLocation(x,y);
    puzzleFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    //JPanel panel = new JPanel(new GridLayout(3,2));
    JPanel panel = new JPanel(new FlowLayout());
    puzzleFrame.add(panel, BorderLayout.CENTER);
    JButton enterCode = createStyledButton("Enter Code");
    panel.add(enterCode);
    enterCode.setPreferredSize(new Dimension(150,200));

    enterCode.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e){
            String codeInput = JOptionPane.showInputDialog(panel,"Enter code");
            if(code.equals(codeInput)){
            JOptionPane.showMessageDialog(panel, message , "message", JOptionPane.INFORMATION_MESSAGE);
            puzzleFrame.setVisible(false);
            App.main(null);
            } else {
            JOptionPane.showMessageDialog(panel, "Incorrect code" , "message", JOptionPane.ERROR_MESSAGE);   
            }
        }
        
    });

    JButton puzzle[] = new JButton[5];

    for(int i = 0; i < 5; i++){
        puzzle[i] = createStyledButton("Puzzle " + (i+1));
        panel.add(puzzle[i]);
        puzzle[i].setFocusPainted(false);
        puzzle[i].setPreferredSize(new Dimension(150,200));
    }

    JButton back = createStyledButton("back");
    panel.add(back);
    back.setFocusPainted(false);
    back.setPreferredSize(new Dimension(220,30));
    JButton instruct = createStyledButton("Instructions");
    panel.add(instruct);
    instruct.setFocusPainted(false);
    instruct.setPreferredSize(new Dimension(220,30));
    
    puzzle[0].addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e){
            new Puzzle1(code, puzzleFrame.getX(), puzzleFrame.getY());
        }
        
    });

    puzzle[1].addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e){
            new Puzzle2(code, puzzleFrame.getX(), puzzleFrame.getY());
        }
        
    });

    puzzle[2].addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e){
            new Puzzle3(code, puzzleFrame.getX(), puzzleFrame.getY());
        }
        
    });

    puzzle[3].addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e){
            new Puzzle4(code, puzzleFrame.getX(), puzzleFrame.getY());
        }
        
    });

    puzzle[4].addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e){
            new Puzzle5(code, puzzleFrame.getX(), puzzleFrame.getY());
        }
        
    });

    back.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e){
            App.main(null);
            App.setLoc(puzzleFrame.getX(),puzzleFrame.getY());
            puzzleFrame.dispose();
        }
        
    });

    instruct.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e){
          JOptionPane.showMessageDialog(puzzleFrame, "<html>Instructions<br/>Collect each letter of the 5 letter code by solving puzzles then reveal a message<br/>1.Tap the button that lights up and follow the instructions<br/>2.Avoid the bombs by deciphering the amount of bombs in a 3x3 Area<br/>3.Word Chain, Chain words by linking the previous word's last letter to the next's first<br/>4.Riddle Game - Solve the riddles by deciphering the hint<br/>5. Make sure all sides added up are the same number </html>", "instructions", JOptionPane.PLAIN_MESSAGE);
        }
        
    });




}

private JButton createStyledButton(String text) {
    JButton button = new JButton(text) {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();

            // Enable anti-aliasing for smoother edges
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw shadow
            g2d.setColor(new Color(0, 0, 0, 50));
            g2d.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 30, 30);

            // Set button color based on its state
            if (getModel().isArmed()) {
                g2d.setColor(new Color(255, 215, 0));
            } else if (getModel().isRollover()) {
                g2d.setColor(new Color(255, 223, 0));
            } else {
                g2d.setColor(new Color(255, 235, 59));
            }

            g2d.fillRoundRect(0, 0, getWidth() - 5, getHeight() - 5, 30, 30);
            g2d.dispose();

            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(new Color(169, 169, 169));
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
        }
    };
    button.setContentAreaFilled(false);
    button.setFocusPainted(false);
    button.setForeground(Color.DARK_GRAY);
    button.setFont(new Font("STENCIL", Font.BOLD, 14));
    button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    button.setHorizontalTextPosition(SwingConstants.CENTER);
    button.setVerticalTextPosition(SwingConstants.BOTTOM);

    return button;
}


}
