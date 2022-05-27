package com.company;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cli {
        Store listItems;
        Prompter myPrompt = new Prompter(new Scanner(System.in));
    public Cli(Store store) throws Exception {
        listItems = store;
            while(true) {
                try{
                    int num = myPrompt.promptMenu();
                    switch (num) {
                        case 1 -> showList();
                        case 2 -> addList();
                        case 3 -> deleteList();
                        case 4 -> exitList();
                        case 5 -> eraseSave();
                    }
                } catch (Exception e) {
                    System.out.println("Recovered from error: " + e);
                }
            }
        }

        //Shows list of items
        public Map<Integer, String> showList(){
            int count = 1;
            Map<Integer, String> indexItemMap = new HashMap<>();
            String[] items = listItems.showItems();
            for(String listItem : items){
                System.out.println(count + ".) " + listItem);
                indexItemMap.put(count, listItem);
                count++;
            }

            return indexItemMap;
        }
        //Adds to to-do list
        public void addList() throws Exception {
            System.out.println("Add your task: \n (Please type task and press Enter)");
            String task = myPrompt.promptString();
            if(task.length() > 0){
                listItems.addItem(task);
                System.out.println("This is your list:");
                showList();
            } else {
                throw new Exception("Input task field must not be left blank.");
            }
            System.out.println("-------");
        }

        public void deleteList(){
            System.out.println("Which task would you like to delete?\n (Please input the corresponding number)");
            Map<Integer, String> indexItemMap = showList();
            int selectedToRemove = myPrompt.promptInt();
            listItems.removeItem(indexItemMap.get(selectedToRemove));
            showList();
        }

        public void exitList(){
            System.out.println("\n SYSTEM TERMINATING \n \n ----- \n \n ----- \n \n CLOSED ");
            System.exit(0);
        }

        public void eraseSave() throws IOException {
            listItems.removeAll();
        }
    }



