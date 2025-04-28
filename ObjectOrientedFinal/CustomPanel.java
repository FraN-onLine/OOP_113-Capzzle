

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {

    private JLabel label;

        public CustomPanel(int rgb, String str) {
            setBackground(new Color(rgb));
            label = new JLabel(str);
            label.setFont(new Font("Elephant", Font.PLAIN, 16));
            add(label); 
        } //this adds a label to a custom colored panel if a string is added

        public CustomPanel(int rgb) {
            setBackground(new Color(rgb));
            add(new JLabel("     "));
        } //does not

        //example of POLYMORPHISM
        //Constructor Overloading
        
    }