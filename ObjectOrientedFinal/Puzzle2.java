

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Puzzle2 extends Puzzle {
    private JButton[][] buttons;
    private int[][] board;
    private final int SIZE = 9;
    private final int MINES = 10;
    private int cellsToReveal;

    public Puzzle2(String code, int x, int y) {
        this.setLocation(x,y);
        this.code = code;
        startPuzzle();
    }

    protected void startPuzzle(){
        setTitle("Minesweeper");
        setIconImage(img);
        setSize(500, 500);
        setLayout(new GridLayout(SIZE, SIZE));

        buttons = new JButton[SIZE][SIZE];
        board = new int[SIZE][SIZE];

        initializeBoard();//call ng function
        placeMines();//same din dito

        for (int y = 0; y < SIZE; y++) {//dito parang initialize nya yung mga button na magkaruon ng action listener
            for (int x = 0; x < SIZE; x++) {
                buttons[x][y] = new JButton();
                buttons[x][y].setBackground(Color.WHITE);
                buttons[x][y].addActionListener(new ButtonListener(x, y));
                add(buttons[x][y]);
            }
        }

        setVisible(true);
    }

    private void initializeBoard() {
        cellsToReveal = SIZE * SIZE - MINES;
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                board[x][y] = 0;
            }
        }
    }

    private void placeMines() {
        Random random = new Random();//agcreate ti random number
        int minesPlaced = 0;//counter para sa mines
        while (minesPlaced < MINES) {//dapat 10 lng yung mines
            int x = random.nextInt(SIZE);//random number in x-coordinate,yung size is 9
            int y = random.nextInt(SIZE);//same sa line 59

            if (board[x][y] != -1) {//mang check nu adda mine na jay certain coordinate jy board
                board[x][y] = -1;//agipan ti mines
                incrementAdjacentCells(x, y);
                minesPlaced++;
            }
        }
    }

    private void incrementAdjacentCells(int x, int y) {//this part kt dejay mangikabli dagijay number nukwa nu adda asideg nga mines
        for (int dy = -1; dy <= 1; dy++) {//check the vertical board
            for (int dx = -1; dx <= 1; dx++)/*check the horizontal*/ {
                if (x + dx >= 0 && x + dx < SIZE && y + dy >= 0 && y + dy < SIZE) {// para lng mangcheck ty boundaries ty board tapno haan mo maaccesst
                    if (board[x + dx][y + dy] != -1) {//check nu dejay part ti board nga awan mine na
                        board[x + dx][y + dy]++;
                    }
                }
            }
        }
    }

    private class ButtonListener implements ActionListener {
        private int x, y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (board[x][y] == -1) {
                buttons[x][y].setBackground(Color.RED);
                revealMines();
                JOptionPane.showMessageDialog(null, "Game Over! You clicked on a mine.");
                resetGame();
            } else {
                revealCell(x, y);
                if (cellsToReveal == 0) {
                    JOptionPane.showMessageDialog(null, "Congratulations! You won!");
                    puzzleFinish();
                }
            }
        }
    }

    private void revealCell(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE || !buttons[x][y].isEnabled()) {
            return; //check the board and if the button is already clicked
        }

        buttons[x][y].setEnabled(false);
        if (board[x][y] == 0) /*this part nu mangpindot ka jy minesweeper kt agblank nukwa dagijay awan number*/{
            buttons[x][y].setText("");
            buttons[x][y].setBackground(Color.LIGHT_GRAY);
            cellsToReveal--;
            for (int dy = -1; dy <= 1; dy++) {// reveal more if there is more blank cell
                for (int dx = -1; dx <= 1; dx++) {
                    revealCell(x + dx, y + dy);
                }
            }
        } else /*this naman kt dejay cells nga adda number na*/{
            buttons[x][y].setText(String.valueOf(board[x][y]));//line 66
            buttons[x][y].setBackground(Color.LIGHT_GRAY);
            cellsToReveal--;
        }
    }

    private void revealMines() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (board[x][y] == -1) {
                    buttons[x][y].setText("*");
                    buttons[x][y].setBackground(Color.RED);
                }
            }
        }
    }

    private void resetGame() {
        initializeBoard();
        placeMines();
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                buttons[x][y].setText("");
                buttons[x][y].setEnabled(true);
                buttons[x][y].setBackground(Color.WHITE);
            }
        }
    }

  
    protected void puzzleFinish(){
        JOptionPane.showMessageDialog(this, "2nd Character of the code is " + code.charAt(1), "code", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }
}


