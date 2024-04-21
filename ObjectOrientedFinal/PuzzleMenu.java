package ObjectOrientedFinal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PuzzleMenu extends JFrame {
    
    PuzzleMenu(){
        
        new JFrame();
        setVisible(true);
        setSize(500,500);
        setLayout(new BorderLayout());

        JButton cButton = new JButton("Create");
        add(cButton, BorderLayout.SOUTH);
       

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,2));
        panel.add(new JLabel("Message:"));

        JTextField messageField = new JTextField();
        panel.add(messageField);

        panel.add(new JLabel("Code: "));

        JTextField codeField = new JTextField();
        panel.add(codeField);
        add(panel, BorderLayout.CENTER);
        getContentPane().setBackground(new Color(0xFFDB58));


        cButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
                String message = messageField.getText();
                String code = codeField.getText();
        
                if (message.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a message.");
                    return;
                }
        
                if (code.length() != 5) {
                    JOptionPane.showMessageDialog(null, "Code must be 5 letters long.");
                    return;
                }

                setVisible(false);
            }
        });
     
    }
}
