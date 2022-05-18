package com.company;
import java.util.Scanner;

public class Prompter {
    private Scanner scanner;

    public Prompter(Scanner scanner){
        this.scanner = scanner;
    }
    public int promptMenu(){
        System.out.println("To Do List \n ------- \n 1.) Show List \n 2.) Add to list \n 3.) Delete from List \n 4.) Close List");
        System.out.println("------- \n (Please input a number)");
        int num = scanner.nextInt();
        scanner.nextLine();
        return num;
    }
}
