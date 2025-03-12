import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Window extends JFrame{

    /*
    //method to make the numslist
    private ArrayList<Integer> makeNumsList() throws InterruptedException {
        ArrayList<Integer> numsList = getButtons();
        return numsList;
    }    
        */

    //Base Variables needed through the entire program
    private int frameSize = 4;
    private int life = 3;
    private int level = 1;
    private boolean playing = true;
    private JPanel basePanel = new JPanel(new BorderLayout());
    private JPanel gameField = new JPanel(new GridLayout(frameSize, frameSize));
    private JLabel lives = new JLabel("Lives: " + life);
    private Random rng = new Random();
    private int pressed = 0;
    private ArrayList<JButton> gameButtons = new ArrayList<>();
    private ArrayList<Integer> numsList;

    public Window(String Name){
        //Calls the super constructor
        super(Name);

        //sets up the frame an desired 
        this.setSize(500, 500);
        this.setLocation(500, 250);
        this.gameField.setPreferredSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(basePanel);
        basePanel.add(gameField, BorderLayout.CENTER);
        basePanel.add(lives, BorderLayout.NORTH);
        this.setVisible(true);
        this.setAlwaysOnTop(true);

        //calls the makeButton method
        try{
            makeButtons();
        }
        catch(InterruptedException IE){
            System.out.println(IE);
            System.out.println("Line 44");
        }
        
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
        if(life > 0){
            life -= 1;
        }
    }

    public int getPressed(){
        return pressed;
    }

    public int getNumList() {
        return numsList.size();
    }


    //Makes all the buttons for the current stage
    private void makeButtons() throws InterruptedException{

        numsList = getButtons();

        //creates the buttons and adds them to the plain
        for(int i = 0; i < (frameSize * frameSize); ++i){
            int buttonID = i;
            JButton press = new JButton();
            press.setBackground(Color.white);
            gameButtons.add(press);

            //Action Listener Creation
            press.addActionListener(e -> {

                if(life <= 0){
                    lives.setText("Lives: Dead");
                    
                    for(JButton a : gameButtons){
                        a.setBackground(Color.gray);
                        a.setEnabled(false);
                    }
                }
                else{
                    if(buttonID == numsList.get(pressed)){
                        press.setBackground(Color.GREEN);
                        curClean();
                        pressed++;
                    }
                    else{
                        press.setBackground(Color.RED);
                        this.setLife();
                        lives.setText("Lives: " + life);
                    }
                }
            });

            press.setVisible(true);
            gameField.add(press);
        }

        //shows the required button to remember
        showRemember();

    }


    private ArrayList<Integer> getButtons(){

        //makes arraylist for the buttons that need memorized
        ArrayList<Integer> buttons = new ArrayList<Integer>();

        //adds in all the buttons that are checked for
        while(buttons.size() < level){

            //variables for check and making ran numbers
            boolean used = false;

            do{
                used = false;
                int num = rng.nextInt((frameSize*frameSize));

                //checks if the same number is being added again
                for( int a: buttons){
                    if(a == num){
                        used = true;
                    }
                }
                if (!used){
                    buttons.add(num);
                    
                }
            }while(used);
        }

        //returns the list
        return buttons;
    }

    private void showRemember(){

        try{
        //shows which buttons were chosen
        for(int a : numsList){
            gameButtons.get(a).setBackground(Color.YELLOW);
            System.out.println(a);
            Thread.sleep(1000);
        }
        System.out.println("nums in the list");
        }
        catch(InterruptedException IE){
            System.out.println("showRemeber Error");
        }

        clean();
    }

    private void clean() {
        //resets the button colors
        for(JButton a: gameButtons){
            a.setBackground(Color.white);
        }
    }

    private void curClean() {
        for(JButton a : gameButtons){
            if(a.getBackground() == Color.RED){
                a.setBackground(Color.white);
            }
        }
    }

    public void winLevel() {

        //once the round is Complete it resets everything and moves up a level
            level++;
            numsList = getButtons();
            clean();
            pressed = 0;

            try{
                Thread.sleep(1000);
                showRemember();
            }
            catch(InterruptedException ie){
                System.out.println(ie);
            }

    }

    
}
