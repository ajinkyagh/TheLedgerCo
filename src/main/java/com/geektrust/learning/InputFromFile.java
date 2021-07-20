package com.geektrust.learning;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class InputFromFile {
    private final String file_name;

    public InputFromFile(String s) {
        this.file_name=s;
    }

    public void inputFromTextFile() throws FileNotFoundException {
        FileReader fr = new FileReader(file_name);
        Scanner inFile = new Scanner(fr);
        SelectOperations selectOperations =new SelectOperations();
        while (inFile.hasNext()) {
            String input = inFile.nextLine();
            selectOperations.bank_operations(input);
        }
    }
}
