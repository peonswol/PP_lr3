package com.PPlabs.lr3.battle;
import com.PPlabs.lr3.Color;
import com.PPlabs.lr3.droid.*;
import com.PPlabs.lr3.play.Arena;
import com.PPlabs.lr3.weapon.Weapon;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import static com.PPlabs.lr3.play.PlayDroidsBattleGame.printAndWrite;


public class BattleOne {

    protected DroidBase firstPlayer;
    protected DroidBase secondPlayer;
    protected DroidBase attacker;
    protected DroidBase defender;
    protected Arena gameArena;
    private Weapon currentWeapon;

    public void play() throws InterruptedException, IOException {
        DroidBase winner;

        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        setDroidsAndThemName(scan);

        dealWithArena(scan);

        showTwoPlayers();

        winner = playRound(rand);

        sayWinner(winner);
    }

    protected void dealWithArena(Scanner scan) throws IOException {
        gameArena = Arena.chooseArena(scan);
        printAndWrite("\nARENA - " + gameArena.name() + "\n");
        changingByArenaBothPlayers();
    }

    private void setDroidsAndThemName(Scanner scan) {

        System.out.println("\n*** CHOOSE DROID FOR PLAYER 1 ***\n");
        firstPlayer = chooseDroid(scan);
        System.out.print("\tGIVE NAME FOR PLAYER 1 --->\t");
        firstPlayer.setName(scan.nextLine());

        System.out.println("\n*** CHOOSE DROID FOR PLAYER 2 ***\n");
        secondPlayer = chooseDroid(scan);
        System.out.print("\tGIVE NAME FOR PLAYER 2 --->\t");
        secondPlayer.setName(scan.nextLine());
    }

    public DroidBase chooseDroid(Scanner scan) {

        DroidBase.listTypeDroid();

        return switchChoosingDroid(scan.nextLine());
    }

    protected DroidBase switchChoosingDroid(String choosing) {
        switch (choosing) {
            case "A1":
                return new DroidA1();
            case "A2":
                return new DroidA2();
            case "A3":
                return new DroidA3();
            case "A4":
                return new DroidA4();
            default:
                System.out.println("\nIncorrect input. Droid is selected by default! ---> A1");
                return new DroidA1();
        }
    }

    private void changingByArenaBothPlayers() {
        gameArena.changeByArena(firstPlayer);
        gameArena.changeByArena(secondPlayer);
    }

    private void showTwoPlayers() throws InterruptedException, IOException {

        Thread.sleep(2000);
        printAndWrite("\n***** FIRST PLAYER *****\n");
        stringAndDrawDroid(firstPlayer);

        Thread.sleep(2000);
        printAndWrite("\n***** SECOND PLAYER *****\n");
        stringAndDrawDroid(secondPlayer);

    }

    protected void stringAndDrawDroid(DroidBase droid) throws IOException {
        printAndWrite(droid.toString() + "\n", Color.BLUE);
        printAndWrite(droid.draw() + "\n", Color.YELLOW);
    }

    protected DroidBase playRound(Random rand) throws InterruptedException, IOException {

        int round = 1;
        boolean attackerIsFirst;
        attackerIsFirst = chooseAttackerRandom(rand);

        while (defender.isAlive() && attacker.haveEnergy()) {

            sayInfoStart(round++, rand);

            kickDefender(rand);

            attacker.setEnergy(attacker.getEnergy() - 1);

            if (!defender.isAlive())
                return attacker;

            attackerIsFirst = swapPlayers(attackerIsFirst);
        }
        return defender;
    }

    protected boolean chooseAttackerRandom(Random rand) {
        boolean attackerIsFirst = rand.nextBoolean();

        if (attackerIsFirst) {
            attacker = firstPlayer;
            defender = secondPlayer;
        } else {
            attacker = secondPlayer;
            defender = firstPlayer;
        }

        return attackerIsFirst;
    }

    protected void sayInfoStart(int round, Random rand) throws InterruptedException, IOException {

        Thread.sleep(1500);

        sayRound(round);
        sayDefenderAndAttacker();
        sayInfoDroid();
        identifyAndSayWeapon(rand);
    }

    private void sayRound(int round) throws IOException {

        printAndWrite("\n\n************************\n" +
                "\t\t✨ ROUND " + round +
                "\n*************************\n", Color.PURPLE);
    }

    private void sayDefenderAndAttacker() throws IOException {
        printAndWrite("ATTACKER ---> " + attacker.getName() + "\nDEFENDER ---> " + defender.getName() + "\n");
    }

    private void sayInfoDroid() throws IOException {
        printAndWrite("----------------------------------------");
        printAndWrite(attacker.toString() + "\n", Color.RED);
        printAndWrite(defender.toString() + "\n", Color.WHITE);
    }


    private void identifyAndSayWeapon(Random rand) throws IOException {

        currentWeapon = Weapon.randomWeapon(rand);

        printAndWrite("\n\t🗡️ WEAPON ---> " + currentWeapon.name() + "\t: damage = " + currentWeapon.getDamage() +
                "\n----------------------------------------\n");

    }

    protected void kickDefender(Random rand) throws InterruptedException, IOException {


        attacker.setDamage(rand.nextInt((int) (currentWeapon.getDamage() * attacker.getAccuracy())));
        defender.setHealth(defender.getHealth() - attacker.getDamage());
        Thread.sleep(1500);
        printAndWrite("Defender : " + defender.getName() + " got hit 🗡️ with " + attacker.getDamage() + " damage\n");
        Thread.sleep(500);
        printAndWrite("----------------------------------------\n");
        Thread.sleep(1500);
    }

    protected boolean swapPlayers(boolean attackerIsFirst) {

        if (attacker.getDamage() > 25) {
            return attackerIsFirst;
        } else if (attackerIsFirst) {
            attacker = secondPlayer;
            defender = firstPlayer;
            return false;
        } else {
            attacker = firstPlayer;
            defender = secondPlayer;
            return true;
        }
    }

    protected void sayWinner(DroidBase winner) throws IOException {

        printAndWrite("\n\n----------------------------------------\n\n", Color.CYAN);

        printAndWrite(firstPlayer.toString() + "\n", Color.PURPLE);
        printAndWrite(secondPlayer.toString() + "\n", Color.PURPLE);

        printAndWrite("\n*** 🎊 WINNER 🎊 ----> " + winner.getName() + "\n" +
                "\n----------------------------------------\n\n\n", Color.CYAN);
    }
}
