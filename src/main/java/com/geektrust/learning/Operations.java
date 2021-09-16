package com.geektrust.learning;

public class Operations {
    OperationsPerformer operationsPerformer = new OperationsPerformer();
    String[] splitInput;
    String option;

    public void select(String input) {

        splitInput = input.split("\\s");
        option=splitInput[0];
        switch (option) {
            case "LOAN":
                operationsPerformer.takeLoan(splitInput);
                break;
            case "BALANCE":
                operationsPerformer.showBalance(splitInput);
                break;
            case "PAYMENT":
                operationsPerformer.makeLumpSumPayment(splitInput);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }
    }
}
