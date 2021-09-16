package com.geektrust.learning;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class File {
    private final String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }
    public void input() throws FileNotFoundException {
        OperationSelector operationSelector = new OperationSelector();
        java.io.FileReader fileReader = new java.io.FileReader(fileName);
        Scanner inFile = new Scanner(fileReader);

        while (inFile.hasNext()) {
            String input = inFile.nextLine();
            operationSelector.selectOperation(input);
        }
    }
}
