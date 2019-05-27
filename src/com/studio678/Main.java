/*
   Написать программу, которая будет выдавать имя владельца автомобиля по его номеру.
   Программа должна быть умной: если у неё в памяти номера нет,
   она должна попросить ввести его имя и запомнить.

   author studio678 24.05.2019
 */

package com.studio678;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static String readBlock(String message) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(message);
        String str = reader.readLine().trim();
        System.out.println();
        return str;
    }

    private static void checkCarBase(TreeMap<String, String> carBase, String command) throws IOException {
        //check what to do with number
        if (carBase.containsKey(command)) {//print if in base
            System.out.println("Owner of car with number " + command + " is: " + carBase.get(command));
            System.out.println();
        } else {//please input owner name and put record to base
            String message = "input car owner name: ";
            String thisCarOwner = readBlock(message);
            carBase.put(command, thisCarOwner);
        }
    }

    private static String getKeyByValue(TreeMap<String,String> map, String value){

        for(Map.Entry<String,String> thisKey: map.entrySet()){
            if(value.equals(thisKey.getValue())){
                return thisKey.getKey();
            }
        }
        return null;
    }


    private final static Pattern CAR_NUMBER_PATTERN = Pattern.compile("([А-Я][0-9]{3}[А-Я]{2}(?:[0-9]+)?)");

    public static void main(String[] args) throws IOException {
        TreeMap<String, String> carBase = new TreeMap<>();
        while (true){
            System.out.println("Input 'LIST' for print all cars in List");
            String message = "input car number, name of owner or LIST";
            String command = readBlock(message);

            if (command.equals("LIST")) {//print all cars in base
                for (String carNumber : carBase.keySet()) {
                    System.out.println(carNumber + " => " + carBase.get(carNumber));
                }
                break;
            } else{//check input
                Matcher matcher = CAR_NUMBER_PATTERN.matcher(command);
                if(matcher.matches()){//if number do
                    checkCarBase(carBase, command);//use car checker
                }else if(carBase.containsValue(command)) {//user input name and its in base
                    String carNumber = getKeyByValue(carBase, command);//
                    if(carNumber == null){//error checker
                        System.out.println("Something gone wrong");
                    }else {//print name of owner and car number if they contains in base
                        System.out.println("Owner of car with number " + carNumber + " is: " + command);
                        System.out.println();
                    }
                }else {//name not in base, please input car number and add to base record
                    String message2 = "Input car number:";
                    String thisCarNumber = readBlock(message2);
                    Matcher thisMatcher = CAR_NUMBER_PATTERN.matcher(thisCarNumber);
                    if(thisMatcher.matches()){//check if input correct
                        carBase.put(thisCarNumber, command);//if so put record
                    }else {//other way please input again
                        while (true) {
                            String thisCarNumber1 = readBlock(message2);
                            thisMatcher = CAR_NUMBER_PATTERN.matcher(thisCarNumber1);
                            if(thisMatcher.matches()){
                                carBase.put(thisCarNumber1, command);
                                break;
                            }
                        }
                    }
                }
            }


        }
    }
}
