public class Main{

    public static void main(String[] args) throws InterruptedException {

        //Window method call
        Window game = new Window("Game");

        while(true){
            Thread.sleep(500);
            if(game.getPressed() == game.getNumList()){
                game.winLevel();
            }

            if(game.getPressed() == (game.getFrameSize()*game.getFrameSize())){
                game.winGame();
            }

            if(game.getlife() == 0 && game.getPressed() > 0){
                game.gameRestart();
            }
        }
    }

}