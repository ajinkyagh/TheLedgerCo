package com.geektrust.learning;

import java.io.FileNotFoundException;

public class Geektrust {
    public static void main(String[] args) throws FileNotFoundException {

        String filename=args[0];
        InputFromFile input=new InputFromFile(filename);
        if (args.length!=0){
            input.inputFromTextFile();
        }


    }
}
