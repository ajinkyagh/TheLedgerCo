package com.geektrust.learning;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private final String fileName;

    public FileReader(String fileName) {
        this.fileName = fileName;
    }
    public void inputFromFile() throws FileNotFoundException {
        OperationSelector operationSelector = new OperationSelector();
        java.io.FileReader fileReader = new java.io.FileReader(fileName);
        Scanner inFile = new Scanner(fileReader);

        while (inFile.hasNext()) {
            String input = inFile.nextLine();
            operationSelector.selectOperation(input);
        }
    }
}
