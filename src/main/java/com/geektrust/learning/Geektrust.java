package com.geektrust.learning;
import java.io.FileNotFoundException;

public class Geektrust {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = args[0];
        FileReader fileReader = new FileReader(fileName);
        fileReader.inputFromFile();
    }
}
