package dk.easv;

import dk.easv.strategy.Context;
import dk.easv.template.Cricket;
import dk.easv.template.Football;
import dk.easv.template.Game;

public class Main {
    public static void main(String[] args) {
        // template pattern
        Game game = new Cricket();
        game.initialize();
        game.startGame();
        for (int i = 0; i < 10; i++) {
            game.play();
        }
        game.endGame();

        // strategy pattern
        Context context = new Context();
        context.setStrategy("two");
        int result = context.executeStrategy(4);
        System.out.println("result: " + result);
    }
}