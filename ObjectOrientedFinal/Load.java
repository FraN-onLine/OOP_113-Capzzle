

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Load extends JFrame {

    private JProgressBar progressBar = new JProgressBar();
    
    //The loading screen graphics
    Load(){
        new JFrame();
        setSize(500,500);
        setVisible(true);
        setTitle("Capzzle");
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image img = new ImageIcon(new File("Cappzle.jpg").getAbsolutePath()).getImage();
        setIconImage(img);

        LoadPanel loadpanel = new LoadPanel();
        loadpanel.setLayout(null);
       
        progressBar.setMinimum(0);
        progressBar.setMaximum(99);
        progressBar.setValue(0);
        progressBar.setForeground(new Color(0xFFDB58));
        progressBar.setBounds(185,150,120,20);
        progressBar.setBorderPainted(false);
        add(loadpanel);
        loadpanel.add(progressBar);

        for (int i = 0; i <= 33; i++) {
            progressBar.setValue(i * 3);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkProgress(){
       if(progressBar.getValue() == progressBar.getMaximum()){
        return true;
       } else
       return false;
    }

   }
