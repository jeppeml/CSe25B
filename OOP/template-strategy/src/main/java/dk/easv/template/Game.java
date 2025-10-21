package dk.easv.template;

public abstract class Game {
    protected int team1score;
    protected int team2score;

    public void initialize(){
        team1score = 0;
        team2score = 0;
    }

    public void startGame(){
        System.out.println("Game started");
    }

    public void endGame(){
        System.out.println("Game ended");
        System.out.println("team1score: " + team1score);
        System.out.println("team2score: " + team2score);
    }

    public abstract void play();
}
