package com.PPlabs.PP_lr3.play;

import com.PPlabs.PP_lr3.Color;
import com.PPlabs.PP_lr3.battle.BattleOne;
import com.PPlabs.PP_lr3.battle.BattleTeam;
import com.PPlabs.PP_lr3.droid.DroidBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PlayDroidsBattleGame {

    BattleOne battle;
    static private FileWriter fileWriter;

    public void play() throws IOException {

        int choosing;
        boolean inCorrect;
        Scanner scan = new Scanner(System.in);
        createFile("droidGame.txt");

        do {
            inCorrect = false;

            System.out.print(Color.PURPLE + "\t\t*** WELCOME TO GAME ***\n\n CHOOSE :\n" +
                    "[1] - CREATE DROID\n" +
                    "[2] - BATTLE 1 & 1\n" +
                    "[3] - BATTLE TEAM & TEAM\n" +
                    "[4] - EXIT\n---->\t" + Color.ANSI_RESET);

            try {
                choosing = Integer.parseInt(scan.nextLine());

                switch (choosing) {
                    case 1 -> {
                        battle = new BattleOne();
                        setDroid(scan);
                    }
                    case 2 -> {
                        fileWriter.write("*********** BATTLE 1 & 1 ***********\n\n");
                        battle = new BattleOne();
                        battle.play();
                    }
                    case 3 -> {
                        fileWriter.write("*********** BATTLE TEAM & TEAM ***********\n\n");
                        battle = new BattleTeam();
                        battle.play();
                    }

                    //TODO case 1
                    case 4 -> System.out.println("GAME IS OVER");

                    default -> {
                        System.out.println("Incorrect answer! Try again !!!\n");
                        inCorrect = true;
                    }
                }

            } catch (IOException errorIOE) {

                System.out.println("An error occurred.");
                errorIOE.printStackTrace();

            } catch (Exception error) {

                System.out.println("!!! INVALID TYPE !!! Try again !!!\n");
                inCorrect = true;
            }

        } while (inCorrect);

        fileWriter.close();
    }

    private void setDroid(Scanner scan) {
        System.out.println("\n*** CHOOSE DROID FOR PLAYER 1 ***\n");
        DroidBase droid = battle.chooseDroid(scan);

        System.out.print("\tGIVE NAME FOR PLAYER 1 --->\t");
        droid.setName(scan.nextLine());

        showDroid(droid);
    }

    private void showDroid(DroidBase droid) {
        System.out.println(droid);
        droid.draw();
    }

    public static void createFile(String nameFile) {
        try {
            File fileGame = new File(nameFile);
            if (fileGame.createNewFile()) {
                System.out.println("File created: " + fileGame.getName());
            } else {
                System.out.println("File already exists.");
            }

            fileWriter = new FileWriter(fileGame.getName());

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void printAndWrite(String str) throws IOException {
        printAndWrite(str, Color.ANSI_RESET);
    }

    public static void printAndWrite(String str, String color) throws IOException {
        System.out.print(color + str + Color.ANSI_RESET);
        fileWriter.write(str);
    }
}


