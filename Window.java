import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Window extends JFrame{

    //Base Variables needed through the entire program
    private int frameSize = 4;
    private int life = 3;
    private int level = 1;
    private boolean playing = true;
    private JPanel basePanel = new JPanel(new BorderLayout());
    private JPanel gameField = new JPanel(new GridLayout(frameSize, frameSize));
    private Random rng = new Random();
    
    

    public Window(String Name, File writer) throws IOException{
        //Calls the super constructor
        super(Name);

        //sets up the frame an desired 
        this.setSize(500, 500);
        this.setLocation(500, 250);
        this.gameField.setPreferredSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(basePanel);
        basePanel.add(gameField, BorderLayout.CENTER);
        this.setVisible(true);

        //calls the makeButton method
        makeButtons(writer);
    }

    //Getters and Setters
    public Integer getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(int num){
        if(frameSize < num){
            frameSize = num;
        }
    }

    public boolean getPlaying() {
        if(life <= 0){
            playing = false;
        }
        return playing;
    }

    public void setLife(){
        life -= 1;
    }


    //Makes all the buttons for the current stage
    private void makeButtons(File writer) throws IOException{
        FileWriter clear = new FileWriter(writer);
        clear.write("");

        //creates the buttons and adds them to the plain
        for(int i = 0; i < (frameSize * frameSize); ++i){
            int buttonID = i;
            Button press = new Button();
            press.addActionListener(e -> {
                try{
                    FileWriter reWriter = new FileWriter(writer, true);
                    reWriter.write(buttonID + " ");
                    System.out.println("Button " + buttonID + " was here");
                    reWriter.close();
                }
                catch(IOException ioe){
                    System.out.println(ioe);
                    System.out.println("Could not write file");
                }
                
            });
            press.setVisible(true);
            gameField.add(press);
        }

    }


    public ArrayList<Integer> getButtons(){

        //makes arraylist for the buttons that need memorized
        ArrayList<Integer> buttons = new ArrayList<Integer>();

        //adds in all the buttons that are checked for
        while(buttons.size() < level){

            //variables for check and making ran numbers
            boolean used = false;
            int num = rng.nextInt((frameSize*frameSize));

            //checks if there is anything in the arraylist already
            if (buttons.size() == 0){
                buttons.add(num);
            }

            //if so it then checks if the same number is being added again
            else {
                for( int a: buttons){
                    if(a == num){
                        used = true;
                    }
                }
                if (!used){
                    buttons.add(num);
                }
            }
        }


        //DELETE THIS 
        for(int a : buttons){
            System.out.println(a);
        }
        System.out.println("nums in the list");

        //returns the list
        return buttons;
    }
}
