package com.PPlabs.lr3.droid;

public class DroidA4 extends DroidBase {

    public DroidA4() {  //health
        setDataAgain();
    }

    @Override
    public String toString() {
        return "\nDroid A4 { " +
                "name = '" + name + '\'' +
                ", health = " + health +
                ", energy = " + energy +
                ", accuracy = " + accuracy +
                ", helpHealth = " + helpHealth +
                " }";
    }

    @Override
    public String draw (){
        return """
                      _n____n__
                     /         \\---||--&lt;
                    /___________\\
                    _|____|____|_
                    _|____|____|_
                     |    |    |
                    --------------
                    | || || || ||\\
                    | || || || || \\++++++++------&lt;
                    ===============
                    |   |  |  |   |
                   (| O | O| O| O |)
                   |   |   |   |   |
                  (| O | O | O | O |)
                   |   |   |   |    |
                 (| O |  O | O  | O |)
                  |   |    |    |    |
                 (| O |  O |  O |  O |)
                 ======================
                """;
    }

    @Override
    public void setDataAgain(){
        setDataAgain(95, 14, 55);
    }
}