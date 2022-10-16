package com.PPlabs.PP_lr3.play;

import com.PPlabs.PP_lr3.Color;
import com.PPlabs.PP_lr3.droid.DroidBase;

import java.util.Scanner;

public enum Arena {
    LAVA(1.3, 0.9), ICE(0.8, 1.2), DARK_FOREST(1, 1);
    private final double multiplicationAccuracy, multiplicationHealth;

    Arena(double multiplicationAccuracy, double multiplicationHealth) {
        this.multiplicationAccuracy = multiplicationAccuracy;
        this.multiplicationHealth = multiplicationHealth;
    }

    public double getMultiplicationAccuracy() {
        return multiplicationAccuracy;
    }

    public double getMultiplicationHealth() {
        return multiplicationHealth;
    }

    public static Arena chooseArena(Scanner scan) {
        String choosing;

        System.out.print(Color.PURPLE + "\nCHOOSE ARENA FOR GAMES :\n" +
                "[L] LAVA ---- 0.9 * HEALTH ---- 1.3 * ACCURACY\n" +
                "[I] ICE ---- 1.2 * HEALTH ---- 0.8 * ACCURACY\n" +
                "[F] DARK FOREST ---- 1 * HEALTH ---- 1 * ACCURACY\n--->    " + Color.ANSI_RESET);
        choosing = scan.nextLine();


        switch (choosing) {
            case "L":
                return LAVA;
            case "I":
                return ICE;
            case "F":
                return DARK_FOREST;
            default:
                System.out.println("\nIncorrect input. Arena is selected by default! ---> DARK_FOREST");
                return DARK_FOREST;
        }
    }

    public void changeByArena(DroidBase droid) {
        droid.setAccuracy(droid.getAccuracy() * getMultiplicationAccuracy());
        droid.setHealth((int) (droid.getHealth() * getMultiplicationHealth()));
    }
}


