import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame{

    //Base Variables needed through the entire program
    int frameSize = 4;
    JPanel basePanel = new JPanel(new BorderLayout());
    JPanel gameField = new JPanel(new GridLayout(frameSize, frameSize));
    

    public Window(String Name){
        //Calls the super constructor
        super(Name);

        //sets up the frame an desired 
        this.setSize(500, 500);
        this.gameField.setPreferredSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(basePanel);
        basePanel.add(gameField, BorderLayout.CENTER);
        this.setVisible(true);

        //Makes all the buttons for the current stage
        for(int i = 0; i < (frameSize * frameSize); ++i){
            Button press = new Button("" + i);
            press.addActionListener(e -> {
                System.out.println("you pressed button " + press.getLabel());
            });
            press.setVisible(true);
            gameField.add(press);
        }
    }
}
