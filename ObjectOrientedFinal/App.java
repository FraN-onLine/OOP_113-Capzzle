package ObjectOrientedFinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    public static void main(String[] args) {

        JFrame frame = new JFrame();

        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setTitle("Capzzle");
        frame.getContentPane().setBackground(new Color(0xFFDB58));
        frame.setLayout(new BorderLayout());
       
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(new CustomPanel(0xFFDB58), BorderLayout.NORTH);
        frame.add(new CustomPanel(0xFFDB58), BorderLayout.SOUTH);
        frame.add(new CustomPanel(0xFFDB58), BorderLayout.WEST);
        frame.add(new CustomPanel(0xFFDB58), BorderLayout.EAST);

        JButton createButton = new JButton("Create +");
        mainPanel.add(createButton);
        createButton.setBackground(Color.GRAY);

        createButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
                System.out.println("Created");
                new PuzzleMenu();
            }
        });

    }
}
