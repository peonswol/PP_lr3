package com.PPlabs.lr3.weapon;

import java.util.Random;

public enum Weapon {
    REVOLVER(17), MACHINE_GUN(21), AUTOMATON(24), PNEUMATIC(29);
    private final int damage;

    Weapon(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public static Weapon randomWeapon(Random rand) {

        int randWeapon = 1 + rand.nextInt(4);
        switch (randWeapon) {
            case 2:
                return MACHINE_GUN;
            case 3:
                return AUTOMATON;
            case 4:
                return PNEUMATIC;
            case 1:
            default:
                return REVOLVER;
        }
    }
}
