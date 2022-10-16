package com.PPlabs.lr3.droid;

public class DroidA3 extends DroidBase {

    public DroidA3() { //energy
        setDataAgain();
    }

    @Override
    public String toString() {
        return "\nDroid A3 { " +
                "name = '" + name + '\'' +
                ", health = " + health +
                ", energy = " + energy +
                ", accuracy = " + accuracy +
                ", helpHealth = " + helpHealth +
                " }";
    }

    @Override
    public String draw(){
        return """
                    ·___·
                   |o   o|
                    \\ ' /
                     |¯|\s
                /¯|¯¯¯|¯ ¯|¯¯¯\\¯\\
                | |___|___| |
                | |\\__|__/| |
                |_| |_|_| |_|
                 ¥  |_|_|  ¥
                  /¯|···|¯\\
                  | |   | |
                  |_|   |_|
                  |¯|   |¯|
                  | |   | |
                  |_|   |_|
                  / \\   / \\
                 (>.<) (>.<)""";
    }

    @Override
    public void setDataAgain(){
        setDataAgain(90, 16, 50);
    }
}


