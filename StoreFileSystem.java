package com.company;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;

public class StoreFileSystem implements Store{


    public void addItem (String item) {
        try {
            File myObj = new File("saveFile.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            final FileWriter myWriter = new FileWriter("saveFile.txt", true);
            final BufferedWriter buffer = new BufferedWriter(myWriter);
            buffer.write(item + "\n");
            buffer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void removeItem(String item){
        try{
            try {
            ArrayList<String> list = new ArrayList<>();
            File myFile = new File("saveFile.txt");
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                list.add(data);
            }
            list.remove(item);
            scanner.close();
            new FileWriter("saveFile.txt", false).close();
            for(String items : list){
                addItem(items);
            }
        }   catch (FileNotFoundException e) {
             e.printStackTrace();
        }
            } catch (IOException e){
                e.printStackTrace();
        }
    }

    public String[] showItems() {
        ArrayList<String> items = new ArrayList<>();
        try {
            File myFile = new File("saveFile.txt");
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                items.add(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return items.toArray(new String[items.size()]);
    }
    public void removeAll() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        File myFile = new File("saveFile.txt");
        Scanner scanner = new Scanner(myFile);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            list.add(data);
        }
        scanner.close();
        new FileWriter("saveFile.txt", false).close();
    }
}

