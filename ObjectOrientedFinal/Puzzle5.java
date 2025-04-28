

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
 
public class Puzzle5 extends Puzzle {
 
    private JTextField[][] grid;
    private JPanel gridPanel;
    private int gridSize;
    Random random = new Random();

    public Puzzle5(String code, int x, int y) { //constructor
        this.setLocation(x,y);
        this.code = code;
        startPuzzle();
    }

    void startPuzzle() { //start puzzle frame settings
        setTitle("Magic Square");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        this.setIconImage(img);
 
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
 
        gridPanel = new JPanel();
        createGrid();
 
        JButton validateButton = new JButton("Validate the Magic Square");
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateMagicSquare();
            }
        });
 
        panel.add(gridPanel, BorderLayout.CENTER);
        panel.add(validateButton, BorderLayout.SOUTH);
 
        add(panel);
    }
 
    private void createGrid() {
        while(gridSize % 2 == 0){ //random size
            gridSize = random.nextInt(5) + 3;
        }
 
            gridPanel.removeAll();
            gridPanel.setLayout(new GridLayout(gridSize, gridSize));
            grid = new JTextField[gridSize][gridSize];
 
            for (int i = 0; i < gridSize; i++) { //reset
                for (int j = 0; j < gridSize; j++) {
                    grid[i][j] = new JTextField();
                    grid[i][j].setHorizontalAlignment(JTextField.CENTER);
                    grid[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                    grid[i][j].setBackground(new Color(0, 171, 169));
                    grid[i][j].setEnabled(true);
                    gridPanel.add(grid[i][j]);
                }
            }

            int position = gridSize / 2; //random start
            int randomStart = random.nextInt(4);
            int randomNumber[] = {1, gridSize * gridSize};
            if(randomStart == 0){
                grid[0][position].setText(String.valueOf(randomNumber[random.nextInt(2)]));
                grid[0][position].setEnabled(false);
            }
            if(randomStart == 1){
                grid[gridSize - 1][position].setText(String.valueOf(randomNumber[random.nextInt(2)]));
                grid[gridSize - 1][position].setEnabled(false);
            }
            if(randomStart == 2){
                grid[position][0].setText(String.valueOf(randomNumber[random.nextInt(2)]));
                grid[position][0].setEnabled(false);
            }
            if(randomStart == 3){
                grid[position][gridSize - 1].setText(String.valueOf(randomNumber[random.nextInt(2)]));
                grid[position][gridSize - 1].setEnabled(false);
            }
 
            gridPanel.revalidate();
            gridPanel.repaint();
       
    }
 
    private void validateMagicSquare() {
        try {
            int[][] magicSquare = new int[gridSize][gridSize];
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    magicSquare[i][j] = Integer.parseInt(grid[i][j].getText());
                }
            }
 
            boolean isMagic = isMagicSquare(magicSquare);
            if (isMagic) {
                puzzleFinish();
            } else {
                showMessage("The grid is not a magic square. Try again.");
            }
        } catch (NumberFormatException ex) {
            showMessage("Please fill in all cells with valid numbers.");
        }
    }
 
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
 
    protected void puzzleFinish() {
        JOptionPane.showMessageDialog(this, "5th Character of the code is " + code.charAt(4), "code", JOptionPane.INFORMATION_MESSAGE);
        this.setVisible(false);
        this.dispose();
    }
 
    private boolean isMagicSquare(int[][] grid) {
        int size = grid.length;
        int sum = (((gridSize * gridSize) +  1) * gridSize) / 2; //formula for magic sum
 
        // Check rows
        for (int i = 0; i < size; i++) {
            int rowSum = 0;
            for (int j = 0; j < size; j++) {
                rowSum += grid[i][j];
            }
            if (rowSum != sum)
                return false;
        }
 
        // Check columns
        for (int i = 0; i < size; i++) {
            int colSum = 0;
            for (int j = 0; j < size; j++) {
                colSum += grid[j][i];
            }
            if (colSum != sum)
                return false;
        }
 
        // Check diagonals
        int diag1Sum = 0, diag2Sum = 0;
        for (int i = 0; i < size; i++) {
            diag1Sum += grid[i][i];
            diag2Sum += grid[i][size - i - 1];
        }
 
        return diag1Sum == sum && diag2Sum == sum;
    }
}