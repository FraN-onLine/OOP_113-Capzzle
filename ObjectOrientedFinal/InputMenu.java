
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

import javax.swing.*;

public class InputMenu extends JFrame {

    private String message;
    private String code;
    private String DATA_FILE;
    private final String directoryPath =  new File("messages").getAbsolutePath(); //consistent directory for all messages
    
    InputMenu(int x, int y){
        this.setLocation(x,y);
        new JFrame();
        setVisible(true);
        setSize(265,210);
        setResizable(false);
        setTitle("ENTER MESSAGE");
        setLayout(new BorderLayout());
        Image img = new ImageIcon(new File("Cappzle.jpg").getAbsolutePath()).getImage();
        this.setIconImage(img);


        JPanel buttons = new JPanel();
        add(buttons,BorderLayout.SOUTH);
        buttons.setLayout(new GridLayout(1,3));
        JButton cButton = new JButton("Create");
        buttons.add(cButton);
        JButton lButton = new JButton("Load");
        buttons.add(lButton);
        JButton rButton = new JButton("Random");
        buttons.add(rButton);
       

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2,1));
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel(new FlowLayout());
        panel1.add(panel2);
        panel1.add(panel3);

        panel2.add(new JLabel("Message:"));

        JTextField messageField = new JTextField();
        messageField.setPreferredSize(new Dimension(200,60));
        panel2.add(messageField);

        panel3.add(new JLabel("Code(5 Letters): "));

        JTextField codeField = new JTextField();
        codeField.setPreferredSize(new Dimension(200,60));
        panel3.add(codeField);
        add(panel1, BorderLayout.CENTER);
        getContentPane().setBackground(new Color(0xFFDB58));


        cButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
                message = messageField.getText();
                code = codeField.getText();
        
                if (message.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a message.");
                    return;
                }
        
                if (code.length() != 5) {
                    JOptionPane.showMessageDialog(null, "Code must be 5 letters long.");
                    return;
                }

                setVisible(false);
                App.addCapsule(code, message);
                saveDataToFile();
            }
        }
        
        );

        lButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
                loadDataFromFile();
                dispose();
            }
        });

        rButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadRandomDataFromFile();
                dispose();
            }
        });
     
    }

     private void saveDataToFile() {
        DATA_FILE = directoryPath + File.separator + code + ".txt"; //originally this just saves in the open folder the path allows all to be stored in one place
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
                writer.println(code + "," + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDataFromFile() {

        DATA_FILE = JOptionPane.showInputDialog(this,"Enter the code of the message you want to load: ");
         DATA_FILE = directoryPath + File.separator + DATA_FILE + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    App.addCapsule(data[0], data[1]); //data 0 is code, data 1 is message
                    return;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No message found");
        }
    }


private void loadRandomDataFromFile() {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files != null && files.length > 0) {
            Random random = new Random();
            File randomFile = files[random.nextInt(files.length)];

            try (BufferedReader reader = new BufferedReader(new FileReader(randomFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 2) {
                        App.addCapsule(data[0], data[1]); // data[0] is code, data[1] is message
                        return;
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading random message");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No messages found");
        }
    }
}
