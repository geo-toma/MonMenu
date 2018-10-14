package com.georges.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Order {
    Scanner sc = new Scanner(System.in);
    String orderSummary = "";

    public int askSomething(String category, String[] responses) {
        for (int i = 1; i <= responses.length; i++)
            System.out.println(i + " - " + responses[i - 1]);
        System.out.println("Que souhaitez-vous comme " + category + "?");
        int nbResponse = - 1;
        boolean responseIsGood;
        String choice = "";
        do {
            try {
                nbResponse = sc.nextInt();
                responseIsGood = (nbResponse >= 1 && nbResponse <= responses.length);
            } catch (InputMismatchException e) {
                sc.next();
                responseIsGood = false;
            }
            if (responseIsGood) {
                choice = "Vous avez choisi comme " + category + " : " + responses[nbResponse - 1];
                orderSummary = orderSummary + choice + "%n";
                System.out.println(choice);
            } else {
                boolean isVowel = "aeiouy".contains(Character.toString(category.charAt(0)));
                if (isVowel)
                    System.out.println("Vous n'avez pas choisi d'" + category + " parmi les choix proposés");
                else
                    System.out.println("Vous n'avez pas choisi de " + category + " parmi les choix proposés");
                System.out.println("Rechoisissez votre " + category);
            }
        } while (!responseIsGood);
        return nbResponse;
    }

    public int askMenu(){
        System.out.println("Choix du menu");
        String[] menu = {"Poulet", "Boeuf", "Vegetarian"};
        int nbMenu = askSomething("Menu", menu);
        return nbMenu;
    }

    public void askSide(boolean allSideEnable){
        System.out.println(" Choix de l'accompagnement");
        String[] side;
        if (allSideEnable){
            side = new String[] {"Legumes frais", "Frites", "Riz"};
        }else {
            side = new String[] {"Riz", "Pas de riz"};
        }
        this.askSomething("Accompagnement", side);
    }

    public void askDrink(){
        System.out.println(" Choix de la boisson");
        String[] drink = {"Eau fraiche", "Eau gazeuse", "Soda"};
        this.askSomething("Boisson", drink);
    }


    public void runMenu(){
        int nb = askMenu();
        switch (nb){
            case 1:
                askSide(true);
                askDrink();
                break;
            case 2:
                askSide(true);
                break;
            case 3:
                askSide(false);
                askDrink();
                break;
        }
    }

    public void runmenus(){
        System.out.println("Combien de menu voulez - vous commander ?");
        int nbQuantity = - 2;
        boolean goodValueOfNbQuanity;
        orderSummary = "Resume de votre commande : %n";
        do {
            try {
                nbQuantity = sc.nextInt();
                goodValueOfNbQuanity = (nbQuantity >= 1);
            } catch (InputMismatchException e) {
                sc.next();
                goodValueOfNbQuanity = false;
            }
            if (goodValueOfNbQuanity) {
                for (int counter = 0; counter < nbQuantity; counter++) {
                    orderSummary += "%nMenu " + (counter + 1) + " : %n";
                    this.runMenu();
                }
            } else
                System.out.println(String.format("Vous n'avez pas entrez une bonne valeur. %n Re-entrez le nombre de Menu :"));
        } while (!goodValueOfNbQuanity);
        System.out.println("");
        System.out.println(String.format("%n" + orderSummary));
    }
}
