import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        //Creates file to read the buttons pressed from
        try{
            File numIn = new File("NumbersIn.txt");
            Scanner scnr = new Scanner(numIn);

            //Window method call
            Window game = new Window("Game", numIn);

            //Variables
            ArrayList<Integer> buttonCheck = game.getButtons();
            
            
            //game loop
            while(game.getPlaying()){
                while (scnr.hasNextInt()){
                    if(scnr.nextInt() == buttonCheck.get(0)){
                        System.out.println("You got it right!");
                    }
                    else{
                        System.out.println("wrong!");
                        game.setLife();
                    }
                }
            }
        }
        catch(IOException ioe){
            System.out.println("file failed to be made terminating process");
            System.out.println(ioe);
            return;
        }
    }
}