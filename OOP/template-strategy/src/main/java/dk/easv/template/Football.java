package dk.easv.template;

public class Football extends Game {

    @Override
    public void play() {
        System.out.println("playing football");
        super.team1score++;
    }
}
