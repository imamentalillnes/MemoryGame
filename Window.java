import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Window extends JFrame{

    //Base Variables needed through the entire program
    final private int frameSize = 4;
    private int life = 3;
    private int level = 1;
    private JPanel basePanel = new JPanel(new BorderLayout());
    private JPanel gameField = new JPanel(new GridLayout(frameSize, frameSize));
    private JLabel lives = new JLabel("Lives: " + life);
    private Random rng = new Random();
    private int pressed = 0;
    private ArrayList<JButton> gameButtons = new ArrayList<>();
    private ArrayList<Integer> numsList;
    private int score = 0;
    private JLabel scoreLabel = new JLabel("Score: " + score);
    private JPanel infoPanel = new JPanel(new GridLayout());

    public Window(String Name){

        //Calls the super constructor
        super(Name);

        //sets up the frame an desired 
        this.setSize(500, 550);
        this.setLocation(500, 250);
        this.gameField.setPreferredSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(basePanel);
        this.setVisible(true);
        this.setAlwaysOnTop(true);

        //Sets the game up for playing
        basePanel.add(infoPanel, BorderLayout.NORTH);
        infoPanel.add(lives);
        infoPanel.add(scoreLabel);
        basePanel.add(gameField, BorderLayout.CENTER);

        //calls the makeButton method
        try{
            makeButtons();
        }
        catch(InterruptedException IE){
            System.out.println(IE);
        }
    }

    //Getters and Setters
    public Integer getFrameSize() {
        return frameSize;
    }

    public void setLife(){
        if(life > 0){
            life -= 1;
        }

        if(life <= 0){
            lives.setText("Lives: Dead");
            pressed = 0;
            
            for(JButton a : gameButtons){
                a.setBackground(Color.gray);
            }
        }
    }

    public int getlife(){
        return life;
    }

    public int getPressed(){
        return pressed;
    }

    public int getNumList() {
        return numsList.size();
    }

    private void upScore() {
        score++;
        scoreLabel.setText("Score: " + score);
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
                    pressed++;
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

        for(JButton a : gameButtons){
            a.setEnabled(false);
        }

        try{
            //shows which buttons were chosen
            for(int a : numsList){
                gameButtons.get(a).setBackground(Color.YELLOW);
                gameButtons.get(a).setEnabled(false);
                Thread.sleep(1000);
            }
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
            a.setEnabled(true);
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
        upScore();
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

    //handles the win condition
    public void winGame() throws InterruptedException{

        //goes through all the buttons
        for(JButton a : gameButtons){
            a.setBackground(Color.MAGENTA);
            a.setEnabled(false);
            Thread.sleep(500);
        }

        Thread.sleep(2000);

        //makes a new panel
        JPanel winPanel = new JPanel();
        JLabel winLabel = new JLabel("YOU WIN!!!");
        winLabel.setSize(24,24);

        //adds the new panels over the old ones
        this.setContentPane(winPanel);
        winLabel.add(winLabel);

    }

    //resets all need components of the game
    public void gameRestart() {

        //resets life
        life = 3;
        lives.setText("Lives: " + life);

        //resets score
        score = 0;
        scoreLabel.setText("Score: " + score);

        //resets level
        level = 1;

        //gets new buttons to remember
        numsList = getButtons();

        //cleans the field
        clean();
        pressed = 0;
        showRemember();
    }
}
