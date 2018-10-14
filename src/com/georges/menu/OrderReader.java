package com.georges.menu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class OrderReader {
    public static void main(String[] args) {
        OrderReader orderReader = new OrderReader();
        orderReader.read();
    }

    public void read(){
        Path oderPath = Paths.get("order.csv");

        List<String> lines = null;
        try {
            lines = Files.readAllLines(oderPath);
        } catch (IOException e) {
            System.out.println("Impossible de lire le fichier des commandes");
        }

        if (lines.size() < 2){
            System.out.println("Aucun commande n'a ete effectuer");
            return;
        }

        String[] menus = {"Menu Poulet", "Menu Boeuf", "Menu Vegetarian"};
        String[] side = {" avec des legumes frais", " avec des frites", " avec du riz"};
        String[] sideVegetarian = {" avec du riz", " sans riz"};
        String[] drink = {" et de l'eau fraiche", " et de l'eau gazeuse", " et du soda"};

        for (int i = 1; i < lines.size(); i++ ){
            String[] split = lines.get(i).split(",");
            int nbMenu = Integer.valueOf(split[0]);
            int nbSide = Integer.valueOf(split[1]);
            int nbDrink = Integer.valueOf(split[2]);
            String orderLine = menus[nbMenu - 1];
            if (nbMenu == 3){
                orderLine += sideVegetarian[nbSide - 1];
            }else
                orderLine += side[nbSide - 1];
            if (nbDrink == -1){
                orderLine += " et sans boisson";
            } else
                orderLine += drink[nbDrink - 1];
            System.out.println(orderLine);
        }

    }
}
