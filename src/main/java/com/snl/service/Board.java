package com.snl.service;

import com.snl.models.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Board {

    Map<Integer, Integer> snake;
    Map<Integer, Integer> ladder;
    List<Player> players;

    public Board(String filePath) throws FileNotFoundException {
        snake = new HashMap<>();
        ladder = new HashMap<>();
        players = new ArrayList<>();
        File file = new File(filePath);
        if(!file.isFile()) {
            System.out.println("File not found");
            throw new FileNotFoundException("File path not valid");

        }
        Scanner scanner = new Scanner(file);
        loadUpBoards(file);
    }

    public void loadUpBoards(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int numOfSnakes = Integer.parseInt(scanner.nextLine().trim());
        for(int i=0; i < numOfSnakes; i++) {
            String snakesPos = scanner.nextLine().trim();
            String[] pos = snakesPos.split(" ");
            snake.put(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
        }
        int numOfLadder = Integer.parseInt(scanner.nextLine().trim());
        for(int i=0; i < numOfLadder; i++) {
            String ladderPos = scanner.nextLine().trim();
            String[] pos = ladderPos.split(" ");
            ladder.put(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
        }
        int numOfPlayers = Integer.parseInt(scanner.nextLine().trim());
        for(int i=0; i < numOfPlayers; i++) {
            String name = scanner.nextLine().trim();
            players.add(new Player(name, 0));
        }
    }

    public boolean checkWinner() {
        return players.stream().filter(player -> player.getPosition() == 100).count() > 0;
    }

    public Player getWinner() {
        return players.stream().filter(player -> player.getPosition() == 100).collect(Collectors.toList()).get(0);
    }

    public void move(Player p , int num) {
        int oldPos = p.getPosition();
        if(p.getPosition() + num > 100) {
            return;
        }
        int newPos = p.getPosition() + num;
        if(snake.containsKey(newPos)) {
            newPos = snake.get(newPos);
            //It is possible that snake bite leading to the ladder
            if(ladder.containsKey(newPos)) {
                newPos = ladder.get(newPos);
            }
            p.setPosition(newPos);
            printMove(p,oldPos, newPos, num);
            return;
        }
        if(ladder.containsKey(newPos)) {
            newPos = ladder.get(newPos);
            //it is possible that ladder drop to a pos where snake is biting
            if(snake.containsKey(newPos)) {
                newPos = ladder.get(newPos);
            }
            p.setPosition(newPos);
            printMove(p,oldPos, newPos, num);
            return;
        }
        p.setPosition(newPos);
        printMove(p,oldPos, newPos, num);
        return;

    }

    private void printMove(Player p , int oldPos, int newPos, int rolled) {
        System.out.println(String.format("%s rolled a %d and moved from %d to %d", p.getName(), rolled, oldPos, newPos ));
    }

    @Override
    public String toString() {
        return "Board{" +
                "snake=" + snake +
                ", ladder=" + ladder +
                ", players=" + players +
                '}';
    }
}
