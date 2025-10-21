package dk.easv.template;

public class Cricket extends Game{

    @Override
    public void play() {
        System.out.println("playing cricket");
    }

    @Override
    public void startGame() {
        super.startGame();
        System.out.println("you have started cricket");
    }
}
