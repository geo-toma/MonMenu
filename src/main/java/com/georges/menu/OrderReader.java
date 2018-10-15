package com.georges.menu;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class OrderReader {
    public static void main(String[] args) {
        OrderReader orderReader = new OrderReader();
        orderReader.read();
    }

    private void read(){

        String[] menus = {"Menu Poulet", "Menu Boeuf", "Menu Vegetarian"};
        String[] side = {" avec des legumes frais", " avec des frites", " avec du riz"};
        String[] sideVegetarian = {" avec du riz", " sans riz"};
        String[] drink = {" et de l'eau fraiche", " et de l'eau gazeuse", " et du soda"};


        try {
            Reader in = new FileReader("order.csv");
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);

            for (CSVRecord record : records){
                int nbMenu = Integer.valueOf(record.get("Menu"));
                int nbSide = Integer.valueOf(record.get("Accompagnement"));
                int nbDrink = Integer.valueOf(record.get("Boisson"));
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
        } catch (FileNotFoundException e) {
            System.out.println("Impossible de trouver le fichier");
        } catch (IOException e) {
            System.out.println("Erreur durant la lecture du fichier");
        }
    }
}
