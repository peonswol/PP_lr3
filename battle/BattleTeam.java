package com.PPlabs.PP_lr3.battle;

import com.PPlabs.PP_lr3.Color;
import com.PPlabs.PP_lr3.droid.DroidBase;
import com.PPlabs.PP_lr3.play.Arena;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static com.PPlabs.PP_lr3.play.PlayDroidsBattleGame.printAndWrite;

public class BattleTeam extends BattleOne {

    private final List<DroidBase> firstTeam = new ArrayList<>();
    private final List<DroidBase> secondTeam = new ArrayList<>();
    private String nameFirstTeam;
    private String nameSecondTeam;
    private int countWinFirstTeam;
    private int countWinSecondTeam;


    @Override
    public void play() throws InterruptedException, IOException {

        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        creatingTeams(scan);

        dealWithArena(scan);

        showTwoTeam();

        playRoundsTeams(rand);

        identifyWinners();
    }

    @Override
    protected void dealWithArena(Scanner scan) throws IOException {
        gameArena = Arena.chooseArena(scan);
        printAndWrite("\nARENA - " + gameArena.name() + "\n");
        changingByArenaAllPlayers();
    }

    private void creatingTeams(Scanner scan) {

        // TODO CHECK DO-WHILE
        while (true) {
            setTypeDroidTeams(scan);

            if (isEqualCountMembers())
                break;

            System.out.println(Color.RED + "‼️ERROR!!! UNEQUAL COUNT OF PARTICIPANTS!!! TRY AGAIN :\n" + Color.ANSI_RESET);
        }

        System.out.print("\nSET NAME FOR THE FIRST TEAM : ");
        nameFirstTeam = scan.nextLine();
        System.out.println("\n\tSET NAME FOR PARTICIPANTS OF FIRST TEAM :");
        setNameDroidTeam(scan, firstTeam);

        System.out.print("\nSET NAME FOR THE SECOND TEAM : ");
        nameSecondTeam = scan.nextLine();
        System.out.println("\nSET NAME FOR PARTICIPANTS OF SECOND TEAM :");
        setNameDroidTeam(scan, secondTeam);
    }

    private void setTypeDroidTeams(Scanner scan) {
        System.out.println("\nCHOOSE DROIDS FOR FIRST TEAM :\n\t(A1 A2 A3 A4 ...)\n");
        chooseDroidForTeam(scan, firstTeam);

        System.out.println("\nCHOOSE DROIDS FOR SECOND TEAM :\n");
        chooseDroidForTeam(scan, secondTeam);
    }

    private void chooseDroidForTeam(Scanner scan, List<DroidBase> team) {

        DroidBase.listTypeDroid();

        String choosing = scan.nextLine();

        String[] typeDroids = choosing.split(" ");

        for (String droid : typeDroids) {
            team.add(switchChoosingDroid(droid));
        }
    }

    private boolean isEqualCountMembers() {
        return firstTeam.size() == secondTeam.size();
    }

    private void setNameDroidTeam(Scanner scan, List<DroidBase> team) {

        for (DroidBase droid : team) {
            System.out.print("\n\tGIVE NAME FOR :" + Color.GREEN + droid + Color.ANSI_RESET + "\n--->\t");
            droid.setName(scan.nextLine());
        }
    }

    private void changingByArenaAllPlayers() {

        for (DroidBase droid : firstTeam) {
            gameArena.changeByArena(droid);
        }

        for (DroidBase droid : secondTeam) {
            gameArena.changeByArena(droid);
        }
    }

    private void showTwoTeam() throws InterruptedException, IOException {

        printAndWrite("\n\n***** FIRST TEAM *****\n");

        listOfDroids(firstTeam);

        printAndWrite("\n\n***** SECOND TEAM *****\n");

        listOfDroids(secondTeam);
    }

    private void listOfDroids(List<DroidBase> team) throws InterruptedException, IOException {
        for (DroidBase droid : team) {
            Thread.sleep(2000);
            stringAndDrawDroid(droid);
        }
    }

    private void playRoundsTeams(Random rand) throws InterruptedException, IOException {

        DroidBase currentWinner;

        int tour = 1;

        firstPlayer = firstTeam.get(0);
        secondPlayer = secondTeam.get(0);

        while (true) {

            sayTourBattle(tour);

            currentWinner = playRound(rand);
            sayWinner(currentWinner);
            countingWins(currentWinner);
            removingLoser(currentWinner);

            if (firstTeam.size() <= 0 || secondTeam.size() <= 0) break;

            swapMembersTeamToBattle(currentWinner);
            tour++;
        }
    }

