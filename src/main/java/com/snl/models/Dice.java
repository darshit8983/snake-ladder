package com.snl.models;

import java.security.SecureRandom;
import java.util.Random;

public class Dice {

    SecureRandom rand;
    int sides = 0;

    public Dice(int sides) {
        this.sides = sides;
        rand = new SecureRandom();
    }

    public int roll() {
       return rand.nextInt(sides) + 1;
    }


}
