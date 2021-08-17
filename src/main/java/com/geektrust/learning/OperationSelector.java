package com.geektrust.learning;

public class OperationSelector {
    OperationsPerformer operationsPerformer = new OperationsPerformer();
    String[] splitInput;
    String option;

    public void selectOperation(String input) {

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