    private void sayTourBattle(int tour) throws IOException, InterruptedException {

        Thread.sleep(2000);
        printAndWrite("\n⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️" +
                "\n\t\t\tTOUR " + tour +
                "\n⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️\n");
    }

    @Override
    protected DroidBase playRound(Random rand) throws InterruptedException, IOException {

        int round = 1;
        boolean attackerIsFirst;
        attackerIsFirst = chooseAttackerRandom(rand);

        while (defender.isAlive() && attacker.haveEnergy()) {

            sayInfoStart(round++, rand);

            kickDefender(rand);

            attacker.setEnergy(attacker.getEnergy() - 1);


            //TODO додати метод
            if (!defender.isAlive()) {

                if (defender.isGotHelpHealth()) {
                    return attacker;
                }

                if (!searchHelpHealth(attackerIsFirst, rand)) {
                    return attacker;
                } else defender.setGotHelpHealth(true);
            }

            attackerIsFirst = swapPlayers(attackerIsFirst);
        }

        return defender;
    }

    private boolean searchHelpHealth(boolean attackerIsFirst, Random rand) throws IOException {

        if (attackerIsFirst) {
            return searchHelpHealthInTeam(secondTeam, rand);
        } else {
            return searchHelpHealthInTeam(firstTeam, rand);
        }
    }

    private boolean searchHelpHealthInTeam(List<DroidBase> team, Random rand) throws IOException {

        boolean toHelp = rand.nextBoolean();

        for (DroidBase helpHealthDroid : team) {

            if (helpHealthDroid.canHelp() && helpHealthDroid != defender && toHelp) {
                defender.setHealth(defender.getHealth() + helpHealthDroid.getHelpHealth());

                printAndWrite("\n!!!!!!!!!! 🎉 REGENERATION 🎉 !!!!!!!!!!\n" +
                        "DEFENDER - " + defender.getName() + " RECEIVED EXTRA " + defender.getHealth() + " HEALTH FROM DROID - " + helpHealthDroid.getName() +
                        "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n", Color.GREEN);

                helpHealthDroid.setHelpHealth(0);

                return true;
            }
        }

        System.out.println(Color.RED + "\n !!! failed regeneration !!! \n" + Color.ANSI_RESET);
        return false;
    }

    private void countingWins(DroidBase currentWinner) {
        if (firstTeam.contains(currentWinner)) {
            countWinFirstTeam++;
        } else countWinSecondTeam++;
    }

    private void removingLoser(DroidBase currentWinner) throws IOException {

        String removedPlayer;

        if (firstTeam.contains(currentWinner)) {

            removedPlayer = secondPlayer.getName();
            secondTeam.remove(secondPlayer);
        } else {
            removedPlayer = firstPlayer.getName();
            firstTeam.remove(firstPlayer);
        }

        printAndWrite("\n‼️PLAYER - " + removedPlayer + " REMOVED FROM GAME️‼️\n");
    }

    private void swapMembersTeamToBattle(DroidBase currentWinner) {

        if (firstTeam.contains(currentWinner)) {
            secondPlayer = secondTeam.get(0);
            firstPlayer.setDataAgain();
        } else {
            firstPlayer = firstTeam.get(0);
            secondPlayer.setDataAgain();
        }
    }

    private void identifyWinners() throws IOException {
        if (countWinFirstTeam == countWinSecondTeam) {
            sayWinnerTeam("NOBODY ⚔️");
        } else sayWinnerTeam(countWinFirstTeam > countWinSecondTeam ? nameFirstTeam : nameSecondTeam);
    }

    private void sayWinnerTeam(String nameWinner) throws IOException {

        printAndWrite("\n\n⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️⚔️\n" +
                "\nNUMBER OF WINS OF THE FIRST TEAM - " + countWinFirstTeam +
                "\nNUMBER OF WINS OF THE SECOND TEAM - " + countWinSecondTeam +
                "\n\n*** 🎊 WINNER 🎊 ----> " + nameWinner +
                "\n\n(○ﾟε^○)⌒☆;:*:;☆Congratulation☆;:*:;☆ (ﾉ^^)ﾉ" +
                "\n\n----------------------------------------\n\n", Color.CYAN);
    }
}


