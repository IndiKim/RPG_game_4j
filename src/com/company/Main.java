package com.company;

import java.util.Random;

public class Main {
    public static int[] heroesHealth = {170, 180, 160, 140};
    public static int[] getHeroesDamage = {20, 15, 25, 10};
    public static String[] heroesAttackType = {"Phisical","Magical","Kinetic","Medical"};

    public static int bossHealth = 700;
    public static int getBossDamage = 20;
    public static String bossAttackType = "";

    public static int roundNumber = 0;
    
    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();

        }
    }

    public static void medicalTreatment(){
        int randomHeal = new Random().nextInt(50);
        int random = new Random().nextInt(heroesAttackType.length);
        if(heroesAttackType[random] != heroesAttackType[3] &&
                heroesHealth[random] > 0 && heroesHealth[random] <= 100 &&
                heroesHealth[3] > 0){
            heroesHealth[random] = heroesHealth[random] + randomHeal;
            System.out.println(heroesAttackType[3] + "Вылечил: " + heroesAttackType[random] + "на целых: " + randomHeal);
        }
    }

    public static void printStatistics() {
        System.out.println("**********" + roundNumber + " ROUND **********");
        System.out.println("Boss health: " + bossHealth + " [" + getBossDamage + " ]");


        for (int i = 0; i < heroesHealth.length; i++)
            System.out.println(heroesAttackType[i]+" health : "+ heroesHealth[i] + " [ " + getHeroesDamage[i] + " ] ");
    }


    public static void round() {
        chooseBossAttackType();
        roundNumber++;
        bossHits();
        medicalTreatment();

        heroesHit();
        printStatistics();
    }

    public static void bossHits() {
        for (int i = 0; i < getHeroesDamage.length; i++) {
            heroesHealth[i] = heroesHealth[i] - getBossDamage;
        }

    }

    public static void heroesHit() {
        for (int i = 0; i < getHeroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossAttackType == heroesAttackType[i]){
                    Random r = new Random();
                    int coef = r.nextInt(2) + 2; // 0 1 2 3 4 5 6 7
                    int heroCriticalDamage = getHeroesDamage[i] * coef;
                    if (bossHealth < heroCriticalDamage) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroCriticalDamage;
                    }
                    System.out.println("Critical damage: "+heroCriticalDamage +
                            "Hero attack type: " + heroesAttackType[i]);
                    System.out.println();

                } else {
                    if (bossHealth < getHeroesDamage[i]){
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - getHeroesDamage[i];
                    }
                }
                bossHealth = bossHealth - getHeroesDamage[i];

            }

        }
    }

    public static Boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes win!");
            return true;
        }

        if(heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0){
            System.out.println ("Boss win!");
            return true;
        }

        return false;
    }

    public static void chooseBossAttackType(){
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0 1 2
        bossAttackType = heroesAttackType[randomIndex];
    }



    }





