package com.snl;

import com.snl.models.Dice;
import com.snl.service.Board;
import com.snl.service.Game;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class SnakeAndLadderRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Board board = new Board(args[0]);
        System.out.println(board);
        Dice d = new Dice(6);
        Game game = new Game(board, d);
        String winner = game.play();
        System.out.println(winner + " wins the game");


    }
}
