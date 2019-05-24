/**
 *  Написать программу, которая будет выдавать имя владельца автомобиля по его номеру.
 *  Программа должна быть умной: если у неё в памяти номера нет,
 *  она должна попросить ввести его имя и запомнить.
 *
 *  author studio678 24.05.2019
 */

package com.studio678;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {
    private static String readBlock(boolean key) throws IOException {
        if (key) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input 'LIST' for print all cars in List");
            System.out.println("Input number of car or command: ");
            String str = reader.readLine().trim();
            System.out.println();
            return str;
        } else{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input name of owner: ");
            String str = reader.readLine().trim();
            System.out.println();
            return str;
        }

    }

    public static void main(String[] args) throws IOException {
        // write your code here
        TreeMap<String, String> carBase = new TreeMap<>();
        while (true){
            String command = readBlock(true);
            if (command.equals("LIST")) {
                for (String carNumber : carBase.keySet()) {
                    System.out.println(carNumber + " => " + carBase.get(carNumber));
                }
                break;
            } else if (carBase.containsKey(command)) {
                System.out.println("Owner of car with number " + command + " is: " + carBase.get(command));
                System.out.println();
            } else {
                String thisCarOwner = readBlock(false);
                carBase.put(command, thisCarOwner);
            }
        }
    }
}
