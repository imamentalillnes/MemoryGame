public class Main{

    public static void main(String[] args) throws InterruptedException {

        //Window method call
        Window game = new Window("Game");

        while(game.getPlaying()){
            Thread.sleep(500);
            if(game.getPressed() == game.getNumList()){
                game.winLevel();
            }
        }
    }

}