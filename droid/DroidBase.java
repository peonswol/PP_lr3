package com.PPlabs.lr3.droid;


import com.PPlabs.lr3.Color;

public abstract class DroidBase {

    protected String name;
    protected int health;
    protected int damage;
    protected int energy;
    protected int helpHealth;
    protected double accuracy = 1;

    public boolean isGotHelpHealth() {
        return gotHelpHealth;
    }

    public void setGotHelpHealth(boolean gotHelpHealth) {
        this.gotHelpHealth = gotHelpHealth;
    }

    protected boolean gotHelpHealth = false;

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public boolean haveEnergy() {
        return energy > 0;
    }

    public boolean canHelp() {
        return helpHealth > 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public void setHealth(int health) {
        this.health = Math.max(health, 0);
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setHelpHealth(int helpHealth) {
        this.helpHealth = helpHealth;
    }

    public int getDamage() {
        return damage;
    }

    public int getEnergy() {
        return energy;
    }

    public int getHelpHealth() {
        return helpHealth;
    }

    public double getAccuracy() {
        return accuracy;
    }

    @Override
    public abstract String toString();

    public abstract String draw();

    public abstract void setDataAgain();

    public void setDataAgain(int health, int energy, int helpHealth) {
        this.health = health;
        this.energy = energy;
        this.helpHealth = helpHealth;
    }

    public static void listTypeDroid() {
        System.out.println(Color.PURPLE + """
                [ HEALTH : ENERGY : HELP HEALTH ] :
                [A1] A1 ---- [ 80  : 15 : 60 ]
                [A2] A2 ---- [ 100 : 13 : 50 ]
                [A3] A3 ---- [ 90  : 16 : 50 ]
                [A4] A4 ---- [ 95  : 14 : 55 ]
                --->   \s""" + Color.ANSI_RESET);
    }
}
