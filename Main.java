package com.company;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> listItems = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    //Main
    public static void main(String[] args) throws Exception {
        Prompter myPrompt = new Prompter(new Scanner(System.in));
        while(true) {
            try{
                int num = myPrompt.promptMenu();
                switch (num) {
                    case 1 -> showList();
                    case 2 -> addList();
                    case 3 -> deleteList();
                    case 4 -> exitList();
                }
            } catch (Exception e) {
                System.out.println("Recovered from error: " + e);
            }
        }
    }

    //Shows list of items
    public static void showList(){
        int count = 1;
        for(String listItem : listItems){
            System.out.println(count + ".) " + listItem);
            count++;
        }
    }
    //Adds to to-do list
    public static void addList() throws Exception {
        System.out.println("Add your task: \n (Please type task and press Enter)");
        String task = scanner.nextLine();
        if(task.length() > 0){
            listItems.add(task);
            System.out.println("This is your list:");
            showList();
        } else {
            throw new Exception("Input task field must not be left blank.");
        }
        System.out.println("-------");
    }

    public static void deleteList(){
        System.out.println("Which task would you like to delete?\n (Please input the corresponding number)");
        showList();
        listItems.remove(scanner.nextInt() - 1);
        showList();
    }

    public static void exitList(){
        System.out.println("\n SYSTEM TERMINATING \n \n ----- \n \n ----- \n \n CLOSED ");
        System.exit(0);
    }
}