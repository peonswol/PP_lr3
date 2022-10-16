package com.PPlabs.PP_lr3.droid;

public class DroidA2 extends DroidBase {

    public DroidA2() {  //health
        setDataAgain();
    }

    @Override
    public String toString() {
        return "\nDroid A2 { " +
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
                      ___       ___
                     [___] /~\\ [___]
                     |ooo|.\\_/.|ooo|
                     |888||   ||888|
                    /|888||   ||888|\\
                  /_,|###||___||###|._\\
                 /~\\  ~~~ /[_]\\ ~~~  /~\\
                (O_O) /~~[_____]~~\\ (O_O)
                     (  |       |  )
                    [~` ]       [ '~]
                    |~~|         |~~|
                    |  |         |  |
                   _<\\/>_       _<\\/>_
                  /_====_\\     /_====_\\
                """;
    }

    @Override
    public void setDataAgain(){
        setDataAgain(100, 13, 50);
    }
}
