package com.snl.service;

import com.snl.models.Dice;
import com.snl.models.Player;

public class Game {

    Board board;
    Dice dice;

    public Game(Board board, Dice dice) {
        this.board = board;
        this.dice = dice;
    }

    public String play() {

        while(!board.checkWinner()) {
            for(Player player : board.players) {
                int num = dice.roll();
                board.move(player, num);
                if(board.checkWinner()) break;
            }
            if(board.checkWinner()) break;
        }
        return board.getWinner().getName();
    }
}
