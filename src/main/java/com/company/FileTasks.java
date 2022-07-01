package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileTasks {

    public static ArrayList<String> extractFile(String filePath) throws IOException {
        Stream<Path> paths = Files.walk(Paths.get(filePath));
        ArrayList<String> list = new ArrayList<>();
        paths.forEach(y -> list.add(String.valueOf(y)));
        return list;
    };

    public static String readFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        String list = "";
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            list = list + myReader.nextLine() + "\n";
        }
        return list;
    };
}
