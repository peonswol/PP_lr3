package com.PPlabs.PP_lr3.droid;

public class DroidA1 extends DroidBase {

    public DroidA1() {   //helpHealth
        setDataAgain();
    }

    @Override
    public String toString() {
        return "\nDroid A1 { " +
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
                 _______             _______
                |@|@|@|@|           |@|@|@|@|
                |@|@|@|@|   _____   |@|@|@|@|
                |@|@|@|@| /\\_T_T_/\\ |@|@|@|@|
                |@|@|@|@||/\\ T T /\\||@|@|@|@|
                 ~/T~~T~||~\\/~T~\\/~||~T~~T\\~
                  \\|__|_| \\(-(O)-)/ |_|__|/
                  _| _|    \\\\8_8//    |_ |_
                |(@)]   /~~[_____]~~\\   [(@)|
                  ~    (  |       |  )    ~
                      [~` ]       [ '~]
                      |~~|         |~~|
                      |  |         |  |
                     _<\\/>_       _<\\/>_
                    /_====_\\     /_====_\\
                """;
    }

    @Override
    public void setDataAgain(){
        setDataAgain(80, 15, 60);
    }
}

