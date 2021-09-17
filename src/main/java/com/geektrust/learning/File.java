package com.geektrust.learning;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class File {
    private final String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }
    public void read() throws FileNotFoundException {
        Operations operations = new Operations();
        java.io.FileReader fileReader = new java.io.FileReader(fileName);
        Scanner inFile = new Scanner(fileReader);

        while (inFile.hasNext()) {
            String input = inFile.nextLine();
            operations.select(input);
        }
    }
}
