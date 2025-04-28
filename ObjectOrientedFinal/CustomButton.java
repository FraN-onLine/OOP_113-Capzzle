

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {

    public CustomButton(String text) { //abstraction and easy setup of a custom button
        super(text);
        setFocusPainted(false);
        setFont(new Font("STENCIL", Font.PLAIN, 16));
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(130, 110));
        setBorderPainted(false);
        addMouseListener(new HoverEffect()); //hover effect

    }

    private class HoverEffect extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            setForeground(getForeground().darker());
            setBackground(new Color(0xFFDB58));
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(Color.lightGray);
            setForeground(Color.black);
            repaint();
        }
    }

}
