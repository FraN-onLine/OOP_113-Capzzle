import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

//place all of these in an ObjectOrientedFinal Directory under some random directory
//open the random directory in the ide
//run App.java

//making it so its portable and not tethered to my device made me wanna gauge my eyes out
//for(int i = 0; i < 10000; i++){System.out.println("A");} System.out.println(H);

public class App {

    private static JPanel mainPanel = new JPanel(); //we declare static fields in app to be called and used by all methods in App
    private static int i = 0; //amount of capsules
    private static CustomButton[] capsule = new CustomButton[9]; //Custom button extends from button to make each capsule have a consistent design from all buttons, a new class is created to hide the button's complexity
    private static JFrame frame = new JFrame();
    private static boolean booted = false;
    private static JButton createButton = new JButton("Create +");
    private static CustomPanel cs = new CustomPanel(0xFFDB58); //cs is just a named custom pannel to be removed and replaced by the create button after one capsule is created

    public static void addCapsule(String code, String message){ //method is called after capsule creation
            if(i == 9){
                JOptionPane.showMessageDialog(mainPanel, "MAX CAPSULES REACHED", "ERROR", JOptionPane.ERROR_MESSAGE);
                return; //we return if 9 capsules are stored to prevent a creation of a new capsule
            }

            capsule[i] = new CustomButton("Capsule " + (i + 1)); //initialize capsule
            mainPanel.add(capsule[i]);

            capsule[i].addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e){
                    new PuzzleMenu(message, code, frame.getX(), frame.getY());//a new puzzle menu is created that stores the code and message
                    frame.dispose();
                }

            });

            mainPanel.remove(createButton);
            frame.remove(cs);
            frame.add(createButton, BorderLayout.SOUTH);
            createButton.setPreferredSize(new Dimension(125,40));
            mainPanel.revalidate();
            mainPanel.repaint();
            i++;
    }

    public static void setLoc(int x, int y){
        frame.setLocation(x, y);  //sets main menu's location realative to puzzlemenu
    }
    public static void main(String[] args) {
        
    
        frame.setSize(500,500);
        frame.setTitle("Capzzle");
        frame.getContentPane().setBackground(new Color(0xFFDB58));
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image img = new ImageIcon(new File("Cappzle.jpg").getAbsolutePath()).getImage();
        frame.setIconImage(img);

        if(!booted){
            Load load = new Load(); //load menu
            if(load.checkProgress()){
             frame.setLocation(load.getX(), load.getY());
             load.dispose();
             frame.setVisible(true);
             booted = true;
             }
             mainPanel.setLayout(new FlowLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(new CustomPanel(0xFFDB58, "CAPZZLE"), BorderLayout.NORTH);
        frame.add(cs, BorderLayout.SOUTH);
        frame.add(new CustomPanel(0xFFDB58), BorderLayout.WEST);
        frame.add(new CustomPanel(0xFFDB58), BorderLayout.EAST);

        createButton.setBorder(null);
        createButton.setFont(new Font("STENCIL", Font.PLAIN, 16));
        mainPanel.add(createButton);

        createButton.setBackground(Color.GRAY);
        createButton.setPreferredSize(new Dimension(125,100));
        createButton.setFocusPainted(false);

        frame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
            for(int j = 0; j < i; j++){
            capsule[j].setBackground(Color.lightGray);
            } //reset button colors after hovering, di ko maayos since after hover then back dito it stays colored
            }
        });

        frame.revalidate();
        frame.repaint();

        createButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
                new InputMenu(frame.getX(), frame.getY());
            }
        });

        } else {
            frame.setVisible(true);
            frame.repaint();
            frame.revalidate();
        }
        
    }
    
}

