package com.company;
import java.util.Scanner;

public class Prompter {
    private final Scanner scanner;

    public Prompter(Scanner scanner){
        this.scanner = scanner;
    }
    public int promptMenu(){
        System.out.println("To Do List \n ------- \n 1.) Show List \n 2.) Add to list \n 3.) Delete from List \n 4.) Close List \n 5.) Erase Save");
        System.out.println("------- \n (Please input a number)");
        int num = scanner.nextInt();
        scanner.nextLine();
        return num;
    }
    public int promptInt(){
       int num = scanner.nextInt();
        return num;
    }

    public String promptString(){
        String str = scanner.nextLine();
        return str;
    }
}
