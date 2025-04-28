

import javax.swing.*;
import java.awt.*;
import java.io.File;

public abstract class Puzzle extends JFrame {

    Image img = new ImageIcon(new File("Cappzle.jpg").getAbsolutePath()).getImage(); //this is inherited

    protected String code; //inherited

    abstract void startPuzzle(); //inherited then overriden

    abstract void puzzleFinish(); //inherited then override
    
}
